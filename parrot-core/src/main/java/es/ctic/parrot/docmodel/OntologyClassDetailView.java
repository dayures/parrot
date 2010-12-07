package es.ctic.parrot.docmodel;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import es.ctic.parrot.de.DocumentableObject;
import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.OntologyClass;

public class OntologyClassDetailView implements DetailView{
	private OntologyClass ontologyClass;
	private String uri;
	private String label;
	private String comment;
	private List<OntologyClass> superClasses;
	private List<OntologyClass> subClasses;
	private Collection<DocumentableObject> inverseReferences;
	
	public OntologyClassDetailView(OntologyClass ontologyClass){
		this.ontologyClass=ontologyClass;
		this.superClasses=new LinkedList<OntologyClass>();
		this.subClasses=new LinkedList<OntologyClass>();
		this.inverseReferences=new LinkedList<DocumentableObject>();
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
	
	public Identifier getIdentifier(){
		return ontologyClass.getIdentifier();
	}

	public List<OntologyClass> getSuperClasses() {
		return Collections.unmodifiableList(this.superClasses);
	}

	public void addSuperClasses(OntologyClass superClasses) {
		this.superClasses.add(superClasses);
	}

	public List<OntologyClass> getSubClasses() {
		return Collections.unmodifiableList(this.subClasses);
	}

	public void addSubClasses(OntologyClass subClasses) {
		this.subClasses.add(subClasses);
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setInverseReferences(Collection<DocumentableObject> inverseReferences) {
		this.inverseReferences = inverseReferences;
	}

	public Collection<DocumentableObject> getInverseReferences() {
		return inverseReferences;
	}

	public String getAnchor() {
		return ontologyClass.getLocalName();
	}
	
	
}
