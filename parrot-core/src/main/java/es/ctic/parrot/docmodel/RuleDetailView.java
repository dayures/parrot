package es.ctic.parrot.docmodel;

import java.util.Collection;
import java.util.Collections;
import java.util.Locale;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.Label;
import es.ctic.parrot.de.RelatedDocument;
import es.ctic.parrot.de.Rule;

/**
 * A detailed view of a rule.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class RuleDetailView implements DetailView{

    private static final Logger logger = Logger.getLogger(RuleDetailView.class);
    private Identifier identifier;
	private String uri;
	private String label;
	private String comment;
	private String date;
	private Collection<String> creators;
	private Collection<String> contributors;
	private Collection<String> publishers;
	private Collection<DocumentableObjectReference> referencedOntologicalObjects;
	private Collection<Label> labels;
	private Collection<RelatedDocument> relatedDocuments;
	private DocumentableObjectReference parent;
    private String anchor;
	
	private RuleDetailView() {
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
	
    /**
	 * @param anchor the anchor to set
	 */
	public void setAnchor(String anchor) {
		this.anchor = anchor;
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
	
    public static RuleDetailView createFromRule(Rule object, Locale locale) {
    	
	    RuleDetailView details = new RuleDetailView();
	    details.setIdentifier(object.getIdentifier());
		details.setUri(object.getURI());
		details.setLabel(object.getLabel(locale));
		details.setComment(object.getComment(locale));
		details.setDate(object.getDate());
		details.setCreators(object.getCreators());
		details.setContributors(object.getContributors());
		details.setPublishers(object.getPublishers());
		details.setParent(DocumentableObjectReference.createReference(object.getParent(), locale));
		details.setReferencedOntologicalObjects(DocumentableObjectReference.createReferences(object.getReferencedOntologicalObjects(), locale));
		details.setLabels(object.getLabels());
        details.setAnchor(object.getLocalName());
		details.setRelatedDocuments(object.getRelatedDocuments(locale));
		
		return details;

    }

    
}
