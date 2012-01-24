package es.ctic.parrot.utils;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * 
 * The class <code>CurieUtils</code> includes methods to obtain CURIEs from URIs.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public final class CurieUtils {

	private static final String DELIMITER = "=";
	private static final String PREFIX_NAMESPACE_FILE_PATH = "prefix/prefix-namespace.txt";
    private static final String REVERSE_LOOKUP_SERVICE_REQUEST_BASE_URL = "http://prefix.cc/reverse?uri=";
    private static final String DEFAULT_URI_ENCODING = "UTF-8";
    private static final String PARROT_USER_AGENT = "Parrot/1.0";    
	private static final Logger logger = Logger.getLogger(CurieUtils.class);
	private static final Map<String, String> prefixMap = loadNamespacePrefixMap();

	/**
	 * Suppress default constructor for noninstantiability.
	 */
	private CurieUtils() {
		throw new AssertionError();
	}
	
    /**
     * Load a Map from a namespace-prefix file (obtained from prefix.cc)
     * @return the Map generated (key=namespace, value=prefix)
     */
	private static Map<String, String> loadNamespacePrefixMap(){
		try {
			return MapUtils.readPropertiesFileAsMap(PREFIX_NAMESPACE_FILE_PATH, DELIMITER);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Returns the <code>CURIE</code> for a given URI or the <code>URI</code> if there is no CURIE. Use cache file from prefix.cc. 
	 * @param uri the URI.
	 * @return the <code>CURIE</code> for a given URI or the <code>URI</code> if there is no CURIE.
	 */
    public static String getCurie(String uri) {
		String mainUri;
		String fragment;

		//logger.debug("uri="+uri);
		
		if (uri.contains("#")){
			String[] uriParts = uri.split("#");
			if (uriParts.length == 2){
				mainUri = uriParts[0]+"#";
				fragment = uriParts[1];
				return getCurieFromMap(mainUri,fragment);
			} else {
				return uri;				
			}
		} else {
			int lastIndexOf = uri.lastIndexOf('/');
			if (lastIndexOf == -1) { // corner case. An URI passed as parameter has no '/' character
				logger.info("Corner case. The URI passed as parameter has no '/' character. URI="+uri);
				return uri;
			} else {
				mainUri = uri.substring(0, lastIndexOf)+"/";
				fragment = uri.substring(lastIndexOf+1);
				return getCurieFromMap(mainUri,fragment);
			}
		}
    }
    
	/**
	 * Returns the <code>CURIE</code> for a given URI or the <code>URI</code> if there is no CURIE. 
	 * @param uri the URI.
	 * @return the <code>CURIE</code> for a given URI or the <code>URI</code> if there is no CURIE.
	 */

    private static String getCurieFromMap(String mainUri, String fragment){
		if (prefixMap.containsKey(mainUri)){
			logger.debug("CURIE=["+prefixMap.get(mainUri) + ":" + fragment+"]");
			return prefixMap.get(mainUri) + ":" + fragment; 
		} else {
			logger.debug("NamespacePrefixMap no contains=" +mainUri);
			return mainUri+fragment;
		}
    }

	/**
	 * Returns the <code>CURIE</code> for a given URI or the <code>URI</code> if there is no CURIE. Use prefix.cc service.
	 * @param uri the URI.
	 * @return the <code>CURIE</code> for a given URI or the <code>URI</code> if there is no CURIE
	 */
    public static String getCurieFromPrefixCc(String uri) {
        Client client = Client.create();
        client.setFollowRedirects(false);
        WebResource webResource = client.resource(getlReverseLookupRequestUrl(uri));
        logger.debug("Requested URI="+getlReverseLookupRequestUrl(uri));
        ClientResponse response = webResource.header("User-Agent", PARROT_USER_AGENT).get(ClientResponse.class);
        if ( (response.getStatus() == HttpURLConnection.HTTP_MOVED_TEMP) && response.getLocation() != null) {
    		String curie = response.getLocation().getPath().substring(1);
    		logger.info("URI '" + uri + "' resolved to [" + curie + "]");
    		return curie;
        } else {
            logger.info("CURIE not found for '" + uri + "'");
            return uri;
        }
    }


    /**
     * Generate a request URL combined the request URL and the URI parameter.
     * @param uriParameter the URI that will be the parameter.
     * @return the request URL.
     */
    private static String getlReverseLookupRequestUrl(String uriParameter) {
        try {
			return REVERSE_LOOKUP_SERVICE_REQUEST_BASE_URL + java.net.URLEncoder.encode(uriParameter,DEFAULT_URI_ENCODING);
		} catch (UnsupportedEncodingException e) {
			return REVERSE_LOOKUP_SERVICE_REQUEST_BASE_URL + uriParameter;
		}
    }

}
