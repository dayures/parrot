package es.ctic.parrot.de;

import java.util.Collection;
import java.util.Locale;

import es.ctic.parrot.transformers.DocumentableObjectVisitor;
import es.ctic.parrot.transformers.TransformerException;

/**
 * A element to be documented by Parrot. This interface encapsulates
 * different documentable elements.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 * 
 */
public interface DocumentableObject {
    /**
	 * Kinds of elements to be documented by Parrot.
	 * 
	 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
	 * @version 1.0
	 * @since 1.0
     */
    public enum Kind {
    	
    	/**
    	 * an undefinied element.
    	 */
        UNDEFINED("undefined"),
        
        /**
         * an ontology.
         */
        ONTOLOGY("ontology"),
        
        /**
         * an ontology class.
         */
        ONTOLOGY_CLASS("class"),
        
        /**
         * an ontology property.
         */
        ONTOLOGY_PROPERTY("property"),
        
        /**
         * an ontology individual.
         */
        ONTOLOGY_INDIVIDUAL("individual"),
        
        /**
         * a datatype.
         */
        DATATYPE("datatype"),
        
        /**
         * a rule.
         */
        RULE("rule"),

        /**
         * a rule set.
         */
        RULE_SET("ruleset"),

        /**
         * a data set.
         */
        DATASET("dataset"),
        
        /**
         * a vocabulary.
         */
        VOCABULARY("vocabulary");
        
        
        private final String name;
        private Kind(String name) { 
            this.name = name;
        }
        public String toString() {
            return name;
        }
    };
	
    /**
     * <code>accept</code> is the method used in the Visitor Pattern.
     * @param visitor the visitor in the Visitor Pattern.
     * @return an Object if it is needed.
     * @throws TransformerException if a failed transformation operation occurs.
     */
	public abstract Object accept(DocumentableObjectVisitor visitor) throws TransformerException;
	
	/**
	 * Returns the identifier of this documentable element.
	 * @return the identifier of this documentable element.
	 */
	public abstract Identifier getIdentifier();
	
	/**
	 * Returns an unique anchor for the element.
	 * @return an unique anchor for the element.
	 */
	public abstract String getLocalName();

    /**
     * Returns the fragment of the URI of this documentable element if exists, or null if doesn't exist. 
     * @return the fragment of the URI of this documentable element if exists, or null if doesn't exist.
     */
    public abstract String getUriFragment();
    
    /**
     * Returns the URI of this documentable element if exists, or null if doesn't exist. 
     * @return the URI of this documentable element if exists, or null if doesn't exist.
     */
    public abstract String getURI();
	
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
	
	/**
	 * Returns the synonyms associated with this documentable element. 
	 * @return the synonyms associated with this documentable element.
	 */
	public abstract Collection<Label> getSynonyms();
	
	/**
	 * Returns the kind string for this documentable element. 
	 * @return the kind string for this documentable element.
	 */
	public abstract String getKindString();

	/**
	 * Returns the related documents of this documentable element for this specified locale.
	 * @param locale the locale.
	 * @return the related documents of this documentable element for this specified locale.
	 */
	public abstract Collection<RelatedDocument> getRelatedDocuments(Locale locale);
	
	/**
	 * Adds a reference to another documentable element that references this documentable element. 
	 * @param documentableObject the documentable element that references this documentable element.
	 */
	public abstract void addReference(DocumentableObject documentableObject);
	
	/**
	 * Returns the documentable elements that references this documentable element. 
	 * @return the documentable elements that references this documentable element.
	 */
	public abstract Collection<DocumentableObject> getInternalReferences();

}
