package es.ctic.parrot.utils;

/**
 * 
 * The class <code>URIUtils</code> includes methods for extracting information from URIs.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public final class URIUtils {

	/**
	 * Suppress default constructor for noninstantiability.
	 */
	private URIUtils() {
		throw new AssertionError();
	}
	
	/**
	 * Returns the namespace of a given URI.
	 * @param uri an URI.
	 * @return the namespace of a given URI.
	 */
	public static String getNamespace(String uri) {
		String _uri = uri.trim();
		if (_uri.contains("#")) {
			//hash namespace
			return _uri.split("#")[0] + "#";
		} else {
			//slash namespace
			int index = _uri.lastIndexOf('/');
			return _uri.substring(0, index+1);
		}
	}

	/**
	 * Returns the localname of a given URI.
	 * @param uri an URI.
	 * @return the localname of a given URI or null if the URI is null.
	 */
	public static String getReference(String uri) {

		if (uri == null){
			return null;
		}

		String _uri = uri.trim();
		if (_uri.contains("#")) {
			//hash namespace
			return (_uri.split("#").length < 2)? _uri.split("#")[0]: _uri.split("#")[1];
		} else {
			//slash namespace
			int index = _uri.lastIndexOf('/');
			if (index+1 == _uri.length()){
				return _uri;
			}
			else {
				return _uri.substring(index+1);
			}
		}
	}

}
