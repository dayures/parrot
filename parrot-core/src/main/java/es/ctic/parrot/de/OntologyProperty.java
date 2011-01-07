package es.ctic.parrot.de;

import java.util.Collection;

public interface OntologyProperty extends DocumentableOntologicalObject {
	public abstract DocumentableObject getRange();
	public abstract void setRange(DocumentableObject range);
	public abstract DocumentableObject getDomain();
	public abstract void setDomain(DocumentableObject domain);
	public abstract int getCardinality();
	
    public abstract Collection<OntologyProperty> getSuperProperties();
    public abstract void setSuperProperties(Collection<OntologyProperty> superProperties);

    public abstract Collection<OntologyProperty> getSubProperties();
    public abstract void setSubProperties(Collection<OntologyProperty> subProperties);
    
    public abstract DocumentableObject getInverseOf();
    
    public abstract boolean isDatatypeProperty();
    public abstract boolean isObjectProperty();
    public abstract boolean isAnnotationProperty();
    
    public abstract boolean isTransitiveProperty();
    public abstract boolean isSymmetricProperty();
    public abstract boolean isFunctionalProperty();
    public abstract boolean isInverseFunctionalProperty();    
}
