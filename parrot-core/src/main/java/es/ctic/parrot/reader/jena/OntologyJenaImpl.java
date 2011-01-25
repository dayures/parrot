package es.ctic.parrot.reader.jena;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.ontology.Ontology;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.StmtIterator;

import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.de.DocumentableObject.Kind;
import es.ctic.parrot.transformers.DocumentableObjectVisitor;
import es.ctic.parrot.transformers.TransformerException;

public class OntologyJenaImpl extends AbstractJenaDocumentableObject implements es.ctic.parrot.de.Ontology {
	
	private static final String VANN_PREFERRED_PREFIX = "http://purl.org/vocab/vann/preferredNamespacePrefix";
	private static final String DC_CREATOR = "http://purl.org/dc/elements/1.1/creator";
	private static final String DC_CONTRIBUTOR = "http://purl.org/dc/elements/1.1/contributor";

	public OntologyJenaImpl(OntResource ontResource, DocumentableObjectRegister register) {
		super(ontResource, register);
	}
	
	public Ontology getOntology(){
		return (Ontology) getOntResource();
	}

	public Object accept(DocumentableObjectVisitor visitor) throws TransformerException {
		return visitor.visit(this);
	}

	public String getPreferredPrefix() {
		if (getOntology().hasProperty(ResourceFactory.createProperty(VANN_PREFERRED_PREFIX)))
			return getOntology().getProperty(ResourceFactory.createProperty(VANN_PREFERRED_PREFIX)).getLiteral().getString();
		else
			return null;
	}

	public String getVersion() {
		return getOntology().getVersionInfo();
	}

	public List<String> getEditors() {
		
		ArrayList<String> editors = new ArrayList<String>();
		StmtIterator it = getOntology().listProperties(ResourceFactory.createProperty(DC_CREATOR));
		while(it.hasNext()){
			editors.add(it.nextStatement().getLiteral().getString());
		}
		return editors;
	}

	public List<String> getContributors() {
		ArrayList<String> contributors = new ArrayList<String>();
		StmtIterator it = getOntology().listProperties(ResourceFactory.createProperty(DC_CONTRIBUTOR));
		while(it.hasNext()){
			contributors.add(it.nextStatement().getLiteral().getString());
		}
		return contributors;
	}

    public String getKindString() {
        return Kind.ONTOLOGY.toString();
    }
    
}
