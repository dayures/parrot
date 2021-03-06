package es.ctic.parrot.web;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.apache.log4j.Logger;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Client to use <a href="http://prefix.cc">prefix.cc</a> service.
 * 
 * @author Carlos Tejo Alonso (<a href="http://www.fundacionctic.org">Fundación CTIC</a>)
 * @version 1.0
 * @since 1.0
 *
 */
public class PrefixCCClient {
    
    private static final String SERVICE_REQUEST_URL_SUFFIX = ".file.txt";
    private static final String SERVICE_REQUEST_BASE_URL = "http://prefix.cc/";
    private static final String PARROT_USER_AGENT = "Parrot/1.0";    
    private static final Logger logger = Logger.getLogger(PrefixCCClient.class);
    
    public String resolvePrefix(String prefix) throws IOException {
        Client client = Client.create();
        WebResource webResource = client.resource(getRequestUrl(prefix));
        ClientResponse response = webResource.accept("text/plain").header("User-Agent", PARROT_USER_AGENT).get(ClientResponse.class);
        if (response.getStatus() == HttpURLConnection.HTTP_OK) {
            String fullUri = parseResponse(response.getEntity(String.class));
            logger.info("Prefix '" + prefix + "' resolved to <" + fullUri + ">");
            return fullUri;
        } else { // not found
            logger.info("Prefix expansion not found for '" + prefix + "'");
            return null;
        }
    }

    private String getRequestUrl(String prefix) {
        return SERVICE_REQUEST_BASE_URL + prefix + SERVICE_REQUEST_URL_SUFFIX;
    }

    private String parseResponse(String response) {
        String firstLine = response.split("\n")[0];
        String fullUri = firstLine.substring(firstLine.indexOf("\t") + 1);
        return fullUri;
    }

}
