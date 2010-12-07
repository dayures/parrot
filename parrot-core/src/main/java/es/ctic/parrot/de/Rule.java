package es.ctic.parrot.de;

import java.util.Collection;

public interface Rule extends DocumentableObject {

    public Collection<OntologyProperty> getReferencedOntologyProperties();

    public Collection<String> getDeclaredVars();
    
}
