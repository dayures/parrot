package es.ctic.parrot.docmodel;

import java.util.Collection;
import java.util.Collections;
import java.util.Locale;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.OntologyProperty;

/**
 * A detailed view of a ontology property.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
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
    private int occurrences;
    private DocumentableObjectReference inverseOf;
	
	/**
	 * Constructs a ontology property detail view (Suppress default constructor for noninstantiability).
	 */
	private OntologyPropertyDetailView(){
        logger.debug("Created " + this.getClass());
	}
	
	/**
	 * Returns the reference to the domain class of this property.
	 * @return the reference to the domain class of this property.
	 */
	public DocumentableObjectReference getDomain() {
		return this.domain;
	}

	/**
	 * Set the domain class of this property.
	 * @param domain the domain class of this property.
	 */
	private void setDomain(DocumentableObjectReference domain) {
		this.domain = domain;
	}

	/**
	 * Returns the reference to the range class of this property.
	 * @return the reference to the range class of this property.
	 */
	public DocumentableObjectReference getRange() {
		return this.range;
	}

	/**
	 * Set the range class of this property.
	 * @param range the range class of this property.
	 */
	private void setRange(DocumentableObjectReference range) {
		this.range = range;
	}

	/**
	 * Returns the references from other document elements to this property.
	 * @return the references from other document elements to this property.
	 */
	public Collection<DocumentableObjectReference> getInverseReferences() {
	    return Collections.unmodifiableCollection(inverseReferences);
	}
	
	/**
	 * Set the references from other document elements to this property.
	 * @param inverseReferences the references from other document elements to this property.
	 */
	private void setInverseReferences(Collection<DocumentableObjectReference> inverseReferences) {
	    this.inverseReferences = inverseReferences;
	}

	/**
	 * Set the number of times that this property is used.
	 * @param occurrences the number of times that this property is used.
	 */
	private void setOccurrences(int occurrences) {
		this.occurrences = occurrences;
	}

	/**
	 * Returns the number of times that this property is used.
	 * @return the number of times that this property is used.
	 */
	public int getOccurrences() {
		return occurrences;
	}

	/**
	 * Set the super properties of this property.
	 * @param superProperties the super properties to set.
	 */
	private void setSuperProperties(Collection<DocumentableObjectReference> superProperties) {
		this.superProperties = superProperties;
	}

	/**
	 * Returns the super properties of this property.
	 * @return the super properties of this property.
	 */
	public Collection<DocumentableObjectReference> getSuperProperties() {
		return Collections.unmodifiableCollection(superProperties);
	}

	/**
	 * Set the sub properties of this property.
	 * @param subProperties the sub properties to set.
	 */
	private void setSubProperties(Collection<DocumentableObjectReference> subProperties) {
		this.subProperties = subProperties;
	}

	/**
	 * Returns the sub properties of this property.
	 * @return the sub properties of this property.
	 */
	public Collection<DocumentableObjectReference> getSubProperties() {
		return Collections.unmodifiableCollection(subProperties);
	}

	/**
	 * Set the inverse property of this property.
	 * @param inverseOf the inverse property to set.
	 */
	private void setInverseOf(DocumentableObjectReference inverseOf) {
		this.inverseOf = inverseOf;
	}

	/**
	 * Returns the inverse property of this property.
	 * @return the inverse property of this property.
	 */
	public DocumentableObjectReference getInverseOf() {
		return inverseOf;
	}
	
    /**
     * Returns <code>true</code> if, and only if, this property is a datatype property.
     * @return <code>true</code> if this property is a datatype property, otherwise <code>false</code>.
     */
	public boolean isDatatypeProperty(){
		return getOntologyProperty().isDatatypeProperty();
	}

    /**
     * Returns <code>true</code> if, and only if, this property is a object property.
     * @return <code>true</code> if this property is a object property, otherwise <code>false</code>.
     */
	public boolean isObjectProperty(){
		return getOntologyProperty().isObjectProperty();
	}
	
    /**
     * Returns <code>true</code> if, and only if, this property is an annotation property.
     * @return <code>true</code> if this property is an annotation property, otherwise <code>false</code>.
     */
	public boolean isAnnotationProperty(){
		return getOntologyProperty().isAnnotationProperty();
	}

    /**
     * Returns <code>true</code> if, and only if, this property is a transitive property.
     * @return <code>true</code> if this property is a transitive property, otherwise <code>false</code>.
     */
	public boolean isTransitiveProperty() {
		return getOntologyProperty().isTransitiveProperty();
	}

	/**
     * Returns <code>true</code> if, and only if, this property is a symmetric property.
     * @return <code>true</code> if this property is a symmetric property, otherwise <code>false</code>.
     */
	public boolean isSymmetricProperty() {
		return getOntologyProperty().isSymmetricProperty();
	}

    /**
     * Returns <code>true</code> if, and only if, this property is a functional property.
     * @return <code>true</code> if this property is a functional property, otherwise <code>false</code>.
     */
	public boolean isFunctionalProperty() {
		return getOntologyProperty().isFunctionalProperty();
	}

	/**
     * Returns <code>true</code> if, and only if, this property is an inverse functional property.
     * @return <code>true</code> if this property is an inverse functional property, otherwise <code>false</code>.
     */
	public boolean isInverseFunctionalProperty() {
		return getOntologyProperty().isInverseFunctionalProperty();
	}

    /**
     * Returns <code>true</code> if, and only if, this property is a reflexive property.
     * @return <code>true</code> if this property is a reflexive property, otherwise <code>false</code>.
     */
	public boolean isReflexiveProperty() {
		return getOntologyProperty().isReflexiveProperty();
	}

    /**
     * Returns <code>true</code> if, and only if, this property is an irreflexive property.
     * @return <code>true</code> if this property is an irreflexive property, otherwise <code>false</code>.
     */
	public boolean isIrreflexiveProperty() {
		return getOntologyProperty().isIrreflexiveProperty();
	}

    /**
     * Returns <code>true</code> if, and only if, this property is an asymmetric property.
     * @return <code>true</code> if this property is an asymmetric property, otherwise <code>false</code>.
     */
	public boolean isAsymmetricProperty() {
		return getOntologyProperty().isAsymmetricProperty();
	}
	
    /**
     * Returns <code>true</code> if, and only if, this property has any axiom.
     * @return <code>true</code> if this property has any axiom, otherwise <code>false</code>.
     */
	public boolean hasAnyAxiom(){
		return (isDatatypeProperty() || isObjectProperty() || isAnnotationProperty() || isTransitiveProperty() || isSymmetricProperty() || isFunctionalProperty() || isInverseFunctionalProperty() || isReflexiveProperty() || isIrreflexiveProperty() || isAsymmetricProperty());
	}

	/**
	 * Set the collection of equivalent properties.
	 * @param equivalentProperties the collection of equivalent properties to set.
	 */
	private void setEquivalentProperties(Collection<DocumentableObjectReference> equivalentProperties) {
		this.equivalentProperties = equivalentProperties;
	}

	/**
	 * Returns the collection of equivalent properties of this property.
	 * @return the collection of equivalent properties of this property.
	 */
	public Collection<DocumentableObjectReference> getEquivalentProperties() {
		return Collections.unmodifiableCollection(equivalentProperties);
	}

	/**
	 * Set the collection of disjoint properties.
	 * @param disjointProperties the collection of disjoint properties to set.
	 */
	private void setDisjointProperties(Collection<DocumentableObjectReference> disjointProperties) {
		this.disjointProperties = disjointProperties;
	}

	/**
	 * Returns the collection of disjoint properties of this property.
	 * @return the collection of disjoint properties of this property.
	 */
	public Collection<DocumentableObjectReference> getDisjointProperties() {
		return Collections.unmodifiableCollection(disjointProperties);
	}

	/**
	 * Set the ontology property (documentable element).
	 * @param ontologyProperty the ontologyProperty to set.
	 */
	private void setOntologyProperty(OntologyProperty ontologyProperty) {
		this.ontologyProperty = ontologyProperty;
	}

	/**
	 * Returns the ontology property.
	 * @return the ontology property.
	 */
	public OntologyProperty getOntologyProperty() {
		return ontologyProperty;
	}

	/**
	 * Returns a detailed view for the ontology property given.
	 * @param object the ontology property.
	 * @param locale the locale.
	 * @return a detailed view for an ontology property.
	 */	
    public static OntologyPropertyDetailView createFromProperty(OntologyProperty object, Locale locale) {
    	
    	OntologyPropertyDetailView details = new OntologyPropertyDetailView();
    	
    	details.setOntologyProperty(object);
		details.setUri(object.getURI());
		details.setUriFragment(object.getUriFragment());
		details.setLabel(object.getLabel(locale));
		details.setDescription(object.getDescription(locale));
		
		// Control version information
		details.setVersion(object.getVersion());
		details.setDate(object.getDate());
		
		details.setCreators(object.getCreators());
		details.setContributors(object.getContributors());
		details.setPublishers(object.getPublishers());
		
		details.setCreatorAgents(object.getCreatorAgents());
		details.setContributorAgents(object.getContributorAgents());
		details.setPublisherAgents(object.getPublisherAgents());

		details.setRights(object.getRights());
		details.setLicenseLabel(object.getLicenseLabel());
		
		details.setDomain(DocumentableObjectReference.createReference(object.getDomain(), locale));
		details.setRange(DocumentableObjectReference.createReference(object.getRange(), locale));
		details.setSuperProperties(DocumentableObjectReference.createReferences(object.getSuperProperties(), locale));
		details.setSubProperties(DocumentableObjectReference.createReferences(object.getSubProperties(), locale));
		details.setEquivalentProperties(DocumentableObjectReference.createReferences(object.getEquivalentProperties(), locale));
		details.setDisjointProperties(DocumentableObjectReference.createReferences(object.getDisjointProperties(), locale));
		details.setInverseRuleReferences(DocumentableObjectReference.createReferences(object.getInverseRuleReferences(), locale));
		details.setInverseReferences(DocumentableObjectReference.createReferences(object.getInternalReferences(), locale));
		details.setOccurrences(object.getOccurrences());
		details.setInverseOf(DocumentableObjectReference.createReference(object.getInverseOf(), locale));

		details.setLexicalInformation(LexicalInformation.createFromLabels(object.getLabels(), details.getLabel(), locale));
		details.setRelatedDocuments(object.getRelatedDocuments(locale));
		
		details.setAnchor(object.getLocalName());
		details.setIdentifier(object.getIdentifier());
		details.setIsDefinedBy(DocumentableObjectReference.createReference(object.getIsDefinedBy(),locale));
		details.setDeprecated(object.isDeprecated());


		
		return details;
    }
}
