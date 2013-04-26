package es.ctic.parrot.docmodel;

import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import org.apache.log4j.Logger;
import es.ctic.parrot.de.Catalog;

/**
 * A detailed view of a catalog.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class CatalogDetailView extends AbstractVersionableDetailView{

    private static final Logger logger = Logger.getLogger(CatalogDetailView.class);
    
	private String homepage;	
	private Collection<DocumentableObjectReference> datasets;
    private String spatial;

	/**
	 * Constructs a catalog detail view (Suppress default constructor for noninstantiability).
	 */
	private CatalogDetailView() {
        logger.debug("Created " + this.getClass());
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
	 * Returns the datasets.
	 * @return the datasets.
	 */
	public Collection<DocumentableObjectReference> getDatasets() {
		return Collections.unmodifiableCollection(this.datasets);
	}
	
	/**
	 * Set the datasets to this detailed view.
	 * @param datasets the datasets to set to this detailed view.
	 */
	private void setDatasets(Collection<DocumentableObjectReference> datasets) {
		this.datasets = datasets;
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
	 * Returns a detailed view for the catalog given.
	 * @param object the catalog.
	 * @param locale the locale.
	 * @return a detailed view for a catalog.
	 */
    public static CatalogDetailView createFromCatalog(Catalog object, Locale locale) {
    	
	    CatalogDetailView details = new CatalogDetailView();
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


		details.setHomepage(object.getHomepage());
		details.setDatasets(DocumentableObjectReference.createReferences(object.getDatasets(),locale));
        details.setSpatial(object.getSpatial());

		return details;
    }
    
}
