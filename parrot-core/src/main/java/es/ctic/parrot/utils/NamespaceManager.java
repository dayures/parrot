package es.ctic.parrot.utils;

import java.util.HashMap;
import java.util.Map;

public class NamespaceManager {
	private Map<String,String> namespaces;
	
	public NamespaceManager(){
		namespaces=new HashMap<String, String>();
	}
	
	public String getUriAbbrev(String uri){
		String base=URIUtils.getNamespace(uri);
		String localname=URIUtils.getReference(uri);
		base=namespaces.get(base);
		if(base!=null)
			return base+":"+localname;
		return uri;
	}
	
	public void addPrefix(String base, String prefix){
		namespaces.put(base, prefix);
	}
	
	public void setNamespaces(Map<String,String> ns){
		namespaces=ns;
	}
}
