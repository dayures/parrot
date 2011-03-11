package es.ctic.parrot.de;

import java.util.Collection;

/**
 * An ontology class to be documented by Parrot. 
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 * 
 */
public interface OntologyClass extends DocumentableOntologicalObject {

    public abstract Collection<OntologyClass> getSuperClasses();
    public abstract void setSuperClasses(Collection<OntologyClass> classes);
    
    public abstract Collection<OntologyClass> getSubClasses();
    public abstract void setSubClasses(Collection<OntologyClass> classes); 
	
    public abstract Collection<OntologyIndividual> getIndividuals();

	public abstract Collection<OntologyClass> getEquivalentClasses();
	public abstract void setEquivalentClasses(Collection<OntologyClass> equivalentClasses);
	
	public abstract Collection<OntologyClass> getDisjointClasses();
	public abstract void setDisjointClasses(Collection<OntologyClass> disjointClasses);

}