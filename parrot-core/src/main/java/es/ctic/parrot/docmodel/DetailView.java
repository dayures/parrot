package es.ctic.parrot.docmodel;

import java.util.Collection;

import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.Label;
import es.ctic.parrot.de.RelatedDocument;

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

	/**
	 * Returns the URI.
	 * @return the URI.
	 */
	public abstract String getUri();

	/**
	 * Returns the description.
	 * @return the description.
	 */
	public abstract String getDescription();

	/**
	 * Returns all labels.
	 * @return all labels.
	 */
	public abstract Collection<Label> getLabels();

	/**
	 * Returns the related documents.
	 * @return the related documents.
	 */
	public abstract Collection<RelatedDocument> getRelatedDocuments();


	/**
	 * Returns the fragment of the URI.
	 * @return the fragment of the URI.
	 */
	public abstract String getUriFragment();

}
