package es.ctic.parrot.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Reader;

import javax.activation.MimetypesFileTypeMap;

import org.apache.log4j.Logger;

/**
 * An input document stored in the current filesystem.
 * 
 * @author Carlos Tejo Alonso (<a href="http://www.fundacionctic.org">Fundación CTIC</a>)
 * @version 1.0
 * @since 1.0
 * 
 */
public class FileInput implements Input {
    
    private static final Logger logger = Logger.getLogger(FileInput.class);

    private File file;
    private String mimeType;
    
    private static MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();

    public FileInput(File file, String mimeType) {
        this.file = file;
        this.mimeType = mimeType;
    }
    
    public FileInput(File file) {
        this.file = file;
        this.mimeType = mimeTypesMap.getContentType(this.file);
        logger.debug("The mimetype " + this.mimeType + " has been guessed for file " + this.file);
    }

    public String getMimeType() {
        return mimeType;
    }

    public Reader openReader() {
		throw new UnsupportedOperationException();        
    }
    
	public String getBase() {
		return null;
	}

	@Override
	public String toString() {
		return "FileInput [mimeType=" + mimeType + ", file=" + file + "]";
	}

	public boolean isPersistent() {
		return true;
	}

	public boolean isReaderProof() {
		return false;
	}

	public InputStream getInputStream() throws FileNotFoundException {
		return new FileInputStream(file);
	}

}
