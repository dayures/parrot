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

public class RifleXmlReader implements DocumentReader {

    public void readDocumentableObjects(Input input,
            DocumentableObjectRegister register) throws IOException,
            ReaderException {
        Parser parser = new Parser();
        Source source = new StreamSource(input.openReader());
        try {
            Document document = parser.parse(source);
            RifVisitor visitor = new RifVisitor(register);
            document.accept(visitor);            
        } catch (Exception e) {
            throw new ReaderException(e);
        }
    }

}
