package es.ctic.parrot.reader.rifleps;

import java.util.Collection;
import java.util.LinkedList;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.rdf.model.Model;

import es.ctic.parrot.de.AbstractDocumentableObject;
import es.ctic.parrot.de.AnonymousIdentifier;
import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.OntologyProperty;
import es.ctic.parrot.de.Rule;
import es.ctic.parrot.de.URIIdentifier;
import es.ctic.parrot.transformers.DocumentableObjectVisitor;

public class RuleImpl extends AbstractDocumentableObject implements Rule {
	
	private net.sourceforge.rifle.ast.Rule rule;
	private Model iriMeta;

    private Identifier identifier;
	
	private static final Logger logger = Logger.getLogger(RuleImpl.class);

	public RuleImpl(net.sourceforge.rifle.ast.Rule rule, Model iriMeta) {
		this.rule = rule;
		this.iriMeta = iriMeta;
		if (rule.getId() == null) {
		    this.identifier = new AnonymousIdentifier();
		} else {
		    this.identifier = new URIIdentifier(rule.getId());
		}
	}

	public Object accept(DocumentableObjectVisitor visitor) {
		return visitor.visit(this);
	}

	public Identifier getIdentifier() {
		return identifier;
	}

	public Collection<OntologyProperty> getReferencedOntologyProperties() {
		return new LinkedList<OntologyProperty>();//FIXME create proper list
	}

	public Collection<String> getDeclaredVars() {
		return new LinkedList<String>();//FIXME create proper list	
	}

}
