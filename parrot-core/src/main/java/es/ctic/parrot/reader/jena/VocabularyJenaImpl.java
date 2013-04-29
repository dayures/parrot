package es.ctic.parrot.reader.jena;

import com.hp.hpl.jena.ontology.OntResource;

import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.de.Vocabulary;
import es.ctic.parrot.transformers.DocumentableObjectVisitor;
import es.ctic.parrot.transformers.TransformerException;

public class VocabularyJenaImpl extends AbstractJenaDocumentableObject implements Vocabulary {
    
    public VocabularyJenaImpl(OntResource resource, DocumentableObjectRegister register, OntResourceAnnotationStrategy annotationStrategy) {
        super(resource, register, annotationStrategy);
    }
    
    public Object accept(DocumentableObjectVisitor visitor)
            throws TransformerException {
        return visitor.visit(this);
    }

    public String getKindString() {
        return Kind.VOCABULARY.toString();
    }

	public String getPreferredPrefix() {
		return getAnnotationStrategy().getPreferredPrefix(getOntResource());
	}
	
	public String getPreferredNamespace() {
		return getAnnotationStrategy().getPreferredNamespace(getOntResource());
	}

	public String getModifiedDate() {
		return getAnnotationStrategy().getModifiedDate(getOntResource());
	}

	public String getClassNumber() {
		return getAnnotationStrategy().getClassNumber(getOntResource());
	}

	public String getPropertyNumber() {
		return getAnnotationStrategy().getPropertyNumber(getOntResource());
	}

	public String getHomepage() {
		return getAnnotationStrategy().getHomepage(getOntResource());
	}
}
