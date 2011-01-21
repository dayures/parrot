package es.ctic.parrot.docmodel;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import es.ctic.parrot.de.DocumentableObject;
import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.Label;
import es.ctic.parrot.de.OntologyClass;
import es.ctic.parrot.de.OntologyIndividual;

public class OntologyClassDetailView extends AbstractOntologicalObjectDetailView implements DetailView{
	private OntologyClass ontologyClass;
	private List<OntologyClass> superClasses;
	private List<OntologyClass> subClasses;
	private Collection<OntologyClass> equivalentClasses;
	private Collection<OntologyClass> disjointClasses;
	private Collection<DocumentableObject> inverseReferences;
	private Collection<OntologyIndividual> individuals;

	public OntologyClassDetailView(OntologyClass ontologyClass){
		this.ontologyClass=ontologyClass;
		this.superClasses=new LinkedList<OntologyClass>();
		this.subClasses=new LinkedList<OntologyClass>();
		this.inverseReferences=new LinkedList<DocumentableObject>();
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

	public void setInverseReferences(Collection<DocumentableObject> inverseReferences) {
		this.inverseReferences = inverseReferences;
	}

	public Collection<DocumentableObject> getInverseReferences() {
		return inverseReferences;
	}

	public String getAnchor() {
		return ontologyClass.getLocalName();
	}

	public void setIndividuals(Collection<OntologyIndividual> individuals) {
		this.individuals = individuals;
	}

	public Collection<OntologyIndividual> getIndividuals() {
		return Collections.unmodifiableCollection(individuals);
	}

	/**
	 * @param equivalentClasses the equivalentClasses to set
	 */
	public void setEquivalentClasses(Collection<OntologyClass> equivalentClasses) {
		this.equivalentClasses = equivalentClasses;
	}

	/**
	 * @return the equivalentClasses
	 */
	public Collection<OntologyClass> getEquivalentClasses() {
		return Collections.unmodifiableCollection(equivalentClasses);
	}

	/**
	 * @param disjointClasses the disjointClasses to set
	 */
	public void setDisjointClasses(Collection<OntologyClass> disjointClasses) {
		this.disjointClasses = disjointClasses;
	}

	/**
	 * @return the disjointClasses
	 */
	public Collection<OntologyClass> getDisjointClasses() {
		return Collections.unmodifiableCollection(disjointClasses);
	}
	
	
}
