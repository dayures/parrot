package es.ctic.parrot.docmodel;

import java.util.Collection;
import java.util.Collections;
import java.util.Locale;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.Label;
import es.ctic.parrot.de.RelatedDocument;
import es.ctic.parrot.de.RuleSet;

public class RuleSetDetailView implements DetailView {

    private static final Logger logger = Logger.getLogger(RuleSetDetailView.class);
    private Identifier identifier;
	private String uri;
	private String label;
	private String comment;
	private String date;
	private String strategy;
	private Integer priority;
	private Collection<String> creators;
	private Collection<String> contributors;
	private Collection<String> publishers;
	private Collection<DocumentableObjectReference> referencedOntologicalObjects;
	private Collection<Label> labels;
	private Collection<RelatedDocument> relatedDocuments;
	private Collection<DocumentableObjectReference> rules;
	private Collection<DocumentableObjectReference> ruleSets;
	private DocumentableObjectReference parent;
    private String anchor;

	
	private RuleSetDetailView() {
        logger.debug("Created " + this.getClass());
    }
    
    /**
	 * @return the referencedOntologicalObjects
	 */
	public Collection<DocumentableObjectReference> getReferencedOntologicalObjects() {
		return Collections.unmodifiableCollection(referencedOntologicalObjects);
	}

	/**
	 * @param referencedOntologicalObjects the referencedOntologicalObjects to set
	 */
	public void setReferencedOntologicalObjects(
			Collection<DocumentableObjectReference> referencedOntologicalObjects) {
		this.referencedOntologicalObjects = referencedOntologicalObjects;
	}

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

	public String getAnchor() {
		return anchor;
	}
	
	public String getUri(){
		return uri;
	}
	
	public void setUri(String uri){
		this.uri=uri;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public void setPublishers(Collection<String> publishers) {
		this.publishers = publishers;
	}

	public Collection<String> getPublishers() {
		return Collections.unmodifiableCollection(publishers);
	}

	public void setCreators(Collection<String> creators) {
		this.creators = creators;
	}

	public Collection<String> getCreators() {
		return Collections.unmodifiableCollection(creators);
	}

	public void setContributors(Collection<String> contributors) {
		this.contributors = contributors;
	}

	public Collection<String> getContributors() {
		return Collections.unmodifiableCollection(contributors);
	}

	/**
	 * @param rules the rules to set
	 */
	public void setRules(Collection<DocumentableObjectReference> rules) {
		this.rules = rules;
	}

	/**
	 * @return the rules
	 */
	public Collection<DocumentableObjectReference> getRules() {
		return Collections.unmodifiableCollection(rules);
	}

	/**
	 * @param strategy the strategy to set
	 */
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	/**
	 * @return the strategy
	 */
	public String getStrategy() {
		return strategy;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	/**
	 * @return the priority
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(DocumentableObjectReference parent) {
		this.parent = parent;
	}

	/**
	 * @return the parent
	 */
	public DocumentableObjectReference getParent() {
		return parent;
	}

	/**
	 * @param ruleSets the ruleSets to set
	 */
	public void setRuleSets(Collection<DocumentableObjectReference> ruleSets) {
		this.ruleSets = ruleSets;
	}

	/**
	 * @return the ruleSets
	 */
	public Collection<DocumentableObjectReference> getRuleSets() {
		return Collections.unmodifiableCollection(ruleSets);
	}
	
	/**
	 * @param labels the labels to set
	 */
	public void setLabels(Collection<Label> labels) {
		this.labels = labels;
	}

	/**
	 * @return the labels
	 */
	public Collection<Label> getLabels() {
		return Collections.unmodifiableCollection(labels);
	}
	
	/**
	 * @param relatedDocuments the relatedDocuments to set
	 */
	public void setRelatedDocuments(Collection<RelatedDocument> relatedDocuments) {
		this.relatedDocuments = relatedDocuments;
	}

	/**
	 * @return the relatedDocuments
	 */
	public Collection<RelatedDocument> getRelatedDocuments() {
		return relatedDocuments;
	}

    public void setAnchor(String anchor) {
        this.anchor = anchor;
    }

    public static RuleSetDetailView createFromRuleSet(RuleSet object, Locale locale) {
        RuleSetDetailView details = new RuleSetDetailView();
        details.setIdentifier(object.getIdentifier());
        details.setUri(object.getURI());
        details.setLabel(object.getLabel(locale));
        details.setComment(object.getComment(locale));
        details.setDate(object.getDate());
        details.setCreators(object.getCreators());
        details.setContributors(object.getContributors());
        details.setPublishers(object.getPublishers());
        details.setReferencedOntologicalObjects(DocumentableObjectReference.createReferences(object.getReferencedOntologicalObjects(), locale));
        details.setRules(DocumentableObjectReference.createReferences(object.getRules(), locale));
        details.setRuleSets(DocumentableObjectReference.createReferences(object.getRuleSets(), locale));
        details.setPriority(object.getPriority());
        details.setStrategy(object.getStrategy());
        details.setParent(DocumentableObjectReference.createReference(object.getParent(), locale));
        details.setLabels(object.getLabels());
        details.setAnchor(object.getLocalName());
        details.setRelatedDocuments(object.getRelatedDocuments(locale));        
        return details;
    }
}
