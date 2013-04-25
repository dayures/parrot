package es.ctic.parrot.de;

import java.util.Collection;

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

 


}
