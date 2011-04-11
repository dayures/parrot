package es.ctic.parrot.de;

import java.util.Collection;

/**
 * An ontological element to be documented by Parrot. This interface encapsulates
 * different ontological documentable elements.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 * 
 */
public interface DocumentableOntologicalObject extends DocumentableObject,Comparable<DocumentableOntologicalObject>{

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
	 * Returns the reference where this ontological element is defined.
	 * @return the reference where this ontological element is defined.
	 */
	public abstract DocumentableObject getIsDefinedBy();
	
	/**
     * Returns <code>true</code> if, and only if, this ontological element is deprecated.
     * @return <code>true</code> if this ontological element is deprecated, otherwise <code>false</code>.
     */
	public abstract boolean isDeprecated();
	
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
	
}
