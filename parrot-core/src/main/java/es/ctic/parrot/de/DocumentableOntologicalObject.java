package es.ctic.parrot.de;

import java.util.Collection;

/**
 * An ontological element to be documented by Parrot. This interface encapsulates
 * different ontological documentable elements.
 * 
 * @author Carlos Tejo Alonso (<a href="http://www.fundacionctic.org">Fundación CTIC</a>)
 * @version 1.0
 * @since 1.0
 * 
 */
public interface DocumentableOntologicalObject extends DocumentableObject,Comparable<DocumentableOntologicalObject>, Versionable{

	/**
	 * Sets a reference to a rule that use this ontological element.
	 * @param rule reference to a rule that use this ontological element.
	 */
	public abstract void addInverseRuleReference(Rule rule);
	
	/**
	 * Returns the rules that reference this ontological element.
	 * @return the rules that reference this ontological element.
	 */
	public abstract Collection<Rule> getInverseRuleReferences();
	
	/**
     * Returns <code>true</code> if, and only if, this ontological element is deprecated.
     * @return <code>true</code> if this ontological element is deprecated, otherwise <code>false</code>.
     */
	public abstract boolean isDeprecated();
	
	/**
	 * Returns the status.
	 * @return the status.
	 */
	public abstract String getStatus();
	
}
