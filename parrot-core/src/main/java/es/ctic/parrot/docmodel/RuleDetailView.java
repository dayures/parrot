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
	
	/**
	 * Constructs a rule detail view (Suppress default constructor for noninstantiability).
	 */
	private RuleDetailView() {
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
	public void setReferencedOntologicalObjects(
			Collection<DocumentableObjectReference> referencedOntologicalObjects) {
		this.referencedOntologicalObjects = referencedOntologicalObjects;
	}

	/**
	 * Set the identifier.
	 * @param identifier the identifier to set.
	 */
	public void setIdentifier(Identifier identifier) {
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
	public void setUri(String uri) {
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
	public void setLabel(String label) {
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
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * Sets the anchor.
	 * @param anchor the anchor to set
	 */
	public void setAnchor(String anchor) {
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
	public void setDate(String date) {
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
	public void setPublishers(Collection<String> publishers) {
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
	public void setCreators(Collection<String> creators) {
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
	public void setContributors(Collection<String> contributors) {
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
	public void setParent(DocumentableObjectReference parent) {
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
	public void setLabels(Collection<Label> labels) {
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
	public void setRelatedDocuments(Collection<RelatedDocument> relatedDocuments) {
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
	 * Returns a detailed view for the rule given.
	 * @param object the rule.
	 * @param locale the locale.
	 * @return a detailed view for a rule.
	 */
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
