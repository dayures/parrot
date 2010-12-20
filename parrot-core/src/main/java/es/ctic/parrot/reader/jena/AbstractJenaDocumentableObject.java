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

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.ResourceRequiredException;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

import es.ctic.parrot.de.AbstractDocumentableObject;
import es.ctic.parrot.de.DocumentableOntologicalObject;
import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.OntologyClass;
import es.ctic.parrot.de.OntologyIndividual;
import es.ctic.parrot.de.Rule;
import es.ctic.parrot.de.URIIdentifier;

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

	public AbstractJenaDocumentableObject(OntResource ontResource) {
		super();
		this.ontResource = ontResource;
	}
	
	public String getURI() {
		return ontResource.getURI();
	}
	
    public String getLabel(Locale locale) {
        String label = null;
        
        if (locale !=null)
        	label = ontResource.getLabel(locale.toString());
        
        if (label == null) {
            label = ontResource.getLabel(null);
        } 
        
        if (label == null) {
            label = ontResource.getURI();
        } 

        return label;
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
		return new URIIdentifier(ontResource.getURI());
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
			OntClass superClass=it.next();
			if(superClass.getURI()!=null){
				OntologyClass _class=new OntologyClassJenaImpl(superClass);
				ontologyClassList.add(_class);
			}
		}
		return ontologyClassList;
	}
	
	protected Collection<OntologyIndividual> ontResourceIteratorToOntologyInstanceList(Iterator<Individual> it) {
		List<OntologyIndividual> ontologyIndividualList = new LinkedList<OntologyIndividual>();
		while(it.hasNext()){
			Individual individual=it.next();
			if(individual.getURI()!=null){
				OntologyIndividual _individual = new OntologyIndividualJenaImpl(individual);
				ontologyIndividualList.add(_individual);
			}
		}
		return ontologyIndividualList;
	}
	
	public void addInverseRuleReference(Rule rule) {
		inverseRuleReferences.add(rule);
	}
	
	public Collection<Rule> getInverseRuleReferences(){
		return Collections.unmodifiableCollection(inverseRuleReferences);
	}
	
	public List<String> getDepictions() {
		
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
	
	public List<String> getVideos() {
		
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
