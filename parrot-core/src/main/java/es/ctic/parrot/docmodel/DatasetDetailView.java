package es.ctic.parrot.docmodel;

import java.util.Collection;
import java.util.Collections;
import java.util.Locale;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.Agent;
import es.ctic.parrot.de.Dataset;
import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.Label;
import es.ctic.parrot.de.RelatedDocument;

/**
 * A detailed view of a dataset.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class DatasetDetailView implements DetailView{

    private static final Logger logger = Logger.getLogger(DatasetDetailView.class);
    private Identifier identifier;
	private String uri;
	private String uriFragment;
	private String label;
	private String comment;
	private Collection<Label> labels;
	private Collection<Label> synonyms;
	private Collection<RelatedDocument> relatedDocuments;
    private String anchor;
    
	private String date;
	private String licenseLabel;
	private String dataDump;
	private String sparqlEndpoint;
	private String homepage;
	
	private Collection<String> creators;
	private Collection<String> contributors;
	private Collection<String> publishers;
	
	private Collection<Agent> creatorAgents;
	private Collection<Agent> contributorAgents;
	private Collection<Agent> publisherAgents;

	/**
	 * Constructs a vocabulary detail view (Suppress default constructor for noninstantiability).
	 */
	private DatasetDetailView() {
        logger.debug("Created " + this.getClass());
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
	 * Set the fragment of the URI.
	 * @param uriFragment the fragment of the URI to set.
	 */
	private void setUriFragment(String uriFragment) {
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
	private void setSynonyms(Collection<Label> synonyms) {
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
	 * Set the date.
	 * @param date date to set.
	 */	
	private void setDate(String date) {
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
	 * Set the license's label.
	 * @param licenseLabel the license's label to set.
	 */
	private void setLicenseLabel(String licenseLabel) {
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
	 * @param dataDump the dataDump to set
	 */
    private void setDataDump(String dataDump) {
		this.dataDump = dataDump;
	}


	/**
	 * @return the dataDump
	 */
	public String getDataDump() {
		return dataDump;
	}


	/**
	 * @param sparqlEndpoint the sparqlEnpoint to set
	 */
	private void setSparqlEndpoint(String sparqlEndpoint) {
		this.sparqlEndpoint = sparqlEndpoint;
	}


	/**
	 * @return the sparqlEndpoint
	 */
	public String getSparqlEndpoint() {
		return sparqlEndpoint;
	}


	/**
	 * @param homepage the homepage to set
	 */
	private void setHomepage(String homepage) {
		this.homepage = homepage;
	}


	/**
	 * @return the homepage
	 */
	public String getHomepage() {
		return homepage;
	}
	
	/**
	 * Returns a detailed view for the vocabulary given.
	 * @param object the vocabulary.
	 * @param locale the locale.
	 * @return a detailed view for a vocabulary.
	 */
    public static DatasetDetailView createFromDataset(Dataset object, Locale locale) {
    	
	    DatasetDetailView details = new DatasetDetailView();
	    details.setIdentifier(object.getIdentifier());
		details.setUri(object.getURI());
		details.setUriFragment(object.getUriFragment());
		details.setLabel(object.getLabel(locale));
		details.setComment(object.getComment(locale));
		
		details.setLabels(object.getLabels());
		details.setSynonyms(object.getSynonyms());
		
        details.setAnchor(object.getLocalName());
		details.setRelatedDocuments(object.getRelatedDocuments(locale));
		
		// Control version information
		details.setCreators(object.getCreators());
		details.setContributors(object.getContributors());
		details.setPublishers(object.getPublishers());

		details.setCreatorAgents(object.getCreatorAgents());
		details.setContributorAgents(object.getContributorAgents());
		details.setPublisherAgents(object.getPublisherAgents());

		details.setDate(object.getDate());
		details.setLicenseLabel(object.getLicenseLabel());
		
		details.setDataDump(object.getdataDump());
		details.setSparqlEndpoint(object.getSparqlEndpoint());
		details.setHomepage(object.getHomepage());
		
		return details;
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
	



    
}
