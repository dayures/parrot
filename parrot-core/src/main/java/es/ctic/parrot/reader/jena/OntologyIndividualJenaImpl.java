package es.ctic.parrot.reader.jena;

import java.util.Collection;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.shared.JenaException;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.util.iterator.Filter;
import com.hp.hpl.jena.vocabulary.OWL;

import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.de.OntologyClass;
import es.ctic.parrot.de.OntologyIndividual;
import es.ctic.parrot.de.DocumentableObject.Kind;
import es.ctic.parrot.transformers.DocumentableObjectVisitor;
import es.ctic.parrot.transformers.TransformerException;

public class OntologyIndividualJenaImpl extends AbstractJenaDocumentableObject
		implements OntologyIndividual {

	public OntologyIndividualJenaImpl(Individual individual, DocumentableObjectRegister register) {
		super(individual, register);
	}

	public Object accept(DocumentableObjectVisitor visitor) throws TransformerException {
        try {
        	return visitor.visit(this);
        } catch (JenaException e) {
        	throw new TransformerException(e);
        }
	}
	
	public Collection<OntologyClass> getTypes() {
		ExtendedIterator<OntClass> it = ((Individual) getOntResource()).listOntClasses(true);
		Filter<OntClass> f = new FilterByNamespace(OWL.NS);
		
		return ontClassIteratorToOntologyClassList(it.filterDrop(f));
	}

    public String getKindString() {
        return Kind.ONTOLOGY_INDIVIDUAL.toString();
    }
    
}
