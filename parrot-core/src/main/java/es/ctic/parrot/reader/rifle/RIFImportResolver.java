package es.ctic.parrot.reader.rifle;

import java.io.IOException;
import java.net.URL;

import net.sourceforge.rifle.ast.Document;
import net.sourceforge.rifle.ast.Import;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.reader.Input;
import es.ctic.parrot.reader.ReaderException;
import es.ctic.parrot.reader.URLInput;
import es.ctic.parrot.reader.jena.JenaOWLReader;

/**
 * This class hold the methods to import documents (<code>rif:Import</code>).
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class RIFImportResolver {
    
    private static final Logger logger = Logger.getLogger(RIFImportResolver.class);
    
    private JenaOWLReader ontologyReader;
    private RifleXMLReader rifXMLReader;
    
    /**
     * Sets RIF XML reader.
     * @param rifXmlReader the RIF XML reader.
     */
    private void setRifXmlReader(RifleXMLReader rifXmlReader) {
        this.rifXMLReader = rifXmlReader;
    }

    /**
     * Returns the RIF XML reader.
     * @return the RIF XML reader. 
     */
    private RifleXMLReader getRifXmlReader() {
        return rifXMLReader;
    }

    /**
     * Sets ontology reader.
     * @param ontologyReader the ontology reader.
     */
    private void setOntologyReader(JenaOWLReader ontologyReader) {
        this.ontologyReader = ontologyReader;
    }

    /**
     * Returns the ontology reader.
     * @return the ontology reader.
     */
    private JenaOWLReader getOntologyReader() {
        return ontologyReader;
    }

    
    /**
     * Constructs an import resolver using a given ontology reader.
     * @param ontologyReader the ontology reader.
     */
    public RIFImportResolver(JenaOWLReader ontologyReader) {
        this.setOntologyReader(ontologyReader);
    }
    
    /**
     * Constructs an import resolver using a given ontology reader and a RIF XML reader.
     * @param ontologyReader the ontology reader.
     * @param rifXmlReader the RIF XML reader.
     */
    public RIFImportResolver(JenaOWLReader ontologyReader, RifleXMLReader rifXmlReader) {
        this.setOntologyReader(ontologyReader);
        this.setRifXmlReader(rifXmlReader);
    }

    /**
     * Resolve the RIF import directive (<code>rif:Import</code>) of the document given.
     * An RIF import directive consists of:
     * <ul>
     * <li>the locator, an IRI that identifies and locates the document to be imported</li>
     * <li>the profile, an <b>optional</b> second IRI that identifies the profile of the import.</li>
     * </ul>
     * If only the locator is present, the document to be imported is treated as a RIF/XML document. 
     * If the profile is present also, the document to be imported is treated as an OWL ontology.
     * @param document the document.
     * @param register the register.
	 * @throws IOException if a failed or interrupted I/O operation occurs, usually during the initial reading of inputs.
	 * @throws ReaderException if a input is malformed or not valid (usually, markup issues).
     * @see <a href="http://www.w3.org/TR/2013/REC-rif-prd-20130205/#Import_directive">Import directive in RIF-PRD</a>
     */
    protected void resolveImports(Document document, DocumentableObjectRegister register) throws IOException, ReaderException {
        for (Import imp : document.getImports()) {
            URL url = new URL(imp.getLocator());
            if (imp.getProfile() != null) {
                // binary import, see http://www.w3.org/TR/rif-rdf-owl/#Importing_RDF_and_OWL_in_RIF
                logger.trace("Reading import with URL <"+url+"> and profile <"+imp.getProfile()+">");
                if (getOntologyReader() == null) {
                    logger.error("Discarding binary import " + url + " because there is no ontology reader is configured");
                } else {
                    Input additionalInput = new URLInput(url, "application/rdf+xml"); // FIXME: hardcoded mime type. Profile is not used yet
                    getOntologyReader().readDocumentableObjects(additionalInput, register);
                }
            } else {
                // unary import, see http://www.w3.org/TR/2013/REC-rif-prd-20130205/#Import_directive
                logger.trace("Reading import with URL <"+url+"> and without profile");            	
                if (getRifXmlReader() == null) {
                    logger.error("Discarding unary import " + url + " because there is no RIF XML reader configured");
                } else {
                    Input additionalInput = new URLInput(url, "application/rif+xml"); // FIXME: hardcoded mime type. Profile is not used yet
                    getRifXmlReader().readDocumentableObjects(additionalInput, register);
                }
            }
        }        
    }


}
