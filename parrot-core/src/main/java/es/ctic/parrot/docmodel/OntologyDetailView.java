package es.ctic.parrot.docmodel;

import java.util.List;
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

	private String version;
	private List<String> creators;
	private List<String> contributors;
	private String preferredPrefix;
	private String preferredNamespace;
	private String date;
	private String rights;
	private String licenseLabel;

	/**
	 * Constructs a ontology detail view (Suppress default constructor for noninstantiability).
	 */
	private OntologyDetailView(){
		logger.debug("Created " + this.getClass());
	}
	
	/**
	 * Set the version.
	 * @param version version to set
	 */
	public void setVersion(String version) {
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
	 * Set the creators.
	 * @param creators creators to set
	 */
	public void setCreators(List<String> creators) {
		this.creators = creators;
	}

	/**
	 * Returns the creators.
	 * @return the creators.
	 */	
	public List<String> getCreators() {
		return creators;
	}

	/**
	 * Set the preferred prefix.
	 * @param preferredPrefix preferred prefix to set
	 */
	public void setPreferredPrefix(String preferredPrefix) {
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
	 * Set the contributors.
	 * @param contributors contributors to set
	 */
	public void setContributors(List<String> contributors) {
		this.contributors = contributors;
	}

	/**
	 * Returns the contributors.
	 * @return the contributors.
	 */
	public List<String> getContributors() {
		return contributors;
	}

	/**
	 * Set the date.
	 * @param date date to set
	 */	
	public void setDate(String date) {
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
	 * Set the preferred namespace.
	 * @param preferredNamespace preferred namespace to set
	 */
	public void setPreferredNamespace(String preferredNamespace) {
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
	 * Set information about the ontology rights.
	 * @param rights information about the ontology rights to set
	 */
	public void setRights(String rights) {
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
	 * @param licenseLabel the license's label to set
	 */
	public void setLicenseLabel(String licenseLabel) {
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
	 * Returns a detailed view for the ontology given.
	 * @param object the ontology.
	 * @param locale the locale.
	 * @return a detailed view for an ontology.
	 */
    public static OntologyDetailView createFromOntology(Ontology object, Locale locale) {
    	
	    OntologyDetailView details = new OntologyDetailView();
		
	    details.setUri(object.getURI());
		details.setLabel(object.getLabel(locale));
		details.setComment(object.getComment(locale));
		details.setVersion(object.getVersion());
		details.setCreators(object.getCreators());
		details.setDate(object.getDate());
		details.setRights(object.getRights());
		details.setLicenseLabel(object.getLicenseLabel());
		details.setContributors(object.getContributors());
		details.setPreferredPrefix(object.getPreferredPrefix());
		details.setPreferredNamespace(object.getPreferredNamespace());
		details.setLabels(object.getLabels());
		details.setRelatedDocuments(object.getRelatedDocuments(locale));
		
		details.setAnchor(object.getLocalName());
		details.setIdentifier(object.getIdentifier());
		details.setIsDefinedBy(DocumentableObjectReference.createReference(object.getIsDefinedBy(),locale));
		details.setDeprecated(object.isDeprecated());
		
		return details;

    }

}
