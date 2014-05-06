package es.ctic.parrot.de;

import java.util.Collection;

/**
 * A rule set be documented by Parrot. 
 * 
 * @author Carlos Tejo Alonso (<a href="http://www.fundacionctic.org">Fundación CTIC</a>)
 * @version 1.0
 * @since 1.0
 * 
 */
public interface RuleSet extends DocumentableObject, Versionable {

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
	
	/**
	 * Returns the strategy of this rule set.
	 * @return the strategy of this rule set.
	 */
	public abstract String getStrategy();
	
	/**
	 * Returns the priority of this rule set.
	 * @return the priority of this rule set.
	 */
	public abstract Integer getPriority();

	/**
	 * Returns the rules of this rule set.
	 * @return the rules of this rule set.
	 */
	public abstract Collection<Rule> getRules();
	
	/**
	 * Returns the rule sets of this rule set.
	 * @return the rule sets of this rule set.
	 */
	public abstract Collection<RuleSet> getRuleSets();

}
