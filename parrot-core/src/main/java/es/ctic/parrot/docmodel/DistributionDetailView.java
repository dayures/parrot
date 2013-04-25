package es.ctic.parrot.docmodel;

import java.util.Locale;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.Catalog;
import es.ctic.parrot.de.Distribution;

/**
 * A detailed view of a distribution.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class DistributionDetailView extends AbstractVersionableDetailView{

    private static final Logger logger = Logger.getLogger(DistributionDetailView.class);
    

	/**
	 * Constructs a vocabulary detail view (Suppress default constructor for noninstantiability).
	 */
	private DistributionDetailView() {
        logger.debug("Created " + this.getClass());
    }	


	
	/**
	 * Returns a detailed view for the vocabulary given.
	 * @param object the vocabulary.
	 * @param locale the locale.
	 * @return a detailed view for a vocabulary.
	 */
    public static DistributionDetailView createFromDistribution(Distribution object, Locale locale) {
    	
	    DistributionDetailView details = new DistributionDetailView();
		details.setLabel(object.getLabel(locale));
		details.setDescription(object.getDescription(locale));		
		details.setLexicalInformation(LexicalInformation.createFromLabels(object.getLabels(), details.getLabel(), locale));
		
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

		return details;
    }
    
}
