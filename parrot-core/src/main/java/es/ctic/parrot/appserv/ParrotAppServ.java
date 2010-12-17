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
import es.ctic.parrot.transformers.DetailsVisitor;
import es.ctic.parrot.transformers.DocumentableObjectVisitor;
import es.ctic.parrot.transformers.OntologyInternalReferenceResolver;
import es.ctic.parrot.transformers.RuleToOntologyReferenceResolver;

/**
 * Main entry point for Parrot users.
 * 
 * @author CTIC
 *
 */
public class ParrotAppServ {
    
    private DocumentReader ontologyWrapper;
    private DocumentReader ruleWrapper;
    private DocumentReader rifPSWrapper;
    
    public ParrotAppServ() {
        // FIXME: initialize wrappers
    }

	/**
	 * Generates the report for a documentary project
	 * 
	 * @param dp
	 * @param outputGenerator
	 * @throws IOException
	 * @throws ReaderException 
	 */
	public void createDocumentation(DocumentaryProject dp, OutputGenerator outputGenerator) throws IOException, ReaderException {
	    DocumentableObjectRegister register = new DocumentableObjectRegister();
		readAndRegisterDocumentableObjects(dp.getInputs(), register);
		resolveInternalReferences(register);
		resolveCrossReferences(register);
		Collection<DocumentableObject> documentableObjects = register.getDocumentableObjects();
		Document document = transformToDocument(documentableObjects, dp.getLocale());		
		outputGenerator.generateOutput(document);
	}

    private void resolveInternalReferences(DocumentableObjectRegister register) {
		DocumentableObjectVisitor ontologyInternalReferenceResolver = new OntologyInternalReferenceResolver(register);
		ontologyInternalReferenceResolver.visit(register);
	}

	private void resolveCrossReferences(DocumentableObjectRegister register) {
        DocumentableObjectVisitor ruleToOntologyReferenceResolver = new RuleToOntologyReferenceResolver();
        ruleToOntologyReferenceResolver.visit(register);
    }

    private void readAndRegisterDocumentableObjects(Collection<Input> inputs, DocumentableObjectRegister register)
            throws IOException, ReaderException {
        for (Input input : inputs) {
            DocumentReader wrapper = getDocumentWrapperForInput(input);
            wrapper.readDocumentableObjects(input, register);
        } 
    }

    private DocumentReader getDocumentWrapperForInput(Input input) throws IOException  {
        if ("application/rdf+xml".equals(input.getMimeType())
        	|| "application/xml".equals(input.getMimeType())
        	|| "application/owl+xml".equals(input.getMimeType())) {
            return getOntologyWrapper();
        } else if ("application/rif+xml".equals(input.getMimeType())){
            return getRuleWrapper();
        } else if ("text/x-rif-ps".equals(input.getMimeType())){
            return getRifPSWrapper();
        } else {
            throw new RuntimeException("Unable to open mimetype " + input.getMimeType());
        }
    }

    private Document transformToDocument(Collection<DocumentableObject> documentableObjects, Locale locale) {
        Document document = new Document();
		document.setTitle("Parrot"); // FIXME
        DetailsVisitor detailVisitor = new DetailsVisitor(document, locale);
		for (DocumentableObject documentableObject : documentableObjects) {
			documentableObject.accept(detailVisitor);
		}
        return document;
    }

    public void setOntologyWrapper(DocumentReader ontologyWrapper) {
        this.ontologyWrapper = ontologyWrapper;
    }

    public DocumentReader getOntologyWrapper() {
        return ontologyWrapper;
    }

    public void setRuleWrapper(DocumentReader ruleWrapper) {
        this.ruleWrapper = ruleWrapper;
    }

    public DocumentReader getRuleWrapper() {
        return ruleWrapper;
    }

	public void setRifPSWrapper(DocumentReader rifPSWrapper) {
		this.rifPSWrapper = rifPSWrapper;
	}

	public DocumentReader getRifPSWrapper() {
		return rifPSWrapper;
	}

}
