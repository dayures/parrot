package es.ctic.parrot.de;

import java.util.Collection;

public interface OntologyProperty extends DocumentableOntologicalObject {
	public abstract DocumentableObject getRange();
	public abstract void setRange(DocumentableObject range);
	public abstract DocumentableObject getDomain();
	public abstract void setDomain(DocumentableObject domain);
	public abstract int getCardinality();
	
    public abstract Collection<DocumentableObject> getSuperProperties();
    public abstract void setSuperProperties(Collection<DocumentableObject> superProperties);
    
	public abstract Collection<DocumentableObject> getEquivalentProperties();
	public abstract void setEquivalentProperties(Collection<DocumentableObject> equivalentProperties);

	public abstract Collection<DocumentableObject> getDisjointProperties();
	public abstract void setDisjointProperties(Collection<DocumentableObject> disjointProperties);
	
    public abstract Collection<DocumentableObject> getSubProperties();
    public abstract void setSubProperties(Collection<DocumentableObject> subProperties);
    
    public abstract DocumentableObject getInverseOf();
    
    public abstract boolean isDatatypeProperty();
    public abstract boolean isObjectProperty();
    public abstract boolean isAnnotationProperty();
    
    public abstract boolean isTransitiveProperty();
    public abstract boolean isSymmetricProperty();
    public abstract boolean isFunctionalProperty();
    public abstract boolean isInverseFunctionalProperty();
    
    public abstract boolean isReflexiveProperty();
    public abstract boolean isIrreflexiveProperty();
    public abstract boolean isAsymmetricProperty(); 

}
