package es.ctic.parrot.docmodel;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.Label;
import es.ctic.parrot.de.RelatedDocument;

/**
 * An implementation of the DetailView interface to serve as a basis for implementing various kinds of detailed views.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public abstract class AbstractOntologicalObjectDetailView implements DetailView {

	private String uri;
	private String uriFragment;
	private String label;
	private String comment;
	private String anchor;
	private Collection<DocumentableObjectReference> inverseRuleReferences = new HashSet<DocumentableObjectReference>();
	private Collection<RelatedDocument> relatedDocuments;
	private Collection<Label> labels;
    private Identifier identifier;
	private DocumentableObjectReference isDefinedBy;
	private boolean deprecated;
	
	private String version;
	private String date;
	private Collection<String> creators;
	private Collection<String> contributors;
	private Collection<String> publishers;
	private String rights;
	private String licenseLabel;
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
	protected void setUri(String uri) {
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
	protected void setLabel(String label) {
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
	protected void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * Sets the rules that reference this ontological element.
	 * @param rules the rules that reference this ontological element.
	 */
	protected void setInverseRuleReferences(Collection<DocumentableObjectReference> rules) {
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
	protected void setRelatedDocuments(Collection<RelatedDocument> relatedDocuments) {
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
	protected void setLabels(Collection<Label> labels) {
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
	protected void setAnchor(String anchor) {
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
	protected void setIdentifier(Identifier identifier) {
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
	protected void setIsDefinedBy(DocumentableObjectReference isDefinedBy) {
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
	protected void setDeprecated(boolean deprecated) {
		this.deprecated = deprecated;
	}

	/**
     * Returns <code>true</code> if, and only if, this ontological element is deprecated.
     * @return <code>true</code> if this ontological element is deprecated, otherwise <code>false</code>.
     */
	public boolean isDeprecated() {
		return deprecated;
	}

	/**
	 * Set the version.
	 * @param version version to set.
	 */
	protected void setVersion(String version) {
		this.version = version;
	}

	/**
	 * Returns the version.
	 * @return the version.
	 */
	public String getVersion() {
		return version;
	}
	
	/**
	 * Set the date.
	 * @param date date to set.
	 */	
	protected void setDate(String date) {
		this.date = date;
	}

	/**
	 * Returns the date.
	 * @return the date.
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Set the creators.
	 * @param creators creators to set.
	 */
	protected void setCreators(Collection<String> creators) {
		this.creators = creators;
	}

	/**
	 * Returns the creators.
	 * @return the creators.
	 */	
	public Collection<String> getCreators() {
		return Collections.unmodifiableCollection(creators);
	}
	
	/**
	 * Set the contributors.
	 * @param contributors contributors to set.
	 */
	protected void setContributors(Collection<String> contributors) {
		this.contributors = contributors;
	}

	/**
	 * Returns the contributors.
	 * @return the contributors.
	 */
	public Collection<String> getContributors() {
		return Collections.unmodifiableCollection(contributors);
	}

	/**
	 * Set the publishers.
	 * @param publishers publishers to set.
	 */
	protected void setPublishers(Collection<String> publishers) {
		this.publishers = publishers;
	}

	/**
	 * Returns the publishers.
	 * @return the publishers.
	 */
	public Collection<String> getPublishers() {
		return Collections.unmodifiableCollection(publishers);
	}
	
	/**
	 * Set information about the ontology rights.
	 * @param rights information about the ontology rights to set.
	 */
	protected void setRights(String rights) {
		this.rights = rights;
	}

	/**
	 * Returns information about the ontology rights.
	 * @return information about the ontology rights.
	 */
	public String getRights() {
		return rights;
	}

	/**
	 * Set the license's label.
	 * @param licenseLabel the license's label to set.
	 */
	protected void setLicenseLabel(String licenseLabel) {
		this.licenseLabel = licenseLabel;
	}

	/**
	 * Returns the label for the license of this ontology.
	 * @return the label for the license of this ontology.
	 */
	public String getLicenseLabel() {
		return licenseLabel;
	}

	/**
	 * Set the fragment of the URI.
	 * @param uriFragment the fragment of the URI to set.
	 */
	public void setUriFragment(String uriFragment) {
		this.uriFragment = uriFragment;
	}

	/**
	 * Returns the fragment of the URI.
	 * @return the fragment of the URI.
	 */
	public String getUriFragment() {
		return uriFragment;
	}
}
