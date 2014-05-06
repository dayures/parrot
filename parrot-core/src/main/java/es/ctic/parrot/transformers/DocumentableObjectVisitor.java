package es.ctic.parrot.transformers;

import es.ctic.parrot.de.Catalog;
import es.ctic.parrot.de.Dataset;
import es.ctic.parrot.de.Distribution;
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
 * <code>DocumentableObjectVisitor</code> is used to implement the Visitor pattern. 
 * An object of this interface can be passed to a Node which will then call its typesafe methods. 
 * Please refer to the Gang of Four book of Design Patterns for more details on the Visitor pattern.
 * 
 * @author Carlos Tejo Alonso (<a href="http://www.fundacionctic.org">Fundación CTIC</a>)
 * @version 1.0
 * @since 1.0
 *
 */

public interface DocumentableObjectVisitor {

	/**
     * Visits the given <code>ontology</code>.
     * @param ontology the <code>ontology</code> to visit.
     * @throws TransformerException if a failed transformation operation occurs.
     */
	public abstract Object visit(Ontology ontology) throws TransformerException;
	
    /**
     * Visits the given <code>class</code>.
     * @param clazz the <code>class</code> to visit.
     * @throws TransformerException if a failed transformation operation occurs.
     */
	public abstract Object visit(OntologyClass clazz) throws TransformerException;

    /**
     * Visits the given <code>property</code>.
     * @param property the <code>property</code> to visit.
     * @throws TransformerException if a failed transformation operation occurs.
     */
	public abstract Object visit(OntologyProperty property) throws TransformerException;

    /**
     * Visits the given <code>individual</code>.
     * @param individual the <code>individual</code> to visit.
     * @throws TransformerException if a failed transformation operation occurs.
     */
	public abstract Object visit(OntologyIndividual individual) throws TransformerException;

	/**
     * Visits the given <code>rule</code>.
     * @param rule the <code>rule</code> to visit.
     * @throws TransformerException if a failed transformation operation occurs.
     */
	public abstract Object visit(Rule rule) throws TransformerException;

    /**
     * Visits the given <code>rule set</code>.
     * @param ruleset the <code>rule set</code> to visit.
     * @throws TransformerException if a failed transformation operation occurs.
     */
	public abstract Object visit(RuleSet ruleset) throws TransformerException;

    /**
     * Visits the given <code>vocabulary</code>.
     * @param vocabulary the <code>vocabulary</code> to visit.
     * @throws TransformerException if a failed transformation operation occurs.
     */
    public abstract Object visit(Vocabulary vocabulary) throws TransformerException;
    
    /**
     * Visits the given <code>data set</code>.
     * @param dataset the <code>data set</code> to visit.
     * @throws TransformerException if a failed transformation operation occurs.
     */
    public abstract Object visit(Dataset dataset) throws TransformerException;

    /**
     * Visits the given <code>catalog</code>.
     * @param dataset the <code>catalog</code> to visit.
     * @throws TransformerException if a failed transformation operation occurs.
     */
    public abstract Object visit(Catalog catalog) throws TransformerException;

    /**
     * Visits the given <code>distribution</code>.
     * @param dataset the <code>distribution</code> to visit.
     * @throws TransformerException if a failed transformation operation occurs.
     */
    public abstract Object visit(Distribution distribution) throws TransformerException;

    /**
     * Visits the given <code>register</code>.
     * @param object the <code>register</code> to visit.
     * @throws TransformerException if a failed transformation operation occurs.
     */
	public abstract Object visit(DocumentableObjectRegister object) throws TransformerException;

}
