package es.ctic.parrot.docmodel;

import java.util.Collection;
import java.util.Collections;
import java.util.Locale;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.Label;
import es.ctic.parrot.de.RelatedDocument;
import es.ctic.parrot.de.Vocabulary;

/**
 * A detailed view of a vocabulary.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class VocabularyDetailView implements DetailView{

    private static final Logger logger = Logger.getLogger(VocabularyDetailView.class);
    private Identifier identifier;
	private String uri;
	private String uriFragment;
	private String label;
	private String comment;
	private Collection<Label> labels;
	private Collection<Label> synonyms;
	private Collection<RelatedDocument> relatedDocuments;
    private String anchor;

    private String preferredPrefix;
	private String preferredNamespace;

    private String modifiedDate;
    
	private Collection<String> creators;
	private Collection<String> contributors;
	private Collection<String> publishers;
	
	private String homepage;
	
	private String classNumber;
	private String propertyNumber;
	
	/**
	 * Constructs a vocabulary detail view (Suppress default constructor for noninstantiability).
	 */
	private VocabularyDetailView() {
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
	 * Set the preferred prefix.
	 * @param preferredPrefix preferred prefix to set.
	 */
	private void setPreferredPrefix(String preferredPrefix) {
		this.preferredPrefix = preferredPrefix;
	}

	/**
	 * Returns the preferred prefix.
	 * @return the preferred prefix.
	 */
	public String getPreferredPrefix() {
		return preferredPrefix;
	}
	
	/**
	 * Set the preferred namespace.
	 * @param preferredNamespace preferred namespace to set.
	 */
	private void setPreferredNamespace(String preferredNamespace) {
		this.preferredNamespace = preferredNamespace;
	}

	/**
	 * Returns the preferred namespace.
	 * @return the preferred namespace.
	 */
	public String getPreferredNamespace() {
		return preferredNamespace;
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
	 * Returns a detailed view for the vocabulary given.
	 * @param object the vocabulary.
	 * @param locale the locale.
	 * @return a detailed view for a vocabulary.
	 */
    public static VocabularyDetailView createFromVocabulary(Vocabulary object, Locale locale) {
    	
	    VocabularyDetailView details = new VocabularyDetailView();
	    details.setIdentifier(object.getIdentifier());
		details.setUri(object.getURI());
		details.setUriFragment(object.getUriFragment());
		details.setLabel(object.getLabel(locale));
		details.setComment(object.getComment(locale));
		
		details.setLabels(object.getLabels());
		details.setSynonyms(object.getSynonyms());
		
        details.setAnchor(object.getLocalName());
		details.setRelatedDocuments(object.getRelatedDocuments(locale));
		
		details.setPreferredNamespace(object.getPreferredNamespace());
		details.setPreferredPrefix(object.getPreferredPrefix());
		
		// Control version information
		details.setModifiedDate(object.getModifiedDate());
		details.setCreators(object.getCreators());
		details.setContributors(object.getContributors());
		details.setPublishers(object.getPublishers());
		
		details.setClassNumber(object.getClassNumber());
		details.setPropertyNumber(object.getPropertyNumber());
		
		details.setHomepage(object.getHomepage());
		
		return details;
    }


	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	private void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}


	/**
	 * @return the modifiedDate
	 */
	public String getModifiedDate() {
		return modifiedDate;
	}


	/**
	 * @param classNumber the classNumber to set
	 */
	private void setClassNumber(String classNumber) {
		this.classNumber = classNumber;
	}


	/**
	 * @return the classNumber
	 */
	public String getClassNumber() {
		return classNumber;
	}


	/**
	 * @param propertyNumber the propertyNumber to set
	 */
	private void setPropertyNumber(String propertyNumber) {
		this.propertyNumber = propertyNumber;
	}


	/**
	 * @return the propertyNumber
	 */
	public String getPropertyNumber() {
		return propertyNumber;
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
    
}
