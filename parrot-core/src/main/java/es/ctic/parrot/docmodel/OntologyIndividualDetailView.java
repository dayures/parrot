package es.ctic.parrot.docmodel;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Locale;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.OntologyIndividual;

public class OntologyIndividualDetailView extends AbstractOntologicalObjectDetailView implements DetailView {
	
    private static final Logger logger = Logger.getLogger(OntologyIndividualDetailView.class);

	private Collection<DocumentableObjectReference> types = new LinkedList<DocumentableObjectReference>();

	private OntologyIndividualDetailView() {
        logger.debug("Created " + this.getClass());
	}

	public Collection<DocumentableObjectReference> getTypes() {
		return Collections.unmodifiableCollection(this.types);
	}

	public void addAllTypes(Collection<DocumentableObjectReference> types) {
		this.types.addAll(types);
	}

    public static OntologyIndividualDetailView createFromIndividual(OntologyIndividual object, Locale locale) {
		
    	OntologyIndividualDetailView details = new OntologyIndividualDetailView();
    	details.setLabel(object.getLabel(locale));
		details.setUri(object.getURI());
		details.addAllTypes(DocumentableObjectReference.createReferences(object.getTypes(),locale));
		details.setInverseRuleReferences(DocumentableObjectReference.createReferences(object.getInverseRuleReferences(), locale));
		details.setLabels(object.getLabels());
		details.setRelatedDocuments(object.getRelatedDocuments(locale));

		details.setAnchor(object.getLocalName());
		details.setIdentifier(object.getIdentifier());
		details.setIsDefinedBy(DocumentableObjectReference.createReference(object.getIsDefinedBy(),locale));
		details.setDeprecated(object.isDeprecated());

		return details;
	}

}
