package es.ctic.parrot.transformers;

import es.ctic.parrot.de.Dataset;
import es.ctic.parrot.de.DocumentableObject;
import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.de.Ontology;
import es.ctic.parrot.de.OntologyClass;
import es.ctic.parrot.de.OntologyIndividual;
import es.ctic.parrot.de.OntologyProperty;
import es.ctic.parrot.de.Rule;
import es.ctic.parrot.de.RuleSet;
import es.ctic.parrot.de.Vocabulary;

/**
 * 
 * A default implementation of <code>DocumentableObjectVisitor</code>.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class AbstractDocumentableObjectVisitor implements
        DocumentableObjectVisitor {

    /**
     * Does nothing.
     * @param object the <code>class</code> to visit.
     * @throws TransformerException if a failed transformation operation occurs.
     * @return always null.
     */
    public Object visit(OntologyClass object) throws TransformerException{
        // default implementation: do nothing
        return null;
    }

    /**
     * Does nothing.
     * @param object the <code>property</code> to visit.
     * @throws TransformerException if a failed transformation operation occurs.
     * @return always null.
     */
    public Object visit(OntologyProperty object) throws TransformerException{
        // default implementation: do nothing
        return null;
    }


    /**
     * Does nothing.
     * @param object the <code>rule</code> to visit.
     * @throws TransformerException if a failed transformation operation occurs.
     * @return always null.
     */
    public Object visit(Rule object) throws TransformerException{
        // default implementation: do nothing
        return null;
    }

    /**
     * Visits the documentable elements that are registered.
     * @param register the <code>register</code> to visit.
     * @throws TransformerException if a failed transformation operation occurs.
     * @return always null.
     */
    public Object visit(DocumentableObjectRegister register) throws TransformerException{
        // default implementation: visit all the objects in the register
        for ( DocumentableObject documentableObject : register.getDocumentableObjects() ) {
            documentableObject.accept(this);
        }
        return null;
    }

    /**
     * Does nothing.
     * @param object the <code>ontology</code> to visit.
     * @throws TransformerException if a failed transformation operation occurs.
     * @return always null.
     */
	public Object visit(Ontology object) throws TransformerException{
        // default implementation: do nothing
		return null;
	}

	/**
     * Does nothing.
     * @param object the <code>individual</code> to visit.
     * @throws TransformerException if a failed transformation operation occurs.
     * @return always null.
     */
	public Object visit(OntologyIndividual object) throws TransformerException{
        // default implementation: do nothing
		return null;
	}

    /**
     * Does nothing.
     * @param object the <code>rule set</code> to visit.
     * @throws TransformerException if a failed transformation operation occurs.
     * @return always null.
     */
	public Object visit(RuleSet object) throws TransformerException{
        // default implementation: do nothing
		return null;
	}
	
    /**
     * Does nothing.
     * @param object the <code>data set</code> to visit.
     * @throws TransformerException if a failed transformation operation occurs.
     * @return always null.
     */
    public Object visit(Dataset object) throws TransformerException{
        // default implementation: do nothing
		return null;        
    }
    
    /**
     * Does nothing.
     * @param object the <code>vocabulary</code> to visit.
     * @throws TransformerException if a failed transformation operation occurs.
     * @return always null.
     */
    public Object visit(Vocabulary object) throws TransformerException{
        // default implementation: do nothing
		return null;        
    }
}
