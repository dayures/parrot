package es.ctic.parrot.reader.rifleps;

import org.apache.log4j.Logger;
import net.sourceforge.rifle.ast.Document;
import net.sourceforge.rifle.ast.Group;
import net.sourceforge.rifle.ast.Rule;
import net.sourceforge.rifle.visitor.Visitor;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import es.ctic.parrot.de.DocumentableObjectRegister;

public class RifVisitor extends Visitor {
    
    private static final Logger logger = Logger.getLogger(RifVisitor.class);
	
	private DocumentableObjectRegister register;

    private Model model;

	public RifVisitor(DocumentableObjectRegister register) {
		super();
		this.register = register;
        model = ModelFactory.createDefaultModel();
	}

	@Override
	public Object visit(Document document) {
		logger.debug("Visiting RIF document AST node: " + document);
		for(Group group : document.getGroups()){
			group.accept(this);
		}
		return null;
	}

	@Override
	public Object visit(Group group) {
		logger.debug("Visiting RIF group AST node: " + group);
		for(Rule rule : group.getRules()){
			rule.accept(this);
		}
		return null;
	}

	@Override
	public Object visit(Rule rule) {
		logger.debug("Visiting RIF rule AST node: " + rule);
		RuleImpl r = new RuleImpl(rule, model);
		register.registerDocumentableObject(r);
		return null;
	}

}
