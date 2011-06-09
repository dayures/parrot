package es.ctic.parrot.de;

import java.util.Collection;


/**
 * A vocabulary to be documented by Parrot. 
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 * 
 */
public interface Vocabulary extends DocumentableObject {

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
	 * Returns the modified date.
	 * @return the modified date.
	 */
	public abstract String getModifiedDate();
	
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
	
	/**
	 * Returns the creators.
	 * @return the creators.
	 */	
	public abstract Collection<String> getCreators();
	
	/**
	 * Returns the contributors.
	 * @return the contributors.
	 */
	public abstract Collection<String> getContributors();
	
	/**
	 * Returns the publishers.
	 * @return the publishers.
	 */
	public abstract Collection<String> getPublishers();
    
}
