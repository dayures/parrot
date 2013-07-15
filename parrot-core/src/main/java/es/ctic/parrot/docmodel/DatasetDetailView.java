package es.ctic.parrot.docmodel;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
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
	private String dcIdentifier;
	private String landingPage;
	private Collection<String> keywords;
    private Collection<DocumentableObjectReference> catalogs;
	private String spatial;
    private Collection<DistributionDetailView> distributionDetailViews = new HashSet<DistributionDetailView>();
    private String language;
	private Collection<String> pages;

	/**
	 * Constructs a dataset detail view (Suppress default constructor for noninstantiability).
	 */
	private DatasetDetailView() {
        logger.debug("Created " + this.getClass());
    }
	
	/**
	 * Set the data dump.
	 * @param dataDump the dataDump to set.
	 */
    private void setDataDump(String dataDump) {
		this.dataDump = dataDump;
	}

	/**
	 * Returns the dataDump.
	 * @return the dataDump.
	 */
	public String getDataDump() {
		return dataDump;
	}

	/**
	 * Set the SPARQL endpoint.
	 * @param sparqlEndpoint the sparqlEnpoint to set
	 */
	private void setSparqlEndpoint(String sparqlEndpoint) {
		this.sparqlEndpoint = sparqlEndpoint;
	}

	/**
	 * Returns the sparqlEndpoint.
	 * @return the sparqlEndpoint.
	 */
	public String getSparqlEndpoint() {
		return sparqlEndpoint;
	}

	/**
	 * Set the homepage.
	 * @param homepage the homepage to set.
	 */
	private void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	/**
	 * Returns the homepage.
	 * @return the homepage.
	 */
	public String getHomepage() {
		return homepage;
	}
	
	/**
	 * Set the vocabularies.
	 * @param vocabularies the vocabularies to set.
	 */
	private void setVocabularies(Collection<String> vocabularies) {
		this.vocabularies = vocabularies;
	}

	/**
	 * Returns the vocabularies.
	 * @return the vocabularies.
	 */
	public Collection<String> getVocabularies() {
		return vocabularies;
	}
	
	/**
	 * Returns the Dublin Core identifier.
	 * @return the Dublin Core identifier.
	 */

	public String getDcIdentifier() {
		return dcIdentifier;
	}

	/**
	 * Set the Dublin Core identifier.
	 * @param dcIdentifier the Dublin Core identifier to set.
	 */
	private void setDcIdentifier(String dcIdentifier) {
		this.dcIdentifier = dcIdentifier;
	}
	
	/**
	 * Returns the URL of the landing page.
	 * @return the URL of the landing page.
	 */
	public String getLandingPage() {
		return landingPage;
	}
	
	/**
	 * Set the landing page.
	 * @param landingPage the landing page to set.
	 */
	private void setLandingPage(String landingPage) {
		this.landingPage = landingPage;
	}
	
	/**
	 * Returns the keywords.
	 * @return the keywords.
	 */
	public Collection<String> getKeywords() {
		return keywords;
	}

	/**
	 * Set the keywords.
	 * @param keywords the keywords to set.
	 */
	private void setKeywords(Collection<String> keywords) {
		this.keywords = keywords;
	}
	
	/**
	 * Returns the catalogs.
	 * @return the catalogs.
	 */
	public Collection<DocumentableObjectReference> getCatalogs() {
		return Collections.unmodifiableCollection(this.catalogs);
	}
	
	/**
	 * Set the catalogs to this detailed view.
	 * @param catalogs the catalogs to set to this detailed view.
	 */
	private void setCatalogs(Collection<DocumentableObjectReference> catalogs) {
		this.catalogs = catalogs;
	}
	
	/**
	 * Returns the spatial coverage.
	 * @return the spatial coverage.
	 */
	public String getSpatial() {
		return spatial;
	}

	/**
	 * Set the spatial coverage.
	 * @param spatial the spatial coverage to set.
	 */
	private void setSpatial(String spatial) {
		this.spatial = spatial;
	}

	/**
	 * Returns the distributions.
	 * @return the distributions.
	 */
	public Collection<DistributionDetailView> getDistributions() {
		return Collections.unmodifiableCollection(distributionDetailViews);
	}
	
    /** 
     * Adds a given detailed distribution view into this document.
     * @param details the detailed distribution view.
     */
    public void addDistributionDetailView(DistributionDetailView details) {
        if (details != null){
        	this.distributionDetailViews.add(details);
        	logger.debug("Added distribution  details view"+details);
        }
    }
    
	/**
	 * Returns the language.
	 * @return the language. 
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * Set the language.
	 * @param language The language to set.
	 */
	private void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the pages
	 */
	public Collection<String> getPages() {
		return pages;
	}

	/**
	 * @param pages the pages to set
	 */
	private void setPages(Collection<String> pages) {
		this.pages = pages;
	}
    
	/**
	 * Returns a detailed view for the dataset given.
	 * @param object the dataset.
	 * @param locale the locale.
	 * @return a detailed view for a dataset.
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
		details.setDcIdentifier(object.getDcIdentifier());
		details.setLandingPage(object.getLandingPage());
		details.setKeywords(object.getKeywords());
        details.setCatalogs(DocumentableObjectReference.createReferences(object.getCatalogs(),locale));
		details.setSpatial(object.getSpatial());
        details.setLanguage(object.getLanguage());
		details.setPages(object.getPages());

		return details;
    }




    
}
