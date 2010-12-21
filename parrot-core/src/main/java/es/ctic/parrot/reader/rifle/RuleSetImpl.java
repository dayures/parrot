package es.ctic.parrot.reader.rifle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.ResourceRequiredException;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

import es.ctic.parrot.de.AbstractDocumentableObject;
import es.ctic.parrot.de.AnonymousIdentifier;
import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.de.DocumentableOntologicalObject;
import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.Rule;
import es.ctic.parrot.de.RuleSet;
import es.ctic.parrot.de.URIIdentifier;
import es.ctic.parrot.transformers.DocumentableObjectVisitor;
import es.ctic.parrot.utils.URIUtils;

public class RuleSetImpl extends AbstractDocumentableObject implements RuleSet {
	
	private static final String DC_PUBLISHER = "http://purl.org/dc/terms/publisher";
	private static final String DC_CONTRIBUTOR = "http://purl.org/dc/terms/contributor";
	private static final String DC_CREATOR = "http://purl.org/dc/terms/creator";
	private static final String DC_DATE = "http://purl.org/dc/terms/date";
	private static final String FOAF_DEPICTION = "http://xmlns.com/foaf/0.1/depiction";
	private static final String OG_VIDEO = "http://ogp.me/ns#video";
	
	private net.sourceforge.rifle.ast.Group ruleSet;
	private OntResource ontResource;

    private Identifier identifier;
	
	private DocumentableObjectRegister register;

	private static final Logger logger = Logger.getLogger(RuleSetImpl.class);
	
	public RuleSetImpl(net.sourceforge.rifle.ast.Group group, DocumentableObjectRegister register) {
		this.ruleSet = group;
		this.register = register;
		if (ruleSet.getId() == null) {
		    this.identifier = new AnonymousIdentifier();
		} else {
		    this.identifier = new URIIdentifier(ruleSet.getId());
		}
		
		OntModel iriMeta = ModelFactory.createOntologyModel();
    	iriMeta.add(ruleSet.getIriMeta());
    	this.setOntResource(iriMeta.getOntResource(getURI()));
	}

	public Object accept(DocumentableObjectVisitor visitor) {
		return visitor.visit(this);
	}

	public Identifier getIdentifier() {
		return identifier;
	}
	
	public String getURI() {
		return getIdentifier().toString();
	}
	
    public String getLabel(Locale locale) {

    	if (getOntResource() == null){
    		return URIUtils.getReference(getURI());
    	}
    	else{
        	String label = null;
        	
    		if (locale !=null)
            	label = getOntResource().getLabel(locale.toString());
            
            if (label == null) {
                label = getOntResource().getLabel(null);
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
    	
    	if (getOntResource() == null){
    		return null;
    	}
    	else{
        	String comment = null;
        	
    		if (locale !=null)
    			comment = getOntResource().getComment(locale.toString());
            
            if (comment == null) {
            	comment = getOntResource().getComment(null);
            } 

            return comment;
    	}

    }
    

	public Collection<String> getDeclaredVars() {
		return new LinkedList<String>();//FIXME create proper list	
	}
	
	public int compareTo(DocumentableOntologicalObject o) {
		return getURI().compareTo(o.getURI());
	}

	public String getDate() {
    	if (getOntResource() == null){
    		return null;
    	}
    	else{
    		
            String date = null;
			RDFNode propertyValue = getOntResource().getPropertyValue(ResourceFactory.createProperty(DC_DATE));
	
			
			if (propertyValue != null && propertyValue.isLiteral()){
				date = propertyValue.asLiteral().getString();
			}
	    	
	        return date;
    	}
	}

	public List<String> getCreators() {
    	if (getOntResource() == null){
    		return new ArrayList<String>();
    	}
    	else {
			ArrayList<String> creators = new ArrayList<String>();
			StmtIterator it = getOntResource().listProperties(ResourceFactory.createProperty(DC_CREATOR));
			while(it.hasNext()){
				creators.add(it.nextStatement().getLiteral().getString());
			}
			return creators;
    	}
	}

	public List<String> getContributors() {
    	if (getOntResource() == null){
    		return new ArrayList<String>();
    	}
    	else {
			ArrayList<String> contributors = new ArrayList<String>();
			StmtIterator it = getOntResource().listProperties(ResourceFactory.createProperty(DC_CONTRIBUTOR));
			while(it.hasNext()){
				contributors.add(it.nextStatement().getLiteral().getString());
			}
			return contributors;
    	}
	}

	public List<String> getPublishers() {
    	if (getOntResource() == null){
    		return new ArrayList<String>();
    	}
    	else {
			ArrayList<String> publishers = new ArrayList<String>();
			StmtIterator it = getOntResource().listProperties(ResourceFactory.createProperty(DC_PUBLISHER));
			while(it.hasNext()){
				publishers.add(it.nextStatement().getLiteral().getString());
			}
			return publishers;
    	}
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
	
	public List<String> getDepictions() {
    	if (getOntResource() == null){
    		return new ArrayList<String>();
    	} else {
			ArrayList<String> depictions = new ArrayList<String>();
			StmtIterator it = ontResource.listProperties(ResourceFactory.createProperty(FOAF_DEPICTION));
			while(it.hasNext()){
				Statement statement = it.nextStatement();
				try{
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
					videos.add(statement.getResource().getURI());
				} catch (ResourceRequiredException e)  {
					logger.warn("Ignore triple "+ statement +" because it is not a Object property");
				}
			}
			return videos;
    	}
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
			Rule rule = (Rule) register.findDocumentableObject(new URIIdentifier(astRule.getId()));
			if (rule != null){
				ruleList.add(rule);
			}
		}
		logger.debug(ruleList);
		return ruleList;
	}

	public String getStrategy() {
		return ruleSet.getStrategy();

	}

	public Integer getPriority() {
		return ruleSet.getPriority();
	}
	

}
