package es.ctic.parrot.de;

import java.util.Collection;

/**
 * A rule to be documented by Parrot. 
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 * 
 */
public interface Rule extends DocumentableObject {

	/**
	 * Returns the references to the documentable ontological elements that reference this rule.
	 * @return the references to the documentable ontological elements that reference this rule.
	 */
    public abstract Collection<DocumentableOntologicalObject> getReferencedOntologicalObjects();

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
	 * Returns the reference to the parent documentable element.
	 * @return the reference to the parent documentable element.
	 */
	public abstract DocumentableObject getParent();
    
}
