package es.ctic.parrot.de;

import java.util.Collection;

/**
 * An ontology individual to be documented by Parrot. 
 * 
 * @author Carlos Tejo Alonso (<a href="http://www.fundacionctic.org">Fundación CTIC</a>)
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
	
	/**
	 * Returns the collection of triples about this individual as subject, removed the well known ones.
	 * @return the collection of triples about this individual as subject, removed the well known ones.
	 */	
	public abstract Collection<Triple> getTriplesAsSubject();
	
	/**
	 * Returns the collection of triples about this individual as object, removed the well known ones.
	 * @return the collection of triples about this individual as object, removed the well known ones.
	 */	
	public abstract Collection<Triple> getTriplesAsObject();
	
}
