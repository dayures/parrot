package es.ctic.parrot.de;


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

}
