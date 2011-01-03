package es.ctic.parrot.de;

import java.util.Collection;
import java.util.List;

public interface Rule extends DocumentableObject {

    public abstract Collection<DocumentableOntologicalObject> getReferencedOntologicalObjects();

    // public Collection<String> getDeclaredVars();
    
	public abstract String getDate();
	public abstract List<String> getCreators();
	public abstract List<String> getContributors();
	public abstract List<String> getPublishers();
	
	public abstract DocumentableObject getParent();
    
}
