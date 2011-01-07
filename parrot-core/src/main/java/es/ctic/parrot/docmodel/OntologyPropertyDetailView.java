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
    private Collection<OntologyProperty> superProperties;
    private Collection<OntologyProperty> subProperties;
    private Collection<DocumentableObject> inverseReferences;
    private int cardinality;
    private String type;
    private DocumentableObject inverseOf;
	
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
	 * @param collection the superProperties to set
	 */
	public void setSuperProperties(Collection<OntologyProperty> collection) {
		this.superProperties = collection;
	}

	/**
	 * @return the superProperties
	 */
	public Collection<OntologyProperty> getSuperProperties() {
		return Collections.unmodifiableCollection(superProperties);
	}

	/**
	 * @param subProperties the subProperties to set
	 */
	public void setSubProperties(Collection<OntologyProperty> subProperties) {
		this.subProperties = subProperties;
	}

	/**
	 * @return the subProperties
	 */
	public Collection<OntologyProperty> getSubProperties() {
		return Collections.unmodifiableCollection(subProperties);
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param inverseOf the inverseOf to set
	 */
	public void setInverseOf(DocumentableObject inverseOf) {
		this.inverseOf = inverseOf;
	}

	/**
	 * @return the inverseOf
	 */
	public DocumentableObject getInverseOf() {
		return inverseOf;
	}
	
}
