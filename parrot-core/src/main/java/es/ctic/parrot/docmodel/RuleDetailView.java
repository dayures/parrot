package es.ctic.parrot.docmodel;

import java.util.Collection;
import java.util.Collections;
import java.util.Locale;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.Agent;
import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.Label;
import es.ctic.parrot.de.RelatedDocument;
import es.ctic.parrot.de.Rule;

/**
 * A detailed view of a rule.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class RuleDetailView implements DetailView{

    private static final Logger logger = Logger.getLogger(RuleDetailView.class);
    private Identifier identifier;
	private String uri;
	private String uriFragment;
	private String label;
	private String comment;
	private Collection<DocumentableObjectReference> referencedOntologicalObjects;
	private Collection<Label> labels;
	private Collection<Label> synonyms;
	private Collection<RelatedDocument> relatedDocuments;
	private DocumentableObjectReference parent;
    private String anchor;
    
	private String version;
	private String date;
	private Collection<String> creators;
	private Collection<String> contributors;
	private Collection<String> publishers;
	private Collection<Agent> creatorAgents;
	private Collection<Agent> contributorAgents;
	private Collection<Agent> publisherAgents;	
	private String rights;
	private String licenseLabel;
	
	/**
	 * Constructs a rule detail view (Suppress default constructor for noninstantiability).
	 */
	private RuleDetailView() {
        logger.debug("Created " + this.getClass());
    }

	/**
	 * Returns the references to the ontological elements that reference this rule.
	 * @return the references to the ontological elements that reference this rule.
	 */
	public Collection<DocumentableObjectReference> getReferencedOntologicalObjects() {
		return Collections.unmodifiableCollection(referencedOntologicalObjects);
	}

	/**
	 * Sets the references to the ontological elements that reference this rule.
	 * @param referencedOntologicalObjects the references to the ontological elements that reference this rule.
	 */
	private void setReferencedOntologicalObjects(
			Collection<DocumentableObjectReference> referencedOntologicalObjects) {
		this.referencedOntologicalObjects = referencedOntologicalObjects;
	}

	/**
	 * Set the identifier.
	 * @param identifier the identifier to set.
	 */
	private void setIdentifier(Identifier identifier) {
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
	private void setUri(String uri) {
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
	private void setLabel(String label) {
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
	private void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * Sets the anchor.
	 * @param anchor the anchor to set
	 */
	private void setAnchor(String anchor) {
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
	 * Sets the reference to the parent element.
	 * @param parent the reference to the parent element.
	 */
	private void setParent(DocumentableObjectReference parent) {
		this.parent = parent;
	}

	/**
	 * Returns the reference to the parent element.
	 * @return the reference to the parent element.
	 */
	public DocumentableObjectReference getParent() {
		return parent;
	}
	
	/**
	 * Set all labels.
	 * @param labels all labels to set.
	 */
	private void setLabels(Collection<Label> labels) {
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
	 * Sets the related documents to this ontological element.
	 * @param relatedDocuments the related documents to this ontological element to set.
	 */
	private void setRelatedDocuments(Collection<RelatedDocument> relatedDocuments) {
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
	 * @param creatorAgents the creatorAgents to set
	 */
	protected void setCreatorAgents(Collection<Agent> creatorAgents) {
		this.creatorAgents = creatorAgents;
	}

	/**
	 * @return the creatorAgents
	 */
	public Collection<Agent> getCreatorAgents() {
		return Collections.unmodifiableCollection(creatorAgents);
	}

	/**
	 * @param contributorAgents the contributorAgents to set
	 */
	protected void setContributorAgents(Collection<Agent> contributorAgents) {
		this.contributorAgents = contributorAgents;
	}

	/**
	 * @return the contributorAgents
	 */
	public Collection<Agent> getContributorAgents() {
		return Collections.unmodifiableCollection(contributorAgents);
	}
	
	/**
	 * @param publisherAgents the publisherAgents to set
	 */
	protected void setPublisherAgents(Collection<Agent> publisherAgents) {
		this.publisherAgents = publisherAgents;
	}


	/**
	 * @return the publisherAgents
	 */
	public Collection<Agent> getPublisherAgents() {
		return Collections.unmodifiableCollection(publisherAgents);
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

	/**
	 * Set the synonyms.
	 * @param synonyms the synonyms to set
	 */
	protected void setSynonyms(Collection<Label> synonyms) {
		this.synonyms = synonyms;
	}

	/**
	 * Returns the synonyms.
	 * @return the synonyms.
	 */
	public Collection<Label> getSynonyms() {
		return Collections.unmodifiableCollection(synonyms);
	}
	
	/**
	 * Returns a detailed view for the rule given.
	 * @param object the rule.
	 * @param locale the locale.
	 * @return a detailed view for a rule.
	 */
    public static RuleDetailView createFromRule(Rule object, Locale locale) {
    	
	    RuleDetailView details = new RuleDetailView();
	    details.setIdentifier(object.getIdentifier());
		details.setUri(object.getURI());
		details.setUriFragment(object.getUriFragment());
		details.setLabel(object.getLabel(locale));
		details.setComment(object.getComment(locale));
		
		// Control version information
		details.setVersion(object.getVersion());
		details.setDate(object.getDate());
		
		details.setCreators(object.getCreators());
		details.setContributors(object.getContributors());
		details.setPublishers(object.getPublishers());
		
		details.setCreatorAgents(object.getCreatorAgents());
		details.setContributorAgents(object.getContributorAgents());
		details.setPublisherAgents(object.getPublisherAgents());

		details.setRights(object.getRights());
		details.setLicenseLabel(object.getLicenseLabel());

		details.setParent(DocumentableObjectReference.createReference(object.getParent(), locale));
		details.setReferencedOntologicalObjects(DocumentableObjectReference.createReferences(object.getReferencedOntologicalObjects(), locale));
		details.setLabels(object.getLabels());
		details.setSynonyms(object.getSynonyms());
		
        details.setAnchor(object.getLocalName());
		details.setRelatedDocuments(object.getRelatedDocuments(locale));
		
		return details;
    }
    
}
