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

public class ImportResolver {
    
    private static final Logger logger = Logger.getLogger(ImportResolver.class);
    
    private JenaOWLReader ontologyReader;
    private DocumentReader rifXmlReader;
    
    public ImportResolver(JenaOWLReader ontologyReader) {
        this.setOntologyReader(ontologyReader);
    }
    
    public ImportResolver(JenaOWLReader ontologyReader, DocumentReader rifXmlReader) {
        this.setOntologyReader(ontologyReader);
        this.setRifXmlReader(rifXmlReader);
    }

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

    public void setRifXmlReader(DocumentReader rifXmlReader) {
        this.rifXmlReader = rifXmlReader;
    }

    public DocumentReader getRifXmlReader() {
        return rifXmlReader;
    }

    public void setOntologyReader(JenaOWLReader ontologyReader) {
        this.ontologyReader = ontologyReader;
    }

    public JenaOWLReader getOntologyReader() {
        return ontologyReader;
    }

}
