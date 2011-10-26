package es.ctic.parrot.de;

/**
 * An identifier for a named element.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 * 
 */
public interface NamedIdentifier extends Identifier {
	
	/**
	 * Returns the name.
	 * @return the name.
	 */
	public abstract String getName();
    
}
