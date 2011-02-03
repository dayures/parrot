package es.ctic.parrot.reader.jena;

import java.util.List;

import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.ontology.Ontology;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.shared.JenaException;

import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.transformers.DocumentableObjectVisitor;
import es.ctic.parrot.transformers.TransformerException;

public class OntologyJenaImpl extends AbstractJenaDocumentableObject implements es.ctic.parrot.de.Ontology {
	
	private static final String VANN_PREFERRED_PREFIX = "http://purl.org/vocab/vann/preferredNamespacePrefix";


	public OntologyJenaImpl(OntResource ontResource, DocumentableObjectRegister register, OntResourceAnnotationStrategy annotationStrategy) {
		super(ontResource, register, annotationStrategy);
	}
	
	public Ontology getOntology(){
		return (Ontology) getOntResource();
	}

	public Object accept(DocumentableObjectVisitor visitor) throws TransformerException {
        try{
        	return visitor.visit(this);
        }catch (JenaException e) {
        	throw new TransformerException(e);
        }
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
		return getAnnotationStrategy().getCreators(getOntResource());
	}

	public List<String> getContributors() {
		return getAnnotationStrategy().getContributors(getOntResource());
	}

    public String getKindString() {
        return Kind.ONTOLOGY.toString();
    }
    
}
