package es.ctic.parrot.reader.rifle;

import net.sourceforge.rifle.ast.Document;
import net.sourceforge.rifle.ast.Group;
import net.sourceforge.rifle.ast.Import;
import net.sourceforge.rifle.ast.Rule;
import net.sourceforge.rifle.ast.visitor.ToPSVisitor;
import net.sourceforge.rifle.ast.visitor.Visitor;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.ontology.OntModel;

import es.ctic.parrot.de.DocumentableObject;
import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.de.RuleSet;
import es.ctic.parrot.reader.jena.OntResourceAnnotationStrategy;

/**
 * 
 * Visitor that fills a <code>document</code> (this document will be used later for presentation issues).
 * <code>DetailsVisitor</code> is an implementation of the Visitor pattern.
 * Please refer to the Gang of Four book of Design Patterns for more details on the Visitor pattern.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class RifleASTVisitor extends Visitor {
    
    private static final Logger logger = Logger.getLogger(RifleASTVisitor.class);
	
	private DocumentableObjectRegister register;
	private OntResourceAnnotationStrategy annotationStrategy;
	private OntModel ontModel;

	public RifleASTVisitor(DocumentableObjectRegister register, OntResourceAnnotationStrategy annotationStrategy, OntModel ontModel) {
		super();
		this.setRegister(register);
		this.setAnnotationStrategy(annotationStrategy);
		this.setOntModel(ontModel);
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
		
		RuleSetImpl ruleset = new RuleSetImpl(group, getRegister(), getAnnotationStrategy(), getOntModel());
		
		if (parent != null) {
			DocumentableObject parentDocumentableObject = getRegister().findDocumentableObject(((RuleSet) parent).getIdentifier());
			logger.debug("Linking ruleset " + ruleset.getIdentifier() + " to his parent " + parentDocumentableObject);
			ruleset.setParent(parentDocumentableObject);
		}

		getRegister().registerDocumentableObject(ruleset);

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
		
		RuleImpl rule = new RuleImpl(astRule, getRegister(), getAnnotationStrategy(), getOntModel());
		
		if (parent != null) {
			DocumentableObject parentDocumentableObject = getRegister().findDocumentableObject(((DocumentableObject) parent).getIdentifier());
			logger.debug("Linking rule " + rule.getIdentifier() + " to his parent " + parentDocumentableObject);
			rule.setParent(parentDocumentableObject);
		}
		
		getRegister().registerDocumentableObject(rule);
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
	 * Sets the annotation strategy. 
	 * @param annotationStrategy the annotation strategy to set.
	 */
	private void setAnnotationStrategy(OntResourceAnnotationStrategy annotationStrategy) {
		this.annotationStrategy = annotationStrategy;
	}

	/**
	 * Returns the annotation strategy.
	 * @return the annotation strategy.
	 */
	public OntResourceAnnotationStrategy getAnnotationStrategy() {
		return annotationStrategy;
	}

	/**
	 * Sets the ontModel.
	 * @param ontModel the ontModel to set.
	 */
	private void setOntModel(OntModel ontModel) {
		this.ontModel = ontModel;
	}

	/**
	 * Returns the ontModel.
	 * @return the ontModel.
	 */
	public OntModel getOntModel() {
		return ontModel;
	}

	/**
	 * Sets the register.
	 * @param register the register to set.
	 */
	private void setRegister(DocumentableObjectRegister register) {
		this.register = register;
	}

	/**
	 * Returns the register.
	 * @return the register.
	 */
	public DocumentableObjectRegister getRegister() {
		return register;
	}

}
