package es.ctic.parrot.transformers;

import java.util.Locale;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.Ontology;
import es.ctic.parrot.de.OntologyClass;
import es.ctic.parrot.de.OntologyIndividual;
import es.ctic.parrot.de.OntologyProperty;
import es.ctic.parrot.de.Rule;
import es.ctic.parrot.de.RuleSet;
import es.ctic.parrot.de.Vocabulary;
import es.ctic.parrot.docmodel.Document;
import es.ctic.parrot.docmodel.OntologyClassDetailView;
import es.ctic.parrot.docmodel.OntologyDetailView;
import es.ctic.parrot.docmodel.OntologyIndividualDetailView;
import es.ctic.parrot.docmodel.OntologyPropertyDetailView;
import es.ctic.parrot.docmodel.RuleDetailView;
import es.ctic.parrot.docmodel.RuleSetDetailView;
import es.ctic.parrot.docmodel.VocabularyDetailView;

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
public class DetailsVisitor extends AbstractDocumentableObjectVisitor {
    
    private static final Logger logger = Logger.getLogger(DetailsVisitor.class);
    
    private Document document;
    private Locale locale;
    
    /**
     * Constructs a details visitor.
     * @param document the document to be fulfilled.
     * @param locale the locale.
     */
    public DetailsVisitor(Document document, Locale locale) {
        this.document = document;
        this.locale = locale;
    }
    
    /**
     * Visits the <code>ontology</code>.
     * @param object the ontology.
     * @return the details view generated.
     *  
     */
	@Override
	public Object visit(Ontology object) throws TransformerException {
	    logger.debug("Visiting ontology " + object);
	    OntologyDetailView details = OntologyDetailView.createFromOntology(object, locale);		
		document.addOntologyDetailView(details);     
		return details;
	}
	
    /**
     * Visits the <code>class</code>.
     * @param object the class.
     * @return the details view generated.
     *  
     */	
	@Override
	public Object visit(OntologyClass object) throws TransformerException {
	    logger.debug("Visiting class " + object);
	    OntologyClassDetailView details = OntologyClassDetailView.createFromClass(object, locale);	
        document.addOntologyClassDetailView(details);     
        return details;
	}
	
    /**
     * Visits the <code>property</code>.
     * @param object the property.
     * @return the details view generated.
     *  
     */	
	@Override
	public Object visit(OntologyProperty object) throws TransformerException {
	    logger.debug("Visiting property " + object);
	    OntologyPropertyDetailView details = OntologyPropertyDetailView.createFromProperty(object, locale);
        document.addOntologyPropertyDetailView(details);     
        return details;
	}

    /**
     * Visits the <code>individual</code>.
     * @param object the individual.
     * @return the details view generated.
     *  
     */
	@Override
	public Object visit(OntologyIndividual object) throws TransformerException {
	    logger.debug("Visiting individual " + object);
		OntologyIndividualDetailView details = OntologyIndividualDetailView.createFromIndividual(object, locale);		
		document.addOntologyIndividualDetailView(details);
		return details;
	}
	
    /**
     * Visits the <code>rule set</code>.
     * @param object the rule set.
     * @return the details view generated.
     *  
     */	
	@Override
	public Object visit(RuleSet object) throws TransformerException {
	    logger.debug("Visiting ruleset " + object);
	    RuleSetDetailView details = RuleSetDetailView.createFromRuleSet(object, locale);		
	    document.addRuleSetDetailView(details);
	    return details;
	}

    /**
     * Visits the <code>rule</code>.
     * @param object the rule.
     * @return the details view generated.
     *  
     */
	@Override
	public Object visit(Rule object) throws TransformerException {
	    logger.debug("Visiting rule " + object);
	    RuleDetailView details = RuleDetailView.createFromRule(object, locale);		
		document.addRuleDetailView(details);
		return details;
	}
	
    /**
     * Visits the <code>vocabulary</code>.
     * @param object the rule.
     * @return the details view generated.
     *  
     */
	@Override
	public Object visit(Vocabulary object) throws TransformerException {
	    logger.debug("Visiting vocabulary " + object);
	    VocabularyDetailView details = VocabularyDetailView.createFromVocabulary(object, locale);		
		document.addVocabularyDetailView(details);
		return details;
	}

}
