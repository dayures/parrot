package es.ctic.parrot.reader.jena;

import java.util.List;

import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.ontology.Ontology;
import com.hp.hpl.jena.shared.JenaException;

import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.transformers.DocumentableObjectVisitor;
import es.ctic.parrot.transformers.TransformerException;

public class OntologyJenaImpl extends AbstractJenaDocumentableObject implements es.ctic.parrot.de.Ontology {
	
	public OntologyJenaImpl(OntResource ontResource, DocumentableObjectRegister register, OntResourceAnnotationStrategy annotationStrategy) {
		super(ontResource, register, annotationStrategy);
	}
	
	public Ontology getOntology(){
		return getOntResource().asOntology();
	}

	public Object accept(DocumentableObjectVisitor visitor) throws TransformerException {
        try{
        	return visitor.visit(this);
        }catch (JenaException e) {
        	throw new TransformerException(e);
        }
    }

	public String getPreferredPrefix() {
		return getAnnotationStrategy().getPreferredPrefix(getOntResource());
	}
	
	public String getPreferredNamespace() {
		return getAnnotationStrategy().getPreferredNamespace(getOntResource());
	}

	public String getVersion() {
		return getAnnotationStrategy().getVersion(getOntResource());
	}

	public List<String> getEditors() {
		return getAnnotationStrategy().getCreators(getOntResource());
	}

	public List<String> getContributors() {
		return getAnnotationStrategy().getContributors(getOntResource());
	}
	
	public String getDate() {
		return getAnnotationStrategy().getDate(getOntResource());
	}
	
	public String getRights() {
		return getAnnotationStrategy().getRights(getOntResource());
	}
	
	public String getLicenseLabel() {
		return getAnnotationStrategy().getLicenseLabel(getOntResource());
	}

    public String getKindString() {
        return Kind.ONTOLOGY.toString();
    }
    
}
