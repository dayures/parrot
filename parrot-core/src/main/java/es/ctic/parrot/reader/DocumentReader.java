package es.ctic.parrot.reader;

import java.io.IOException;

import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.reader.jena.OntResourceAnnotationStrategy;

public interface DocumentReader {
    
	public abstract void readDocumentableObjects(Input input, DocumentableObjectRegister register) throws IOException, ReaderException;
	public abstract OntResourceAnnotationStrategy getAnnotationStrategy();

}
