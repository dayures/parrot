package es.ctic.parrot.reader.rifle;

import net.sourceforge.rifle.ast.Document;
import net.sourceforge.rifle.ast.Group;
import net.sourceforge.rifle.ast.Import;
import net.sourceforge.rifle.ast.Rule;
import net.sourceforge.rifle.ast.visitor.ToPSVisitor;
import net.sourceforge.rifle.ast.visitor.Visitor;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.DocumentableObject;
import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.de.RuleSet;
import es.ctic.parrot.reader.jena.OntResourceAnnotationStrategy;

public class RifleASTVisitor extends Visitor {
    
    private static final Logger logger = Logger.getLogger(RifleASTVisitor.class);
	
	private DocumentableObjectRegister register;
	private OntResourceAnnotationStrategy annotationStrategy;

	public RifleASTVisitor(DocumentableObjectRegister register, OntResourceAnnotationStrategy annotationStrategy) {
		super();
		this.register = register;
		this.setAnnotationStrategy(annotationStrategy);
	}

	@Override
	public Object visit(Document document, Object o) {
		logger.debug("Visiting RIF document AST node: " + document);
		if (logger.isDebugEnabled()) { // because the message is computationally expensive
		    ToPSVisitor toPSVisitor = new ToPSVisitor();
		    document.accept(toPSVisitor, o);
		    logger.debug("AST: " + toPSVisitor.getPS());
		}
		if (document.getGroup() != null) {
			document.getGroup().accept(this, o);
		}
		return null;
	}

	@Override
	public Object visit(Group group, Object parent) {
		logger.debug("Visiting RIF group AST node: " + group);
		
		RuleSetImpl ruleset = new RuleSetImpl(group, register, getAnnotationStrategy());
		
		if (parent != null) {
			DocumentableObject parentDocumentableObject = register.findDocumentableObject(((RuleSet) parent).getIdentifier());
			logger.debug("Linking ruleset " + ruleset.getIdentifier() + " to his parent " + parentDocumentableObject);
			ruleset.setParent(parentDocumentableObject);
		}

		register.registerDocumentableObject(ruleset);

		for(Rule rule : group.getRules()){
			rule.accept(this, ruleset);
		}
		
		for(Group g : group.getGroups()){
			g.accept(this, ruleset);
		}
		return null;
	}

	@Override
	public Object visit(Rule astRule, Object parent) {
		logger.debug("Visiting RIF rule AST node: " + astRule);
		
		RuleImpl rule = new RuleImpl(astRule, register, getAnnotationStrategy());
		
		if (parent != null) {
			DocumentableObject parentDocumentableObject = register.findDocumentableObject(((DocumentableObject) parent).getIdentifier());
			logger.debug("Linking rule " + rule.getIdentifier() + " to his parent " + parentDocumentableObject);
			rule.setParent(parentDocumentableObject);
		}
		
		register.registerDocumentableObject(rule);
//		if (astRule.getInnerRule() != null){
//			astRule.getInnerRule().accept(this, rule);
//		}
		
		
		return null;
	}

    @Override
    public Object visit(Import imp, Object o) {
        // nothing to do, imports have been resolved before
        return null;
    }

	/**
	 * @param annotationStrategy the annotationStrategy to set
	 */
	public void setAnnotationStrategy(OntResourceAnnotationStrategy annotationStrategy) {
		this.annotationStrategy = annotationStrategy;
	}

	/**
	 * @return the annotationStrategy
	 */
	public OntResourceAnnotationStrategy getAnnotationStrategy() {
		return annotationStrategy;
	}

}
