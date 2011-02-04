package es.ctic.parrot.docmodel;

import java.util.List;

import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.Ontology;

public class OntologyDetailView extends AbstractOntologicalObjectDetailView implements DetailView{
	private Ontology ontology;
	private String version;
	private List<String> editors;
	private List<String> contributors;
	private String preferredPrefix;
	private String date;
	
	public OntologyDetailView(Ontology ontology){
		this.ontology=ontology;
	}
	
	public Identifier getIdentifier(){
		return ontology.getIdentifier();
	}

	public String getAnchor() {
		return ontology.getLocalName();
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVersion() {
		return version;
	}

	public void setEditors(List<String> editors) {
		this.editors = editors;
	}

	public List<String> getEditors() {
		return editors;
	}

	public void setPreferredPrefix(String preferredPrefix) {
		this.preferredPrefix = preferredPrefix;
	}

	public String getPreferredPrefix() {
		return preferredPrefix;
	}

	public void setContributors(List<String> contributors) {
		this.contributors = contributors;
	}

	public List<String> getContributors() {
		return contributors;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}
}
