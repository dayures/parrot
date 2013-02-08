package es.ctic.parrot.de;

import java.util.Collection;


/**
 * An ontology to be documented by Parrot. 
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 * 
 */
public interface Ontology extends DocumentableOntologicalObject {
	
	/**
	 * Returns the preferred prefix.
	 * @return the preferred prefix.
	 */
	public abstract String getPreferredPrefix();
	
	/**
	 * Returns the preferred namespace.
	 * @return the preferred namespace.
	 */
	public abstract String getPreferredNamespace();

	/**
	 * Returns the collection of documentable object references.
	 * @return the collection of documentable object references.
	 */
	public abstract Collection<DocumentableObject> getDefines();

	/**
	 * Returns the collection of imports.
	 * @return the collection of imports.
	 */
	public abstract Collection<DocumentableObject> getImports();
	
	/**
	 * Returns the version IRI.
	 * @return the version IRI.
	 */
	public abstract String getVersionIRI();
}
