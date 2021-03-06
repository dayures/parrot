package es.ctic.parrot.reader.jena;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.shared.JenaException;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.util.iterator.Filter;
import com.hp.hpl.jena.vocabulary.OWL;

import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.OntologyClass;
import es.ctic.parrot.de.OntologyIndividual;
import es.ctic.parrot.de.Triple;
import es.ctic.parrot.de.URIIdentifier;
import es.ctic.parrot.transformers.DocumentableObjectVisitor;
import es.ctic.parrot.transformers.TransformerException;

/**
 * An implementation of {@link es.ctic.parrot.de.OntologyIndividual} coupled to <a href="http://openjena.org/">Jena</a>.
 * 
 * @author Carlos Tejo Alonso (<a href="http://www.fundacionctic.org">Fundación CTIC</a>)
 * @version 1.0
 * @since 1.0
 *
 */
public class OntologyIndividualJenaImpl extends AbstractJenaDocumentableObject
		implements OntologyIndividual {

    private static final Logger logger = Logger.getLogger(OntologyIndividualJenaImpl.class);

	/**
	 * Constructs an ontology individual.
	 * @param individual the Jena individual to set.
	 * @param register the register to set
	 * @param annotationStrategy the annotation strategy to set.
	 */
	public OntologyIndividualJenaImpl(Individual individual, DocumentableObjectRegister register, OntResourceAnnotationStrategy annotationStrategy) {
		super(individual, register, annotationStrategy);
	}
	
	private Individual getIndividual(){
		return getOntResource().asIndividual();
	}

	public Object accept(DocumentableObjectVisitor visitor) throws TransformerException {
        try {
        	return visitor.visit(this);
        } catch (JenaException e) {
        	throw new TransformerException(e);
        }
	}
	
	public Collection<OntologyClass> getTypes() {
		ExtendedIterator<OntClass> it = getIndividual().listOntClasses(true);
		Filter<OntClass> f = new FilterByNamespace(OWL.NS);
		
		return ontClassIteratorToOntologyClassList(it.filterDrop(f));
	}

    public String getKindString() {
        return Kind.ONTOLOGY_INDIVIDUAL.toString();
    }
    
    /**
     * Converts an iterator over Jena ontClasses to a collection of documentable ontology classes.
     * @param it an iterator over Jena ontClasses.
     * @return a collection of documentable ontology classes.
     */
	private Collection<OntologyClass> ontClassIteratorToOntologyClassList(Iterator<OntClass> it) {
		Collection<OntologyClass> ontologyClassList = new LinkedList<OntologyClass>();
		
		while(it.hasNext()){
			
			OntClass clazz=it.next();
			
			if (clazz.isIndividual() == true){
				logger.debug("Resource="+clazz +" is a subclass but it is an individual");
			} else { // is not an individual
				Identifier identifier = null;
				
				if (clazz.isAnon() == false){
					identifier = new URIIdentifier(clazz.getURI());
				} else {
					identifier = new JenaAnonymousIdentifier(clazz.getId());
				}
	
				OntologyClass _class = (OntologyClass) this.getRegister().findDocumentableObject(identifier); 
				if (_class != null) { // do not add null elements in the list 
					ontologyClassList.add(_class);
				}
			}
		}
		return ontologyClassList;
	}

	public Collection<Triple> getTriplesAsSubject() {
   		return getAnnotationStrategy().getTriplesAsSubject(getOntResource());
	}

	public Collection<Triple> getTriplesAsObject() {
   		return getAnnotationStrategy().getTriplesAsObject(getOntResource());
	}

}
