package es.ctic.parrot.reader.rifle;

import java.io.IOException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import net.sourceforge.rifle.ast.Document;
import net.sourceforge.rifle.prd.xml.Parser;
import net.sourceforge.rifle.prd.xml.ParserException;
import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.reader.DocumentReader;
import es.ctic.parrot.reader.Input;
import es.ctic.parrot.reader.ReaderException;
import es.ctic.parrot.reader.jena.JenaOWLReader;

/**
 * A reader for RIF XML input documents that uses <a href="http://rifle.sourceforge.net/">rifle</a>. 
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 * 
 */
public class RifleXMLReader extends RIFImportResolver implements DocumentReader {

    /**
     * Constructs a RIF XML reader.
     * @param ontologyReader the ontology reader.
     */
    public RifleXMLReader(JenaOWLReader ontologyReader) {
        super(ontologyReader);
        setRifXmlReader(this);
    }
    
    public void readDocumentableObjects(Input input,
            DocumentableObjectRegister register) throws IOException,
            ReaderException {
        Parser parser = new Parser();
        Source source = new StreamSource(input.openReader());
        Document document = null;
        try {
            document = parser.parse(source);
        } catch (ParserException e) {
            throw new ReaderException("A problem occurred when parsing the RIF/XML document: " + e.getMessage(), e);
        }
        
        try {
            resolveImports(document, register);
            RifleASTVisitor visitor = new RifleASTVisitor(register, getOntologyReader().getAnnotationStrategy(), getOntologyReader().getOntModel());
            document.accept(visitor);
        } catch (Exception e) {
            throw new ReaderException("While extracting information from RIF/XML document", e);
        }
    }

}
