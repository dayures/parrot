package es.ctic.parrot.docmodel;

import java.util.Collection;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.OntologyProperty;
import es.ctic.parrot.de.Rule;

public class RuleDetailView implements DetailView{

    private static final Logger logger = Logger.getLogger(RuleDetailView.class);
    private Rule rule;
    private Identifier identifier;
    
    public RuleDetailView(Rule rule) {
        logger.debug("Created " + this.getClass());
        this.rule=rule;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

	public String getAnchor() {
		return rule.getLocalName();
	}
	
	public Collection<String> getDeclaredVars() {
	    return rule.getDeclaredVars();
	}
	
	public Collection<OntologyProperty> getReferencedOntologyProperties() {
	    return rule.getReferencedOntologyProperties();
	}
    
}
