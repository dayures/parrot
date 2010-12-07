package es.ctic.parrot.reader.jena;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.ontology.Ontology;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.StmtIterator;

import es.ctic.parrot.transformers.DocumentableObjectVisitor;

public class OntologyJenaImpl extends AbstractJenaDocumentableObject implements es.ctic.parrot.de.Ontology {
	
	public OntologyJenaImpl(OntResource ontResource) {
		super(ontResource);
	}
	
	public Ontology getOntology(){
		return (Ontology) getOntResource();
	}

	public Object accept(DocumentableObjectVisitor visitor) {
		return visitor.visit(this);
	}

	public String getPreferredPrefix() {
		if (getOntology().hasProperty(ResourceFactory.createProperty("http://purl.org/vocab/vann/preferredNamespacePrefix")))
			return getOntology().getProperty(ResourceFactory.createProperty("http://purl.org/vocab/vann/preferredNamespacePrefix")).getLiteral().getString();
		else
			return null;
	}

	public String getVersion() {
		return getOntology().getVersionInfo();
	}

	public List<String> getEditors() {
		
		ArrayList<String> editors = new ArrayList<String>();
		StmtIterator it = getOntology().listProperties(ResourceFactory.createProperty("http://purl.org/dc/terms/creator"));
		while(it.hasNext()){
			editors.add(it.nextStatement().getLiteral().getString());
		}
		return editors;
	}

	public List<String> getContributors() {
		ArrayList<String> contributors = new ArrayList<String>();
		StmtIterator it = getOntology().listProperties(ResourceFactory.createProperty("http://purl.org/dc/terms/contributor"));
		while(it.hasNext()){
			contributors.add(it.nextStatement().getLiteral().getString());
		}
		return contributors;
	}


}
