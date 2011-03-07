package es.ctic.parrot.transformers;

import java.util.Locale;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.Ontology;
import es.ctic.parrot.de.OntologyClass;
import es.ctic.parrot.de.OntologyIndividual;
import es.ctic.parrot.de.OntologyProperty;
import es.ctic.parrot.de.Rule;
import es.ctic.parrot.de.RuleSet;
import es.ctic.parrot.docmodel.Document;
import es.ctic.parrot.docmodel.OntologyClassDetailView;
import es.ctic.parrot.docmodel.OntologyDetailView;
import es.ctic.parrot.docmodel.OntologyIndividualDetailView;
import es.ctic.parrot.docmodel.OntologyPropertyDetailView;
import es.ctic.parrot.docmodel.RuleDetailView;
import es.ctic.parrot.docmodel.RuleSetDetailView;

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
    
	@Override
	public Object visit(Ontology object) throws TransformerException {
	    logger.debug("Visiting ontology " + object);
	    OntologyDetailView details = OntologyDetailView.createFromOntology(object, locale);		
		document.addOntologyDetailView(details);     
		return details;
	}
	
	@Override
	public Object visit(OntologyClass object) throws TransformerException {
	    logger.debug("Visiting class " + object);
	    OntologyClassDetailView details = OntologyClassDetailView.createFromClass(object, locale);	
        document.addOntologyClassDetailView(details);     
        return details;
	}
	
	@Override
	public Object visit(OntologyProperty object) throws TransformerException {
	    logger.debug("Visiting property " + object);
	    OntologyPropertyDetailView details = OntologyPropertyDetailView.createFromProperty(object, locale);
        document.addOntologyPropertyDetailView(details);     
        return details;
	}

	@Override
	public Object visit(OntologyIndividual object) throws TransformerException {
	    logger.debug("Visiting individual " + object);
		OntologyIndividualDetailView details = OntologyIndividualDetailView.createFromIndividual(object, locale);		
		document.addOntologyIndividualDetailView(details);
		return details;
	}
	
	@Override
	public Object visit(RuleSet object) throws TransformerException {
	    logger.debug("Visiting ruleset " + object);
	    RuleSetDetailView details = RuleSetDetailView.createFromRuleSet(object, locale);		
	    document.addRuleSetDetailView(details);
	    return details;
	}

	@Override
	public Object visit(Rule object) throws TransformerException {
	    logger.debug("Visiting rule " + object);
	    RuleDetailView details = RuleDetailView.createFromRule(object, locale);		
		document.addRuleDetailView(details);
		return details;
	}

}
