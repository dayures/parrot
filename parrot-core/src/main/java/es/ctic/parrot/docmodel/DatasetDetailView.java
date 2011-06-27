package es.ctic.parrot.docmodel;

import java.util.Collection;
import java.util.Locale;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.Dataset;

/**
 * A detailed view of a dataset.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class DatasetDetailView extends AbstractVersionableDetailView{

    private static final Logger logger = Logger.getLogger(DatasetDetailView.class);
    
	private String dataDump;
	private String sparqlEndpoint;
	private String homepage;
	private Collection<String> vocabularies;


	/**
	 * Constructs a vocabulary detail view (Suppress default constructor for noninstantiability).
	 */
	private DatasetDetailView() {
        logger.debug("Created " + this.getClass());
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
	 * @param vocabularies the vocabularies to set
	 */
	private void setVocabularies(Collection<String> vocabularies) {
		this.vocabularies = vocabularies;
	}

	/**
	 * @return the vocabularies
	 */
	public Collection<String> getVocabularies() {
		return vocabularies;
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
		details.setDescription(object.getDescription(locale));
        details.setAnchor(object.getLocalName());
		
		details.setLexicalInformation(LexicalInformation.createFromLabels(object.getLabels(), details.getLabel(), locale));
		details.setRelatedDocuments(object.getRelatedDocuments(locale));
		
		// Control version information
		
		details.setDate(object.getDate());
		details.setModifiedDate(object.getModifiedDate());
		details.setIssuedDate(object.getIssuedDate());

		details.setCreators(object.getCreators());
		details.setContributors(object.getContributors());
		details.setPublishers(object.getPublishers());
		details.setCreatorAgents(object.getCreatorAgents());
		details.setContributorAgents(object.getContributorAgents());
		details.setPublisherAgents(object.getPublisherAgents());

		details.setLicenseLabel(object.getLicenseLabel());
		
		// Not used
//		details.setVersion(object.getVersion());

		
		details.setDataDump(object.getdataDump());
		details.setSparqlEndpoint(object.getSparqlEndpoint());
		details.setHomepage(object.getHomepage());
		details.setVocabularies(object.getVocabularies());
		
		return details;
    }


    
}
