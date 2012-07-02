package es.ctic.parrot;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ResourceFactory;

import es.ctic.parrot.reader.Input;
import es.ctic.parrot.reader.ReaderException;
import es.ctic.parrot.reader.URLInput;


/**
 * A factory for creating {@link  es.ctic.parrot.DocumentaryProject Documentary Projects}.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class DocumentaryProjectFactory {

	/**
	 * Creates a documentary project.
	 * @param locale the locale.
	 * @return a Documentary Project.
	 */
	static public DocumentaryProject createDocumentaryProject(Locale locale){
		return new DocumentaryProjectImpl.Builder().locale(locale).build();
	} 
	
	/**
	 * Creates a documentary project.
	 * @param locale the locale.
	 * @param customizeCssUrl the customize CSS URL.
	 * @return a Documentary Project.
	 */
	static public DocumentaryProject createDocumentaryProject(Locale locale, String customizeCssUrl){
		return new DocumentaryProjectImpl.Builder().locale(locale).customizeCssUrl(customizeCssUrl).build();
	} 
	
	/**
	 * Creates a documentary project.
	 * @param locale the locale.
	 * @param customizeCssUrl the customize CSS URL.
	 * @param generatedReportUrl the generated report URL.
	 * @return a Documentary Project.
	 */
	static public DocumentaryProject createDocumentaryProject(Locale locale, String customizeCssUrl, String generatedReportUrl){
		return new DocumentaryProjectImpl.Builder().locale(locale).customizeCssUrl(customizeCssUrl).generatedReportUrl(generatedReportUrl).build();
	} 

	/**
	 * Creates a documentary project from an existing report.
	 * @param locale the locale.
	 * @param previousReportUrl the previous report URL.
	 * @param customizeCssUrl the customize CSS URL.
	 * @param generatedReportUrl the generated report URL.
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ReaderException
	 * @return a Documentary Project.
	 */
	static public DocumentaryProject createDocumentaryProjectFromExistingReport(Locale locale, String customizeCssUrl, String generatedReportUrl, String previousReportUrl) throws MalformedURLException, IOException, ReaderException {

		Model model = ModelFactory.createDefaultModel();
	    
		// Init java-rdfa in jena
		try {
			Class.forName("net.rootdev.javardfa.jena.RDFaReader");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		Input input = new URLInput(new URL(previousReportUrl), "application/xhtml+xml");
		String base = input.getBase();
		try {
			model.read(input.openReader(), base == null ? "http://example.org/base#" : base, "XHTML");
		} catch (RuntimeException e) {
			// encapsulate RuntimeException generated by jena when a parser exception is arised
			throw new ReaderException(e);
		}

		String prologueUrl = getPrologueURLFromExistingReport(model);
		String appendixUrl = getAppendixURLFromExistingReport(model);

		DocumentaryProject dp = new DocumentaryProjectImpl.Builder().locale(locale).generatedReportUrl(generatedReportUrl).customizeCssUrl(customizeCssUrl).prologueURL(prologueUrl).appendixURL(appendixUrl).build();
		
        dp.addAllInput(getInputsFromExistingReport(model));
        
        return dp;
	}
	
	/**
	 * Returns a collection of inputs obtained from an existing report (using RDFa).
	 * @param model the model.
	 * @return a collection of inputs.
	 * @throws ClassNotFoundException 
	 * @throws ReaderException 
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	private static Collection<Input> getInputsFromExistingReport(Model model) throws MalformedURLException, IOException, ReaderException{
	    Collection<Input> inputs = new HashSet<Input>();
		
		NodeIterator iterator = model.listObjectsOfProperty(ResourceFactory.createProperty("http://vocab.ctic.es/parrot#readAs"));
		for (RDFNode node : iterator.toList()){
			inputs.add(new URLInput(new URL(node.asResource().getURI())));
		}

		iterator = model.listObjectsOfProperty(ResourceFactory.createProperty("http://vocab.ctic.es/parrot#readAsRDFOWLOntology"));
		for (RDFNode node : iterator.toList()){
			inputs.add(new URLInput(new URL(node.asResource().getURI()),"application/owl+xml"));
		}

		iterator = model.listObjectsOfProperty(ResourceFactory.createProperty("http://vocab.ctic.es/parrot#readAsN3OWLOntology"));
		for (RDFNode node : iterator.toList()){
			inputs.add(new URLInput(new URL(node.asResource().getURI()),"text/n3"));
		}

		iterator = model.listObjectsOfProperty(ResourceFactory.createProperty("http://vocab.ctic.es/parrot#readAsXHTMLRDFaOntology"));
		for (RDFNode node : iterator.toList()){
			inputs.add(new URLInput(new URL(node.asResource().getURI()),"application/xhtml+xml"));
		}

		iterator = model.listObjectsOfProperty(ResourceFactory.createProperty("http://vocab.ctic.es/parrot#readAsHTMLRDFaOntology"));
		for (RDFNode node : iterator.toList()){
			inputs.add(new URLInput(new URL(node.asResource().getURI()),"text/html"));
		}

		iterator = model.listObjectsOfProperty(ResourceFactory.createProperty("http://vocab.ctic.es/parrot#readAsRIFXMLDocument"));
		for (RDFNode node : iterator.toList()){
			inputs.add(new URLInput(new URL(node.asResource().getURI()),"application/rif+xml"));
		}
		
		iterator = model.listObjectsOfProperty(ResourceFactory.createProperty("http://vocab.ctic.es/parrot#readAsRIFPSDocument"));
		for (RDFNode node : iterator.toList()){
			inputs.add(new URLInput(new URL(node.asResource().getURI()),"text/x-rif-ps"));
		}
		
		return inputs;
	}

	/**
	 * Returns the URL where is the prologue obtained from an existing report (using RDFa).
	 * @param model the model.
	 * @return the URL where is the prologue or <code>null</code> if there is no prologue URL.
	 * @throws ReaderException 
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	private static String getPrologueURLFromExistingReport(Model model) throws MalformedURLException, IOException, ReaderException {

		NodeIterator iterator = model.listObjectsOfProperty(ResourceFactory.createProperty("http://vocab.ctic.es/parrot#hasPrologue"));
		
		for (RDFNode node : iterator.toList()){
			return node.asResource().getURI();
		}
		
		return null;
	}

	/**
	 * Returns the URL where is the appendix obtained from an existing report (using RDFa).
	 * @param model the model.
	 * @return the URL where is the appendix or <code>null</code> if there is no appendix URL.
	 * @throws ReaderException 
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	private static String getAppendixURLFromExistingReport(Model model) throws MalformedURLException, IOException, ReaderException {

		NodeIterator iterator = model.listObjectsOfProperty(ResourceFactory.createProperty("http://vocab.ctic.es/parrot#hasAppendix"));
		
		for (RDFNode node : iterator.toList()){
			return node.asResource().getURI();
		}
		
		return null;
	}
	
}
