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

public class DetailsVisitor extends AbstractDocumentableObjectVisitor {
    
    private static final Logger logger = Logger.getLogger(DetailsVisitor.class);
    
    private Document document;
    private Locale locale;
    
    public DetailsVisitor(Document document, Locale locale) {
        this.document = document;
        this.locale = locale;
    }
    
	public Object visit(Ontology object) throws TransformerException {
	    logger.debug("Visiting ontology " + object);
		
	    OntologyDetailView details = OntologyDetailView.createFromOntology(object, locale);		
		document.addOntologyDetailView(details);     
		
		return details;
	}
	
	public Object visit(OntologyClass object) throws TransformerException {
	    logger.debug("Visiting class " + object);

	    OntologyClassDetailView details = OntologyClassDetailView.createFromClass(object, locale);	
        document.addOntologyClassDetailView(details);     
		
        return details;
	}
	
	public Object visit(OntologyProperty object) throws TransformerException {
	    logger.debug("Visiting property " + object);
		OntologyPropertyDetailView details = new OntologyPropertyDetailView(object);
		details.setUri(object.getURI());
		details.setLabel(object.getLabel(locale));
		details.setComment(object.getComment(locale));
		details.setDomain(object.getDomain());
		details.setRange(object.getRange());
		details.setSuperProperties(object.getSuperProperties());
		details.setSubProperties(object.getSubProperties());
		details.setEquivalentProperties(object.getEquivalentProperties());
		details.setDisjointProperties(object.getDisjointProperties());
		details.setInverseRuleReferences(object.getInverseRuleReferences());
		details.setInverseReferences(object.getInternalReferences());
		details.setCardinality(object.getCardinality());
		details.setInverseOf(object.getInverseOf());
		details.setLabels(object.getLabels());
		details.setRelatedDocuments(object.getRelatedDocuments(locale));
		
        document.addOntologyPropertyDetailView(details);     
		return details;
	}

	
	/* (non-Javadoc)
	 * @see es.ctic.parrot.transformers.AbstractDocumentableObjectVisitor#visit(es.ctic.parrot.de.OntologyIndividual)
	 */
	@Override
	public Object visit(OntologyIndividual object) throws TransformerException {
	    logger.debug("Visiting individual " + object);
		OntologyIndividualDetailView details = new OntologyIndividualDetailView(object);
		details.setLabel(object.getLabel(locale));
		details.setUri(object.getURI());
		details.addAllTypes(object.getTypes());
		details.setInverseRuleReferences(object.getInverseRuleReferences());
		details.setLabels(object.getLabels());
		details.setRelatedDocuments(object.getRelatedDocuments(locale));
		
		document.addOntologyIndividualDetailView(details);
		return details;
	}
	
	
	public Object visit(RuleSet object) throws TransformerException {
	    logger.debug("Visiting ruleset " + object);
	    
	    RuleSetDetailView details = RuleSetDetailView.createFromRuleSet(object, locale);		
	    document.addRuleSetDetailView(details);

	    return details;
	}

	public Object visit(Rule object) throws TransformerException {
	    logger.debug("Visiting rule " + object);
	    
	    RuleDetailView details = RuleDetailView.createFromRule(object, locale);		
		document.addRuleDetailView(details);

		return details;
	}

}
