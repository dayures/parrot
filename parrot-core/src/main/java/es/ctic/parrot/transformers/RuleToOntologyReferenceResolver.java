package es.ctic.parrot.transformers;

import java.util.Collection;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.DocumentableOntologicalObject;
import es.ctic.parrot.de.Rule;

/**
 * 
 * Visitor that resolve references between rules and ontology elements.
 * <code>RuleToOntologyReferenceResolver</code> is an implementation of the Visitor pattern.
 * Please refer to the Gang of Four book of Design Patterns for more details on the Visitor pattern.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class RuleToOntologyReferenceResolver extends AbstractDocumentableObjectVisitor {
    
    private static final Logger logger = Logger.getLogger(RuleToOntologyReferenceResolver.class);

    /**
     * Adds a reference from the ontological elements referenced by a rule to that rule.
     * @param rule the rule to visit.
     * @return always null. 
     */
    @Override
    public Object visit(Rule rule) throws TransformerException {
    	logger.debug("Visiting rule " + rule.toString());
        Collection<DocumentableOntologicalObject> referencedOntologicalObjects = rule.getReferencedOntologicalObjects();
        for ( DocumentableOntologicalObject obj : referencedOntologicalObjects ) {
        	obj.addInverseRuleReference(rule);
        }
        return null;
    }

}
