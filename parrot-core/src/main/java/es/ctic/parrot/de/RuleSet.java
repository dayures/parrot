package es.ctic.parrot.de;

import java.util.Collection;
import java.util.List;

public interface RuleSet extends DocumentableObject {

    public Collection<DocumentableOntologicalObject> getReferencedOntologicalObjects();
    
	public String getStrategy();
	public Integer getPriority();

	public String getDate();
	public List<String> getCreators();
	public List<String> getContributors();
	public List<String> getPublishers();

	public Collection<Rule> getRules();
    
}