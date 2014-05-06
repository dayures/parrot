package es.ctic.parrot.de;


/**
 * A distribution of a dataset. 
 * 
 * @author Carlos Tejo Alonso (<a href="http://www.fundacionctic.org">Fundación CTIC</a>)
 * @version 1.0
 * @since 1.0
 * 
 */
public interface Distribution extends DocumentableObject, Versionable{
  
	
	/**
	 * Returns the access URL.
	 * @return the access URL.
	 */
	public abstract String getAccessURL();
	
	/**
	 * Returns the download URL.
	 * @return the download URL.
	 */
	public abstract String getDownloadURL();
	
	/**
	 * Returns the byte size.
	 * @return the byte size.
	 */
	public abstract String getByteSize();
	
	/**
	 * Returns the MIME type.
	 * @return the MIME type.
	 */
	public abstract MIMEType getMIMEType();
}
