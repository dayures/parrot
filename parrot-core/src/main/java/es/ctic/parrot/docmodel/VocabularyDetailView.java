package es.ctic.parrot.docmodel;

import java.util.Locale;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.Vocabulary;

/**
 * A detailed view of a vocabulary.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class VocabularyDetailView extends AbstractVersionableDetailView{

    private static final Logger logger = Logger.getLogger(VocabularyDetailView.class);

    private String preferredPrefix;
	private String preferredNamespace;
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
		details.setDescription(object.getDescription(locale));
		
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
		details.setCreatorAgents(object.getCreatorAgents());
		details.setContributorAgents(object.getContributorAgents());
		details.setPublisherAgents(object.getPublisherAgents());


		details.setClassNumber(object.getClassNumber());
		details.setPropertyNumber(object.getPropertyNumber());
		
		details.setHomepage(object.getHomepage());
		
		return details;
    }
    
}
