package es.ctic.parrot.reader;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;

/**
 * An input document provided as a String.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
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

	public String getBase() {
		return null;
	}
	
	@Override
	public String toString() {
		return "StringInput [mimeType=" + mimeType + ", content=" + content + "]";
	}

	public boolean isPersistent() {
		return false;
	}

	public boolean isReaderProof() {
		return true;
	}

	public InputStream getInputStream() {
		throw new UnsupportedOperationException();
	}
    
}
