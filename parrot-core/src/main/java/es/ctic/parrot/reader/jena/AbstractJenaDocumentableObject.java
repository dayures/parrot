package es.ctic.parrot.reader.jena;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;

import es.ctic.parrot.de.AbstractDocumentableObject;
import es.ctic.parrot.de.DocumentableObject;
import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.de.DocumentableOntologicalObject;
import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.Label;
import es.ctic.parrot.de.RelatedDocument;
import es.ctic.parrot.de.Rule;
import es.ctic.parrot.de.URIIdentifier;
import es.ctic.parrot.de.UndefinedOntologyDocumentableObject;


/**
 * An implementation of the DocumentableOntologicalObject interface to serve as a basis for implementing various kinds of ontology documentable elements.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public abstract class AbstractJenaDocumentableObject extends
		AbstractDocumentableObject implements DocumentableOntologicalObject {
	
	private static final Logger logger = Logger.getLogger(AbstractJenaDocumentableObject.class);
	
	private OntResource ontResource;
	private Collection<Rule> inverseRuleReferences = new HashSet<Rule>();
	private OntResourceAnnotationStrategy annotationStrategy;

	/**
	 * Constructs an abstract Jena documentable element, setting the ontResource, the annotation strategy and the register.
	 * @param ontResource the ontResource.
	 * @param register the register.
	 * @param annotationStrategy the annotation strategy.
	 */
	public AbstractJenaDocumentableObject(OntResource ontResource, DocumentableObjectRegister register, OntResourceAnnotationStrategy annotationStrategy) {
		super();
		this.setOntResource(ontResource);
		this.setAnnotationStrategy(annotationStrategy);
		this.setRegister(register);
		logger.debug("Created a documentable object for " + ontResource);
	}
	
	/**
	 * Returns the ontResource.
	 * @return the ontResource.
	 */
	public OntResource getOntResource() {
		return ontResource;
	}
	
	/**
	 * Sets the ontResource.
	 * @param ontResource the ontResource to set.
	 */
	private void setOntResource(OntResource ontResource) {
		this.ontResource = ontResource;
	}
	
	public void addInverseRuleReference(Rule rule) {
		inverseRuleReferences.add(rule);
	}
	
	public Collection<Rule> getInverseRuleReferences(){
		return Collections.unmodifiableCollection(inverseRuleReferences);
	}
	
	/**
	 * Sets the annotation strategy.
	 * @param annotationStrategy the annotation strategy to set.
	 */
	private void setAnnotationStrategy(OntResourceAnnotationStrategy annotationStrategy) {
		this.annotationStrategy = annotationStrategy;
	}

	/**
	 * Returns the annotation strategy.
	 * @return the annotation strategy
	 */
	public OntResourceAnnotationStrategy getAnnotationStrategy() {
		return annotationStrategy;
	}
	
	/**
	 * Returns the URI of this documentable element or <code>null</code> if it's a blank node.
	 * @return the URI of this documentable element or <code>null</code> if it's a blank node.
	 */
	public String getURI() {
		return getOntResource().getURI();
	}
	/**
	 * Returns the identifier.
	 * @return the identifier.
	 */
	public Identifier getIdentifier() {
		if (getOntResource().isAnon() == true){
			return new JenaAnonymousIdentifier(getOntResource().getId());
		} else{
			return new URIIdentifier(getOntResource().getURI());
		}
	}

	/**
	 * Returns the compact URI (for instance <code>foaf:name</code>) or the URI if it cannot be compact. 
	 * @return the compact URI or the URI if it cannot be compact.
	 */
	public String getURIAbbrev(){
		String ns=getOntResource().getModel().getNsURIPrefix(getOntResource().getNameSpace());
		if(ns!=null){
			return ns+":"+getOntResource().getLocalName();
		}
		return getOntResource().getURI();
	}
	
	public int compareTo(DocumentableOntologicalObject o) {
		return getURI().compareTo(o.getURI());
	}

    /**
     * Converts an iterator over Jena resources to a collection of documentable elements.
     * @param it an iterator over Jena resources.
     * @return a collection of documentable elements.
     */
	@SuppressWarnings("unchecked")
	protected <TR extends DocumentableObject, TJ extends Resource> Collection<TR> resourceIteratorToDocumentableObjectList(Iterator<TJ> it) {
		
		List<TR> documentableObjectList = new LinkedList<TR>();
		
		while(it.hasNext()){
			Resource resource = it.next();
			
			Identifier identifier = null;
			
			if (resource.isAnon() == false){
				identifier = new URIIdentifier(resource.getURI());
			} else {
				identifier = new JenaAnonymousIdentifier(resource.getId());
			}
			
			TR _resource = (TR) this.getRegister().findDocumentableObject(identifier); 

			if (_resource != null) { 
				documentableObjectList.add(_resource);
			} else {  // do not add null elements in the list
				// logger.debug("Not found in register: " + identifier);
				if (resource.isAnon()){
					logger.debug("Not added in the list the anon resource: " + resource.getId());
				}else {
					if (isDomainSpecific(resource.getURI())) {
						_resource = (TR) new UndefinedOntologyDocumentableObject(resource.getURI());
						documentableObjectList.add(_resource);
					} else {
						logger.debug("Not added in the list the resource " + identifier + " (not domain specific)");
					}
				}

			}
		}
		return documentableObjectList;
	}
	
	/**
     * Returns <code>true</code> if, and only if, the URI is domain specified.
     * @param uri the URI.
     * @return <code>true</code> if the URI is domain specified, otherwise <code>false</code>.
	 */
	public static boolean isDomainSpecific(String uri) {
       	return !uri.startsWith(RDFS.getURI()) && !uri.startsWith(RDF.getURI()) && !uri.startsWith(OWL.getURI());
    }
	
    public String getComment(Locale locale) {
		return getAnnotationStrategy().getComment(getOntResource(), locale);
	}
    
	public Collection<Label> getLabels(Locale locale){
   		return getAnnotationStrategy().getLabels(getOntResource(), locale);
   	}

    public Collection<Label> getLabels(){
   		return getAnnotationStrategy().getLabels(getOntResource(), null);
   	}
    
    public String getLabel(Locale locale) {
    	if (ontResource.isAnon()){
    		return getKindString() + getIdentifier().toString();
    	} else {
    		return getAnnotationStrategy().getLabel(getOntResource(), locale);
    	}
    }
    
    public String getLabel() {
        return getAnnotationStrategy().getLabel(getOntResource(), null);
    }

	public Collection<RelatedDocument> getRelatedDocuments(Locale locale) {
        return getAnnotationStrategy().getRelatedDocuments(getOntResource(), locale);
    }
	
	public DocumentableObject getIsDefinedBy() {
		String isDefinedBy = getAnnotationStrategy().getIsDefinedBy(getOntResource());
		if (isDefinedBy != null) {
			Identifier identifier = new URIIdentifier(isDefinedBy);
			return this.getRegister().findDocumentableObject(identifier);	
		} else {
			return null;
		}
    }
	
	public boolean isDeprecated(){
		return getAnnotationStrategy().isDeprecated(getOntResource());
	}
	
	public String getVersion() {
		return getAnnotationStrategy().getVersion(getOntResource());
	}

	public String getDate() {
		return getAnnotationStrategy().getDate(getOntResource());
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
	
	public String getRights() {
		return getAnnotationStrategy().getRights(getOntResource());
	}
	
	public String getLicenseLabel() {
		return getAnnotationStrategy().getLicenseLabel(getOntResource());
	}
	
}
