package es.ctic.parrot.de;

import java.util.List;

public interface Ontology extends DocumentableOntologicalObject {
	
	public abstract String getPreferredPrefix();
	public abstract String getVersion();
	public abstract List<String> getEditors();
	public abstract List<String> getContributors();

}
