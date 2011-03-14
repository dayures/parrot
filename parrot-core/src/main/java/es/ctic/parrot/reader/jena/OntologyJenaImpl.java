package es.ctic.parrot.reader.jena;

import java.util.Collection;

import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.shared.JenaException;

import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.transformers.DocumentableObjectVisitor;
import es.ctic.parrot.transformers.TransformerException;

/**
 * An implementation of the Ontology (documentable element) interface coupled to <a href="http://openjena.org/">Jena</a>.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class OntologyJenaImpl extends AbstractJenaDocumentableObject implements es.ctic.parrot.de.Ontology {
	
	public OntologyJenaImpl(OntResource ontResource, DocumentableObjectRegister register, OntResourceAnnotationStrategy annotationStrategy) {
		super(ontResource, register, annotationStrategy);
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

	public Collection<String> getCreators() {
		return getAnnotationStrategy().getCreators(getOntResource());
	}

	public Collection<String> getContributors() {
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
