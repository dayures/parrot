package es.ctic.parrot.de;

import java.util.Collection;
import java.util.List;

public interface OntologyClass extends DocumentableOntologicalObject {

    public abstract Collection<OntologyClass> getSuperClasses();
    public abstract void setSuperClasses(List<OntologyClass> classes);
    public abstract Collection<OntologyClass> getSubClasses();
    public abstract void setSubClasses(List<OntologyClass> classes); 
    
}