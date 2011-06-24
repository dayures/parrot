package es.ctic.parrot.docmodel;

import java.util.Collection;

import es.ctic.parrot.de.Agent;

/**
 * A detailed view of a versionable element. This interface encapsulates different detailed views.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public interface VersionableView {


	/**
	 * Returns the version.
	 * @return the version.
	 */
	public abstract String getVersion();	
	
	/**
	 * Returns the date.
	 * @return the date.
	 */
	public abstract String getDate();

	/**
	 * Returns the modified date.
	 * @return the modified date.
	 */
	public abstract String getModifiedDate();
	
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
	
	/**
	 * Returns information about the ontology rights.
	 * @return information about the ontology rights.
	 */
	public abstract String getRights();
	
	/**
	 * Returns the label for the license of this ontology.
	 * @return the label for the license of this ontology.
	 */
	public abstract String getLicenseLabel();
	
	/**
	 * Returns the reference where this element is defined.
	 * @return the reference where this element is defined.
	 */
	public abstract DocumentableObjectReference getIsDefinedBy();
}
