package es.ctic.parrot.reader;

import java.io.IOException;
import java.io.Reader;

/**
 * An input document for Parrot. This interface encapsulates
 * different sources for input documents.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 * 
 */
public interface Input {

    /**
     * Returns a reader to read the contents of this input document.
     * @return a reader to read the contents of this input document.
     * @throws IOException if a failed or interrupted I/O operation occurs.
     */
    public abstract Reader openReader() throws IOException;

    /**
     * Returns the MIME type of this input document.
     * @return the MIME type of this input document.
     */
    public abstract String getMimeType();

    /**
     * Returns the base to resolve relative URIs of this input document.
     * @return the base to resolve relative URIs of this input document.
     */
    public abstract String getBase();

}
