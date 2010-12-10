package es.ctic.parrot.docmodel;

import java.util.Collection;
import java.util.Collections;

import es.ctic.parrot.de.DocumentableObject;
import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.OntologyProperty;

public class OntologyPropertyDetailView implements DetailView{
	private OntologyProperty ontologyProperty;
	private String uri;
	private String label;
	private String comment;
	private DocumentableObject domain;
	private DocumentableObject range;
    private Collection<DocumentableObject> inverseReferences;
    private int cardinality;
	
	public OntologyPropertyDetailView(OntologyProperty ontologyProperty){
		this.ontologyProperty=ontologyProperty;
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
		return ontologyProperty.getIdentifier();
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public DocumentableObject getDomain() {
		return this.domain;
	}

	public void setDomain(DocumentableObject domain) {
		this.domain = domain;
	}

	public DocumentableObject getRange() {
		return this.range;
	}

	public void setRange(DocumentableObject range) {
		this.range = range;
	}

	public Collection<DocumentableObject> getInverseReferences() {
	    return Collections.unmodifiableCollection(inverseReferences);
	}
	
	public void setInverseReferences(Collection<DocumentableObject> inverseReferences) {
	    this.inverseReferences = inverseReferences;
	}

	public String getAnchor() {
		return ontologyProperty.getLocalName();
	}

	public void setCardinality(int cardinality) {
		this.cardinality = cardinality;
	}

	public int getCardinality() {
		return cardinality;
	}
	
}
