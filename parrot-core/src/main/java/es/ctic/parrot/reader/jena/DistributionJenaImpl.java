package es.ctic.parrot.reader.jena;

import java.util.Collection;
import java.util.Locale;

import com.hp.hpl.jena.ontology.OntResource;

import es.ctic.parrot.de.AbstractVersionable;
import es.ctic.parrot.de.Distribution;
import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.Label;
import es.ctic.parrot.de.RelatedDocument;
import es.ctic.parrot.de.URIIdentifier;
import es.ctic.parrot.transformers.DocumentableObjectVisitor;
import es.ctic.parrot.transformers.TransformerException;

public class DistributionJenaImpl extends AbstractVersionable implements Distribution {

	/**
	 * Constructs an abstract Jena documentable element, setting the ontResource, the annotation strategy.
	 * @param ontResource the ontResource.
	 * @param annotationStrategy the annotation strategy.
	 */
	public DistributionJenaImpl(OntResource ontResource, OntResourceAnnotationStrategy annotationStrategy) {
		super();
		this.setOntResource(ontResource);
		this.setAnnotationStrategy(annotationStrategy);
	}


	/**
	 * Returns the identifier.
	 * @return the identifier.
	 */
	public Identifier getIdentifier() {
		if (getOntResource().isAnon() == true){
			return new JenaAnonymousIdentifier(getOntResource().getId());
		} else{
			return new URIIdentifier(getOntResource().getURI());
		}
	}

    public String getDescription(Locale locale) {
		return getAnnotationStrategy().getDescription(getOntResource(), locale);
	}
    
	public Collection<Label> getLabels(Locale locale){
   		return getAnnotationStrategy().getLabels(getOntResource(), locale);
   	}

    public Collection<Label> getLabels(){
   		return getAnnotationStrategy().getLabels(getOntResource(), null);
   	}

    public String getLabel(String language) {
    	return getLabel(new Locale(language));
    }

    public String getLabel(Locale locale) {
    	String label = getAnnotationStrategy().getLabel(getOntResource(), locale);
    	
    	if (label != null){
    		return label;
    	} else {
    		return getKindString() + getIdentifier().toString();
    	}
    }
    
    public String getLabel() {
        return getAnnotationStrategy().getLabel(getOntResource(), null);
    }

	public String getKindString() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public Collection<RelatedDocument> getRelatedDocuments(Locale locale) {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}
	
	public String getUriFragment() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public String getURI() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public Object accept(DocumentableObjectVisitor visitor)	throws TransformerException {
        return visitor.visit(this);
	}



}
