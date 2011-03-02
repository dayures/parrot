package es.ctic.parrot.de;

import java.util.Collection;

public interface OntologyIndividual extends DocumentableOntologicalObject {
	
	public abstract Collection<OntologyClass> getTypes();
}
