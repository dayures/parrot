package es.ctic.parrot.reader.rifle;

import java.io.IOException;
import java.net.URL;

import net.sourceforge.rifle.ast.Document;
import net.sourceforge.rifle.ast.Import;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.reader.DocumentReader;
import es.ctic.parrot.reader.Input;
import es.ctic.parrot.reader.ReaderException;
import es.ctic.parrot.reader.URLInput;
import es.ctic.parrot.reader.jena.JenaOWLReader;

/**
 * This class hold the methods to import documents (usually <code>owl:imports</code> and <code>rif:Import</code>).
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class ImportResolver {
    
    private static final Logger logger = Logger.getLogger(ImportResolver.class);
    
    private JenaOWLReader ontologyReader;
    private DocumentReader rifXmlReader;
    
    /**
     * Constructs an import resolver using a given ontology reader.
     * @param ontologyReader the ontology reader.
     */
    public ImportResolver(JenaOWLReader ontologyReader) {
        this.setOntologyReader(ontologyReader);
    }
    
    /**
     * Constructs an import resolver using a given ontology reader and a RIF XML reader.
     * @param ontologyReader the ontology reader.
     * @param rifXmlReader the RIF XML reader.
     */
    public ImportResolver(JenaOWLReader ontologyReader, DocumentReader rifXmlReader) {
        this.setOntologyReader(ontologyReader);
        this.setRifXmlReader(rifXmlReader);
    }

    /**
     * Resolver the import documents (usually <code>owl:imports</code> and <code>rif:Import</code>) of the document given.
     * @param document the document.
     * @param register the register.
	 * @throws IOException if a failed or interrupted I/O operation occurs, usually during the initial reading of inputs.
	 * @throws ReaderException if a input is malformed or not valid (usually, markup issues).
     */
    protected void resolveImports(Document document, DocumentableObjectRegister register) throws IOException, ReaderException {
        for (Import imp : document.getImports()) {
            URL url = new URL(imp.getLocator());
            if (imp.getProfile() != null) {
                // binary import, see http://www.w3.org/TR/rif-rdf-owl/#Importing_RDF_and_OWL_in_RIF
                if (getOntologyReader() == null) {
                    logger.error("Discarding binary import " + url + " because there is no ontology reader is configured");
                } else {
                    Input additionalInput = new URLInput(url, "application/rdf+xml"); // FIXME: hardcoded mime type. Profile is not used yet
                    getOntologyReader().readDocumentableObjects(additionalInput, register);
                }
            } else {
                // unary import
                if (getRifXmlReader() == null) {
                    logger.error("Discarding unary import " + url + " because there is no RIF XML reader configured");
                } else {
                    Input additionalInput = new URLInput(url, "application/rif+xml"); // FIXME: hardcoded mime type. Profile is not used yet
                    getRifXmlReader().readDocumentableObjects(additionalInput, register);
                }
            }
        }        
    }

    /**
     * Sets RIF XML reader.
     * @param rifXmlReader the RIF XML reader.
     */
    public void setRifXmlReader(DocumentReader rifXmlReader) {
        this.rifXmlReader = rifXmlReader;
    }

    /**
     * Returns the RIF XML reader.
     * @return the RIF XML reader. 
     */
    public DocumentReader getRifXmlReader() {
        return rifXmlReader;
    }

    /**
     * Sets ontology reader.
     * @param ontologyReader the ontology reader.
     */
    public void setOntologyReader(JenaOWLReader ontologyReader) {
        this.ontologyReader = ontologyReader;
    }

    /**
     * Returns the ontology reader.
     * @return the ontology reader.
     */
    public JenaOWLReader getOntologyReader() {
        return ontologyReader;
    }

}
