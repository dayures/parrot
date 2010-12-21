package es.ctic.parrot.docmodel;

import java.util.Collection;
import java.util.Collections;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.DocumentableOntologicalObject;
import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.Rule;
import es.ctic.parrot.de.RuleSet;

public class RuleSetDetailView implements DetailView{

    private static final Logger logger = Logger.getLogger(RuleSetDetailView.class);
    private RuleSet ruleSet;
    private Identifier identifier;
	private String uri;
	private String label;
	private String comment;
	private String date;
	private Collection<String> creators;
	private Collection<String> contributors;
	private Collection<String> publishers;
	private Collection<String> depictions;
	private Collection<String> videos;
	private Collection<DocumentableOntologicalObject> referencedOntologicalObjects;
	private Collection<Rule> rules;

	
	public RuleSetDetailView(RuleSet ruleSet) {
        this.ruleSet = ruleSet;
        logger.debug("Created " + this.getClass());
    }
    
    /**
	 * @return the referencedOntologicalObjects
	 */
	public Collection<DocumentableOntologicalObject> getReferencedOntologicalObjects() {
		return Collections.unmodifiableCollection(referencedOntologicalObjects);
	}

	/**
	 * @param referencedOntologicalObjects the referencedOntologicalObjects to set
	 */
	public void setReferencedOntologicalObjects(
			Collection<DocumentableOntologicalObject> referencedOntologicalObjects) {
		this.referencedOntologicalObjects = referencedOntologicalObjects;
	}

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

	public String getAnchor() {
		return ruleSet.getLocalName();
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

	public void setDepictions(Collection<String> depictions) {
		this.depictions = depictions;
	}

	public Collection<String> getDepictions() {
		return Collections.unmodifiableCollection(depictions);
	}

	public void setVideos(Collection<String> videos) {
		this.videos = videos;
	}

	public Collection<String> getVideos() {
		return Collections.unmodifiableCollection(videos);
	}

	/**
	 * @param rules the rules to set
	 */
	public void setRules(Collection<Rule> rules) {
		this.rules = rules;
	}

	/**
	 * @return the rules
	 */
	public Collection<Rule> getRules() {
		return Collections.unmodifiableCollection(rules);
	}
    
}
