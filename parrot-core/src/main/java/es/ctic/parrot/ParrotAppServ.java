package es.ctic.parrot;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ResourceFactory;

import es.ctic.parrot.de.DocumentableObject;
import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.docmodel.Document;
import es.ctic.parrot.generators.OutputGenerator;
import es.ctic.parrot.generators.OutputGenerator.Profile;
import es.ctic.parrot.reader.DocumentReader;
import es.ctic.parrot.reader.Input;
import es.ctic.parrot.reader.ReaderException;
import es.ctic.parrot.reader.URLInput;
import es.ctic.parrot.reader.jena.JenaOWLReader;
import es.ctic.parrot.reader.rifle.RiflePSReader;
import es.ctic.parrot.reader.rifle.RifleXmlReader;
import es.ctic.parrot.transformers.DetailsVisitor;
import es.ctic.parrot.transformers.DocumentableObjectVisitor;
import es.ctic.parrot.transformers.GlossaryVisitor;
import es.ctic.parrot.transformers.OntologyInternalReferenceResolver;
import es.ctic.parrot.transformers.RuleToOntologyReferenceResolver;
import es.ctic.parrot.transformers.TransformerException;

/**
 * Main entry point for Parrot users.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class ParrotAppServ {
    
    private static final Logger logger = Logger.getLogger(ParrotAppServ.class);

    final private JenaOWLReader ontologyReader;
    final private DocumentReader ruleXmlReader;
    final private DocumentReader rifPSReader;
    
    /**
     * Constructs a parrot service.
     * Nowadays, the supported mimetypes are:
	 * <ul>
	 * 	<li><code>application/rdf+xml</code></li>
	 * 	<li><code>application/xml</code></li>
	 *  <li><code>application/owl+xml</code></li>
	 *  <li><code>application/xhtml+xml</code></li>
	 *  <li><code>text/html</code></li>
	 *  <li><code>text/n3</code></li>
	 *  <li><code>text/rdf+n3</code></li>
  	 * 	<li><code>text/x-rif-ps</code></li>
	 * 	<li><code>application/rif+xml</code></li>
	 * </ul>
	 * 
     */
    public ParrotAppServ() {
    	ontologyReader = new JenaOWLReader();
    	ruleXmlReader = new RifleXmlReader(getOntologyReader());
    	rifPSReader = new RiflePSReader(getOntologyReader(), getRuleXmlReader());
    }

	/**
	 * Generates the report for a documentary project.
	 * 
	 * @param dp the documentary Project to be fulfilled.
	 * @param outputGenerator the output generator.
	 * @param profile the end user profile.
	 * @throws IOException if a failed or interrupted I/O operation occurs, usually during the initial reading of inputs.
	 * @throws ReaderException if a input is malformed or not valid (usually, markup issues).
	 */
	public void createDocumentation(DocumentaryProject dp, OutputGenerator outputGenerator, Profile profile) throws IOException, ReaderException, TransformerException {
		DocumentableObjectRegister register = new DocumentableObjectRegister();
		readAndRegisterDocumentableObjects(dp.getInputs(), register);
		resolveInternalReferences(register);
		resolveCrossReferences(register);
		Document document = transformToDocument(register.getDocumentableObjects(), dp.getLocale(), dp.getInputs(), dp.getPrologueURL(), dp.getAppendixURL(), dp.getReportURL(), getLanguagesInModel(), dp.getCustomizeCssUrl());
		outputGenerator.generateOutput(document, profile);
	}

	private Set<String> getLanguagesInModel() {
		Set<String> languages = new HashSet<String>();
		for(RDFNode rdfNode: getOntologyReader().getOntModel().listObjects().toSet()){
			if (rdfNode.isLiteral()){
				if (rdfNode.asLiteral().getLanguage().length() != 0){
					languages.add(rdfNode.asLiteral().getLanguage().split("-")[0]);
				}
			}
		}
		return languages;
	}
	
	/**
	 * Read a collection of inputs and register its documentable elements (ontologies, classes, properties, individuals, rulesets and rules). 
	 * @param inputs a collection of inputs to be read and register its documentable elements.
	 * @param register a register to add the elements to document.
	 * @throws IOException if a failed or interrupted I/O operation occurs, usually during the initial reading of inputs.
	 * @throws ReaderException if a input is malformed or not valid (usually, markup issues).
	 */
    private void readAndRegisterDocumentableObjects(Collection<Input> inputs, DocumentableObjectRegister register) throws IOException, ReaderException {
		for (Input input : inputs) {
		    DocumentReader reader = getDocumentReaderForInput(input);
		    reader.readDocumentableObjects(input, register);
		} 
    }	

    /**
     * Resolves internal references as, for instance, references between a subclass and their superclass. Other references are: property domain, property range, superproperties, subproperties, superclasses and subclasses.
     * @param register a register to find elements to connect.
     * @throws TransformerException if a failed transformation operation occurs.
     */
    private void resolveInternalReferences(DocumentableObjectRegister register) throws TransformerException {
		DocumentableObjectVisitor ontologyInternalReferenceResolver = new OntologyInternalReferenceResolver(register);
		ontologyInternalReferenceResolver.visit(register);
	}

    /**
     * Resolves references between ontologies and rules.
     * @param register a register to find elements to connect.
     * @throws TransformerException if a failed transformation operation occurs.
     */
	private void resolveCrossReferences(DocumentableObjectRegister register) throws TransformerException {
        DocumentableObjectVisitor ruleToOntologyReferenceResolver = new RuleToOntologyReferenceResolver();
        ruleToOntologyReferenceResolver.visit(register);
    }

	/**
	 * Returns the most suitable reader for a specified input.
	 * @param input a input.
	 * @return the document reader most suitable for a input.
	 * @throws ReaderException if there is no reader to that MIME type. 
	 */
    private DocumentReader getDocumentReaderForInput(Input input) throws ReaderException{
        if (isOntologyReadable(input.getMimeType())) {
            return getOntologyReader();
        } else if (isRuleXmlReadable(input.getMimeType())){
            return getRuleXmlReader();
        } else if (isRifPSReadable(input.getMimeType())){
            return getRifPSReader();
        } else {
            throw new ReaderException("Unable to open mimetype " + input.getMimeType());
        }
    }

    /**
     * Generates an internal document. This document could be pass later to an output generator in order to generate the final documentation. 
     * @param documentableObjects a collection of documentable elements.
     * @param locale a locale used for customize the output.
     * @param inputs a collection of inputs where the documentable objects come from.
     * @param prologueURL the URL where is the prologue.
     * @param appendixURL the URL where is the appendix.
     * @param reportURL the URL report.
     * @param languages a collection of the languages.
     * @return a document to be presented by an output generator.
     * @throws TransformerException if a failed transformation operation occurs.
     */
    private Document transformToDocument(Collection<DocumentableObject> documentableObjects, Locale locale, Collection<Input> inputs, String prologueURL, String appendixURL, String reportURL, Collection<String> languages, String customizeCssUrl) throws TransformerException {
        Document document = new Document(locale);
		document.setTitle("Parrot"); // FIXME
		document.setInputs(inputs);
		document.setPrologueURL(prologueURL);
		document.setAppendixURL(appendixURL);
		document.setReportURL(reportURL);
		document.setLanguages(languages);
		document.setCustomizeCssUrl(customizeCssUrl);
        DetailsVisitor detailVisitor = new DetailsVisitor(document, locale);
        GlossaryVisitor glossaryVisitor = new GlossaryVisitor(document, locale);
		for (DocumentableObject documentableObject : documentableObjects) {
			documentableObject.accept(detailVisitor);
			documentableObject.accept(glossaryVisitor);
		}
        return document;
    }


	/**
	 * Returns a reader for ontologies (OWl, RDF, RDFa).
	 * @return the ontology reader.
	 */
	private JenaOWLReader getOntologyReader() {
		return ontologyReader;
	}


	/**
	 * Returns the RIF XML Reader.
	 * @return the RIF XML Reader.
	 */
	private DocumentReader getRuleXmlReader() {
		return ruleXmlReader;
	}

	/**
	 * Returns the RIF Presentation Syntax (PS) Reader.
	 * @return the RIF Presentation Syntax (PS) Reader.
	 */
	private DocumentReader getRifPSReader() {
		return rifPSReader;
	}

	/**
	 * Returns true if the MIME type could be read by an Ontology Reader.
	 * Nowadays, the supported MIME types are:
	 * <ul>
	 * 	<li>application/rdf+xml</li>
	 * 	<li>application/xml</li>
	 *  <li>application/owl+xml</li>
	 *  <li>application/xhtml+xml</li>
	 *  <li>text/html</li>
	 *  <li>text/n3</li>
	 *  <li>text/rdf+n3</li>
	 * </ul>
	 * @param mimetype the MIME type.
	 * @return true if the MIME type is supported.
	 */
	private boolean isOntologyReadable(String mimetype){
		if ("application/rdf+xml".equals(mimetype)
    	 || "application/xml".equals(mimetype)
    	 || "application/owl+xml".equals(mimetype)
    	 || "application/xhtml+xml".equals(mimetype)
    	 || "text/html".equals(mimetype)
     	 || "text/n3".equals(mimetype)
    	 || "text/rdf+n3".equals(mimetype)){
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns true if the MIME type could be read by an RIF XML Reader.
	 * Nowadays, the supported MIME type is:
	 * <ul>
	 * 	<li>application/rif+xml</li>
	 * </ul 
	 * @param mimetype the MIME type.
	 * @return true if the mimetype is supported.
	 */
	private boolean isRuleXmlReadable(String mimetype){
		 return "application/rif+xml".equals(mimetype) ? true : false;
	}
	
	/**
	 * Returns true if the MIME type could be read by an RIF PS Reader.
	 * Nowadays, the supported MIME type is:
	 * <ul>
	 * 	<li>text/x-rif-ps</li>
	 * </ul>
	 * @param mimetype the MIME type.
	 * @return true if the MIME type is supported.
	 */
	private boolean isRifPSReadable(String mimetype){
		 return "text/x-rif-ps".equals(mimetype) ? true : false;
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
	private Collection<Input> getInputsFromExistingReport(Model model) throws MalformedURLException, IOException, ReaderException{
	    Collection<Input> inputs = new HashSet<Input>();
		
		NodeIterator iterator = model.listObjectsOfProperty(ResourceFactory.createProperty("http://vocab.ctic.es/parrot#documents"));
		for (RDFNode node : iterator.toList()){
			inputs.add(new URLInput(new URL(node.asResource().getURI())));
		}

		iterator = model.listObjectsOfProperty(ResourceFactory.createProperty("http://vocab.ctic.es/parrot#documentsRDFOWLOntology"));
		for (RDFNode node : iterator.toList()){
			inputs.add(new URLInput(new URL(node.asResource().getURI()),"application/owl+xml"));
		}

		iterator = model.listObjectsOfProperty(ResourceFactory.createProperty("http://vocab.ctic.es/parrot#documentsN3OWLOntology"));
		for (RDFNode node : iterator.toList()){
			inputs.add(new URLInput(new URL(node.asResource().getURI()),"text/n3"));
		}

		iterator = model.listObjectsOfProperty(ResourceFactory.createProperty("http://vocab.ctic.es/parrot#documentsHTMLRDFaOntology"));
		for (RDFNode node : iterator.toList()){
			inputs.add(new URLInput(new URL(node.asResource().getURI()),"application/xhtml+xml"));
		}

		iterator = model.listObjectsOfProperty(ResourceFactory.createProperty("http://vocab.ctic.es/parrot#documentsXHTMLRDFaOntology"));
		for (RDFNode node : iterator.toList()){
			inputs.add(new URLInput(new URL(node.asResource().getURI()),"text/html"));
		}

		iterator = model.listObjectsOfProperty(ResourceFactory.createProperty("http://vocab.ctic.es/parrot#documentsRIFXMLDocument"));
		for (RDFNode node : iterator.toList()){
			inputs.add(new URLInput(new URL(node.asResource().getURI()),"application/rif+xml"));
		}
		
		iterator = model.listObjectsOfProperty(ResourceFactory.createProperty("http://vocab.ctic.es/parrot#documentsRIFPSDocument"));
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
	private String getPrologueURLFromExistingReport(Model model) throws MalformedURLException, IOException, ReaderException {

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
	private String getAppendixURLFromExistingReport(Model model) throws MalformedURLException, IOException, ReaderException {


		NodeIterator iterator = model.listObjectsOfProperty(ResourceFactory.createProperty("http://vocab.ctic.es/parrot#hasAppendix"));
		
		for (RDFNode node : iterator.toList()){
			return node.asResource().getURI();
		}
		
		return null;

	}

	/**
	 * Initializes a documentary project from an existing report.
	 * @param dp the documentary project to initialize.
	 * @param reportURL the report URL.
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ReaderException
	 */
	public void initializeDocumentaryProjectFromExistingReport(DocumentaryProject dp, String reportURL) throws MalformedURLException, IOException, ReaderException {
	    Model model = ModelFactory.createDefaultModel();
	    
		// Init java-rdfa in jena
		try {
			Class.forName("net.rootdev.javardfa.jena.RDFaReader");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		Input input = new URLInput(new URL(reportURL), "application/xhtml+xml");
		String base = input.getBase();
		try {
			model.read(input.openReader(), base == null ? "http://example.org/base#" : base, "XHTML");
		} catch (RuntimeException e) {
			// encapsulate RuntimeException generated by jena when a parser exception is arised
			throw new ReaderException(e);
		}

		dp.setPrologueURL(getPrologueURLFromExistingReport(model));
        dp.setAppendixURL(getAppendixURLFromExistingReport(model));
        dp.addAllInput(getInputsFromExistingReport(model));
	}

}
