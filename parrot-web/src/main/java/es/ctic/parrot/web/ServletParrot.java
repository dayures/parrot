package es.ctic.parrot.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
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
import es.ctic.parrot.reader.ReaderException;
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
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ErrorBuffer errors = new ErrorBuffer();
		List<String> advices = new ArrayList<String>();
		req.setAttribute(ERRORS_GENERAL, errors.getErrorsNotAssociated());
		req.setAttribute(ADVICES, advices);

		try {
			DocumentaryProject dp = createDocumentaryProject(res.getOutputStream());
			addUriInputs(dp, req);
			addDirectInput(dp, req);
			if (dp.getInputs().isEmpty()) {
				forwardToForm(req, res);
			}

			// Obtenemos un objeto Print Writer para enviar respuesta
			res.setContentType("text/html");
			try {
				generate(dp);
			} catch (Exception e) {
				logger.error("While generating documentation", e);
				errors.addError("Error parsing the document.");
//				if (((Input) (dp.getInputs().toArray()[0])).getMimeType().equals("application/rif+xml")){
//					advices.add("Check the document markup using a <a href=\"http://idi.fundacionctic.org/rifle/\" >RIF validator</a>");			    			    		
//				}else {
//					advices.add("Check the document markup using a <a href=\"http://www.w3.org/RDF/Validator/\" >RDF validator</a>");
//				}
				forwardToForm(req, res);
			}
		} catch (MalformedURLException e) {
			errors.addError("Malformed URI: " + e.getMessage());
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

	public void generate(DocumentaryProject dp) throws IOException, ReaderException {
		ParrotAppServ app = new ParrotAppServ();
		DocumentReader ontologyWrapper = new JenaOWLReader();
		DocumentReader ruleWrapper = new RifXmlReader(ontologyWrapper);
		DocumentReader rifPSWrapper = new RiflePSReader(ontologyWrapper, ruleWrapper);
		app.setOntologyWrapper(ontologyWrapper);
		app.setRuleWrapper(ruleWrapper);
		app.setRifPSWrapper(rifPSWrapper);
		app.createDocumentation(dp);
	}
	
	private DocumentaryProject createDocumentaryProject(OutputStream out) {
		InputStream template = Thread.currentThread().getContextClassLoader().getResourceAsStream("html/template.vm");
		return new DocumentaryProject(template, out, LANG);		
	}
	
	private void addUriInputs(DocumentaryProject dp, HttpServletRequest req) throws MalformedURLException, IOException {
		String[] uriInputs = req.getParameterValues(DOCUMENT_URI);
		String[] uriInputMimetypes = req.getParameterValues(MIMETYPE);

		if (uriInputs == null) {
			uriInputs = new String[0];
		}

		for( int i=0 ; i<uriInputs.length ; i++) {
			String uriInput = uriInputs[i];
			if (checkURI(uriInput)){
				logger.info("Trying to add valid input: " + uriInput);
				if (i<uriInputMimetypes.length || uriInputMimetypes[i].equals("default")) { // allow content negotiation
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
		String directInputMimetype = req.getParameter(MIMETYPE_TEXT);		
		dp.addInput(new StringInput(directInputText, directInputMimetype)); 
	}

}
