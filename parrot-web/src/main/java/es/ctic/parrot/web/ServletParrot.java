package es.ctic.parrot.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

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
import es.ctic.parrot.ParrotAppServ;
import es.ctic.parrot.generators.HtmlOutputGenerator;
import es.ctic.parrot.generators.OutputGenerator.Profile;
import es.ctic.parrot.reader.Input;
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
	private static final String ADVICES = "advices";

	private static final org.apache.log4j.Logger logger = Logger.getLogger(ServletParrot.class);

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		String showForm = req.getParameter("showform");
		if ( showForm != null && showForm.equalsIgnoreCase("true") && req.getMethod().equalsIgnoreCase("POST") == false){
			forwardToForm(req, res);
		} else {
			ErrorBuffer errors = new ErrorBuffer();
			List<String> advices = new ArrayList<String>();
			req.setAttribute(ERRORS_GENERAL, errors.getErrorsNotAssociated());
			req.setAttribute(ADVICES, advices);
			
			Locale locale = Locale.ENGLISH; // default Locale
			
			String language = req.getParameter("language");
			if ( language != null && language.trim().length() != 0){
				locale = new Locale(language);
			}
	
			String profile_param = req.getParameter("profile");
			
			Profile profile = Profile.UNDEFINED;
			
			if (profile_param != null){
			
				profile_param = profile_param.toLowerCase();			
			
				if (profile_param.equals("business")){
					profile = Profile.BUSINESS;
				} 
				
				if (profile_param.equals("technical")){
					profile = Profile.TECHNICAL;
				}
			}
			
			String reportURL = req.getParameter("reportURL");
			
			try {
				DocumentaryProject dp = new DocumentaryProject(locale);
	
				String queryString = req.getQueryString();
				if (queryString != null){
					dp.setReportURL(req.getRequestURL() + "?" + queryString);
				} else {
					dp.setReportURL(req.getRequestURL() + "?");
				}
	
				addFileUploadInput(dp, req);
				
				addDirectInputs(dp, req);
	
				addRefererInput(dp, req);
				
				if (dp.getInputs().isEmpty()){
					addUriInputs(dp, req);
				}
	
				// Read a previous report
				if (reportURL != null){
	                ParrotAppServ parrotAppServ = getParrotAppServ();
	                Collection<Input> inputs = parrotAppServ.getInputsFromExistingReport(reportURL);
	                dp.setPrologueURL(parrotAppServ.getPrologueURLFromExistingReport(reportURL));
	                dp.setAppendixURL(parrotAppServ.getAppendixURLFromExistingReport(reportURL));
	               	dp.addAllInput(inputs);
				}
	
				if (dp.getInputs().isEmpty()) {
					forwardToForm(req, res);
				} else {
				    InputStream template = getTemplateInputStream();
				    ByteArrayOutputStream out = new ByteArrayOutputStream();
	                HtmlOutputGenerator outputGenerator = new HtmlOutputGenerator(out, template);
	                ParrotAppServ parrotAppServ = getParrotAppServ();
				    parrotAppServ.createDocumentation(dp, outputGenerator, profile);
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
	}


	private void addRefererInput(DocumentaryProject dp, HttpServletRequest req) throws MalformedURLException, IOException, ReaderException {
		String referer = req.getParameter("referer");
		if ( referer != null && referer.equalsIgnoreCase("true")){
			dp.addInput(new URLInput(new URL(req.getHeader("Referer"))));
		}
		
	}

	private void forwardToForm(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {
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
	
    private InputStream getTemplateInputStream() {
        InputStream template = Thread.currentThread().getContextClassLoader().getResourceAsStream("html/template.vm");
		if (template == null) {
		    throw new RuntimeException("Failed to load resource");
		}
        return template;
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

}
