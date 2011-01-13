package es.ctic.parrot.transformers;

import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.de.Ontology;
import es.ctic.parrot.de.OntologyClass;
import es.ctic.parrot.de.OntologyIndividual;
import es.ctic.parrot.de.OntologyProperty;
import es.ctic.parrot.de.Rule;
import es.ctic.parrot.de.RuleSet;

public interface DocumentableObjectVisitor {
    
	public abstract Object visit(Ontology ontology) throws TransformerException;
	public abstract Object visit(OntologyClass object) throws TransformerException;
    public abstract Object visit(OntologyProperty object) throws TransformerException;
	public abstract Object visit(OntologyIndividual ontologyIndividual) throws TransformerException;
	
	public abstract Object visit(Rule object) throws TransformerException;
	public abstract Object visit(RuleSet object) throws TransformerException;
	
	public abstract Object visit(DocumentableObjectRegister register) throws TransformerException;

}
