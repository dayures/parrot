package es.ctic.parrot.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import es.ctic.parrot.DocumentaryProject;
import es.ctic.parrot.appserv.ParrotAppServ;
import es.ctic.parrot.reader.DocumentReader;
import es.ctic.parrot.reader.StringInput;
import es.ctic.parrot.reader.URLInput;
import es.ctic.parrot.reader.Input;
import es.ctic.parrot.reader.jena.JenaOWLReader;
import es.ctic.parrot.reader.naiverifxml.RifXmlReader;
import es.ctic.parrot.reader.rifleps.RiflePSReader;
import es.ctic.parrot.utils.ErrorBuffer;


public class ServletParrot extends HttpServlet {
	
	private static final String ERRORS_URI = "errorsUri";
	private static final String ERRORS_GENERAL = "errorsGeneral";
	private static final String DOCUMENT_URI = "documentUri";
	private static final String DOCUMENT_TEXT = "documentText";
	private static final String MIMETYPE = "mimetype";
	private static final String MIMETYPE_TEXT = "mimetypeText";
	private static final String ADVICES = "advices";
	private static final String LANG = "EN"; // FIXME. Now it is fixed to be in English
	
	private static final org.apache.log4j.Logger logger = Logger.getLogger(ServletParrot.class);

	private static final long serialVersionUID = 1L;


	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doGetUri(req, res);
	}
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doGetText(req, res);
	}
	

	private void doGetUri(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		ErrorBuffer errors = new ErrorBuffer();

		String[] uris = req.getParameterValues(DOCUMENT_URI);
		String[] mimetypes = req.getParameterValues(MIMETYPE);
		
		if (uris == null){
			uris = new String[0];
		}
		
    	DocumentaryProject dp = createDocumentaryProject(uris, mimetypes, errors);
		
       	req.setAttribute(ERRORS_URI, errors.getErrorsUri());
       	req.setAttribute(ERRORS_GENERAL, errors.getErrorsNotAssociated());
		
		if (dp.getInputs().size() > 0 && errors.isEmpty()) {
			// Obtenemos un objeto Print Writer para enviar respuesta
			res.setContentType("text/html");
			PrintWriter pw = res.getWriter();
        	try {
        		pw.print(this.generate(dp));
        	} catch (Exception e){
				logger.error(e.getClass().getCanonicalName() + " " +e.getMessage());
		    	errors.addError("Error parsing the input URIs.");
				forwardToForm(req, res);			
        	} finally{
        		pw.close();	
        	}
		} else {
			forwardToForm(req, res);
		}
	}

	private void doGetText(HttpServletRequest req, HttpServletResponse res)
	throws IOException, ServletException {
		
		ErrorBuffer errors = new ErrorBuffer();
		List<String> advices = new ArrayList<String>();
		
		String text = req.getParameter(DOCUMENT_TEXT);
		String mimetype = req.getParameter(MIMETYPE_TEXT);
		
		if (text == null || (text.trim().length() == 0) ){
			forwardToForm(req, res);			
		}

		DocumentaryProject dp = createDocumentaryProject(text, mimetype, errors);
		
		req.setAttribute(ERRORS_GENERAL, errors.getErrorsNotAssociated());
       	req.setAttribute(ADVICES, advices);
		
		if (dp.getInputs().size() > 0 && errors.isEmpty()) {
			// Obtenemos un objeto Print Writer para enviar respuesta
			res.setContentType("text/html");
			PrintWriter pw = res.getWriter();
			try {
				pw.print(this.generate(dp));
			} catch (Exception e){
				logger.error(e.getClass().getCanonicalName() + " " +e.getMessage());
		    	errors.addError("Error parsing the document.");
		    	if (((Input) (dp.getInputs().toArray()[0])).getMimeType().equals("application/rif+xml")){
		    		advices.add("Check the document markup using a <a href=\"http://idi.fundacionctic.org/rifle/\" >RIF validator</a>");			    			    		
		    	}else {
		    		advices.add("Check the document markup using a <a href=\"http://www.w3.org/RDF/Validator/\" >RDF validator</a>");
		    	}
		    	forwardToForm(req, res);
			} finally{
				pw.close();	
			}
		} else {
			forwardToForm(req, res);
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
	
	public String generate(DocumentaryProject dp) throws IOException{
        ParrotAppServ app = new ParrotAppServ();
        DocumentReader ontologyWrapper = new JenaOWLReader();
        DocumentReader ruleWrapper = new RifXmlReader(ontologyWrapper);
        DocumentReader rifPSWrapper = new RiflePSReader(ontologyWrapper, ruleWrapper);
        app.setOntologyWrapper(ontologyWrapper);
        app.setRuleWrapper(ruleWrapper);
        app.setRifPSWrapper(rifPSWrapper);
        app.createDocumentation(dp);
    	return dp.getOutputStream().toString();
	}

	private DocumentaryProject createDocumentaryProject(String[] uris, String[] mimetypes, ErrorBuffer errors){
		InputStream template = Thread.currentThread().getContextClassLoader().getResourceAsStream("html/template.vm");
        OutputStream out=new ByteArrayOutputStream();
        DocumentaryProject dp = new DocumentaryProject(template, out, LANG);

    	logger.info("createDocumentaryProject");

        for(int i = 0; i<uris.length; i++){
	        if (this.checkURI(uris[i])){
	        	logger.info("Trying to add valid input:" + uris[i]);
	        	try {
	        		if (mimetypes[i].equals("default")){ // allow content negotiation
	        			dp.addInput(new URLInput(new URL(uris[i])));
	        		} else {
	        			dp.addInput(new URLInput(new URL(uris[i]), mimetypes[i]));
	        		}

	        	} catch (java.net.MalformedURLException e){
		        	logger.error("Probably " + uris[i] + " is a malformed URL");
		        	errors.addError(uris[i], "Probably the URI is malformed");
		        }catch (Exception e){
		        	logger.error("Error adding the URI: " + uris[i]);
		        	errors.addError(uris[i], e.getMessage());
		        }
	        }
        }
		return dp;
	}
	
	private DocumentaryProject createDocumentaryProject(String text, String mimetype, ErrorBuffer errors){
		InputStream template = Thread.currentThread().getContextClassLoader().getResourceAsStream("html/template.vm");
        OutputStream out=new ByteArrayOutputStream();
        DocumentaryProject dp = new DocumentaryProject(template, out, LANG);

    	logger.info("createDocumentaryProject");

    	try {
    		dp.addInput(new StringInput(text,mimetype)); 
        }catch (Exception e){
        	logger.error("Error adding the text: " + text);
        	errors.addError(e.getMessage());
        }
		return dp;
	}
}
