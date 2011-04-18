package es.ctic.parrot.reader.jena;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.shared.JenaException;

import es.ctic.parrot.de.DocumentableObject;
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

	private static final String RDF_SCHEMA_IS_DEFINED_BY = "http://www.w3.org/2000/01/rdf-schema#isDefinedBy";

	private Collection<DocumentableObject> defines;

	/**
	 * Constructs an ontology.
	 * @param ontology the Jena ontology to set.
	 * @param register the register to set
	 * @param annotationStrategy the annotation strategy to set.
	 */
	public OntologyJenaImpl(OntResource ontology, DocumentableObjectRegister register, OntResourceAnnotationStrategy annotationStrategy) {
		super(ontology, register, annotationStrategy);
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

    public String getKindString() {
        return Kind.ONTOLOGY.toString();
    }

    /**
     * Returns the documentable objects that are defined by this ontology.
	 * @return the documentable objects that are defined by this ontology.
	 */
	public Collection<DocumentableObject> getDefines() {
		
		OntModel ontModel = getOntResource().getOntModel();
		Collection <Resource> df = new HashSet<Resource>();
		
		if(defines == null){
			
			StmtIterator listStatements = ontModel.listStatements(null, ResourceFactory.createProperty(RDF_SCHEMA_IS_DEFINED_BY) ,getOntResource());
			while (listStatements.hasNext()){
				Statement statement = listStatements.next();
				df.add(ontModel.getOntResource(statement.getSubject().asResource()));
			}
			
			defines = resourceIteratorToDocumentableObjectList(df.iterator());
		}
		return Collections.unmodifiableCollection(defines);
	}

}
