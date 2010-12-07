package es.ctic.parrot.reader;

import java.io.IOException;

import es.ctic.parrot.de.DocumentableObjectRegister;

public interface DocumentReader {
    
	public void readDocumentableObjects(Input input, DocumentableObjectRegister register) throws IOException;

}
