package es.ctic.parrot.de;

import java.util.Collection;

/**
 * A rule to be documented by Parrot. 
 * 
 * @author Carlos Tejo Alonso (<a href="http://www.fundacionctic.org">Fundación CTIC</a>)
 * @version 1.0
 * @since 1.0
 * 
 */
public interface Rule extends DocumentableObject, Versionable {

	/**
	 * Returns the references to the documentable ontological elements that reference this rule.
	 * @return the references to the documentable ontological elements that reference this rule.
	 */
    public abstract Collection<DocumentableOntologicalObject> getReferencedOntologicalObjects();

	/**
	 * Returns the reference to the parent documentable element.
	 * @return the reference to the parent documentable element.
	 */
	public abstract DocumentableObject getParent();
	
}
