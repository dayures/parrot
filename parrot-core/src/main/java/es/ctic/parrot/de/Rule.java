package es.ctic.parrot.de;

import java.util.Collection;
import java.util.List;

public interface Rule extends DocumentableObject {

    public Collection<DocumentableOntologicalObject> getReferencedOntologicalObjects();

    // public Collection<String> getDeclaredVars();
    
	public String getDate();
	public List<String> getCreators();
	public List<String> getContributors();
	public List<String> getPublishers();
    
}
