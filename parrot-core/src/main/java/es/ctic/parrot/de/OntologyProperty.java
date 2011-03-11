package es.ctic.parrot.de;

import java.util.Collection;

/**
 * An ontology property to be documented by Parrot. 
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 * 
 */
public interface OntologyProperty extends DocumentableOntologicalObject {
	
	/**
	 * Returns the reference to the range class of this property.
	 * @return the reference to the range class of this property.
	 */
	public abstract DocumentableObject getRange();
	
	/**
	 * Set the range class of this property.
	 * @param range the range class of this property.
	 */
	public abstract void setRange(DocumentableObject range);
	
	/**
	 * Returns the reference to the domain class of this property.
	 * @return the reference to the domain class of this property.
	 */
	public abstract DocumentableObject getDomain();
	
	/**
	 * Set the domain class of this property.
	 * @param domain the domain class of this property.
	 */
	public abstract void setDomain(DocumentableObject domain);
	
	/**
	 * Returns the number of times that this property is used.
	 * @return the number of times that this property is used.
	 */
	public abstract int getCardinality();
	
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
