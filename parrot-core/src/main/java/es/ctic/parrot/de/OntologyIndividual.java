package es.ctic.parrot.de;

import java.util.Collection;

/**
 * An ontology individual to be documented by Parrot. 
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 * 
 */
public interface OntologyIndividual extends DocumentableOntologicalObject {
	
	public abstract Collection<OntologyClass> getTypes();
}
