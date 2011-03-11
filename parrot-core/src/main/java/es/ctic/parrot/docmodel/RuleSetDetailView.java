package es.ctic.parrot.docmodel;

import java.util.Collection;
import java.util.Collections;
import java.util.Locale;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.Label;
import es.ctic.parrot.de.RelatedDocument;
import es.ctic.parrot.de.RuleSet;

/**
 * A detailed view of a rule set.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
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

	
	/**
	 * Constructs a rule individual detail view (Suppress default constructor for noninstantiability).
	 */
	private RuleSetDetailView() {
        logger.debug("Created " + this.getClass());
    }
    
	/**
	 * Returns the references to the ontological elements that reference this rule.
	 * @return the references to the ontological elements that reference this rule.
	 */
	public Collection<DocumentableObjectReference> getReferencedOntologicalObjects() {
		return Collections.unmodifiableCollection(referencedOntologicalObjects);
	}

	/**
	 * Sets the references to the ontological elements that reference this rule.
	 * @param referencedOntologicalObjects the references to the ontological elements that reference this rule.
	 */
	private void setReferencedOntologicalObjects(
			Collection<DocumentableObjectReference> referencedOntologicalObjects) {
		this.referencedOntologicalObjects = referencedOntologicalObjects;
	}

	/**
	 * Set the identifier.
	 * @param identifier the identifier to set.
	 */
	private void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	}

	/**
	 * Returns the identifier.
	 * @return the identifier.
	 */
	public Identifier getIdentifier() {
		return identifier;
	}
	
	/**
	 * Returns the URI.
	 * @return the URI.
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * Sets the URI.
	 * @param uri the URI to set.
	 */
	private void setUri(String uri) {
		this.uri=uri;
	}

	/**
	 * Returns the label.
	 * @return the label.
	 */
	public String getLabel() {
		return this.label;
	}

	/**
	 * Sets the label.
	 * @param label the label to set.
	 */
	private void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Returns the description.
	 * @return the description.
	 */
	public String getComment() {
		return this.comment;
	}

	/**
	 * Sets the description.
	 * @param comment the description to set.
	 */	
	private void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * Sets the anchor.
	 * @param anchor the anchor to set
	 */
	private void setAnchor(String anchor) {
		this.anchor = anchor;
	}

	/**
	 * Returns the anchor.
	 * @return the anchor.
	 */
	public String getAnchor() {
		return anchor;
	}
	
	/**
	 * Set the date.
	 * @param date date to set.
	 */	
	private void setDate(String date) {
		this.date = date;
	}

	/**
	 * Returns the date.
	 * @return the date.
	 */	
	public String getDate() {
		return date;
	}

	/**
	 * Sets the publishers
	 * @param publishers to set.
	 */
	private void setPublishers(Collection<String> publishers) {
		this.publishers = publishers;
	}

	/**
	 * Returns the publishers.
	 * @return the publishers.
	 */
	public Collection<String> getPublishers() {
		return Collections.unmodifiableCollection(publishers);
	}

	/**
	 * Set the creators.
	 * @param creators creators to set
	 */	
	private void setCreators(Collection<String> creators) {
		this.creators = creators;
	}
	
	/**
	 * Returns the creators.
	 * @return the creators.
	 */	
	public Collection<String> getCreators() {
		return Collections.unmodifiableCollection(creators);
	}
	
	/**
	 * Set the contributors.
	 * @param contributors contributors to set
	 */
	private void setContributors(Collection<String> contributors) {
		this.contributors = contributors;
	}

	/**
	 * Returns the contributors.
	 * @return the contributors.
	 */
	public Collection<String> getContributors() {
		return Collections.unmodifiableCollection(contributors);
	}

	/**
	 * Sets the reference to the parent element.
	 * @param parent the reference to the parent element.
	 */
	private void setParent(DocumentableObjectReference parent) {
		this.parent = parent;
	}

	/**
	 * Returns the reference to the parent element.
	 * @return the reference to the parent element.
	 */
	public DocumentableObjectReference getParent() {
		return parent;
	}
	
	/**
	 * Set all labels.
	 * @param labels all labels to set.
	 */
	private void setLabels(Collection<Label> labels) {
		this.labels = labels;
	}

	/**
	 * Returns all labels.
	 * @return all labels.
	 */
	public Collection<Label> getLabels() {
		return Collections.unmodifiableCollection(labels);
	}
	
	/**
	 * Sets the related documents to this ontological element.
	 * @param relatedDocuments the related documents to this ontological element to set.
	 */
	private void setRelatedDocuments(Collection<RelatedDocument> relatedDocuments) {
		this.relatedDocuments = relatedDocuments;
	}

	/**
	 * Returns the related documents.
	 * @return the related documents.
	 */
	public Collection<RelatedDocument> getRelatedDocuments() {
		return relatedDocuments;
	}
	
	/**
	 * Sets the rules of this rule set.
	 * @param rules the rules of this rule set to set.
	 */
	private void setRules(Collection<DocumentableObjectReference> rules) {
		this.rules = rules;
	}

	/**
	 * Returns the rules of this rule set.
	 * @return the rules of this rule set.
	 */
	public Collection<DocumentableObjectReference> getRules() {
		return Collections.unmodifiableCollection(rules);
	}

	/**
	 * Sets the strategy of this rule set.
	 * @param strategy the strategy of this rule set to set
	 */
	private void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	/**
	 * Returns the strategy of this rule set.
	 * @return the strategy of this rule set.
	 */
	public String getStrategy() {
		return strategy;
	}

	/**
	 * Sets the priority of this rule set.
	 * @param priority the priority of this rule set to set.
	 */
	private void setPriority(Integer priority) {
		this.priority = priority;
	}

	/**
	 * Returns the priority of this rule set.
	 * @return the priority of this rule set.
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * Sets the rule sets of this rule set.
	 * @param ruleSets the rule sets of this rule set to set.
	 */
	private void setRuleSets(Collection<DocumentableObjectReference> ruleSets) {
		this.ruleSets = ruleSets;
	}

	/**
	 * Returns the rule sets of this rule set.
	 * @return the rule sets of this rule set.
	 */
	public Collection<DocumentableObjectReference> getRuleSets() {
		return Collections.unmodifiableCollection(ruleSets);
	}
	
	/**
	 * Returns a detailed view for the rule set given.
	 * @param object the rule set.
	 * @param locale the locale.
	 * @return a detailed view for a rule set.
	 */
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
