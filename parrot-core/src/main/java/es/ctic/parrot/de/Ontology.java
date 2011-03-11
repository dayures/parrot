package es.ctic.parrot.de;

import java.util.Collection;

/**
 * An ontology to be documented by Parrot. 
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 * 
 */
public interface Ontology extends DocumentableOntologicalObject {
	
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
	 * Returns the version.
	 * @return the version.
	 */
	public abstract String getVersion();
	
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
	 * Returns the date.
	 * @return the date.
	 */
	public abstract String getDate();
	
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

}
