package es.ctic.parrot.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import es.ctic.parrot.DocumentaryProject;
import es.ctic.parrot.DocumentaryProjectFactory;
import es.ctic.parrot.ParrotAppServ;
import es.ctic.parrot.generators.HtmlOutputGenerator;
import es.ctic.parrot.generators.OutputGenerator.Profile;
import es.ctic.parrot.reader.InputStreamInput;
import es.ctic.parrot.reader.ReaderException;
import es.ctic.parrot.reader.StringInput;
import es.ctic.parrot.reader.URLInput;
import es.ctic.parrot.transformers.TransformerException;
import es.ctic.parrot.utils.ErrorBuffer;


public class ServletParrot extends HttpServlet {

	private static final String AUTODETECT_MIMETYPE = "default";
	private static final String ERRORS_GENERAL = "errorsGeneral";
	private static final String DOCUMENT_URI = "documentUri";
	private static final String DOCUMENT_TEXT = "documentText";
	private static final String MIMETYPE = "mimetype";
	private static final String MIMETYPE_TEXT = "mimetypeText";

	private static final org.apache.log4j.Logger logger = Logger.getLogger(ServletParrot.class);

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		logRequest(req);

		String showForm = req.getParameter("showform");
		if ( showForm != null && showForm.equalsIgnoreCase("true") && req.getMethod().equalsIgnoreCase("POST") == false){
			forwardToForm(req, res);
			return ; // Finish the method
		}

		ErrorBuffer errors = new ErrorBuffer();
		req.setAttribute(ERRORS_GENERAL, errors.getErrorsNotAssociated());

