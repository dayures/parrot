package es.ctic.parrot.reader.jena;

import com.hp.hpl.jena.ontology.OntResource;

import es.ctic.parrot.de.Catalog;
import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.transformers.DocumentableObjectVisitor;
import es.ctic.parrot.transformers.TransformerException;

public class CatalogJenaImpl extends AbstractJenaDocumentableObject implements Catalog {

    public static final String VOID_NS = "http://rdfs.org/ns/void#";
    public static final String DCAT_NS = "http://www.w3.org/ns/dcat#";
    public static final String DCAT_DERI_NS = "http://vocab.deri.ie/dcat#";
    
    public CatalogJenaImpl(OntResource resource, DocumentableObjectRegister register, OntResourceAnnotationStrategy annotationStrategy) {
        super(resource, register, annotationStrategy);
    }
    
    public Object accept(DocumentableObjectVisitor visitor)
            throws TransformerException {
        return visitor.visit(this);
    }

    public String getKindString() {
        return Kind.CATALOG.toString();
    }

	public String getHomepage() {
		return getAnnotationStrategy().getHomepage(getOntResource());
	}
	
}
