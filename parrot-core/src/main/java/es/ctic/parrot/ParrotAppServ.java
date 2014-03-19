package es.ctic.parrot;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.rdf.model.RDFNode;

import es.ctic.parrot.de.DocumentableObject;
import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.docmodel.Document;
import es.ctic.parrot.generators.OutputGenerator;
import es.ctic.parrot.project.DocumentaryProject;
import es.ctic.parrot.reader.DocumentReader;
import es.ctic.parrot.reader.Input;
import es.ctic.parrot.reader.ReaderException;
import es.ctic.parrot.reader.jena.JenaOWLReader;
import es.ctic.parrot.reader.rifle.RiflePSReader;
import es.ctic.parrot.reader.rifle.RifleXMLReader;
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
    final private RifleXMLReader ruleXmlReader;
    final private RiflePSReader rifPSReader;
    
    /**
     * Constructs a parrot service.
     * Nowadays, the supported mimetypes are:
	 * <ul>
	 * 	<li><code>application/rdf+xml</code></li>
	 * 	<li><code>application/xml</code></li>
	 *
	 *  <li><code>text/n3</code></li>
	 *  <li><code>text/rdf+n3</code></li>
	 *  <li><code>text/turtle</code></li>
  	 * 	<li><code>application/n-triples</code></li>
	 *
	 *  <li><code>application/xhtml+xml</code></li>
	 *  <li><code>text/html</code></li>
	 *
	 * 	<li><code>application/rif+xml</code></li>
  	 * 	<li><code>text/x-rif-ps</code></li>
	 * </ul>
	 * 
     */
    public ParrotAppServ() {
    	ontologyReader = new JenaOWLReader();
    	ruleXmlReader = new RifleXMLReader(getOntologyReader());
    	rifPSReader = new RiflePSReader(getOntologyReader(), getRuleXmlReader());
    }

	/**
	 * Generates the report for a documentary project.
	 * 
	 * @param dp the documentary Project to be fulfilled.
	 * @param outputGenerator the output generator.
	 * @throws IOException if a failed or interrupted I/O operation occurs, usually during the initial reading of inputs.
	 * @throws ReaderException if a input is malformed or not valid (usually, markup issues).
	 */
	public void createDocumentation(DocumentaryProject dp, OutputGenerator outputGenerator) throws IOException, ReaderException, TransformerException {
		logger.debug("Start to create a document");
		DocumentableObjectRegister register = new DocumentableObjectRegister();
		readAndRegisterDocumentableObjects(dp.getInputs(), register);
		resolveInternalReferences(register);
		resolveCrossReferences(register);
		Document document = transformToDocument(register.getAllRegisteredDocumentableObjects(), dp.getLocale(), dp.getInputs(), dp.getPrologueURL(), dp.getAppendixURL(), dp.getGeneratedReportURL(), getLanguagesInModel(), dp.getCustomizeCssUrl());
		outputGenerator.generateOutput(document, dp.getProfile());
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
	private RifleXMLReader getRuleXmlReader() {
		return ruleXmlReader;
	}

	/**
	 * Returns the RIF Presentation Syntax (PS) Reader.
	 * @return the RIF Presentation Syntax (PS) Reader.
	 */
	private RiflePSReader getRifPSReader() {
		return rifPSReader;
	}

	/**
	 * Returns true if the MIME type could be read by an Ontology Reader.
	 * Nowadays, the supported MIME types are:
	 * <ul>
	 * 	<li><code>application/rdf+xml</code></li>
	 * 	<li><code>application/xml</code></li>
	 *  <li><code>text/n3</code></li>
	 *  <li><code>text/rdf+n3</code></li>
	 *  <li><code>text/turtle</code></li>
  	 * 	<li><code>application/n-triples</code></li>
	 *  <li><code>application/xhtml+xml</code></li>
	 *  <li><code>text/html</code></li>
	 *  
	 * </ul>
	 * @param mimetype the MIME type.
	 * @return true if the MIME type is supported.
	 */
	private boolean isOntologyReadable(String mimetype){
		if ("application/rdf+xml".equals(mimetype)
    	 || "application/xml".equals(mimetype)
     	 || "text/n3".equals(mimetype)
    	 || "text/rdf+n3".equals(mimetype)
     	 || "text/turtle".equals(mimetype)
    	 || "application/n-triples".equals(mimetype)
    	 || "application/xhtml+xml".equals(mimetype)
    	 || "text/html".equals(mimetype)){
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns true if the MIME type could be read by an RIF XML Reader.
	 * Nowadays, the supported MIME type is:
	 * <ul>
	 * 	<li><code>application/rif+xml</code></li>
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
	 * 	<li><code>text/x-rif-ps</code></li>
	 * </ul>
	 * @param mimetype the MIME type.
	 * @return true if the MIME type is supported.
	 */
	private boolean isRifPSReadable(String mimetype){
		 return "text/x-rif-ps".equals(mimetype) ? true : false;
	}

}
