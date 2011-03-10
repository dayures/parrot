package es.ctic.parrot.docmodel;

import es.ctic.parrot.de.Identifier;

/**
 * A detailed view of a documentable element. This interface encapsulates different detailed views.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public interface DetailView {

	/**
	 * Returns the identifier.
	 * @return the identifier.
	 */
	public abstract Identifier getIdentifier();
	
	/**
	 * Returns the anchor. 
	 * @return the anchor
	 */
	public abstract String getAnchor();
	
	/**
	 * Returns the label.
	 * @return the label.
	 */
	public abstract String getLabel();
}
