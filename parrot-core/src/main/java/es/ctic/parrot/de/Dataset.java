package es.ctic.parrot.de;

import java.util.Collection;

public interface Dataset extends DocumentableObject {
	
	/**
	 * Returns the date.
	 * @return the date.
	 */
	public abstract String getDate();
	
	/**
	 * Returns the label for the license of this element.
	 * @return the label for the license of this element.
	 */
	public abstract String getLicenseLabel();
	
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

	/**
	 * Returns the creator agents.
	 * @return the creator agents.
	 */
	public abstract Collection<Agent> getCreatorAgents();
	
	/**
	 * Returns the contributor agents.
	 * @return the contributor agents.
	 */
	public abstract Collection<Agent> getContributorAgents();
	
	/**
	 * Returns the publisher agents.
	 * @return the publisher agents.
	 */
	public abstract Collection<Agent> getPublisherAgents();
}
