package es.ctic.parrot.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * A namespace manager than can provide compact URIs for URIs (this class is NOT used yet). 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class NamespaceManager {
	
	private Map<String,String> namespaces;
	
	/**
	 * Constructs a namespace manager
	 */
	public NamespaceManager(){
		namespaces=new HashMap<String, String>();
	}
	
	/**
	 * Get the compact URI (for instance <code>foaf:name</code>) from a given URI. 
	 * @param uri the URI.
	 * @return the compact URI.
	 */
	public String getCompactUri(String uri){
		String base=URIUtils.getNamespace(uri);
		String localname=URIUtils.getReference(uri);
		base=namespaces.get(base);
		if(base!=null)
			return base+":"+localname;
		return uri;
	}
	
	/**
	 * Adds a base and a prefix into the namespace manager. 
	 * @param base the base.
	 * @param prefix the prefix.
	 */
	public void addPrefix(String base, String prefix){
		namespaces.put(base, prefix);
	}
	
	/**
	 * Set a namespace manager.
	 * @param ns a namespace manager.
	 */
	public void setNamespaces(Map<String,String> ns){
		namespaces=ns;
	}
}
