package es.ctic.parrot.appserv;

import java.io.IOException;
import java.util.Collection;
import java.util.Locale;

import es.ctic.parrot.DocumentaryProject;
import es.ctic.parrot.de.DocumentableObject;
import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.docmodel.Document;
import es.ctic.parrot.generators.OutputGenerator;
import es.ctic.parrot.reader.DocumentReader;
import es.ctic.parrot.reader.Input;
import es.ctic.parrot.reader.ReaderException;
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
 * @author CTIC
 *
 */
public class ParrotAppServ {
    
    private JenaOWLReader ontologyReader;
    private DocumentReader ruleXmlReader;
    private DocumentReader rifPSReader;
    
    /**
     * Creates a parrot service.
     */
    public ParrotAppServ() {
		setOntologyReader(new JenaOWLReader());
		setRuleXmlReader(new RifleXmlReader(getOntologyReader()));
		setRifPSReader(new RiflePSReader(getOntologyReader(), getRuleXmlReader()));
    }

	/**
	 * Generates the report for a documentary project
	 * 
	 * @param dp the documentary Project to be fulfilled
	 * @param outputGenerator the output generator
	 * @throws IOException if a failed or interrupted I/O operation occurs, usually during the initial reading of inputs.
	 * @throws ReaderException if a input is malformed or not valid (usually, markup issues)   
	 */
	public void createDocumentation(DocumentaryProject dp, OutputGenerator outputGenerator) throws IOException, ReaderException, TransformerException {
	    DocumentableObjectRegister register = new DocumentableObjectRegister();
		readAndRegisterDocumentableObjects(dp.getInputs(), register);
		resolveInternalReferences(register);
		resolveCrossReferences(register);
		Document document = transformToDocument(register.getDocumentableObjects(), dp.getLocale());
		outputGenerator.generateOutput(document);
	}
	
	/**
	 * Read a collection of inputs and register its documentable elements (ontologies, classes, properties, individuals, rulesets and rules) 
	 * @param inputs a collection of inputs to be read and register its documentable elements
	 * @param register a register to add the elements to document
	 * @throws IOException if a failed or interrupted I/O operation occurs, usually during the initial reading of inputs.
	 * @throws ReaderException if a input is malformed or not valid (usually, markup issues)
	 */
    private void readAndRegisterDocumentableObjects(Collection<Input> inputs, DocumentableObjectRegister register) throws IOException, ReaderException {
		for (Input input : inputs) {
		    DocumentReader reader = getDocumentReaderForInput(input);
		    reader.readDocumentableObjects(input, register);
		} 
    }	

    /**
     * Resolves internal references as, for instance, references between a subclass and their superclass. Other references are: property domain, property range, superproperties, subproperties, superclasses and subclasses
     * @param register a register to find elements to connect
     * @throws TransformerException
     */
    private void resolveInternalReferences(DocumentableObjectRegister register) throws TransformerException {
		DocumentableObjectVisitor ontologyInternalReferenceResolver = new OntologyInternalReferenceResolver(register);
		ontologyInternalReferenceResolver.visit(register);
	}

    /**
     * Resolves references between ontologies and rules
     * @param register a register to find elements to connect
     * @throws TransformerException
     */
	private void resolveCrossReferences(DocumentableObjectRegister register) throws TransformerException {
        DocumentableObjectVisitor ruleToOntologyReferenceResolver = new RuleToOntologyReferenceResolver();
        ruleToOntologyReferenceResolver.visit(register);
    }

	/**
	 * Returns the most suitable reader for a specified input
	 * @param input a input
	 * @return the document reader most suitable for a input
	 * @throws ReaderException if there is no reader to that mimetype 
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
     * Generates an internal document. This document could be pass later to an output generator in order to generate the final documentation 
     * @param documentableObjects a collection of documentable elements
     * @param locale a locale used for customize the output
     * @return a document to be presented by an output generator
     * @throws TransformerException
     */
    private Document transformToDocument(Collection<DocumentableObject> documentableObjects, Locale locale) throws TransformerException {
        Document document = new Document(locale);
		document.setTitle("Parrot"); // FIXME
        DetailsVisitor detailVisitor = new DetailsVisitor(document, locale);
        GlossaryVisitor glossaryVisitor = new GlossaryVisitor(document, locale);
		for (DocumentableObject documentableObject : documentableObjects) {
			documentableObject.accept(detailVisitor);
			documentableObject.accept(glossaryVisitor);
		}
        return document;
    }

	/**
	 * @param ontologyReader the ontologyReader to set
	 */
	private void setOntologyReader(JenaOWLReader ontologyReader) {
		this.ontologyReader = ontologyReader;
	}

	/**
	 * @return the ontologyReader
	 */
	private JenaOWLReader getOntologyReader() {
		return ontologyReader;
	}

	/**
	 * @param ruleXmlReader the ruleXmlReader to set
	 */
	private void setRuleXmlReader(DocumentReader ruleXmlReader) {
		this.ruleXmlReader = ruleXmlReader;
	}

	/**
	 * @return the ruleXmlReader
	 */
	private DocumentReader getRuleXmlReader() {
		return ruleXmlReader;
	}

	/**
	 * @param rifPSReader the rifPSReader to set
	 */
	private void setRifPSReader(DocumentReader rifPSReader) {
		this.rifPSReader = rifPSReader;
	}

	/**
	 * @return the rifPSReader
	 */
	private DocumentReader getRifPSReader() {
		return rifPSReader;
	}

	/**
	 * Returns true if the mimetype could be read by an Ontology Reader.
	 * Nowadays, the supported mimetypes are:
	 * <ul>
	 * 	<li>application/rdf+xml</li>
	 * 	<li>application/xml</li>
	 *  <li>application/owl+xml</li>
	 *  <li>application/xhtml+xml</li>
	 *  <li>text/html</li>
	 *  <li>text/n3</li>
	 *  <li>text/rdf+n3</li>
	 * </ul 
	 * @param mimetype
	 * @return true if the mimetype is supported
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
	 * Returns true if the mimetype could be read by an RIF XML Reader.
	 * Nowadays, the supported mimetype is:
	 * <ul>
	 * 	<li>application/rif+xml</li>
	 * </ul 
	 * @param mimetype
	 * @return true if the mimetype is supported
	 */
	private boolean isRuleXmlReadable(String mimetype){
		 return "application/rif+xml".equals(mimetype) ? true : false;
	}
	
	/**
	 * Returns true if the mimetype could be read by an RIF PS Reader.
	 * Nowadays, the supported mimetype is:
	 * <ul>
	 * 	<li>application/rif+xml</li>
	 * </ul 
	 * @param mimetype
	 * @return true if the mimetype is supported
	 */
	private boolean isRifPSReadable(String mimetype){
		 return "text/x-rif-ps".equals(mimetype) ? true : false;
	}

}
