package es.ctic.parrot.docmodel;

import java.util.List;

import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.Ontology;

public class OntologyDetailView implements DetailView{
	private Ontology ontology;
	private String uri;
	private String label;
	private String comment;
	private String version;
	private List<String> editors;
	private List<String> contributors;
	private String preferredPrefix;
	
	public OntologyDetailView(Ontology ontology){
		this.ontology=ontology;
	}
	
	public String getUri(){
		return uri;
	}
	
	public void setUri(String uri){
		this.uri=uri;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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
	
}
