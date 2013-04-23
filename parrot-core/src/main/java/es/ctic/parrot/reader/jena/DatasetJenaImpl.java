package es.ctic.parrot.reader.jena;

import java.util.Collection;

import com.hp.hpl.jena.ontology.OntResource;

import es.ctic.parrot.de.Dataset;
import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.transformers.DocumentableObjectVisitor;
import es.ctic.parrot.transformers.TransformerException;

public class DatasetJenaImpl extends AbstractJenaDocumentableObject implements Dataset {

    public static final String VOID_NS = "http://rdfs.org/ns/void#";
    public static final String DCAT_NS = "http://www.w3.org/ns/dcat#";
    public static final String DCAT_DERI_NS = "http://vocab.deri.ie/dcat#";
    
    public DatasetJenaImpl(OntResource resource, DocumentableObjectRegister register, OntResourceAnnotationStrategy annotationStrategy) {
        super(resource, register, annotationStrategy);
    }
    
    public Object accept(DocumentableObjectVisitor visitor)
            throws TransformerException {
        return visitor.visit(this);
    }

    public String getKindString() {
        return Kind.DATASET.toString();
    }

	public String getdataDump() {
		return getAnnotationStrategy().getDataDump(getOntResource());
	}

	public String getSparqlEndpoint() {
		return getAnnotationStrategy().getSparqlEndpoint(getOntResource());
	}

	public String getHomepage() {
		return getAnnotationStrategy().getHomepage(getOntResource());
	}
	
	public Collection<String> getVocabularies() {
		return getAnnotationStrategy().getVocabularies(getOntResource());
	}
	
	public String getDcIdentifier(){
		return getAnnotationStrategy().getDcIdentifier(getOntResource());
	}

}
