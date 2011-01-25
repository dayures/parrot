package es.ctic.parrot.de;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

import es.ctic.parrot.transformers.DocumentableObjectVisitor;
import es.ctic.parrot.transformers.TransformerException;

public class UndefinedOntologyDocumentableObject extends AbstractDocumentableObject implements OntologyProperty, OntologyClass{

	private static final Logger logger = Logger.getLogger(UndefinedOntologyDocumentableObject.class);

	public UndefinedOntologyDocumentableObject(String uri) {
		super();
		this.uri = uri;
	}

	private String uri;
	
	public Identifier getIdentifier() {
		return new URIIdentifier(this.getURI());
	}

	public String getURI() {
		return uri;
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

	public void addInverseRuleReference(Rule rule) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//
	}

	public Collection<Rule> getInverseRuleReferences() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return null;
	}

	public Object accept(DocumentableObjectVisitor visitor)
			throws TransformerException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return null;
	}

	public String getComment(Locale locale) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return null;
	}

	public Collection<RelatedDocument> getRelatedDocuments(Locale locale) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return null;
	}

	public Collection<Label> getLabels(Locale locale) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return null;
	}

	public Collection<Label> getLabels() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return null;
	}

	public int compareTo(DocumentableOntologicalObject o) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return 0;
	}

	public Collection<OntologyClass> getSuperClasses() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return null;
	}

	public void setSuperClasses(List<OntologyClass> classes) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//
	}

	public Collection<OntologyClass> getSubClasses() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return null;
	}

	public void setSubClasses(List<OntologyClass> classes) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//
	}

	public Collection<OntologyIndividual> getIndividuals() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return null;
	}

	public Collection<OntologyClass> getEquivalentClasses() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return null;
	}

	public void setEquivalentClasses(Collection<OntologyClass> equivalentClasses) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//
	}

	public Collection<OntologyClass> getDisjointClasses() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return null;
	}

	public void setDisjointClasses(Collection<OntologyClass> disjointClasses) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//
	}

	public DocumentableObject getRange() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return null;
	}

	public void setRange(DocumentableObject range) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//
	}

	public DocumentableObject getDomain() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return null;
	}

	public void setDomain(DocumentableObject domain) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//
	}

	public int getCardinality() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return 0;
	}

	public Collection<OntologyProperty> getSuperProperties() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return null;
	}

	public void setSuperProperties(Collection<OntologyProperty> superProperties) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//
	}

	public Collection<OntologyProperty> getEquivalentProperties() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return null;
	}

	public void setEquivalentProperties(
			Collection<OntologyProperty> equivalentProperties) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//
	}

	public Collection<OntologyProperty> getDisjointProperties() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return null;
	}

	public void setDisjointProperties(
			Collection<OntologyProperty> disjointProperties) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//
	}

	public Collection<OntologyProperty> getSubProperties() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return null;
	}

	public void setSubProperties(Collection<OntologyProperty> subProperties) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//
	}

	public DocumentableObject getInverseOf() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return null;
	}

	public boolean isDatatypeProperty() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return false;
	}

	public boolean isObjectProperty() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return false;
	}

	public boolean isAnnotationProperty() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return false;
	}

	public boolean isTransitiveProperty() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return false;
	}

	public boolean isSymmetricProperty() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return false;
	}

	public boolean isFunctionalProperty() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return false;
	}

	public boolean isInverseFunctionalProperty() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return false;
	}

	public boolean isReflexiveProperty() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return false;
	}

	public boolean isIrreflexiveProperty() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return false;
	}

	public boolean isAsymmetricProperty() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return false;
	}

}
