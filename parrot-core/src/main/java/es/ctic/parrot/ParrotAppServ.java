package es.ctic.parrot;

import java.io.IOException;
import java.util.Collection;
import java.util.Locale;

import es.ctic.parrot.de.DocumentableObject;
import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.docmodel.Document;
import es.ctic.parrot.generators.OutputGenerator;
import es.ctic.parrot.generators.OutputGenerator.Profile;
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
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class ParrotAppServ {
    
    private JenaOWLReader ontologyReader;
    private DocumentReader ruleXmlReader;
    private DocumentReader rifPSReader;
    
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
	 * </ul>
	 * 
     */
    public ParrotAppServ() {
		setOntologyReader(new JenaOWLReader());
		setRuleXmlReader(new RifleXmlReader(getOntologyReader()));
		setRifPSReader(new RiflePSReader(getOntologyReader(), getRuleXmlReader()));
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
		Document document = transformToDocument(register.getDocumentableObjects(), dp.getLocale());
		outputGenerator.generateOutput(document, profile);
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
     * @return a document to be presented by an output generator.
     * @throws TransformerException if a failed transformation operation occurs.
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
	 * Set the ontology reader for this service.
	 * @param ontologyReader the ontology reader to set.
	 */
	private void setOntologyReader(JenaOWLReader ontologyReader) {
		this.ontologyReader = ontologyReader;
	}

	/**
	 * Returns a reader for ontologies (OWl, RDF, RDFa).
	 * @return the ontology reader.
	 */
	private JenaOWLReader getOntologyReader() {
		return ontologyReader;
	}

	/**
	 * Set the RIF XML reader for this service.
	 * @param ruleXmlReader the RIF XML reader to set.
	 */
	private void setRuleXmlReader(DocumentReader ruleXmlReader) {
		this.ruleXmlReader = ruleXmlReader;
	}

	/**
	 * Returns the RIF XML Reader.
	 * @return the RIF XML Reader.
	 */
	private DocumentReader getRuleXmlReader() {
		return ruleXmlReader;
	}

	/**
	 * Set the RIF Presentation Syntax (PS) reader for this service.
	 * @param rifPSReader the RIF Presentation Syntax (PS) reader to set.
	 */
	private void setRifPSReader(DocumentReader rifPSReader) {
		this.rifPSReader = rifPSReader;
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
	 * 	<li>application/rif+xml</li>
	 * </ul 
	 * @param mimetype the MIME type.
	 * @return true if the MIME type is supported.
	 */
	private boolean isRifPSReadable(String mimetype){
		 return "text/x-rif-ps".equals(mimetype) ? true : false;
	}

}
