package es.ctic.parrot.reader.rifle;

import net.sourceforge.rifle.ast.Document;
import net.sourceforge.rifle.ast.Group;
import net.sourceforge.rifle.ast.Import;
import net.sourceforge.rifle.ast.Rule;
import net.sourceforge.rifle.ast.visitor.ToPSVisitor;
import net.sourceforge.rifle.ast.visitor.Visitor;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.DocumentableObjectRegister;

public class RifleASTVisitor extends Visitor {
    
    private static final Logger logger = Logger.getLogger(RifleASTVisitor.class);
	
	private DocumentableObjectRegister register;

	public RifleASTVisitor(DocumentableObjectRegister register) {
		super();
		this.register = register;
	}

	@Override
	public Object visit(Document document) {
		logger.debug("Visiting RIF document AST node: " + document);
		if (logger.isDebugEnabled()) { // because the message is computationally expensive
		    ToPSVisitor toPSVisitor = new ToPSVisitor();
		    document.accept(toPSVisitor);
		    logger.debug("AST: " + toPSVisitor.getPS());
		}
		if (document.getGroup() != null) {
			document.getGroup().accept(this);
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
		
		RuleImpl r = new RuleImpl(rule);
		register.registerDocumentableObject(r);
		return null;
	}

    @Override
    public Object visit(Import imp) {
        // nothing to do, imports have been resolved before
        return null;
    }

}
