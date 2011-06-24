package es.ctic.parrot.de;

import java.util.Collection;

import es.ctic.parrot.docmodel.DocumentableObjectReference;

/**
 * The interface of elements that have version control information.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 * 
 */
public interface Versionable {

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
	 * Returns the creators.
	 * @return the creators.
	 */	
	public abstract Collection<String> getCreators();
	
	/**
	 * Returns the creator agents.
	 * @return the creator agents.
	 */
	public abstract Collection<Agent> getCreatorAgents();
	
	/**
	 * Returns the contributors.
	 * @return the contributors.
	 */
	public abstract Collection<String> getContributors();
	
	/**
	 * Returns the contributor agents.
	 * @return the contributor agents.
	 */
	public abstract Collection<Agent> getContributorAgents();
	
	/**
	 * Returns the publishers.
	 * @return the publishers.
	 */
	public abstract Collection<String> getPublishers();
	
	/**
	 * Returns the publisher agents.
	 * @return the publisher agents.
	 */
	public abstract Collection<Agent> getPublisherAgents();
	
	/**
	 * Returns information about the element rights.
	 * @return information about the element rights.
	 */
	public abstract String getRights();
	
	/**
	 * Returns the label for the license of this element.
	 * @return the label for the license of this element.
	 */
	public abstract String getLicenseLabel();
	
	/**
	 * Returns the reference where this element is defined.
	 * @return the reference where this element is defined.
	 */
	public abstract DocumentableObject getIsDefinedBy();
	
}
