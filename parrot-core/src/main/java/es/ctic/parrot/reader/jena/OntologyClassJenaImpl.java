package es.ctic.parrot.reader.jena;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.OWL2;

import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.de.OntologyClass;
import es.ctic.parrot.de.OntologyIndividual;
import es.ctic.parrot.de.OntologyProperty;
import es.ctic.parrot.transformers.DocumentableObjectVisitor;

public class OntologyClassJenaImpl extends AbstractJenaDocumentableObject implements OntologyClass{

    private Collection<OntologyClass> superClasses;
	private Collection<OntologyClass> subClasses;

	private Collection<OntologyClass> equivalentClasses;

	private Collection<OntologyClass> disjointClasses;
	
	private Collection<OntologyIndividual> individuals;
	
	public OntologyClassJenaImpl(OntClass ontclass, DocumentableObjectRegister register){
		super(ontclass, register);
	}
	
	public Object accept(DocumentableObjectVisitor visitor) {
		return visitor.visit(this);
	}
	
	public OntClass getOntClass(){
		return (OntClass) getOntResource();
	}

	public Collection<OntologyClass> getSuperClasses(){
		if(superClasses==null){
			Iterator<OntClass> it=getOntClass().listSuperClasses();
			superClasses = ontClassIteratorToOntologyClassList(it);
		}
		return superClasses;
	}

	public Collection<OntologyClass> getSubClasses() {
		if(subClasses==null){
			Iterator<OntClass> it=getOntClass().listSubClasses();
			subClasses= ontClassIteratorToOntologyClassList(it);
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

	public void setSubClasses(List<OntologyClass> classes) {
		this.subClasses=classes;
	}

	public void setSuperClasses(List<OntologyClass> classes) {
		this.superClasses=classes;
	}

	public Collection<OntologyIndividual> getIndividuals() {
		if(individuals==null){
			ExtendedIterator<Individual> it = (ExtendedIterator<Individual>) getOntClass().listInstances(true);
			individuals = ontResourceIteratorToOntologyInstanceList(it);
		}
		return individuals;
	}

	public Collection<OntologyClass> getEquivalentClasses() {
		OntModel ontModel = getOntClass().getOntModel();
		Collection <OntClass> equivalents = new HashSet<OntClass>();

		if(equivalentClasses == null){
			
			StmtIterator listStatements = ontModel.listStatements(getOntClass(), OWL2.equivalentClass, (RDFNode) null);
			while (listStatements.hasNext()){
				Statement statement = listStatements.next();
				equivalents.add(ontModel.getOntResource(statement.getObject().asResource()).asClass());
			}
			
			listStatements = ontModel.listStatements(null, OWL2.equivalentClass, getOntClass());
			while (listStatements.hasNext()){
				Statement statement = listStatements.next();
				equivalents.add(ontModel.getOntResource(statement.getSubject()).asClass());
			}
			
			equivalentClasses = ontClassIteratorToOntologyClassList(equivalents.iterator());
		}
		return Collections.unmodifiableCollection(equivalentClasses);
	}

	public void setEquivalentClasses(Collection<OntologyClass> equivalentClasses) {
		this.equivalentClasses = equivalentClasses;
	}

	public Collection<OntologyClass> getDisjointClasses() {
		OntModel ontModel = getOntClass().getOntModel();
		Collection <OntClass> disjoints = new HashSet<OntClass>();

		if(disjointClasses == null){
			
			StmtIterator listStatements = ontModel.listStatements(getOntClass(), OWL.disjointWith, (RDFNode) null);
			while (listStatements.hasNext()){
				Statement statement = listStatements.next();
				disjoints.add(ontModel.getOntResource(statement.getObject().asResource()).asClass());
			}
			
			listStatements = ontModel.listStatements(null, OWL2.disjointWith, getOntClass());
			while (listStatements.hasNext()){
				Statement statement = listStatements.next();
				disjoints.add(ontModel.getOntResource(statement.getSubject()).asClass());
			}
			
			disjointClasses = ontClassIteratorToOntologyClassList(disjoints.iterator());
		}
		return Collections.unmodifiableCollection(disjointClasses);
	}

	public void setDisjointClasses(Collection<OntologyClass> disjointClasses) {
		this.disjointClasses = disjointClasses;
	}
	
}
