package es.ctic.parrot.de;

import java.util.Collection;

/**
 * A catalog. 
 * 
 * @author Carlos Tejo Alonso (<a href="http://www.fundacionctic.org">Fundación CTIC</a>)
 * @version 1.0
 * @since 1.0
 * 
 */
public interface Catalog extends DocumentableObject, Versionable{
	
	/**
	 * Returns the homepage.
	 * @return the homepage.
	 */
	public abstract String getHomepage();
	
	/**
	 * Returns the datasets.
	 * @return the datasets.
	 */	
    public abstract Collection<DocumentableObject> getDatasets();
    
	/**
	 * Returns the spatial coverage.
	 * @return the spatial coverage.
	 */
	public abstract String getSpatial();

	/**
	 * Returns the language.
	 * @return the language.
	 */
	public abstract String getLanguage();

}
