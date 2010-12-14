package es.ctic.parrot.docmodel;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.OntologyClass;
import es.ctic.parrot.de.OntologyIndividual;

public class OntologyIndividualDetailView extends AbstractOntologicalObjectDetailView implements DetailView {
	
	private OntologyIndividual ontologyIndividual;
	private List<OntologyClass> types = new LinkedList<OntologyClass>();

	public OntologyIndividualDetailView(OntologyIndividual ontologyIndividual) {
		super();
		this.ontologyIndividual = ontologyIndividual;
	}

	public Identifier getIdentifier() {
		return ontologyIndividual.getIdentifier();
	}

	public String getAnchor() {
		return getOntologyIndividual().getLocalName();
	}

	public void setOntologyIndividual(OntologyIndividual ontologyIndividual) {
		this.ontologyIndividual = ontologyIndividual;
	}

	public OntologyIndividual getOntologyIndividual() {
		return ontologyIndividual;
	}

	public List<OntologyClass> getTypes() {
		return Collections.unmodifiableList(this.types);
	}

	public void addAllTypes(Collection<OntologyClass> types) {
		this.types.addAll(types);
	}

}
