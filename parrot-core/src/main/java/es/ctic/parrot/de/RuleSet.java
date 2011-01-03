package es.ctic.parrot.de;

import java.util.Collection;
import java.util.List;

public interface RuleSet extends DocumentableObject {

    public abstract Collection<DocumentableOntologicalObject> getReferencedOntologicalObjects();
    
	public abstract String getStrategy();
	public abstract Integer getPriority();

	public abstract String getDate();
	public abstract List<String> getCreators();
	public abstract List<String> getContributors();
	public abstract List<String> getPublishers();

	public abstract Collection<Rule> getRules();
	public abstract DocumentableObject getParent();
    
}
