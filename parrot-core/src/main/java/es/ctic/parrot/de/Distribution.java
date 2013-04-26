package es.ctic.parrot.de;


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
}
