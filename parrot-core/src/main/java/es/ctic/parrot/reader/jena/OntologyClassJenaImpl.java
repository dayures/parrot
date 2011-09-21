package es.ctic.parrot.reader.jena;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.RDFList;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.shared.JenaException;
import com.hp.hpl.jena.vocabulary.OWL2;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;

import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.de.OntologyClass;
import es.ctic.parrot.de.OntologyIndividual;
import es.ctic.parrot.transformers.DocumentableObjectVisitor;
import es.ctic.parrot.transformers.TransformerException;

/**
 * An implementation of the OntologyClass (documentable element) interface coupled to <a href="http://openjena.org/">Jena</a>.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class OntologyClassJenaImpl extends AbstractJenaDocumentableObject implements OntologyClass{

	private static final Logger logger = Logger.getLogger(OntologyClassJenaImpl.class);
	
    private Collection<OntologyClass> superClasses;
	private Collection<OntologyClass> subClasses;
	private Collection<OntologyClass> equivalentClasses;
	private Collection<OntologyClass> disjointClasses;
	private Collection<OntologyIndividual> individuals;
	
	/**
	 * Constructs an ontology class.
	 * @param ontclass the Jena ontClass to set.
	 * @param register the register to set
	 * @param annotationStrategy the annotation strategy to set.
	 */
	public OntologyClassJenaImpl(OntClass ontclass, DocumentableObjectRegister register, OntResourceAnnotationStrategy annotationStrategy){
		super(ontclass, register, annotationStrategy);
	}
	
	public Object accept(DocumentableObjectVisitor visitor) throws TransformerException {
        try {
        	return visitor.visit(this);
        } catch (JenaException e) {
        	throw new TransformerException(e);
        }
	}
	
	private OntClass getOntClass(){
		return  getOntResource().asClass();
	}

	public Collection<OntologyClass> getSuperClasses(){
		if(superClasses==null){
			superClasses = resourceIteratorToDocumentableObjectList(getOntClass().listSuperClasses());
		}
		return superClasses;
	}

	public Collection<OntologyClass> getSubClasses() {
		if(subClasses==null){
			subClasses = resourceIteratorToDocumentableObjectList(getOntClass().listSubClasses());
		}
		return subClasses;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.getURI()!=null) ? 0 : this.getURI().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractJenaDocumentableObject other = (AbstractJenaDocumentableObject) obj;
		if (this.getURI() == null || other.getURI()==null) {
			return false;
		}else{
			return this.getURI().equals(other.getURI());
		}
	}

	public void setSubClasses(Collection<OntologyClass> classes) {
		this.subClasses=classes;
	}

	public void setSuperClasses(Collection<OntologyClass> classes) {
		this.superClasses=classes;
	}

	public Collection<OntologyIndividual> getIndividuals() {
		if(individuals==null){
			individuals = resourceIteratorToDocumentableObjectList(getOntClass().listInstances(true));
		}
		return individuals;
	}

	public Collection<OntologyClass> getEquivalentClasses() {
		OntModel ontModel = getOntClass().getOntModel();
		Collection <Resource> equivalents = new HashSet<Resource>();

		if(equivalentClasses == null){
			
			for(StmtIterator listStatements = ontModel.listStatements(getOntClass(), OWL2.equivalentClass, (RDFNode) null); listStatements.hasNext(); ){
				Statement statement = listStatements.next();
				equivalents.add(ontModel.getOntResource(statement.getObject().asResource()));
			}
			
			for(StmtIterator listStatements = ontModel.listStatements(null, OWL2.equivalentClass, getOntClass()); listStatements.hasNext(); ){
				Statement statement = listStatements.next();
				equivalents.add(ontModel.getOntResource(statement.getSubject().asResource()));
			}
			
			equivalentClasses = resourceIteratorToDocumentableObjectList(equivalents.iterator());
		}
		return Collections.unmodifiableCollection(equivalentClasses);
	}

	public void setEquivalentClasses(Collection<OntologyClass> equivalentClasses) {
		this.equivalentClasses = equivalentClasses;
	}

	public Collection<OntologyClass> getDisjointClasses() {
		OntModel ontModel = getOntClass().getOntModel();
		Collection <Resource> disjoints = new HashSet<Resource>();

		if(disjointClasses == null){
			
			StmtIterator listStatements = ontModel.listStatements(getOntClass(), OWL2.disjointWith, (RDFNode) null);
			while (listStatements.hasNext()){
				Statement statement = listStatements.next();
				disjoints.add(ontModel.getOntResource(statement.getObject().asResource()));
			}
			
			listStatements = ontModel.listStatements(null, OWL2.disjointWith, getOntClass());
			while (listStatements.hasNext()){
				Statement statement = listStatements.next();
				disjoints.add(ontModel.getOntResource(statement.getSubject().asResource()));
			}
			
			
			listStatements = ontModel.listStatements(null, RDF.type, OWL2.AllDisjointClasses);
			while (listStatements.hasNext()){
				Statement statement = listStatements.next();
				RDFList listDisjointClasses = statement.getSubject().getPropertyResourceValue(OWL2.members).as(RDFList.class);
				
				if (listDisjointClasses.contains(getOntClass())){
					Set<RDFNode> rdfNodeSet = listDisjointClasses.iterator().toSet();
					Set<Resource> ontClassSet = new HashSet<Resource>();
					for(RDFNode node : rdfNodeSet){
						ontClassSet.add(node.asResource());
					}
					ontClassSet.remove(getOntClass());
					disjoints.addAll(ontClassSet);
				}
				
			}
			
			disjointClasses = resourceIteratorToDocumentableObjectList(disjoints.iterator());
		}
		return Collections.unmodifiableCollection(disjointClasses);
	}

	public void setDisjointClasses(Collection<OntologyClass> disjointClasses) {
		this.disjointClasses = disjointClasses;
	}

    public String getKindString() {
        return Kind.ONTOLOGY_CLASS.toString();
    }
	
    public boolean isOwlClass(){
    	return getOntResource().hasRDFType(OWL2.Class,true);
    }

    public boolean isRdfsClass(){
    	return getOntResource().hasRDFType(RDFS.Class,true);
    }

}
