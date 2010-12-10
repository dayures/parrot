package es.ctic.parrot.reader;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * An input document provided as a String.
 * 
 * @author CTIC
 *
 */
public class StringInput implements Input {
    
    private String mimeType;
    private String content;
    
    public StringInput(String content, String mimeType) {
        this.content = content;
        this.mimeType = mimeType;
    }

    public String getMimeType() {
        return mimeType;
    }

    public Reader openReader() throws IOException {
        return new StringReader(content);
    }

	@Override
	public String toString() {
		return "StringInput [mimeType=" + mimeType + ", content=" + content
				+ "]";
	}
    
}
