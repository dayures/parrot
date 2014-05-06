package es.ctic.parrot.de;

import java.util.Collection;

/**
 * A dataset. 
 * 
 * @author Carlos Tejo Alonso (<a href="http://www.fundacionctic.org">Fundación CTIC</a>)
 * @version 1.0
 * @since 1.0
 * 
 */
public interface Dataset extends DocumentableObject, Versionable{
	
	/**
	 * Returns the data dump URL.
	 * @return the data dump URL.
	 */
	public abstract String getdataDump();
	
	/**
	 * Returns the SPARQL endpoint URL.
	 * @return the SPARQL endpoint URL.
	 */
	public abstract String getSparqlEndpoint();
	
	/**
	 * Returns the homepage.
	 * @return the homepage.
	 */
	public abstract String getHomepage();

	/**
	 * Returns the collection of vocabularies (represented by its URI) used in the dataset.
	 * @return the collection of vocabularies (represented by its URI) used in the dataset.
	 */
	public abstract Collection<String> getVocabularies();
	
	/**
	 * Returns the Dublin Core identifier.
	 * @return the Dublin Core identifier.
	 */
	public abstract String getDcIdentifier();
	
	/**
	 * Returns the landing page.
	 * @return the landing page.
	 */
	public abstract String getLandingPage();
	
	/**
	 * Returns the collection of keywords of the dataset.
	 * @return the collection of keywords of the dataset.
	 */
	public abstract Collection<String> getKeywords();

	/**
	 * Returns the catalogs.
	 * @return the catalogs.
	 */	
    public abstract Collection<DocumentableObject> getCatalogs();
    
	/**
	 * Returns the spatial coverage.
	 * @return the spatial coverage.
	 */
	public abstract String getSpatial();

	/**
	 * Returns the distributions.
	 * @return the distributions.
	 */	
	public abstract Collection<Distribution> getDistributions();

	/**
	 * Returns the language.
	 * @return the language.
	 */
	public abstract String getLanguage();
	
	/**
	 * Returns the collection of pages of the dataset.
	 * @return the collection of pages of the dataset.
	 */
	public abstract Collection<String> getPages();

}
