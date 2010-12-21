package es.ctic.parrot.transformers;

import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.de.Ontology;
import es.ctic.parrot.de.OntologyClass;
import es.ctic.parrot.de.OntologyIndividual;
import es.ctic.parrot.de.OntologyProperty;
import es.ctic.parrot.de.Rule;
import es.ctic.parrot.de.RuleSet;

public interface DocumentableObjectVisitor {
    
	public abstract Object visit(OntologyClass object);
    public abstract Object visit(OntologyProperty object);
	public abstract Object visit(Rule object);
	public abstract Object visit(RuleSet object);
	public abstract Object visit(DocumentableObjectRegister register);
	public abstract Object visit(Ontology ontology);
	public abstract Object visit(OntologyIndividual ontologyIndividual);

}
