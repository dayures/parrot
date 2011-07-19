package es.ctic.parrot.docmodel;

import java.util.Collection;
import java.util.Locale;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.Ontology;

/**
 * A detailed view of a ontology.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class OntologyDetailView extends AbstractOntologicalObjectDetailView implements DetailView{
	
    private static final Logger logger = Logger.getLogger(OntologyDetailView.class);

	private String preferredPrefix;
	private String preferredNamespace;
	private Collection<DocumentableObjectReference> defines;


	/**
	 * Constructs a ontology detail view (Suppress default constructor for noninstantiability).
	 */
	private OntologyDetailView(){
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
	 * Returns a detailed view for the ontology given.
	 * @param object the ontology.
	 * @param locale the locale.
	 * @return a detailed view for an ontology.
	 */
    public static OntologyDetailView createFromOntology(Ontology object, Locale locale) {
    	
	    OntologyDetailView details = new OntologyDetailView();
		
	    details.setUri(object.getURI());
		details.setUriFragment(object.getUriFragment());
		details.setLabel(object.getLabel(locale));
		details.setDescription(object.getDescription(locale));

		// Control version information
		details.setVersion(object.getVersion());

		details.setDate(object.getDate());
		details.setModifiedDate(object.getModifiedDate());
		details.setIssuedDate(object.getIssuedDate());
		
		details.setCreators(object.getCreators());
		details.setContributors(object.getContributors());
		details.setPublishers(object.getPublishers());
		details.setCreatorAgents(object.getCreatorAgents());
		details.setContributorAgents(object.getContributorAgents());
		details.setPublisherAgents(object.getPublisherAgents());
		
		details.setRights(object.getRights());
		details.setLicenseLabel(object.getLicenseLabel());

		details.setPreferredPrefix(object.getPreferredPrefix());
		details.setPreferredNamespace(object.getPreferredNamespace());


		details.setLexicalInformation(LexicalInformation.createFromLabels(object.getLabels(), details.getLabel(), locale));
		details.setRelatedDocuments(object.getRelatedDocuments(locale));
		
		details.setAnchor(object.getLocalName());
		details.setIdentifier(object.getIdentifier());
		details.setIsDefinedBy(DocumentableObjectReference.createReference(object.getIsDefinedBy(),locale));
		details.setDeprecated(object.isDeprecated());
		
		details.setDefines(DocumentableObjectReference.createReferences(object.getDefines(),locale));
		
		return details;

    }

	/**
	 * @param defines the defines to set
	 */
	private void setDefines(Collection<DocumentableObjectReference> defines) {
		this.defines = defines;
	}

	/**
	 * @return the defines
	 */
	public Collection<DocumentableObjectReference> getDefines() {
		return defines;
	}

}
