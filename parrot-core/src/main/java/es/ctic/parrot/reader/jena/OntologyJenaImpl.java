package es.ctic.parrot.reader.jena;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.shared.JenaException;
import com.hp.hpl.jena.vocabulary.RDFS;

import es.ctic.parrot.de.DocumentableObject;
import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.transformers.DocumentableObjectVisitor;
import es.ctic.parrot.transformers.TransformerException;

/**
 * An implementation of {@link es.ctic.parrot.de.Ontology} coupled to <a href="http://openjena.org/">Jena</a>.
 * 
 * @author Carlos Tejo Alonso (<a href="http://www.fundacionctic.org">Fundación CTIC</a>)
 * @version 1.0
 * @since 1.0
 *
 */
public class OntologyJenaImpl extends AbstractJenaDocumentableObject implements es.ctic.parrot.de.Ontology {

	private static final Logger logger = Logger.getLogger(OntologyJenaImpl.class);

	private Collection<DocumentableObject> defines;
	private Collection<DocumentableObject> imports;

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

	public String getVersionIRI() {
		return getAnnotationStrategy().getVersionIRI(getOntResource());
	}

	public String getPriorVersion() {
		return getAnnotationStrategy().getPriorVersion(getOntResource());
	}
	
	public String getIncompatibleWith() {
		return getAnnotationStrategy().getIncompatibleWith(getOntResource());
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
			
			StmtIterator listStatements = ontModel.listStatements(null, ResourceFactory.createProperty(RDFS.isDefinedBy.getURI()) ,getOntResource());
			while (listStatements.hasNext()){
				Statement statement = listStatements.next();
				df.add(ontModel.getOntResource(statement.getSubject().asResource()));
			}
			
			defines = resourceIteratorToDocumentableObjectList(df.iterator());
		}
		return Collections.unmodifiableCollection(defines);
	}
	
    /**
     * Returns the imports of this ontology.
	 * @return the imports of this ontology.
	 */
	public Collection<DocumentableObject> getImports() {
		
		OntModel ontModel = getOntResource().getOntModel();
		Collection <Resource> _imports = new HashSet<Resource>();
		
		if(imports == null){
			
			StmtIterator listStatements = ontModel.listStatements(getOntResource(), ontModel.getProfile().IMPORTS(), (RDFNode) null);
			while (listStatements.hasNext()){
				Statement statement = listStatements.next();
				_imports.add(ontModel.getOntResource(statement.getObject().asResource()));
	            logger.debug(getOntResource().getURI()+"<imports>"+ statement.getResource().getURI());
			}
			
			imports = resourceIteratorToDocumentableObjectList(_imports.iterator());
		}
		return Collections.unmodifiableCollection(imports);
	}

}
