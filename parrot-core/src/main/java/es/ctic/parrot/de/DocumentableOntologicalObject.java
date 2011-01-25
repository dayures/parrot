package es.ctic.parrot.de;

import java.util.Collection;

public interface DocumentableOntologicalObject extends DocumentableObject,Comparable<DocumentableOntologicalObject>{

	public abstract void addInverseRuleReference(Rule rule);
	public abstract Collection<Rule> getInverseRuleReferences();
	
}
