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

	/**
	 * Returns the super classes.
	 * @return the super classes.
	 */	
    public abstract Collection<OntologyClass> getSuperClasses();
    
	/**
	 * Set the super classes to this detailed view.
	 * @param superClasses the super classes to set to this detailed view.
	 */
    public abstract void setSuperClasses(Collection<OntologyClass> superClasses);
    
	/**
	 * Returns the sub classes.
	 * @return the sub classes.
	 */
    public abstract Collection<OntologyClass> getSubClasses();
    
	/**
	 * Set the sub classes to this detailed view.
	 * @param subClasses the sub classes to set to this detailed view.
	 */
    public abstract void setSubClasses(Collection<OntologyClass> subClasses); 
	
	/**
	 * Returns the individuals.
	 * @return the individuals.
	 */
    public abstract Collection<OntologyIndividual> getIndividuals();

	/**
	 * Returns the equivalent classes.
	 * @return the equivalent classes.
	 */
	public abstract Collection<OntologyClass> getEquivalentClasses();
	
	/**
	 * Set the equivalent classes to this detailed view.
	 * @param equivalentClasses the equivalent classes to set.
	 */
	public abstract void setEquivalentClasses(Collection<OntologyClass> equivalentClasses);
	
	/**
	 * Returns the disjoint classes.
	 * @return the disjoint classes.
	 */
	public abstract Collection<OntologyClass> getDisjointClasses();
	
	/**
	 * Set the disjoint classes to this detailed view.
	 * @param disjointClasses the disjoint classes to set to this detailed view.
	 */
	public abstract void setDisjointClasses(Collection<OntologyClass> disjointClasses);
	
    /**
     * Returns <code>true</code> if, and only if, this class is an OWL class.
     * @return <code>true</code> if this class is an OWL class, otherwise <code>false</code>.
     */
    public abstract boolean isOwlClass();

    /**
     * Returns <code>true</code> if, and only if, this class is an RDFS class.
     * @return <code>true</code> if this class is an RDFS class, otherwise <code>false</code>.
     */
    public abstract boolean isRdfsClass();


}