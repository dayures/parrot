package es.ctic.parrot.de;

import java.util.Collection;

/**
 * An ontology property to be documented by Parrot. 
 * 
 * @author Carlos Tejo Alonso (<a href="http://www.fundacionctic.org">Fundación CTIC</a>)
 * @version 1.0
 * @since 1.0
 * 
 */
public interface OntologyProperty extends DocumentableOntologicalObject {
	
	/**
	 * Returns the collection of range classes of this property or <code>null</code> if not exists.
	 * @return the collection of range classes of this property or <code>null</code> if not exists.
	 */
	public abstract Collection<DocumentableObject> getRanges();
	
	/**
	 * Set the collection of range classes of this property.
	 * @param domain the collection of range classes of this property.
	 */
	public abstract void setRanges(Collection<DocumentableObject> ranges);

	
	/**
	 * Returns the collection of elements in the complex range.
	 * @return the collection of in the complex range of this property.
	 */
	public abstract Collection<DocumentableObject> getComplexRange();
	
	/**
	 * Set the complex range
	 * @param complexDomain the complex range to set.
	 */
	public abstract void setComplexRange(Collection<DocumentableObject> complexDomain);
	
	/**
	 * Returns the type of complex range.
	 * @return the type of complex range of this property.
	 */
	public abstract String getComplexRangeType();

	
	/**
	 * Returns the collection of domain classes of this property or <code>null</code> if not exists.
	 * @return the collection of domain classes of this property or <code>null</code> if not exists.
	 */
	public abstract Collection<DocumentableObject> getDomains();
	
	/**
	 * Set the collection of domain classes of this property.
	 * @param domain the collection of domain classes of this property.
	 */
	public abstract void setDomains(Collection<DocumentableObject> domains);

	/**
	 * Returns the collection of elements in the complex domain.
	 * @return the collection of in the complex domain of this property.
	 */
	public abstract Collection<DocumentableObject> getComplexDomain();
	
	/**
	 * Set the complex domain
	 * @param complexDomain the complex domain to set.
	 */
	public abstract void setComplexDomain(Collection<DocumentableObject> complexDomain);
	
	/**
	 * Returns the type of complex domain.
	 * @return the type of complex domain of this property.
	 */
	public abstract String getComplexDomainType();
	
	/**
	 * Returns the number of times that this property is used.
	 * @return the number of times that this property is used.
	 */
	public abstract int getOccurrences();
	
	/**
	 * Returns the super properties of this property.
	 * @return the super properties of this property.
	 */
    public abstract Collection<DocumentableObject> getSuperProperties();
	
    /**
	 * Set the super properties of this property.
	 * @param superProperties the super properties to set.
	 */
    public abstract void setSuperProperties(Collection<DocumentableObject> superProperties);

	/**
	 * Returns the sub properties of this property.
	 * @return the sub properties of this property.
	 */
    public abstract Collection<DocumentableObject> getSubProperties();
	/**
	 * Set the sub properties of this property.
	 * @param subProperties the sub properties to set.
	 */
    public abstract void setSubProperties(Collection<DocumentableObject> subProperties);

	/**
	 * Returns the collection of equivalent properties of this property.
	 * @return the collection of equivalent properties of this property.
	 */
	public abstract Collection<DocumentableObject> getEquivalentProperties();
	
	/**
	 * Set the collection of equivalent properties.
	 * @param equivalentProperties the collection of equivalent properties to set.
	 */
	public abstract void setEquivalentProperties(Collection<DocumentableObject> equivalentProperties);

	/**
	 * Returns the collection of disjoint properties of this property.
	 * @return the collection of disjoint properties of this property.
	 */
	public abstract Collection<DocumentableObject> getDisjointProperties();
	
	/**
	 * Set the collection of disjoint properties.
	 * @param disjointProperties the collection of disjoint properties to set.
	 */
	public abstract void setDisjointProperties(Collection<DocumentableObject> disjointProperties);
	
	/**
	 * Returns the inverse property of this property.
	 * @return the inverse property of this property.
	 */
    public abstract DocumentableObject getInverseOf();
    
    /**
     * Returns <code>true</code> if, and only if, this property is a datatype property.
     * @return <code>true</code> if this property is a datatype property, otherwise <code>false</code>.
     */
    public abstract boolean isDatatypeProperty();
    
    /**
     * Returns <code>true</code> if, and only if, this property is a object property.
     * @return <code>true</code> if this property is a object property, otherwise <code>false</code>.
     */
    public abstract boolean isObjectProperty();
    
    /**
     * Returns <code>true</code> if, and only if, this property is an annotation property.
     * @return <code>true</code> if this property is an annotation property, otherwise <code>false</code>.
     */
    public abstract boolean isAnnotationProperty();
    
    /**
     * Returns <code>true</code> if, and only if, this property is a transitive property.
     * @return <code>true</code> if this property is a transitive property, otherwise <code>false</code>.
     */    
    public abstract boolean isTransitiveProperty();
    
	/**
     * Returns <code>true</code> if, and only if, this property is a symmetric property.
     * @return <code>true</code> if this property is a symmetric property, otherwise <code>false</code>.
     */
    public abstract boolean isSymmetricProperty();
    
    /**
     * Returns <code>true</code> if, and only if, this property is a functional property.
     * @return <code>true</code> if this property is a functional property, otherwise <code>false</code>.
     */
    public abstract boolean isFunctionalProperty();
    
	/**
     * Returns <code>true</code> if, and only if, this property is an inverse functional property.
     * @return <code>true</code> if this property is an inverse functional property, otherwise <code>false</code>.
     */
    public abstract boolean isInverseFunctionalProperty();
    
    /**
     * Returns <code>true</code> if, and only if, this property is a reflexive property.
     * @return <code>true</code> if this property is a reflexive property, otherwise <code>false</code>.
     */
    public abstract boolean isReflexiveProperty();
    
    /**
     * Returns <code>true</code> if, and only if, this property is an irreflexive property.
     * @return <code>true</code> if this property is an irreflexive property, otherwise <code>false</code>.
     */
    public abstract boolean isIrreflexiveProperty();
    
    /**
     * Returns <code>true</code> if, and only if, this property is an asymmetric property.
     * @return <code>true</code> if this property is an asymmetric property, otherwise <code>false</code>.
     */
    public abstract boolean isAsymmetricProperty(); 

}
