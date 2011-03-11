package es.ctic.parrot.docmodel;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.Label;
import es.ctic.parrot.de.RelatedDocument;


public abstract class AbstractOntologicalObjectDetailView implements DetailView {

	private String uri;
	private String label;
	private String comment;
	private String anchor;
	private Collection<DocumentableObjectReference> inverseRuleReferences = new HashSet<DocumentableObjectReference>();
	private Collection<RelatedDocument> relatedDocuments;
	private Collection<Label> labels;
    private Identifier identifier;
	private DocumentableObjectReference isDefinedBy;
	private boolean deprecated;

	/**
	 * Returns the URI.
	 * @return the URI.
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * Sets the URI.
	 * @param uri the URI to set.
	 */
	public void setUri(String uri) {
		this.uri=uri;
	}

	/**
	 * Returns the label.
	 * @return the label.
	 */
	public String getLabel() {
		return this.label;
	}

	/**
	 * Sets the label.
	 * @param label the label to set.
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Returns the description.
	 * @return the description.
	 */
	public String getComment() {
		return this.comment;
	}

	/**
	 * Sets the description.
	 * @param comment the description to set.
	 */	
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * Sets the rules that reference this ontological element.
	 * @param rules the rules that reference this ontological element.
	 */
	public void setInverseRuleReferences(Collection<DocumentableObjectReference> rules) {
		this.inverseRuleReferences = rules;
	}

	/**
	 * Returns the rules that reference this ontological element.
	 * @return the rules that reference this ontological element.
	 */
	public Collection<DocumentableObjectReference> getInverseRuleReferences() {
		return Collections.unmodifiableCollection(inverseRuleReferences);
	}

	/**
	 * Sets the related documents to this ontological element.
	 * @param relatedDocuments the related documents to this ontological element to set.
	 */
	public void setRelatedDocuments(Collection<RelatedDocument> relatedDocuments) {
		this.relatedDocuments = relatedDocuments;
	}

	/**
	 * Returns the related documents.
	 * @return the related documents.
	 */
	public Collection<RelatedDocument> getRelatedDocuments() {
		return relatedDocuments;
	}

	/**
	 * Set all labels.
	 * @param labels all labels to set.
	 */
	public void setLabels(Collection<Label> labels) {
		this.labels = labels;
	}

	/**
	 * Returns all labels.
	 * @return all labels.
	 */
	public Collection<Label> getLabels() {
		return Collections.unmodifiableCollection(labels);
	}

	/**
	 * Sets the anchor.
	 * @param anchor the anchor to set
	 */
	public void setAnchor(String anchor) {
		this.anchor = anchor;
	}

	/**
	 * Returns the anchor.
	 * @return the anchor.
	 */
	public String getAnchor() {
		return anchor;
	}

	/**
	 * Set the identifier.
	 * @param identifier the identifier to set.
	 */
	public void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	}

	/**
	 * Returns the identifier.
	 * @return the identifier.
	 */
	public Identifier getIdentifier() {
		return identifier;
	}

	/**
	 * Sets the reference where this ontological element is defined.
	 * @param isDefinedBy the reference where this ontological element is defined.
	 */
	public void setIsDefinedBy(DocumentableObjectReference isDefinedBy) {
		this.isDefinedBy = isDefinedBy;
	}

	/**
	 * Returns the reference where this ontological element is defined.
	 * @return the reference where this ontological element is defined.
	 */
	public DocumentableObjectReference getIsDefinedBy() {
		return isDefinedBy;
	}

	/**
	 * Sets the value of the deprecation of this ontological element. 
	 * @param deprecated the value of the deprecation of this ontological element.
	 */
	public void setDeprecated(boolean deprecated) {
		this.deprecated = deprecated;
	}

	/**
     * Returns <code>true</code> if, and only if, this ontological element is deprecated.
     * @return <code>true</code> if this ontological element is deprecated, otherwise <code>false</code>.
     */
	public boolean isDeprecated() {
		return deprecated;
	}

}
