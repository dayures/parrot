package es.ctic.parrot.reader.rifle;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntResource;

import es.ctic.parrot.de.AbstractDocumentableObject;
import es.ctic.parrot.de.DocumentableObject;
import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.de.DocumentableOntologicalObject;
import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.Label;
import es.ctic.parrot.de.RelatedDocument;
import es.ctic.parrot.de.Rule;
import es.ctic.parrot.de.RuleSet;
import es.ctic.parrot.de.URIIdentifier;
import es.ctic.parrot.reader.jena.OntResourceAnnotationStrategy;
import es.ctic.parrot.transformers.DocumentableObjectVisitor;
import es.ctic.parrot.transformers.TransformerException;

public class RuleSetImpl extends AbstractDocumentableObject implements RuleSet {
	
	private net.sourceforge.rifle.ast.Group ruleSet;
	private DocumentableObject parent;
	private OntResource ontResource;
    private OntResourceAnnotationStrategy annotationStrategy;

    private Identifier identifier;
	
	private static final Logger logger = Logger.getLogger(RuleSetImpl.class);
	
	public RuleSetImpl(net.sourceforge.rifle.ast.Group group, DocumentableObjectRegister register, OntResourceAnnotationStrategy annotationStrategy, OntModel ontModel) {
		this.ruleSet = group;
		this.setRegister(register);
		this.setAnnotationStrategy(annotationStrategy);
		
		if (ruleSet.getId() == null) {
			// Rule without identifier
		    this.identifier = new RifleAnonymousIdentifier(Integer.toString(ruleSet.getLocalId()));
		} else {
			// Rule with identifier
			this.identifier = new URIIdentifier(ruleSet.getId());
		}
		
		ontModel.add(ruleSet.getIriMeta());
    	this.setOntResource(ontModel.getOntResource(getURI()));
	}

	public Object accept(DocumentableObjectVisitor visitor) throws TransformerException {
		return visitor.visit(this);
	}

	public Identifier getIdentifier() {
		return identifier;
	}
	
	/** (non-Javadoc)
	 * @see es.ctic.parrot.de.DocumentableObject#getURI()
	 * 
	 * @return the uri (id) of the ruleset or null if the ruleset has not id
	 */
	public String getURI() {
		if (ruleSet.getId() == null) {
		    return null;
		} else {
			return getIdentifier().toString();
		}	
	}
	
	public Collection<String> getDeclaredVars() {
		return new LinkedList<String>();//FIXME create proper list	
	}
	
	public int compareTo(DocumentableOntologicalObject o) {
		return getURI().compareTo(o.getURI());
	}


	/**
	 * @param ontResource the ontResource to set
	 */
	public void setOntResource(OntResource ontResource) {
		this.ontResource = ontResource;
	}

	/**
	 * @return the ontResource
	 */
	public OntResource getOntResource() {
		return ontResource;
	}
	
	public Collection<DocumentableOntologicalObject> getReferencedOntologicalObjects() {
		Set<DocumentableOntologicalObject> referencedOntologicalObjects = new TreeSet<DocumentableOntologicalObject>(new DocumentableObjectComparator());
		// FIXME implement this method properly
		return referencedOntologicalObjects;

	}

	public Collection<Rule> getRules() {
		return Collections.unmodifiableCollection(astRuleCollectionToRuleCollection(ruleSet.getRules()));
	}
	
	protected Collection<es.ctic.parrot.de.Rule> astRuleCollectionToRuleCollection(Collection<net.sourceforge.rifle.ast.Rule> astRules) {
		Collection<es.ctic.parrot.de.Rule> ruleList = new LinkedList<es.ctic.parrot.de.Rule>();

		for(net.sourceforge.rifle.ast.Rule astRule : astRules){
			Rule rule = (Rule) this.getRegister().findDocumentableObject(new URIIdentifier(astRule.getId()));
			if (rule != null){
				ruleList.add(rule);
			}else{
				// anonymous rule
				rule = (Rule) this.getRegister().findDocumentableObject(new RifleAnonymousIdentifier(Integer.toString(astRule.getLocalId())));
				if (rule != null){ 
					ruleList.add(rule);
				} else {
					logger.debug("Rule not found in register " + astRule.toString());
				}
			}
		}
		return ruleList;
	}

	public String getStrategy() {
		return ruleSet.getStrategy();
	}

	public Integer getPriority() {
		return ruleSet.getPriority();
	}

	public DocumentableObject getParent() {
		return this.parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(DocumentableObject parent) {
		this.parent = parent;
	}

	public Collection<RuleSet> getRuleSets() {
		return Collections.unmodifiableCollection(astGroupCollectionToRuleSetCollection(ruleSet.getGroups()));

	}

	protected Collection<es.ctic.parrot.de.RuleSet> astGroupCollectionToRuleSetCollection(Collection<net.sourceforge.rifle.ast.Group> astGroups) {
		
		Collection<es.ctic.parrot.de.RuleSet> ruleSetList = new LinkedList<es.ctic.parrot.de.RuleSet>();

		for(net.sourceforge.rifle.ast.Group group : astGroups){
			RuleSet ruleSet = (RuleSet) this.getRegister().findDocumentableObject(new URIIdentifier(group.getId()));
			if (ruleSet != null){
				ruleSetList.add(ruleSet);
			}else{
				// anonymous rule
				ruleSet = (RuleSet) this.getRegister().findDocumentableObject(new RifleAnonymousIdentifier(Integer.toString(group.getLocalId())));
				if (ruleSet != null){ 
					ruleSetList.add(ruleSet);
				} else {
					logger.debug("Group not found in register " + group.toString());
				}
			}
		}
		return ruleSetList;
	}
	
    public String getKindString() {
        return Kind.RULE_SET.toString();
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
    
    /****************************************************************************/
    
    public String getComment(Locale locale) {
    	return getAnnotationStrategy().getComment(getOntResource(), locale);
    }
    
    public Collection<Label> getLabels(){
   		return getAnnotationStrategy().getLabels(getOntResource(), null);
   	}

    public Collection<Label> getLabels(Locale locale){
   		return getAnnotationStrategy().getLabels(getOntResource(), locale);
   	}

    public String getLabel() {
   		return getLabel(null);
    }

    public String getLabel(Locale locale) {
    	// Anonymous ruleset
    	if (getOntResource() == null){
    		return getKindString() + getIdentifier().toString();
    	} else {
    		return getAnnotationStrategy().getLabel(getOntResource(), locale);
    	}
    }
    
	public Collection<RelatedDocument> getRelatedDocuments(Locale locale) {
        return getAnnotationStrategy().getRelatedDocuments(getOntResource(), locale);
    }
    
	public Collection<String> getCreators() {
		return getAnnotationStrategy().getCreators(getOntResource());
	}

	public Collection<String> getContributors() {
		return getAnnotationStrategy().getContributors(getOntResource());
	}

	public Collection<String> getPublishers() {
		return getAnnotationStrategy().getPublishers(getOntResource());
	}
	
	public String getDate() {
		return getAnnotationStrategy().getDate(getOntResource());
	}


}

