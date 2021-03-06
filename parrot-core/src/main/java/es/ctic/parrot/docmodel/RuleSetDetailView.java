package es.ctic.parrot.docmodel;

import java.util.Collection;
import java.util.Collections;
import java.util.Locale;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.RuleSet;

/**
 * A detailed view of a rule set.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class RuleSetDetailView extends AbstractVersionableDetailView {

    private static final Logger logger = Logger.getLogger(RuleSetDetailView.class);

	private String strategy;
	private Integer priority;

	private Collection<DocumentableObjectReference> referencedOntologicalObjects;
	private Collection<DocumentableObjectReference> rules;
	private Collection<DocumentableObjectReference> ruleSets;
	private DocumentableObjectReference parent;
    

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
		details.setUriFragment(object.getUriFragment());
        details.setLabel(object.getLabel(locale));
        logger.debug("ruleset="+object +"- object.getLabel(locale)="+object.getLabel(locale));
        details.setDescription(object.getDescription(locale));
        
		// Control version information
		details.setVersion(object.getVersion());

		details.setDate(object.getDate());
		details.setModifiedDate(object.getModifiedDate());
		details.setIssuedDate(object.getIssuedDate());
		
		details.setCreators(object.getCreators());
		details.setContributors(object.getContributors());
		details.setPublishers(object.getPublishers());
		details.setCreatorAgents(object.getCreatorAgents());
		details.setContributorAgents(object.getContributorAgents());
		details.setPublisherAgents(object.getPublisherAgents());

		details.setRights(object.getRights());
		details.setLicenseLabel(object.getLicenseLabel());
		
		details.setIsDefinedBy(DocumentableObjectReference.createReference(object.getIsDefinedBy(),locale));
		
		// Other info
		
        details.setReferencedOntologicalObjects(DocumentableObjectReference.createReferences(object.getReferencedOntologicalObjects(), locale));
        details.setRules(DocumentableObjectReference.createReferences(object.getRules(), locale));
        details.setRuleSets(DocumentableObjectReference.createReferences(object.getRuleSets(), locale));
        details.setPriority(object.getPriority());
        details.setStrategy(object.getStrategy());
        details.setParent(DocumentableObjectReference.createReference(object.getParent(), locale));

        details.setAnchor(object.getLocalName());

        details.setLexicalInformation(LexicalInformation.createFromLabels(object.getLabels(), details.getLabel(), locale));
        details.setRelatedDocuments(object.getRelatedDocuments(locale));        

        return details;
    }
}
