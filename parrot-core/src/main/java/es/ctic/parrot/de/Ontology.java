package es.ctic.parrot.de;

import java.util.List;

public interface Ontology extends DocumentableOntologicalObject {
	
	public String getPreferredPrefix();
	public String getVersion();
	public List<String> getEditors();
	public List<String> getContributors();

}
