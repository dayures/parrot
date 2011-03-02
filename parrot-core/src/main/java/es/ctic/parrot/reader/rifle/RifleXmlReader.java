package es.ctic.parrot.reader.rifle;

import java.io.IOException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import net.sourceforge.rifle.ast.Document;
import net.sourceforge.rifle.prd.xml.Parser;
import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.reader.DocumentReader;
import es.ctic.parrot.reader.Input;
import es.ctic.parrot.reader.ReaderException;
import es.ctic.parrot.reader.jena.JenaOWLReader;

public class RifleXmlReader extends ImportResolver implements DocumentReader {

    public RifleXmlReader(JenaOWLReader ontologyReader) {
        super(ontologyReader);
        setRifXmlReader(this);
    }
    
    public void readDocumentableObjects(Input input,
            DocumentableObjectRegister register) throws IOException,
            ReaderException {
        Parser parser = new Parser();
        Source source = new StreamSource(input.openReader());
        try {
            Document document = parser.parse(source);
            resolveImports(document, register);
            RifleASTVisitor visitor = new RifleASTVisitor(register, getOntologyReader().getAnnotationStrategy(), getOntologyReader().getOntModel());
            document.accept(visitor, null);
        } catch (Exception e) {
            throw new ReaderException("While parsing RIF XML document", e);
        }
    }

}
