package es.ctic.parrot.docmodel;

import java.util.Collection;
import java.util.Collections;

import es.ctic.parrot.de.DocumentableObject;
import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.OntologyProperty;

public class OntologyPropertyDetailView extends AbstractOntologicalObjectDetailView  implements DetailView{
	private OntologyProperty ontologyProperty;
	private DocumentableObject domain;
	private DocumentableObject range;
    private Collection<DocumentableObject> superProperties;
	private Collection<DocumentableObject> inverseReferences;
    private int cardinality;
	
	public OntologyPropertyDetailView(OntologyProperty ontologyProperty){
		this.ontologyProperty=ontologyProperty;
	}
	
	public Identifier getIdentifier(){
		return ontologyProperty.getIdentifier();
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

	/**
	 * @param superProperties the superProperties to set
	 */
	public void setSuperProperties(Collection<DocumentableObject> superProperties) {
		this.superProperties = superProperties;
	}

	/**
	 * @return the superProperties
	 */
	public Collection<DocumentableObject> getSuperProperties() {
		return Collections.unmodifiableCollection(superProperties);
	}
	
}
