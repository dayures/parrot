package es.ctic.parrot.de;

import java.util.Collection;
import java.util.Locale;

import es.ctic.parrot.transformers.DocumentableObjectVisitor;
import es.ctic.parrot.transformers.TransformerException;


public interface Distribution extends Versionable{
    
    /**
     * <code>accept</code> is the method used in the Visitor Pattern.
     * @param visitor the visitor in the Visitor Pattern.
     * @return an Object if it is needed.
     * @throws TransformerException if a failed transformation operation occurs.
     */
	public abstract Object accept(DocumentableObjectVisitor visitor) throws TransformerException;

	/**
	 * Returns the best label associated with this documentable element with a specified locale. 
	 * @param locale the locale.
	 * @return the best label associated with this documentable element with a specified locale.
	 */
	public abstract String getLabel(Locale locale);
	
	/**
	 * Returns the best label associated with this documentable element.
	 * @return the best labels associated with this documentable element.
	 */
	public abstract String getLabel();
	
	/**
	 * Returns the description associated with this documentable element with a specified locale. 
	 * @param locale the locale.
	 * @return the description associated with this documentable element with a specified locale.
	 */
	public abstract String getDescription(Locale locale);    
	
	/**
	 * Returns the labels associated with this documentable element with a specified locale. 
	 * @param locale the locale.
	 * @return the labels associated with this documentable element with a specified locale.
	 */
	public abstract Collection<Label> getLabels(Locale locale);
	
	/**
	 * Returns the labels associated with this documentable element. 
	 * @return the labels associated with this documentable element.
	 */
	public abstract Collection<Label> getLabels();

}
