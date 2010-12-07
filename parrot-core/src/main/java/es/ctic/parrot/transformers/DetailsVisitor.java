package es.ctic.parrot.transformers;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.Ontology;
import es.ctic.parrot.de.OntologyClass;
import es.ctic.parrot.de.OntologyIndividual;
import es.ctic.parrot.de.OntologyProperty;
import es.ctic.parrot.de.Rule;
import es.ctic.parrot.docmodel.Document;
import es.ctic.parrot.docmodel.OntologyClassDetailView;
import es.ctic.parrot.docmodel.OntologyDetailView;
import es.ctic.parrot.docmodel.OntologyIndividualDetailView;
import es.ctic.parrot.docmodel.OntologyPropertyDetailView;
import es.ctic.parrot.docmodel.RuleDetailView;

public class DetailsVisitor extends AbstractDocumentableObjectVisitor {
    
    private static final Logger logger = Logger.getLogger(DetailsVisitor.class);
    
    private Document document;
    private String lang;
    
    public DetailsVisitor(Document document,String lang) {
        this.document = document;
        this.lang=lang;
    }
    
	public Object visit(OntologyClass object) {
	    logger.debug("Visiting class " + object);
		OntologyClassDetailView details=new OntologyClassDetailView(object);
		details.setUri(object.getURI());
		details.setLabel(object.getLabel(lang));
		details.setComment(object.getComment(lang));
		for(OntologyClass ontclass:object.getSuperClasses()){
			details.addSuperClasses(ontclass);
		}
		for(OntologyClass ontclass:object.getSubClasses()){
			details.addSubClasses(ontclass);
		}
		details.setInverseReferences(object.getInternalReferences());
        document.addOntologyClassDetailView(details);     
		return details;
	}
	
	public Object visit(OntologyProperty object) {
	    logger.debug("Visiting property " + object);
		OntologyPropertyDetailView details = new OntologyPropertyDetailView(object);
		details.setUri(object.getURI());
		details.setLabel(object.getLabel(lang));
		details.setComment(object.getComment(lang));
		details.setDomain(object.getDomain());
		details.setRange(object.getRange());
		details.setInverseReferences(object.getInternalReferences());
		details.setCardinality(object.getCardinality());
        document.addOntologyPropertyDetailView(details);     
		return details;
	}
	
	public Object visit(Ontology object) {
	    logger.debug("Visiting ontology " + object);
		OntologyDetailView details=new OntologyDetailView(object);
		details.setUri(object.getURI());
		details.setLabel(object.getLabel(lang));
		details.setComment(object.getComment(lang));
		details.setVersion(object.getVersion());
		details.setEditors(object.getEditors());
		details.setContributors(object.getContributors());
		details.setPreferredPrefix(object.getPreferredPrefix());
        document.addOntologyDetailView(details);     
		return details;
	}
	
	public Object visit(Rule object) {
	    logger.debug("Visiting rule " + object);
	    RuleDetailView details = new RuleDetailView(object);
	    details.setIdentifier(object.getIdentifier());
	    document.addRuleDetailView(details);
	    return details;
	}

	/* (non-Javadoc)
	 * @see es.ctic.parrot.transformers.AbstractDocumentableObjectVisitor#visit(es.ctic.parrot.de.OntologyIndividual)
	 */
	@Override
	public Object visit(OntologyIndividual object) {
	    logger.debug("Visiting individual " + object);
		OntologyIndividualDetailView details = new OntologyIndividualDetailView(object);
		details.setLabel(object.getLabel(lang));
		details.setUri(object.getURI());
		details.addAllTypes(object.getTypes());
		document.addOntologyIndividualDetailView(details);
		return details;
	}
	

}
