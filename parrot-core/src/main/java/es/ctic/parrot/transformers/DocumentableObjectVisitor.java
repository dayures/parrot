package es.ctic.parrot.transformers;

import es.ctic.parrot.de.Dataset;
import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.de.Ontology;
import es.ctic.parrot.de.OntologyClass;
import es.ctic.parrot.de.OntologyIndividual;
import es.ctic.parrot.de.OntologyProperty;
import es.ctic.parrot.de.Rule;
import es.ctic.parrot.de.RuleSet;

/**
 * 
 * <code>DocumentableObjectVisitor</code> is used to implement the Visitor pattern. 
 * An object of this interface can be passed to a Node which will then call its typesafe methods. 
 * Please refer to the Gang of Four book of Design Patterns for more details on the Visitor pattern.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */

public interface DocumentableObjectVisitor {

	/**
     * Visits the given <code>ontology</code>.
     * @param object the <code>ontology</code> to visit.
     * @throws TransformerException if a failed transformation operation occurs.
     */
	public abstract Object visit(Ontology object) throws TransformerException;
	
    /**
     * Visits the given <code>class</code>.
     * @param object the <code>class</code> to visit.
     * @throws TransformerException if a failed transformation operation occurs.
     */
	public abstract Object visit(OntologyClass object) throws TransformerException;

    /**
     * Visits the given <code>property</code>.
     * @param object the <code>property</code> to visit.
     * @throws TransformerException if a failed transformation operation occurs.
     */
	public abstract Object visit(OntologyProperty object) throws TransformerException;

    /**
     * Visits the given <code>individual</code>.
     * @param object the <code>individual</code> to visit.
     * @throws TransformerException if a failed transformation operation occurs.
     */
	public abstract Object visit(OntologyIndividual object) throws TransformerException;

	/**
     * Visits the given <code>rule</code>.
     * @param object the <code>rule</code> to visit.
     * @throws TransformerException if a failed transformation operation occurs.
     */
	public abstract Object visit(Rule object) throws TransformerException;

    /**
     * Visits the given <code>rule set</code>.
     * @param object the <code>rule set</code> to visit.
     * @throws TransformerException if a failed transformation operation occurs.
     */
	public abstract Object visit(RuleSet object) throws TransformerException;
	
    /**
     * Visits the given <code>data set</code>.
     * @param object the <code>data set</code> to visit.
     * @throws TransformerException if a failed transformation operation occurs.
     */
    public abstract Object visit(Dataset dataset) throws TransformerException;

    /**
     * Visits the given <code>register</code>.
     * @param object the <code>register</code> to visit.
     * @throws TransformerException if a failed transformation operation occurs.
     */
	public abstract Object visit(DocumentableObjectRegister object) throws TransformerException;

}
