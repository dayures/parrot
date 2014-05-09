package es.ctic.parrot.de;

import java.util.Collection;
import java.util.Locale;

import org.apache.log4j.Logger;

import es.ctic.parrot.transformers.DocumentableObjectVisitor;
import es.ctic.parrot.transformers.TransformerException;

/**
 * An external element to be documented by Parrot. 
 * 
 * @author Carlos Tejo Alonso (<a href="http://www.fundacionctic.org">Fundación CTIC</a>)
 * @version 1.0
 * @since 1.0
 * 
 */

public class ExternalDocumentableObject extends AbstractDocumentableObject implements OntologyProperty, OntologyClass, DocumentableOntologicalObject{

	private static final Logger logger = Logger.getLogger(ExternalDocumentableObject.class);
	private NamedIdentifier identifier; 
    
	/**
     * Constructs an undefined ontology element for the given NamedIdentifier
     * @param identifier the named identifier.
     */
	public ExternalDocumentableObject(NamedIdentifier identifier) {
		super();
		this.identifier = identifier;
	}

	
	/**
     * Constructs an undefined ontology element for the given URI
     * @param uri the URI.
     */
	public ExternalDocumentableObject(String uri) {
		super();
		this.identifier = new URIIdentifier(uri); // FIXME ?
	}

	public Identifier getIdentifier() {
		return identifier;
	}

	public String getURI() {
		return identifier.getName();
	}

	public String getLabel(Locale locale) {
		return this.getURI();
	}

	public String getLabel() {
        return this.getLabel(null);
	}
	
	/** (non-Javadoc)
	 * @see es.ctic.parrot.de.DocumentableObject#getLocalName()
	 * @return an unique anchor for the element
	 */
	public String getLocalName() {
		return "anchor"+getIdentifier().hashCode();
	}

    public String getKindString() {
        return Kind.UNDEFINED.toString();
    }

	public void addInverseRuleReference(Rule rule) {
		//do nothing
	}

	public Collection<Rule> getInverseRuleReferences() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public DocumentableObject getIsDefinedBy() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public boolean isDeprecated() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public String getVersion() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public String getDate() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public Collection<String> getCreators() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public Collection<String> getContributors() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public Collection<String> getPublishers() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public String getRights() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public String getLicenseLabel() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public Object accept(DocumentableObjectVisitor visitor)	throws TransformerException {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public String getDescription(Locale locale) {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public Collection<Label> getLabels(Locale locale) {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public Collection<Label> getLabels() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public Collection<RelatedDocument> getRelatedDocuments(Locale locale) {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public int compareTo(DocumentableOntologicalObject arg0) {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public Collection<DocumentableObject> getSuperClasses() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public void setSuperClasses(Collection<DocumentableObject> superClasses) {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public Collection<DocumentableObject> getSubClasses() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public void setSubClasses(Collection<DocumentableObject> subClasses) {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public Collection<OntologyIndividual> getIndividuals() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public Collection<OntologyClass> getEquivalentClasses() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public void setEquivalentClasses(Collection<OntologyClass> equivalentClasses) {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public Collection<OntologyClass> getDisjointClasses() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public void setDisjointClasses(Collection<OntologyClass> disjointClasses) {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public Collection<DocumentableObject> getRanges() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public void setRanges(Collection<DocumentableObject> ranges) {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public Collection<DocumentableObject> getDomains() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public void setDomains(Collection<DocumentableObject> domain) {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public int getOccurrences() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public Collection<DocumentableObject> getSuperProperties() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public void setSuperProperties(Collection<DocumentableObject> superProperties) {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public Collection<DocumentableObject> getSubProperties() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public void setSubProperties(Collection<DocumentableObject> subProperties) {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public Collection<DocumentableObject> getEquivalentProperties() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public void setEquivalentProperties(Collection<DocumentableObject> equivalentProperties) {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public Collection<DocumentableObject> getDisjointProperties() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public void setDisjointProperties(Collection<DocumentableObject> disjointProperties) {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public DocumentableObject getInverseOf() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public boolean isDatatypeProperty() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public boolean isObjectProperty() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public boolean isAnnotationProperty() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public boolean isTransitiveProperty() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public boolean isSymmetricProperty() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public boolean isFunctionalProperty() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public boolean isInverseFunctionalProperty() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public boolean isReflexiveProperty() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public boolean isIrreflexiveProperty() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public boolean isAsymmetricProperty() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public String getUriFragment() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public Collection<Agent> getPublisherAgents() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public Collection<Agent> getCreatorAgents() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public Collection<Agent> getContributorAgents() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public String getModifiedDate() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public String getIssuedDate() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public String getStatus() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public boolean isOwlClass() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public boolean isRdfsClass() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public Collection<DocumentableObject> getComplexDomain() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}
	
	public void setComplexDomain(Collection<DocumentableObject> complexDomain) {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public String getComplexDomainType() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public Collection<DocumentableObject> getComplexRange() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public void setComplexRange(Collection<DocumentableObject> complexDomain) {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}


	public String getComplexRangeType() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

    
}
