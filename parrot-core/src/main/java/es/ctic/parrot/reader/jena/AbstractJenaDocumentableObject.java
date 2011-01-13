package es.ctic.parrot.reader.jena;

import java.util.ArrayList;
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
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.ResourceRequiredException;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

import es.ctic.parrot.de.AbstractDocumentableObject;
import es.ctic.parrot.de.DocumentableObject;
import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.de.DocumentableOntologicalObject;
import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.OntologyClass;
import es.ctic.parrot.de.Rule;
import es.ctic.parrot.de.URIIdentifier;
import es.ctic.parrot.utils.URIUtils;

public abstract class AbstractJenaDocumentableObject extends
		AbstractDocumentableObject {
	
	private static final Logger logger = Logger.getLogger(AbstractJenaDocumentableObject.class);
	
	private OntResource ontResource;
	private Collection<Rule> inverseRuleReferences = new HashSet<Rule>();
	private static final String FOAF_DEPICTION = "http://xmlns.com/foaf/0.1/depiction";
	private static final String OG_VIDEO = "http://ogp.me/ns#video";

	/**
	 * @return the ontResource
	 */
	public OntResource getOntResource() {
		return ontResource;
	}

	public AbstractJenaDocumentableObject(OntResource ontResource, DocumentableObjectRegister register) {
		super();
		this.ontResource = ontResource;
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
	
    public String getLabel(Locale locale) {
        String label = null;
    	if (getOntResource() == null){
    		return URIUtils.getReference(getURI());
    	}
    	else{
	        if (locale !=null)
	        	label = ontResource.getLabel(locale.toString());
	        
	        if (label == null) {
	            label = ontResource.getLabel(null);
	        } 
	        
	        if (label == null) {
	            label = URIUtils.getReference(ontResource.getURI());
	        } 
	
	        return label;
    	}
    }
    
    public String getLabel() {
        return this.getLabel(null);
    }
    
    public String getComment(Locale locale) {
        String comment = ontResource.getComment(locale.toString());
        if (comment == null) {
            return ontResource.getComment(null);
        } else {
            return comment;
        }
    }

	public Identifier getIdentifier() {
		if (ontResource.isAnon() == true){
			return new JenaAnonymousIdentifier(ontResource.getModel(),ontResource.getId());
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
				identifier = new JenaAnonymousIdentifier(clazz.getModel(), clazz.getId());
			}

			OntologyClass _class = (OntologyClass) this.getRegister().findDocumentableObject(identifier); 
			if (_class != null) { // do not add null elements in the list 
				ontologyClassList.add(_class);
			}
		}
		return ontologyClassList;
	}
	
//	protected Collection<OntologyIndividual> ontResourceIteratorToOntologyInstanceList(Iterator<Individual> it) {
//		List<OntologyIndividual> ontologyIndividualList = new LinkedList<OntologyIndividual>();
//		while(it.hasNext()){
//			Individual individual=it.next();
//			
//			Identifier identifier = null;
//			
//			if (individual.isAnon() == false){
//				identifier = new URIIdentifier(individual.getURI());
//			} else {
//				identifier = new JenaAnonymousIdentifier(individual.getModel(), individual.getId());
//			}
//
//			OntologyIndividual _individual = (OntologyIndividual) this.getRegister().findDocumentableObject(identifier);
//
//			if (_individual != null) { // do not add null elements in the list 
//				ontologyIndividualList.add(_individual);
//			}
//		}
//		return ontologyIndividualList;
//	}
	
//	protected Collection<OntologyProperty> ontPropertyIteratorToOntologyPropertyList(Iterator<OntProperty> it) {
//		List<OntologyProperty> ontologyPropertyList = new LinkedList<OntologyProperty>();
//		
//		while(it.hasNext()){
//			OntProperty property = it.next();
//			
//			Identifier identifier = new URIIdentifier(property.getURI());
//
//			OntologyProperty _property = (OntologyProperty) this.getRegister().findDocumentableObject(identifier); 
//
//			if (_property != null) { // do not add null elements in the list 
//				ontologyPropertyList.add(_property);
//			}
//		}
//		return ontologyPropertyList;
//	}
	
	protected Collection<DocumentableObject> resourceIteratorToDocumentableObjectList(Iterator<Resource> it) {
		
		List<DocumentableObject> documentableObjectList = new LinkedList<DocumentableObject>();
		
		while(it.hasNext()){
			Resource resource = it.next();
			
			Identifier identifier = null;
			
			if (resource.isAnon() == false){
				identifier = new URIIdentifier(resource.getURI());
			} else {
				identifier = new JenaAnonymousIdentifier(resource.getModel(), resource.getId());
			}
			
			DocumentableObject _resource = this.getRegister().findDocumentableObject(identifier); 

			if (_resource != null) { // do not add null elements in the list 
				documentableObjectList.add(_resource);
			} else {
				logger.debug("Not found in register: " + identifier);
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
	
	public List<String> getDepictions() {
    	if (getOntResource() == null){
    		return new ArrayList<String>();
    	} else {
			ArrayList<String> depictions = new ArrayList<String>();
			StmtIterator it = ontResource.listProperties(ResourceFactory.createProperty(FOAF_DEPICTION));
			while(it.hasNext()){
				Statement statement = it.nextStatement();
				try {
					depictions.add(statement.getResource().getURI());
				} catch (ResourceRequiredException e)  {
					logger.warn("Ignore triple "+ statement +" because it is not a Object property");
				}
			}
			return depictions;
    	}
	}
	
	public List<String> getVideos() {
    	if (getOntResource() == null){
    		return new ArrayList<String>();
    	} else {		
			ArrayList<String> videos = new ArrayList<String>();
			StmtIterator it = ontResource.listProperties(ResourceFactory.createProperty(OG_VIDEO));
			while(it.hasNext()){
				Statement statement = it.nextStatement();
				try{
					videos.add(it.nextStatement().getLiteral().getString());
				} catch (ResourceRequiredException e)  {
					logger.warn("Ignore triple "+ statement +" because it is not a Object property");
				}
			}
			return videos;
    	}
	}
	

}
