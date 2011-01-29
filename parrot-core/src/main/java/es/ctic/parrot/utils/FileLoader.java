package es.ctic.parrot.utils;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

public class FileLoader {
	private static final Logger logger = Logger.getLogger(FileLoader.class);
	
    private static final String ACCEPT_HTTP_HEADER = "Accept"; 
	private static final String ACCEPT_MIME_TYPES = "application/rdf+xml";
	
    public static InputStream openOwlInputStream(String filename) throws FileNotFoundException {
        logger.debug("Opening resource input stream for filename: " + filename);
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream in = classLoader.getResourceAsStream(filename);
        if (in == null) {
        	try {
				in=loadOntologyFromURL(filename);
				return in;
			} catch (IOException e) {
				logger.error("Resource file not found: " + filename);
				throw new FileNotFoundException(filename);
			} 
        } else {
            return in;
        }
    }
    
    private static InputStream loadOntologyFromURL(String url) throws IOException { 
    	URL uri=new URL(url);
        URLConnection connection = uri.openConnection();	   
        connection.setRequestProperty(ACCEPT_HTTP_HEADER, ACCEPT_MIME_TYPES); 
        return new BufferedInputStream(connection.getInputStream());   
	} 
}
