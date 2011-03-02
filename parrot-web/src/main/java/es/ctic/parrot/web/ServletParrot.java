package es.ctic.parrot.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
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
import es.ctic.parrot.appserv.ParrotAppServ;
import es.ctic.parrot.generators.HtmlOutputGenerator;
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
		ErrorBuffer errors = new ErrorBuffer();
		List<String> advices = new ArrayList<String>();
		req.setAttribute(ERRORS_GENERAL, errors.getErrorsNotAssociated());
		req.setAttribute(ADVICES, advices);
		
		Locale locale = Locale.ENGLISH; // default Locale
		
		String language = req.getParameter("language");
		if ( language != null && language.trim().length() != 0) 
			locale = new Locale(language);

		try {
			DocumentaryProject dp = new DocumentaryProject(locale);
			
			addFileUploadInput(dp, req);
			
			addDirectInput(dp, req);
			
			if (dp.getInputs().isEmpty()){
				addUriInputs(dp, req);
			}
			
			if (dp.getInputs().isEmpty()) {
				forwardToForm(req, res);
			} else {
			    InputStream template = getTemplateInputStream();
			    ByteArrayOutputStream out = new ByteArrayOutputStream();
                HtmlOutputGenerator outputGenerator = new HtmlOutputGenerator(out, template);
                ParrotAppServ parrotAppServ = getParrotAppServ();
			    parrotAppServ.createDocumentation(dp, outputGenerator);
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
		}		
		catch (IOException e) {
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
			InputStream is = null;
			String providedContentType = null;
			String mimetypeFile = null;
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();

				if (!item.isFormField()) {
					is = item.getInputStream();
					providedContentType = item.getContentType();
				} else {
					if (item.getFieldName().equals("mimetypeFile")) {
						mimetypeFile = item.getString();
					}
				}
			}
			
			if (is != null) {
				final String BASE = null;
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

	private void addDirectInput(DocumentaryProject dp, HttpServletRequest req) {
		String directInputText = req.getParameter(DOCUMENT_TEXT);
		if (directInputText != null) {
			directInputText = directInputText.trim();
		}
		if (directInputText != null && directInputText.length() > 0) {
		    String directInputMimetype = req.getParameter(MIMETYPE_TEXT);
		    if (directInputMimetype == null) {
		        throw new IllegalArgumentException("Mimetype specification is required for direct input");
		    }
		    dp.addInput(new StringInput(directInputText, directInputMimetype)); 
		}
	}

}
