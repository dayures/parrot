package es.ctic.parrot.reader.jena;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.de.OntologyClass;
import es.ctic.parrot.de.OntologyIndividual;
import es.ctic.parrot.transformers.DocumentableObjectVisitor;

public class OntologyClassJenaImpl extends AbstractJenaDocumentableObject implements OntologyClass{

    private Collection<OntologyClass> superClasses;
	private Collection<OntologyClass> subClasses;
	
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
	
}
