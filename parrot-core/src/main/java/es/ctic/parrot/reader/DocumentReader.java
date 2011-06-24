package es.ctic.parrot.reader;

import java.io.IOException;

import es.ctic.parrot.de.DocumentableObjectRegister;

/**
 * 
 * A reader to read input document by Parrot. This interface encapsulates
 * different readers for input documents.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public interface DocumentReader {

	/**
     * Reads the input document and fulfill the register with the documentable objects found.
     * @param input The input document.
     * @param register The register to be fulfilled.
     * @throws IOException if a failed or interrupted I/O operation occurs.
     * @throws ReaderException if a input is malformed or not valid (usually, markup issues).
     */
	public abstract void readDocumentableObjects(Input input, DocumentableObjectRegister register) throws IOException, ReaderException;

}
