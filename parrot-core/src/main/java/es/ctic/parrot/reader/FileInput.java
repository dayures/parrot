package es.ctic.parrot.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import javax.activation.MimetypesFileTypeMap;

import org.apache.log4j.Logger;

/**
 * An input document stored in a filesystem.
 * 
 * @author CTIC
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
        logger.info("The mimetype " + this.mimeType + " has been guessed for file " + this.file);
    }

    public String getMimeType() {
        return mimeType;
    }

    public Reader openReader() throws FileNotFoundException {
        return new FileReader(file);
    }

	@Override
	public String toString() {
		return "FileInput [mimeType=" + mimeType + ", file=" + file + "]";
	}

	public String getBase() {
		return null;
	}

}
