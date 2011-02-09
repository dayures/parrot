package es.ctic.parrot.reader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * A web input document, anything that can be referenced by a URL.
 * 
 * @author CTIC
 *
 */

public class URLInput implements Input {

    private static final Logger logger = Logger.getLogger(URLInput.class);

    // application/rdf+xml, application/xml : http://www.w3.org/TR/owl-ref/#MIMEType
    // application/rif+xml : http://www.w3.org/TR/rif-core/#Appendix:_RIF_Media_Type_Registration
    // application/owl+xml : http://www.w3.org/TR/2009/REC-owl2-xml-serialization-20091027/#Appendix:_Internet_Media_Type.2C_File_Extension.2C_and_Macintosh_File_Type

    private static final String ACCEPT_HEADER_VALUES = "application/rdf+xml;application/xml;application/rif+xml;application/owl+xml;text/x-rif-ps;application/xhtml+xml;text/n3;text/rdf+n3;text/html;*/*"; // FIXME: add other mimetypes
    private static final Set<String> STRICT_MIMETYPES = new HashSet<String>(Arrays.asList("application/rdf+xml","application/xml","application/rif+xml","application/owl+xml","text/x-rif-ps", "application/xhtml+xml", "text/n3", "text/rdf+n3", "text/html"));

    private static final Map<String,String> MAP_EXTENSION_MIMETYPE = createMapExtensionMimeytpe();
    private static Map<String, String> createMapExtensionMimeytpe() {
        Map<String, String> result = new HashMap<String, String>();
        result.put("rdf","application/rdf+xml");
        result.put("xml", "application/xml");
        result.put("rif", "application/rif+xml");
        result.put("owl", "application/owl+xml");
        result.put("rifps", "text/x-rif-ps");
        result.put("xhtml", "application/xhtml+xml");
        result.put("n3", "text/n3");
        result.put("html", "text/html");
        return Collections.unmodifiableMap(result);
    }
    //private static final Set<String> NOT_STRICT_MIMETYPES = new HashSet<String>(Arrays.asList("text/x-rif-ps"));

    public static final char extensionSeparator = '.';
    
    private URL url;
    private String mimeType;

    public URLInput(URL url, String mimeType) throws IOException, ReaderException {
        this.url = url;
        this.mimeType = mimeType;
        checkUrl();
    }

    /**
     * Checks if the URL is available and establish content negotiation 
     * @throws IOException 
     * @throws ReaderException 
     */
    private void checkUrl() throws IOException, ReaderException {
        try {
            logger.debug("Detecting content type for URL " + this.url + " with mimetype " + this.mimeType);
            HttpURLConnection connection = (HttpURLConnection) this.url.openConnection();
            connection.setRequestMethod("HEAD"); 
            connection.addRequestProperty("Accept", ACCEPT_HEADER_VALUES);

            connection.connect();

            logger.debug("HTTP Status Response code: " + connection.getResponseCode() + " for URL " + this.url);

            String extension = url.getPath().substring(url.getPath().lastIndexOf(extensionSeparator) + 1);

            if (isValidResponseCode(connection.getResponseCode()) == false ){
                logger.error("URI " + this.url + " not accesible. HTTP Status code: " + connection.getResponseCode());
                throw new ReaderException("URI not accesible. HTTP Status code: " + connection.getResponseCode());
            } 

            // For content negotiation, set mimetype
            if (this.mimeType == null){
                this.mimeType = getCleanMimeType(connection.getContentType());
                if (STRICT_MIMETYPES.contains(this.mimeType)){ 
                    logger.info("Found content-type: " + this.mimeType + " for URL " + this.url);
                } else if (MAP_EXTENSION_MIMETYPE.get(extension) != null){
                    this.mimeType = MAP_EXTENSION_MIMETYPE.get(extension);
                	logger.info("Use extension "+ extension + " to fix content-type: " + this.mimeType + " for URL " + this.url);
                }else {
                    logger.error("mimeType not valid: " + this.mimeType + " for URL " + this.url);
                    throw new ReaderException("mimeType not valid: " + this.mimeType);
                }
            }
        } catch (IOException e) {
            throw new ReaderException("I/O Error, cannot read from " + this.url);
        } catch (ClassCastException e) {
            throw new ReaderException("Cannot open HTTP connection, probably " + this.url + " is not an HTTP URL", e);
        }
    }

    public URLInput(URL url) throws IOException, ReaderException {
        this.url = url;
        checkUrl();
    }

    public String getMimeType() {
        return mimeType;
    }

    public Reader openReader() throws IOException {
        URLConnection conn = url.openConnection();

        logger.info("MimeType: " + this.getMimeType());
        if (STRICT_MIMETYPES.contains(this.getMimeType())){
            conn.setRequestProperty("Accept", this.getMimeType());	
        }

        return new InputStreamReader(conn.getInputStream());
    }

    public String getCleanMimeType(String rawMimeType){
    	if (rawMimeType == null)
    		return null;
        return rawMimeType.split(";")[0];
    }

    public boolean isValidResponseCode(int code){
        if (code>=200 && code<400)
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "URLInput [mimeType=" + mimeType + ", url=" + url + "]";
    }

	public String getBase() {
		return this.url.toString();
	}


}