		try {

			final DocumentaryProject dp = getDocumentaryProject(req);

			if (dp.getInputs().isEmpty()) {
				forwardToForm(req, res);
			} else {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				HtmlOutputGenerator outputGenerator = new HtmlOutputGenerator.Builder().out(out).build();
				Profile profile = getProfile(req);
				getParrotAppServ().createDocumentation(dp, outputGenerator, profile);
				res.setContentType("text/html");
				res.getOutputStream().write(out.toByteArray());
			}
		} catch (MalformedURLException e) {
			logger.error("While generating documentation", e);
			errors.addError("Malformed URI: " + e.getMessage());
			forwardToForm(req, res);
		} catch (ReaderException e) {
			logger.error("While generating documentation", e);
			errors.addError("Unable to read input document: " + e.getMessage());
			forwardToForm(req, res);
		} catch (TransformerException e){
			logger.error("While processing documentation", e);
			errors.addError("Error while processing documentation: " + e.getMessage());
			forwardToForm(req, res);
		} catch (IOException e) {
			logger.error("While generating documentation", e);
			errors.addError("I/O Error: " + e.getMessage());
			forwardToForm(req, res);
		} catch (FileUploadException e) {
			logger.error("While uploading file", e);
			res.sendError(400);
		} catch (IllegalArgumentException e) {
			logger.error("Illegal request: " + req, e);
			res.sendError(400);
		} catch (RuntimeException e) {
			logger.error("Unexpected error while generating documentation", e);
			res.sendError(500);
		}
	}

	private Profile getProfile(HttpServletRequest req) {
		Profile profile = Profile.TECHNICAL; // default profile
		String profile_param = req.getParameter("profile");
		if (profile_param != null){

			profile_param = profile_param.toLowerCase();			

			if ("business".equals(profile_param)){
				profile = Profile.BUSINESS;
			} 

			if ("technical".equals(profile_param)){
				profile = Profile.TECHNICAL;
			}
		}
		return profile;
	}

	private void logRequest(HttpServletRequest req) {

		logger.info("requestURL="+req.getRequestURL());

		Map<String, String []> parameterMap = req.getParameterMap();
		for (Map.Entry<String,String []> entry : parameterMap.entrySet())
		{
			StringBuffer sb = new StringBuffer("parameter="+entry.getKey() + " -");

			for (String valuesArray : entry.getValue()){
				sb.append(" value="+valuesArray);
			}
			logger.info(sb);
		}
		logger.info("referer="+req.getParameter("referer"));		

	}


	private void addRefererInput(DocumentaryProject dp, HttpServletRequest req) throws MalformedURLException, IOException, ReaderException {
		String referer = req.getParameter("referer");
		if ( referer != null && referer.equalsIgnoreCase("true")){
			dp.addInput(new URLInput(new URL(req.getHeader("Referer"))));
		}

	}

	private void forwardToForm(HttpServletRequest req, HttpServletResponse res)	throws ServletException, IOException {
		// forward to form
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/parrot.jsp");
		dispatcher.forward(req,res);
	}

	private boolean checkURI(String uri) {
		if (uri == null || ("".equals(uri)) || ("http://".equals(uri))){
			return false;
		} else {
			return true;
		}
	}

	private ParrotAppServ getParrotAppServ() {
		return new ParrotAppServ();
	}

	private void addFileUploadInput(DocumentaryProject dp, HttpServletRequest req) throws FileUploadException, IOException {
		if (ServletFileUpload.isMultipartContent(req)) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List /* FileItem */ items = upload.parseRequest(req);
			Iterator iter = items.iterator();
			List<InputStream> inputStreams = new LinkedList<InputStream>();
			List<String> providedContentTypes = new LinkedList<String>();
			List<String> mimetypeFiles = new LinkedList<String>();

			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();

				if (!item.isFormField()) {
					if (item.getName().length() != 0) {
						logger.debug("Found non-empty file upload field with name=" + item.getName());
						inputStreams.add(item.getInputStream());
						providedContentTypes.add(item.getContentType());
					}
				} else { // regular field
					if (item.getFieldName().equals("mimetypeFile")) {
						logger.debug("Found mimetypeFile=" + item.getString());
						mimetypeFiles.add(item.getString());
					}
				}
			}

			Iterator<String> providedContentTypesIterator = providedContentTypes.iterator();
			Iterator<String> mimetypeFilesIterator = mimetypeFiles.iterator();
			for (InputStream is : inputStreams) {
				final String BASE = null;
				String providedContentType = providedContentTypesIterator.next();
				String mimetypeFile = mimetypeFilesIterator.next();
				logger.debug("Adding input with provided content type=" + providedContentType + " and mimetypeFile=" + mimetypeFile);
				if (mimetypeFile == null || mimetypeFile.equals(AUTODETECT_MIMETYPE)) {
					dp.addInput(new InputStreamInput(is, req.getCharacterEncoding(), providedContentType, BASE));				
				} else {
					dp.addInput(new InputStreamInput(is, req.getCharacterEncoding(), mimetypeFile, BASE));
				}
			}
		}
	}

	private void addUriInputs(DocumentaryProject dp, HttpServletRequest req) throws MalformedURLException, IOException, ReaderException {
		String[] uriInputs = req.getParameterValues(DOCUMENT_URI);
		String[] uriInputMimetypes = req.getParameterValues(MIMETYPE);

		if (uriInputs == null) {
			uriInputs = new String[0];
		}

		if (uriInputMimetypes == null) {
			uriInputMimetypes = new String[0];
		}

		for( int i=0 ; i<uriInputs.length ; i++) {
			String uriInput = uriInputs[i];
			if (checkURI(uriInput)) {
				if (uriInput.contains(":") == false) {
					PrefixCCClient prefixCCClient = new PrefixCCClient();
					String prefixCCResponse = prefixCCClient.resolvePrefix(uriInput);
					if (prefixCCResponse != null) {
						uriInput = prefixCCResponse;
					}
				}
				logger.info("Trying to add valid input: " + uriInput);
				if (uriInputMimetypes.length <= i || uriInputMimetypes[i].equals(AUTODETECT_MIMETYPE)) { // allow content negotiation
					dp.addInput(new URLInput(new URL(uriInput)));
				} else {
					dp.addInput(new URLInput(new URL(uriInput), uriInputMimetypes[i]));
				}
			}
		}
	}

	private void addDirectInputs(DocumentaryProject dp, HttpServletRequest req) {

		String[] directInputTexts = req.getParameterValues(DOCUMENT_TEXT);
		String[] directInputMimetypes = req.getParameterValues(MIMETYPE_TEXT);

		if (directInputTexts == null) {
			directInputTexts = new String[0];
		}

		if (directInputMimetypes == null) {
			directInputMimetypes = new String[0];
		}

		for( int i=0 ; i<directInputTexts.length ; i++) {
			String directInputText = directInputTexts[i];

			if (directInputText != null) {
				directInputText = directInputText.trim();
			}
			if (directInputText != null && directInputText.length() > 0) {
				String directInputMimetype = directInputMimetypes[i];
				if (directInputMimetype == null) {
					throw new IllegalArgumentException("Mimetype specification is required for direct input");
				}
				dp.addInput(new StringInput(directInputText, directInputMimetype)); 
			}
		}
	}
	
	private DocumentaryProject getDocumentaryProject(HttpServletRequest req) throws FileUploadException, IOException, ReaderException{
		
		DocumentaryProject dp = createDocumentaryProject(req);

		addFileUploadInput(dp, req);

		addDirectInputs(dp, req);

		addRefererInput(dp, req);

		if (dp.getInputs().isEmpty()){
			addUriInputs(dp, req);
		}
		
		return dp;
	}

	private DocumentaryProject createDocumentaryProject(HttpServletRequest req) throws MalformedURLException, IOException, ReaderException {
		
		Locale locale = getLocale(req);
		
		String customizeCssUrl = getCustomizeCssUrl(req);
		
		String generatedReportUrl = getGeneratedReportUrl(req);
		
		DocumentaryProject dp = DocumentaryProjectFactory.createDocumentaryProject(locale, customizeCssUrl, generatedReportUrl);
		
		// Read a previous report
		String previousReportUrl = req.getParameter("reportURL");
		if (checkURI(previousReportUrl)){
			dp = DocumentaryProjectFactory.createDocumentaryProjectFromExistingReport(locale, customizeCssUrl, generatedReportUrl, previousReportUrl);
		}

		return dp;
	}

	private String getGeneratedReportUrl(HttpServletRequest req) {
		String queryString = req.getQueryString();
		if (queryString != null){
			return req.getRequestURL() + "?" + queryString;
		} else {
			return req.getRequestURL() + "?";
		}
	}

	private String getCustomizeCssUrl(HttpServletRequest req) {
		return req.getParameter("customizeCssUrl");
	}

	private Locale getLocale(HttpServletRequest req) {
		Locale locale = Locale.ENGLISH; // default Locale
		String language = req.getParameter("language");
		if ( language != null && language.trim().length() != 0){
			locale = new Locale(language);
		}
		return locale;
	}

}
