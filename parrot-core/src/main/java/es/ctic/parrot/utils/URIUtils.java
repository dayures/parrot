package es.ctic.parrot.utils;

public final class URIUtils {
	
	private URIUtils() {
		
	}
	
	public static String getNamespace(String namespace) {
		String uri = namespace.trim();
		if (uri.contains("#")) {
			//hash namespace
			return uri.split("#")[0] + "#";
		} else {
			//slash namespace
			int index = uri.lastIndexOf('/');
			return uri.substring(0, index+1);
		}
	}

	public static String getReference(String namespace) {
		String uri = namespace.trim();
		if (uri.contains("#")) {
			//hash namespace
			return uri.split("#")[1];
		} else {
			//slash namespace
			int index = uri.lastIndexOf('/');
			return uri.substring(index+1);
		}
	}
	
}
