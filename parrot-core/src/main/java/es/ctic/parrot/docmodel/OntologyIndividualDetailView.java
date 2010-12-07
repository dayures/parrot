package es.ctic.parrot.docmodel;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.OntologyClass;
import es.ctic.parrot.de.OntologyIndividual;

public class OntologyIndividualDetailView implements DetailView {
	
	private OntologyIndividual ontologyIndividual;
	private String label;
	private String uri;
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

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getUri() {
		return uri;
	}
	
	public List<OntologyClass> getTypes() {
		return Collections.unmodifiableList(this.types);
	}

	public void addAllTypes(Collection<OntologyClass> types) {
		this.types.addAll(types);
	}

}
