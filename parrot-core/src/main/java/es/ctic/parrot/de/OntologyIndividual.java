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
	
	/**
	 * Returns the collection of the classes that this individual is instance of.
	 * @return the collection of the classes that this individual is instance of.
	 */	
	public abstract Collection<OntologyClass> getTypes();
}
