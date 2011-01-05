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

}
