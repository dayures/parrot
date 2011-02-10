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

	private OntologyClassDetailView(){
        logger.debug("Created " + this.getClass());
	}
	
	public Collection<DocumentableObjectReference> getSuperClasses() {
		return Collections.unmodifiableCollection(this.superClasses);
	}

	public Collection<DocumentableObjectReference> getSubClasses() {
		return Collections.unmodifiableCollection(this.subClasses);
	}


	public void setInverseReferences(Collection<DocumentableObjectReference> inverseReferences) {
		this.inverseReferences = inverseReferences;
	}

	public Collection<DocumentableObjectReference> getInverseReferences() {
		return inverseReferences;
	}

	public void setIndividuals(Collection<DocumentableObjectReference> individuals) {
		this.individuals = individuals;
	}

	public Collection<DocumentableObjectReference> getIndividuals() {
		return Collections.unmodifiableCollection(individuals);
	}

	/**
	 * @param equivalentClasses the equivalentClasses to set
	 */
	public void setEquivalentClasses(Collection<DocumentableObjectReference> equivalentClasses) {
		this.equivalentClasses = equivalentClasses;
	}

	/**
	 * @return the equivalentClasses
	 */
	public Collection<DocumentableObjectReference> getEquivalentClasses() {
		return Collections.unmodifiableCollection(equivalentClasses);
	}

	/**
	 * @param disjointClasses the disjointClasses to set
	 */
	public void setDisjointClasses(Collection<DocumentableObjectReference> disjointClasses) {
		this.disjointClasses = disjointClasses;
	}

	/**
	 * @return the disjointClasses
	 */
	public Collection<DocumentableObjectReference> getDisjointClasses() {
		return Collections.unmodifiableCollection(disjointClasses);
	}
	

    public static OntologyClassDetailView createFromClass(OntologyClass object, Locale locale) {
    	
    	OntologyClassDetailView details = new OntologyClassDetailView();
    	
		details.setUri(object.getURI());
		details.setLabel(object.getLabel(locale));
		details.setComment(object.getComment(locale));
		details.setSuperClasses(DocumentableObjectReference.createReferences(object.getSuperClasses(), locale));
		details.setSubClasses(DocumentableObjectReference.createReferences(object.getSubClasses(), locale));
		details.setEquivalentClasses(DocumentableObjectReference.createReferences(object.getEquivalentClasses(),locale));
		details.setDisjointClasses(DocumentableObjectReference.createReferences(object.getDisjointClasses(), locale));
		details.setInverseRuleReferences(object.getInverseRuleReferences());
		details.setInverseReferences(DocumentableObjectReference.createReferences(object.getInternalReferences(), locale));
		details.setIndividuals(DocumentableObjectReference.createReferences(object.getIndividuals(), locale));
		details.setLabels(object.getLabels());
		details.setRelatedDocuments(object.getRelatedDocuments(locale));

		details.setAnchor(object.getLocalName());
		details.setIdentifier(object.getIdentifier());
		
		return details;

    }

	/**
	 * @param superClasses the superClasses to set
	 */
	public void setSuperClasses(Collection<DocumentableObjectReference> superClasses) {
		this.superClasses = superClasses;
	}

	/**
	 * @param subClasses the subClasses to set
	 */
	public void setSubClasses(Collection<DocumentableObjectReference> subClasses) {
		this.subClasses = subClasses;
	}
}
