package es.ctic.parrot.reader.jena;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.ontology.OntClass;
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
import es.ctic.parrot.de.OntologyClass;
import es.ctic.parrot.de.RelatedDocument;
import es.ctic.parrot.de.Rule;
import es.ctic.parrot.de.URIIdentifier;
import es.ctic.parrot.de.UndefinedOntologyDocumentableObject;

public abstract class AbstractJenaDocumentableObject extends
		AbstractDocumentableObject {
	
	private static final Logger logger = Logger.getLogger(AbstractJenaDocumentableObject.class);
	
	private OntResource ontResource;
	private Collection<Rule> inverseRuleReferences = new HashSet<Rule>();
	private OntResourceAnnotationStrategy annotationStrategy;

	/**
	 * @return the ontResource
	 */
	public OntResource getOntResource() {
		return ontResource;
	}

	public AbstractJenaDocumentableObject(OntResource ontResource, DocumentableObjectRegister register, OntResourceAnnotationStrategy annotationStrategy) {
		super();
		this.ontResource = ontResource;
		this.setAnnotationStrategy(annotationStrategy);
		this.setRegister(register);
		logger.debug("Created a documentable object for " + ontResource);
	}
	
	/** (non-Javadoc)
	 * @see es.ctic.parrot.de.DocumentableObject#getURI()
	 * 
	 * @return the uri of the documentable object or null if it's a blank node
	 */
	public String getURI() {
		return ontResource.getURI();
	}
	
	public Identifier getIdentifier() {
		if (ontResource.isAnon() == true){
			return new JenaAnonymousIdentifier(ontResource.getId());
		} else{
			return new URIIdentifier(ontResource.getURI());
		}
	}

	public String getURIAbbrev(){
		String ns=ontResource.getModel().getNsURIPrefix(ontResource.getNameSpace());
		if(ns!=null){
			return ns+":"+ontResource.getLocalName();
		}
		return ontResource.getURI();
	}
	
	public int compareTo(DocumentableOntologicalObject o) {
		return getURI().compareTo(o.getURI());
	}

	protected Collection<OntologyClass> ontClassIteratorToOntologyClassList(Iterator<OntClass> it) {
		List<OntologyClass> ontologyClassList = new LinkedList<OntologyClass>();
		
		while(it.hasNext()){
			OntClass clazz=it.next();
			
			Identifier identifier = null;
			
			if (clazz.isAnon() == false){
				identifier = new URIIdentifier(clazz.getURI());
			} else {
				identifier = new JenaAnonymousIdentifier(clazz.getId());
			}

			OntologyClass _class = (OntologyClass) this.getRegister().findDocumentableObject(identifier); 
			if (_class != null) { // do not add null elements in the list 
				ontologyClassList.add(_class);
			}
		}
		return ontologyClassList;
	}
	
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
	
	public void addInverseRuleReference(Rule rule) {
		inverseRuleReferences.add(rule);
	}
	
	public Collection<Rule> getInverseRuleReferences(){
		return Collections.unmodifiableCollection(inverseRuleReferences);
	}
	
	/**
	 * 
	 * @param uri The URI to check
	 * @return true if the URI is domain specific, false if not
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
        return getAnnotationStrategy().getLabel(getOntResource(), locale);
    }
    
    public String getLabel() {
        return getAnnotationStrategy().getLabel(getOntResource(), null);
    }

	public Collection<RelatedDocument> getRelatedDocuments(Locale locale) {
        return getAnnotationStrategy().getRelatedDocuments(getOntResource(), locale);
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


}
