package es.ctic.parrot.de;

import java.util.Collection;
import java.util.List;

/**
 * A rule set be documented by Parrot. 
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 * 
 */
public interface RuleSet extends DocumentableObject {

    public abstract Collection<DocumentableOntologicalObject> getReferencedOntologicalObjects();
    
	public abstract String getStrategy();
	public abstract Integer getPriority();

	public abstract String getDate();
	public abstract List<String> getCreators();
	public abstract List<String> getContributors();
	public abstract List<String> getPublishers();

	public abstract Collection<Rule> getRules();
	public abstract Collection<RuleSet> getRuleSets();
	
	public abstract DocumentableObject getParent();
    
}
