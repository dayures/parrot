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
    
    public ParrotAppServ() {
		setOntologyReader(new JenaOWLReader());
		setRuleXmlReader(new RifleXmlReader(getOntologyReader()));
		setRifPSReader(new RiflePSReader(getOntologyReader(), getRuleXmlReader()));
    }

	/**
	 * Generates the report for a documentary project
	 * 
	 * @param dp
	 * @param outputGenerator
	 * @throws IOException
	 * @throws ReaderException 
	 */
	public void createDocumentation(DocumentaryProject dp, OutputGenerator outputGenerator) throws IOException, ReaderException, TransformerException {
	    DocumentableObjectRegister register = new DocumentableObjectRegister();
		readAndRegisterDocumentableObjects(dp.getInputs(), register);
		resolveInternalReferences(register);
		resolveCrossReferences(register);
		Collection<DocumentableObject> documentableObjects = register.getDocumentableObjects();
		Document document = transformToDocument(documentableObjects, dp.getLocale());
		//ontologyReader.getOntModel().write(System.out);
		outputGenerator.generateOutput(document);
	}
	
    private void readAndRegisterDocumentableObjects(Collection<Input> inputs, DocumentableObjectRegister register) throws IOException, ReaderException {
		for (Input input : inputs) {
		    DocumentReader reader = getDocumentReaderForInput(input);
		    reader.readDocumentableObjects(input, register);
		} 
    }	

    private void resolveInternalReferences(DocumentableObjectRegister register) throws TransformerException {
		DocumentableObjectVisitor ontologyInternalReferenceResolver = new OntologyInternalReferenceResolver(register);
		ontologyInternalReferenceResolver.visit(register);
	}

	private void resolveCrossReferences(DocumentableObjectRegister register) throws TransformerException {
        DocumentableObjectVisitor ruleToOntologyReferenceResolver = new RuleToOntologyReferenceResolver();
        ruleToOntologyReferenceResolver.visit(register);
    }

    private DocumentReader getDocumentReaderForInput(Input input) throws IOException  {
        if (   "application/rdf+xml".equals(input.getMimeType())
        	|| "application/xml".equals(input.getMimeType())
        	|| "application/owl+xml".equals(input.getMimeType())
        	|| "application/xhtml+xml".equals(input.getMimeType())
        	|| "text/html".equals(input.getMimeType())
        	|| "text/n3".equals(input.getMimeType())
        	|| "text/rdf+n3".equals(input.getMimeType())) {
            return getOntologyReader();
        } else if ("application/rif+xml".equals(input.getMimeType())){
            return getRuleXmlReader();
        } else if ("text/x-rif-ps".equals(input.getMimeType())){
            return getRifPSReader();
        } else {
            throw new RuntimeException("Unable to open mimetype " + input.getMimeType());
        }
    }

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
	public void setOntologyReader(JenaOWLReader ontologyReader) {
		this.ontologyReader = ontologyReader;
	}

	/**
	 * @return the ontologyReader
	 */
	public JenaOWLReader getOntologyReader() {
		return ontologyReader;
	}

	/**
	 * @param ruleXmlReader the ruleXmlReader to set
	 */
	public void setRuleXmlReader(DocumentReader ruleXmlReader) {
		this.ruleXmlReader = ruleXmlReader;
	}

	/**
	 * @return the ruleXmlReader
	 */
	public DocumentReader getRuleXmlReader() {
		return ruleXmlReader;
	}

	/**
	 * @param rifPSReader the rifPSReader to set
	 */
	public void setRifPSReader(DocumentReader rifPSReader) {
		this.rifPSReader = rifPSReader;
	}

	/**
	 * @return the rifPSReader
	 */
	public DocumentReader getRifPSReader() {
		return rifPSReader;
	}


}
