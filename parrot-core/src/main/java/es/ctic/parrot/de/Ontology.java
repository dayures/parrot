package es.ctic.parrot.de;


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
	
}
