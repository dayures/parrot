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
 */
public interface Input {

    /**
     * Returns a reader to read the contents of the input document
     * @return A reader to read the contents of the input document
     * @throws IOException
     */
    public abstract Reader openReader() throws IOException;

    /**
     * @return The MIME type of the input document
     */
    public abstract String getMimeType();

    /**
     * @return The Base
     */
    public abstract String getBase();

}
