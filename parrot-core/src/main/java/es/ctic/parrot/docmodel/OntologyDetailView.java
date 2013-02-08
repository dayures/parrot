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
	private String versionIRI;
	private String priorVersion;
	private String incompatibleWith;
	private Collection<DocumentableObjectReference> defines;
	private Collection<DocumentableObjectReference> imports;


	/**
	 * Constructs a ontology detail view (Suppress default constructor for noninstantiability).
	 */
	private OntologyDetailView(){
		logger.debug("Created " + this.getClass());
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
		details.setVersionIRI(object.getVersionIRI());
		details.setPriorVersion(object.getPriorVersion());
		details.setIncompatibleWith(object.getIncompatibleWith());


		details.setLexicalInformation(LexicalInformation.createFromLabels(object.getLabels(), details.getLabel(), locale));
		details.setRelatedDocuments(object.getRelatedDocuments(locale));
		
		details.setAnchor(object.getLocalName());
		details.setIdentifier(object.getIdentifier());
		details.setIsDefinedBy(DocumentableObjectReference.createReference(object.getIsDefinedBy(),locale));
		details.setDeprecated(object.isDeprecated());
		
		details.setDefines(DocumentableObjectReference.createReferences(object.getDefines(),locale));
		details.setImports(DocumentableObjectReference.createReferences(object.getImports(),locale));
		
		details.setStatus(object.getStatus());
		
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

	/**
	 * @param imports the imports to set
	 */
	public void setImports(Collection<DocumentableObjectReference> imports) {
		this.imports = imports;
	}

	/**
	 * @return the imports
	 */
	public Collection<DocumentableObjectReference> getImports() {
		return imports;
	}
	
	/**
	 * Returns the version IRI.
	 * @return the version IRI.
	 */
	public String getVersionIRI() {
		return versionIRI;
	}

	/**
	 * Set the version IRI.
	 * @param versionIRI version IRI to set.
	 */
	private void setVersionIRI(String versionIRI) {
		this.versionIRI = versionIRI;
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
	 * Returns the prior version IRI.
	 * @return the prior version IRI.
	 */
	public String getPriorVersion() {
		return priorVersion;
	}
	
	/**
	 * Set the prior version IRI.
	 * @param priorVersion prior version IRI to set.
	 */
	private void setPriorVersion(String priorVersion) {
		this.priorVersion = priorVersion;
	}

	/**
	 * Returns the URI of an ontology incompatible with this version.
	 * @returns the URI of an ontology incompatible with this version.
	 */
	public String getIncompatibleWith() {
		return incompatibleWith;
	}

	/**
	 * Set the URI of an ontology incompatible with this version.
	 * @param incompatibleWith the URI of an ontology incompatible with this version.
	 */
	private void setIncompatibleWith(String incompatibleWith) {
		this.incompatibleWith = incompatibleWith;
	}
}
