package es.ctic.parrot.docmodel;

import java.util.Collection;
import java.util.Collections;
import java.util.Locale;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.OntologyProperty;

public class OntologyPropertyDetailView extends AbstractOntologicalObjectDetailView  implements DetailView{
	
    private static final Logger logger = Logger.getLogger(OntologyPropertyDetailView.class);
    
    private OntologyProperty ontologyProperty;

	private DocumentableObjectReference domain;
	private DocumentableObjectReference range;
    private Collection<DocumentableObjectReference> superProperties;
    private Collection<DocumentableObjectReference> subProperties;
    private Collection<DocumentableObjectReference> inverseReferences;
	private Collection<DocumentableObjectReference> equivalentProperties;
	private Collection<DocumentableObjectReference> disjointProperties;
    private int cardinality;
    private DocumentableObjectReference inverseOf;
	
	private OntologyPropertyDetailView(){
        logger.debug("Created " + this.getClass());
	}
	
	public DocumentableObjectReference getDomain() {
		return this.domain;
	}

	public void setDomain(DocumentableObjectReference domain) {
		this.domain = domain;
	}

	public DocumentableObjectReference getRange() {
		return this.range;
	}

	public void setRange(DocumentableObjectReference range) {
		this.range = range;
	}

	public Collection<DocumentableObjectReference> getInverseReferences() {
	    return Collections.unmodifiableCollection(inverseReferences);
	}
	
	public void setInverseReferences(Collection<DocumentableObjectReference> inverseReferences) {
	    this.inverseReferences = inverseReferences;
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
	public void setSuperProperties(Collection<DocumentableObjectReference> collection) {
		this.superProperties = collection;
	}

	/**
	 * @return the superProperties
	 */
	public Collection<DocumentableObjectReference> getSuperProperties() {
		return Collections.unmodifiableCollection(superProperties);
	}

	/**
	 * @param subProperties the subProperties to set
	 */
	public void setSubProperties(Collection<DocumentableObjectReference> subProperties) {
		this.subProperties = subProperties;
	}

	/**
	 * @return the subProperties
	 */
	public Collection<DocumentableObjectReference> getSubProperties() {
		return Collections.unmodifiableCollection(subProperties);
	}

	/**
	 * @param inverseOf the inverseOf to set
	 */
	public void setInverseOf(DocumentableObjectReference inverseOf) {
		this.inverseOf = inverseOf;
	}

	/**
	 * @return the inverseOf
	 */
	public DocumentableObjectReference getInverseOf() {
		return inverseOf;
	}
	
	public boolean isDatatypeProperty(){
		return getOntologyProperty().isDatatypeProperty();
	}

	public boolean isObjectProperty(){
		return getOntologyProperty().isObjectProperty();
	}
	
	public boolean isAnnotationProperty(){
		return getOntologyProperty().isAnnotationProperty();
	}
	
	public boolean isTransitiveProperty() {
		return getOntologyProperty().isTransitiveProperty();
	}

	public boolean isSymmetricProperty() {
		return getOntologyProperty().isSymmetricProperty();
	}

	public boolean isFunctionalProperty() {
		return getOntologyProperty().isFunctionalProperty();
	}
	
	public boolean isInverseFunctionalProperty() {
		return getOntologyProperty().isInverseFunctionalProperty();
	}

	public boolean isReflexiveProperty() {
		return getOntologyProperty().isReflexiveProperty();
	}
	
	public boolean isIrreflexiveProperty() {
		return getOntologyProperty().isIrreflexiveProperty();
	}

	public boolean isAsymmetricProperty() {
		return getOntologyProperty().isAsymmetricProperty();
	}
	
	public boolean hasAnyAxiom(){
		return (isDatatypeProperty() || isObjectProperty() || isAnnotationProperty() || isTransitiveProperty() || isSymmetricProperty() || isFunctionalProperty() || isInverseFunctionalProperty() || isReflexiveProperty() || isIrreflexiveProperty() || isAsymmetricProperty());
	}

	/**
	 * @param equivalentProperties the equivalentProperties to set
	 */
	public void setEquivalentProperties(Collection<DocumentableObjectReference> equivalentProperties) {
		this.equivalentProperties = equivalentProperties;
	}

	/**
	 * @return the equivalentProperties
	 */
	public Collection<DocumentableObjectReference> getEquivalentProperties() {
		return Collections.unmodifiableCollection(equivalentProperties);
	}

	/**
	 * @param disjointProperties the disjointProperties to set
	 */
	public void setDisjointProperties(Collection<DocumentableObjectReference> disjointProperties) {
		this.disjointProperties = disjointProperties;
	}

	/**
	 * @return the disjointProperties
	 */
	public Collection<DocumentableObjectReference> getDisjointProperties() {
		return Collections.unmodifiableCollection(disjointProperties);
	}

	/**
	 * @param ontologyProperty the ontologyProperty to set
	 */
	public void setOntologyProperty(OntologyProperty ontologyProperty) {
		this.ontologyProperty = ontologyProperty;
	}

	/**
	 * @return the ontologyProperty
	 */
	public OntologyProperty getOntologyProperty() {
		return ontologyProperty;
	}

    public static OntologyPropertyDetailView createFromProperty(OntologyProperty object, Locale locale) {
    	
    	OntologyPropertyDetailView details = new OntologyPropertyDetailView();
    	details.setOntologyProperty(object);
		details.setUri(object.getURI());
		details.setLabel(object.getLabel(locale));
		details.setComment(object.getComment(locale));
		details.setDomain(DocumentableObjectReference.createReference(object.getDomain(), locale));
		details.setRange(DocumentableObjectReference.createReference(object.getRange(), locale));
		details.setSuperProperties(DocumentableObjectReference.createReferences(object.getSuperProperties(), locale));
		details.setSubProperties(DocumentableObjectReference.createReferences(object.getSubProperties(), locale));
		details.setEquivalentProperties(DocumentableObjectReference.createReferences(object.getEquivalentProperties(), locale));
		details.setDisjointProperties(DocumentableObjectReference.createReferences(object.getDisjointProperties(), locale));
		details.setInverseRuleReferences(object.getInverseRuleReferences());
		details.setInverseReferences(DocumentableObjectReference.createReferences(object.getInternalReferences(), locale));
		details.setCardinality(object.getCardinality());
		details.setInverseOf(DocumentableObjectReference.createReference(object.getInverseOf(), locale));
		details.setLabels(object.getLabels());
		details.setRelatedDocuments(object.getRelatedDocuments(locale));
		
		details.setAnchor(object.getLocalName());
		details.setIdentifier(object.getIdentifier());

		return details;
    }
}
