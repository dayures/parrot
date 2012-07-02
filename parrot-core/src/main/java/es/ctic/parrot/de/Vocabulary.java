package es.ctic.parrot.de;

/**
 * A vocabulary to be documented by Parrot. 
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 * 
 */
public interface Vocabulary extends DocumentableObject, Versionable {

	/**
	 * Returns the preferred prefix.
	 * @return the preferred prefix.
	 */
	public abstract String getPreferredPrefix();
	
	/**
	 * Returns the preferred namespace.
	 * @return the preferred namespace.
	 */
	public abstract String getPreferredNamespace();
	
	/**
	 * Returns the number of classes.
	 * @return the number of classes.
	 */
	public abstract String getClassNumber();

	/**
	 * Returns the number of properties.
	 * @return the number of properties.
	 */
	public abstract String getPropertyNumber();

	/**
	 * Returns the homepage.
	 * @return the homepage.
	 */
	public abstract String getHomepage();
    
}
