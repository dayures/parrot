package es.ctic.parrot.transformers;

import es.ctic.parrot.de.DocumentableObject;
import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.de.Ontology;
import es.ctic.parrot.de.OntologyClass;
import es.ctic.parrot.de.OntologyIndividual;
import es.ctic.parrot.de.OntologyProperty;
import es.ctic.parrot.de.Rule;
import es.ctic.parrot.de.RuleSet;

public class AbstractDocumentableObjectVisitor implements
        DocumentableObjectVisitor {

    public Object visit(OntologyClass object) throws TransformerException{
        // default implementation: do nothing
        return null;
    }

    public Object visit(OntologyProperty object) throws TransformerException{
        // default implementation: do nothing
        return null;
    }

    public Object visit(Rule object) throws TransformerException{
        // default implementation: do nothing
        return null;
    }

    public Object visit(DocumentableObjectRegister register) throws TransformerException{
        // default implementation: visit all the objects in the register
        for ( DocumentableObject documentableObject : register.getDocumentableObjects() ) {
            documentableObject.accept(this);
        }
        return null;
    }

	public Object visit(Ontology ontology) throws TransformerException{
        // default implementation: do nothing
		return null;
	}

	public Object visit(OntologyIndividual ontologyIndividual) throws TransformerException{
        // default implementation: do nothing
		return null;
	}

	public Object visit(RuleSet ruleSet) throws TransformerException{
        // default implementation: do nothing
		return null;
	}
	
}
