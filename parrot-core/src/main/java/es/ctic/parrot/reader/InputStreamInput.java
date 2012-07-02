package es.ctic.parrot.reader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.log4j.Logger;
/**
 * An input document provided as input stream of bytes.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 */
public class InputStreamInput implements Input {
	
	private static final Logger logger = Logger.getLogger(InputStreamInput.class);
	
	private String base;
	private String mimeType;
	private InputStream is;
	private String charsetName;
	
	public InputStreamInput(InputStream is, String charsetName, String mimeType, String base) {
		this.is = is;
		this.charsetName = charsetName;
		this.mimeType = mimeType;
		this.base = base;
	}

	public Reader openReader() throws IOException {
		if (charsetName != null) {
			logger.debug("Opening reader with charset " + charsetName);
			return new InputStreamReader(is, charsetName);
		} else {
			logger.debug("Unknown charset, using default charset");
			return new InputStreamReader(is); // default charset
		}
	}

	public String getMimeType() {
		return mimeType;
	}

	public String getBase() {
		return base;
	}

	public boolean isPersistent() {
		return false;
	}

}
