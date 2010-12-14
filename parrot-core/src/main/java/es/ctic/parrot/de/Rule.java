package es.ctic.parrot.de;

import java.util.Collection;
import java.util.List;

public interface Rule extends DocumentableOntologicalObject {

    public Collection<OntologyProperty> getReferencedOntologyProperties();

    public Collection<String> getDeclaredVars();
    
	public String getDate();
	public List<String> getEditors();
	public List<String> getContributors();
	public List<String> getPublishers();
    
}
