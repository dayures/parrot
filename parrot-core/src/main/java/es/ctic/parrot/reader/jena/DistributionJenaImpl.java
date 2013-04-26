package es.ctic.parrot.reader.jena;

import com.hp.hpl.jena.ontology.OntResource;

import es.ctic.parrot.de.Distribution;
import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.transformers.DocumentableObjectVisitor;
import es.ctic.parrot.transformers.TransformerException;

public class DistributionJenaImpl extends AbstractJenaDocumentableObject implements Distribution {

	/**
	 * Constructs an DistributionJenaImpl element, setting the ontResource, the register and the annotation strategy.
	 * @param ontResource the ontResource.
	 * @param register the register.
	 * @param annotationStrategy the annotation strategy.
	 */
    public DistributionJenaImpl(OntResource resource, DocumentableObjectRegister register, OntResourceAnnotationStrategy annotationStrategy) {
        super(resource, register, annotationStrategy);
	}

    public String getKindString() {
        return Kind.DISTRIBUTION.toString();
    }	

	public Object accept(DocumentableObjectVisitor visitor)	throws TransformerException {
        return visitor.visit(this);
	}

	public String getAccessURL(){
		return getAnnotationStrategy().getAccessURL(getOntResource());
	}
	
	public String getDownloadURL(){
		return getAnnotationStrategy().getDownloadURL(getOntResource());
	}
	public String getByteSize(){
		return getAnnotationStrategy().getByteSize(getOntResource());
	}

}
