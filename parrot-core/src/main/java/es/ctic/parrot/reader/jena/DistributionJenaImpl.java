package es.ctic.parrot.reader.jena;

import com.hp.hpl.jena.ontology.OntResource;

import es.ctic.parrot.de.Distribution;
import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.de.MIMEType;
import es.ctic.parrot.transformers.DocumentableObjectVisitor;
import es.ctic.parrot.transformers.TransformerException;

/**
 * An implementation of {@link es.ctic.parrot.de.Distribution} coupled to <a href="http://openjena.org/">Jena</a>.
 * 
 * @author Carlos Tejo Alonso (<a href="http://www.fundacionctic.org">Fundación CTIC</a>)
 * @version 1.0
 * @since 1.0
 *
 */
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

	public MIMEType getMIMEType(){
		return getAnnotationStrategy().getMIMEType(getOntResource());
	}
}
