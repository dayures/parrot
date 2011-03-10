package es.ctic.parrot.docmodel;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Locale;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.OntologyClass;

public class OntologyClassDetailView extends AbstractOntologicalObjectDetailView implements DetailView{
	
    private static final Logger logger = Logger.getLogger(OntologyClassDetailView.class);

	private Collection<DocumentableObjectReference> superClasses = new LinkedList<DocumentableObjectReference>();
	private Collection<DocumentableObjectReference> subClasses = new LinkedList<DocumentableObjectReference>();
	private Collection<DocumentableObjectReference> equivalentClasses;
	private Collection<DocumentableObjectReference> disjointClasses;
	private Collection<DocumentableObjectReference> inverseReferences = new LinkedList<DocumentableObjectReference>();
	private Collection<DocumentableObjectReference> individuals;

	/**
	 * Constructs a ontology class detail view. 
	 */
	private OntologyClassDetailView(){
        logger.debug("Created " + this.getClass());
	}
	
	/**
	 * Returns the super classes.
	 * @return the super classes.
	 */	
	public Collection<DocumentableObjectReference> getSuperClasses() {
		return Collections.unmodifiableCollection(this.superClasses);
	}

	/**
	 * Returns the sub classes.
	 * @return the sub classes.
	 */
	public Collection<DocumentableObjectReference> getSubClasses() {
		return Collections.unmodifiableCollection(this.subClasses);
	}

	/**
	 * Set the inverse references to this detailed view.
	 * @param individuals the inverse references to set to this detailed view.
	 */
	public void setInverseReferences(Collection<DocumentableObjectReference> inverseReferences) {
		this.inverseReferences = inverseReferences;
	}

	/**
	 * Returns the inverse references.
	 * @return the inverse references.
	 */
	public Collection<DocumentableObjectReference> getInverseReferences() {
		return inverseReferences;
	}

	/**
	 * Set the individuals to this detailed view.
	 * @param individuals the individuals to set to this detailed view.
	 */
	public void setIndividuals(Collection<DocumentableObjectReference> individuals) {
		this.individuals = individuals;
	}

	/**
	 * Returns the individuals.
	 * @return the individuals.
	 */
	public Collection<DocumentableObjectReference> getIndividuals() {
		return Collections.unmodifiableCollection(individuals);
	}

	/**
	 * Set the equivalent classes to this detailed view.
	 * @param equivalentClasses the equivalent classes to set.
	 */
	public void setEquivalentClasses(Collection<DocumentableObjectReference> equivalentClasses) {
		this.equivalentClasses = equivalentClasses;
	}

	/**
	 * Returns the equivalent classes.
	 * @return the equivalent classes.
	 */
	public Collection<DocumentableObjectReference> getEquivalentClasses() {
		return Collections.unmodifiableCollection(equivalentClasses);
	}

	/**
	 * Set the disjoint classes to this detailed view.
	 * @param disjointClasses the disjoint classes to set to this detailed view.
	 */
	public void setDisjointClasses(Collection<DocumentableObjectReference> disjointClasses) {
		this.disjointClasses = disjointClasses;
	}

	/**
	 * Returns the disjoint classes.
	 * @return the disjoint classes.
	 */
	public Collection<DocumentableObjectReference> getDisjointClasses() {
		return Collections.unmodifiableCollection(disjointClasses);
	}
	
	/**
	 * Set the super classes to this detailed view.
	 * @param superClasses the super classes to set to this detailed view.
	 */
	public void setSuperClasses(Collection<DocumentableObjectReference> superClasses) {
		this.superClasses = superClasses;
	}

	/**
	 * Set the sub classes to this detailed view.
	 * @param subClasses the sub classes to set to this detailed view.
	 */
	public void setSubClasses(Collection<DocumentableObjectReference> subClasses) {
		this.subClasses = subClasses;
	}
	
	/**
	 * Returns a detailed view for the ontology class given.
	 * @param object the onlotogy class.
	 * @param locale the locale.
	 * @return a detailed view for an ontology class.
	 */
    public static OntologyClassDetailView createFromClass(OntologyClass object, Locale locale) {
    	
    	OntologyClassDetailView details = new OntologyClassDetailView();
    	
		details.setUri(object.getURI());
		details.setLabel(object.getLabel(locale));
		details.setComment(object.getComment(locale));
		details.setSuperClasses(DocumentableObjectReference.createReferences(object.getSuperClasses(), locale));
		details.setSubClasses(DocumentableObjectReference.createReferences(object.getSubClasses(), locale));
		details.setEquivalentClasses(DocumentableObjectReference.createReferences(object.getEquivalentClasses(),locale));
		details.setDisjointClasses(DocumentableObjectReference.createReferences(object.getDisjointClasses(), locale));
		details.setInverseRuleReferences(DocumentableObjectReference.createReferences(object.getInverseRuleReferences(), locale));
		details.setInverseReferences(DocumentableObjectReference.createReferences(object.getInternalReferences(), locale));
		details.setIndividuals(DocumentableObjectReference.createReferences(object.getIndividuals(), locale));
		details.setLabels(object.getLabels());
		details.setRelatedDocuments(object.getRelatedDocuments(locale));

		details.setAnchor(object.getLocalName());
		details.setIdentifier(object.getIdentifier());
		details.setIsDefinedBy(DocumentableObjectReference.createReference(object.getIsDefinedBy(),locale));
		details.setDeprecated(object.isDeprecated());
		
		return details;

    }

}
