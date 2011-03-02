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

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri=uri;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setInverseRuleReferences(Collection<DocumentableObjectReference> rules) {
		this.inverseRuleReferences = rules;
	}

	public Collection<DocumentableObjectReference> getInverseRuleReferences() {
		return Collections.unmodifiableCollection(inverseRuleReferences);
	}

	/**
	 * @param relatedDocuments the relatedDocuments to set
	 */
	public void setRelatedDocuments(Collection<RelatedDocument> relatedDocuments) {
		this.relatedDocuments = relatedDocuments;
	}

	/**
	 * @return the relatedDocuments
	 */
	public Collection<RelatedDocument> getRelatedDocuments() {
		return relatedDocuments;
	}

	/**
	 * @param labels the labels to set
	 */
	public void setLabels(Collection<Label> labels) {
		this.labels = labels;
	}

	/**
	 * @return the labels
	 */
	public Collection<Label> getLabels() {
		return Collections.unmodifiableCollection(labels);
	}

	/**
	 * @param anchor the anchor to set
	 */
	public void setAnchor(String anchor) {
		this.anchor = anchor;
	}

	/**
	 * @return the anchor
	 */
	public String getAnchor() {
		return anchor;
	}

	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	}

	/**
	 * @return the identifier
	 */
	public Identifier getIdentifier() {
		return identifier;
	}

	/**
	 * @param isDefinedBy the isDefinedBy to set
	 */
	public void setIsDefinedBy(DocumentableObjectReference isDefinedBy) {
		this.isDefinedBy = isDefinedBy;
	}

	/**
	 * @return the isDefinedBy
	 */
	public DocumentableObjectReference getIsDefinedBy() {
		return isDefinedBy;
	}


}
