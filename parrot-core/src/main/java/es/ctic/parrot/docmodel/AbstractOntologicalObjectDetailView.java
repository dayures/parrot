package es.ctic.parrot.docmodel;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import es.ctic.parrot.de.Label;
import es.ctic.parrot.de.RelatedDocument;
import es.ctic.parrot.de.Rule;


public abstract class AbstractOntologicalObjectDetailView implements DetailView {

	private String uri;
	private String label;
	private String comment;
	private Collection<String> depictions;
	private Collection<String> videos;
	private Collection<Rule> inverseRuleReferences = new HashSet<Rule>();
	private Collection<RelatedDocument> relatedDocuments;
	private Collection<Label> labels;

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

	public void setInverseRuleReferences(Collection<Rule> rules) {
		this.inverseRuleReferences = rules;
	}

	public Collection<Rule> getInverseRuleReferences() {
		return Collections.unmodifiableCollection(inverseRuleReferences);
	}

	public void setDepictions(Collection<String> depictions) {
		this.depictions = depictions;
	}

	public Collection<String> getDepictions() {
		return Collections.unmodifiableCollection(depictions);
	}

	public void setVideos(Collection<String> videos) {
		this.videos = videos;
	}

	public Collection<String> getVideos() {
		return Collections.unmodifiableCollection(videos);
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


}
