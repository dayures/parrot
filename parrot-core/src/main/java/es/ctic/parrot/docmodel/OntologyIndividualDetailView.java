package es.ctic.parrot.docmodel;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Locale;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.OntologyIndividual;
import es.ctic.parrot.de.Triple;

/**
 * A detailed view of a ontology individual.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class OntologyIndividualDetailView extends AbstractOntologicalObjectDetailView implements DetailView {
	
    private static final Logger logger = Logger.getLogger(OntologyIndividualDetailView.class);

	private Collection<DocumentableObjectReference> types = new LinkedList<DocumentableObjectReference>();

	private Collection<Triple> triplesAsSubject = new HashSet<Triple>();
	private Collection<Triple> triplesAsObject = new HashSet<Triple>();

	/**
	 * Constructs a ontology individual detail view (Suppress default constructor for noninstantiability).
	 */
	private OntologyIndividualDetailView() {
        logger.debug("Created " + this.getClass());
	}

	/**
	 * Returns the collection of the classes that this individual is instance of.
	 * @return the collection of the classes that this individual is instance of.
	 */	
	public Collection<DocumentableObjectReference> getTypes() {
		return Collections.unmodifiableCollection(this.types);
	}

	/**
	 * Adds the types given to this individual.
	 * @param types the types.
	 */
	private void addAllTypes(Collection<DocumentableObjectReference> types) {
		this.types.addAll(types);
	}

	/**
	 * Returns a detailed view for the ontology individual given.
	 * @param object the ontology individual.
	 * @param locale the locale.
	 * @return a detailed view for an ontology individual.
	 */
    public static OntologyIndividualDetailView createFromIndividual(OntologyIndividual object, Locale locale) {
		
    	OntologyIndividualDetailView details = new OntologyIndividualDetailView();
    	
    	details.setLabel(object.getLabel(locale));
		details.setUri(object.getURI());
		details.setUriFragment(object.getUriFragment());
		details.setDescription(object.getDescription(locale));
		
		// Control version information
		details.setVersion(object.getVersion());
		details.setDate(object.getDate());

		details.setCreators(object.getCreators());
		details.setContributors(object.getContributors());
		details.setPublishers(object.getPublishers());
		
		details.setCreatorAgents(object.getCreatorAgents());
		details.setContributorAgents(object.getContributorAgents());
		details.setPublisherAgents(object.getPublisherAgents());

		details.setRights(object.getRights());
		details.setLicenseLabel(object.getLicenseLabel());
		
		details.addAllTypes(DocumentableObjectReference.createReferences(object.getTypes(),locale));
		details.setInverseRuleReferences(DocumentableObjectReference.createReferences(object.getInverseRuleReferences(), locale));

		details.setLabels(object.getLabels());
		details.setRelatedDocuments(object.getRelatedDocuments(locale));

		details.setAnchor(object.getLocalName());
		details.setIdentifier(object.getIdentifier());
		details.setIsDefinedBy(DocumentableObjectReference.createReference(object.getIsDefinedBy(),locale));
		details.setDeprecated(object.isDeprecated());
		
		details.setTriplesAsSubject(object.getTriplesAsSubject());
		details.setTriplesAsObject(object.getTriplesAsObject());
		
		return details;
	}

	/**
	 * @param triplesAsSubject the triplesAsSubject to set
	 */
	public void setTriplesAsSubject(Collection<Triple> triplesAsSubject) {
		this.triplesAsSubject = triplesAsSubject;
	}

	/**
	 * @return the triplesAsSubject
	 */
	public Collection<Triple> getTriplesAsSubject() {
		return triplesAsSubject;
	}

	/**
	 * @param triplesAsObject the triplesAsObject to set
	 */
	public void setTriplesAsObject(Collection<Triple> triplesAsObject) {
		this.triplesAsObject = triplesAsObject;
	}

	/**
	 * @return the triplesAsObject
	 */
	public Collection<Triple> getTriplesAsObject() {
		return triplesAsObject;
	}


}
