package es.ctic.parrot.reader.rifle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
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
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.ResourceRequiredException;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.RDF;

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
import es.ctic.parrot.transformers.DocumentableObjectVisitor;
import es.ctic.parrot.transformers.TransformerException;
import es.ctic.parrot.utils.URIUtils;

public class RuleSetImpl extends AbstractDocumentableObject implements RuleSet {
	
	private static final String DC_PUBLISHER = "http://purl.org/dc/elements/1.1/publisher";
	private static final String DC_CONTRIBUTOR = "http://purl.org/dc/elements/1.1/contributor";
	private static final String DC_CREATOR = "http://purl.org/dc/elements/1.1/creator";
	private static final String DC_DATE = "http://purl.org/dc/elements/1.1/date";
	private static final String FOAF_DEPICTION = "http://xmlns.com/foaf/0.1/depiction";
	private static final String OG_VIDEO = "http://ogp.me/ns#video";
	private static final String RULESET_DEFAULT_LABEL = "Ruleset-";
	private static final String TYPE_TEXT = "text/plain";
	private static final String TYPE_VIDEO = "video/mpeg";
	private static final String TYPE_IMAGE = "image/png";
	private static final String DC_TERMS_IS_PART_OF = "http://purl.org/dc/terms/isPartOf";
	private static final String DC_DCMITYPE_TEXT = "http://purl.org/dc/dcmitype/Text";
	private static final String RDF_SCHEMA_LABEL = "http://www.w3.org/2000/01/rdf-schema#label";
	private static final String LINGKNOW_VALUE = "http://idi.fundacionctic.org/lingknow/value";
	private static final String LINGKNOW_OCCURS = "http://idi.fundacionctic.org/lingknow/occurs";
	private static final String TELIX_REALIZES = "http://ontorule-project.eu/telix#realizes";
	private static final String SKOS_XL_PREF_LABEL = "http://www.w3.org/2008/05/skos-xl#prefLabel";
	private static final String SKOS_XL_ALT_LABEL = "http://www.w3.org/2008/05/skos-xl#altLabel";
	private static final String SKOS_XL_LITERAL_FORM = "http://www.w3.org/2008/05/skos-xl#literalForm";
	private static final String SKOS_CORE_PREF_LABEL = "http://www.w3.org/2004/02/skos/core#prefLabel";
	private static final String SKOS_CORE_ALT_LABEL = "http://www.w3.org/2004/02/skos/core#altLabel";
	
	
	private net.sourceforge.rifle.ast.Group ruleSet;
	private DocumentableObject parent;
	private OntResource ontResource;

    private Identifier identifier;
	
	private static final Logger logger = Logger.getLogger(RuleSetImpl.class);
	
	public RuleSetImpl(net.sourceforge.rifle.ast.Group group, DocumentableObjectRegister register) {
		this.ruleSet = group;
		this.setRegister(register);
		
		if (ruleSet.getId() == null) {
			// Rule without identifier
		    this.identifier = new RifleAnonymousIdentifier(Integer.toString(ruleSet.getLocalId()));
		} else {
			// Rule with identifier
			this.identifier = new URIIdentifier(ruleSet.getId());
		}
		
		OntModel iriMeta = ModelFactory.createOntologyModel();
    	iriMeta.add(ruleSet.getIriMeta());
    	this.setOntResource(iriMeta.getOntResource(getURI()));
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
	 * @param documentableObject the parentGroup to set
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
    public String getLabel(Locale locale) {
    	
    	// Anonymous ruleset
    	if (getURI() == null){
    		return RULESET_DEFAULT_LABEL + getIdentifier().toString();
    	}
        
        Collection<Label> labels = getLabels(locale);
        
        if (labels.isEmpty()){
        	return URIUtils.getReference(getURI());
        }
        
        /* Preferred order:
         * 
         * http://www.w3.org/2008/05/skos-xl#prefLabel
         * http://www.w3.org/2008/05/skos-xl#altLabel
         * http://www.w3.org/2004/02/skos/core#prefLabel
         * http://www.w3.org/2004/02/skos/core#altLabel
         * http://www.w3.org/2000/01/rdf-schema#label
         * 
         */
        
        for (Label label : labels){
        	if (label.getQualifier().equals(SKOS_XL_PREF_LABEL)) {
        		return label.getText();
        	}
        }

        for (Label label : labels){
        	if (label.getQualifier().equals(SKOS_XL_ALT_LABEL)) {
        		return label.getText();
        	}
        }

        for (Label label : labels){
        	if (label.getQualifier().equals(SKOS_CORE_PREF_LABEL)) {
        		return label.getText();
        	}
        }
        
        for (Label label : labels){
        	if (label.getQualifier().equals(SKOS_CORE_ALT_LABEL)) {
        		return label.getText();
        	}
        }

        for (Label label : labels){
        	if (label.getQualifier().equals(RDF_SCHEMA_LABEL)) {
        		return label.getText();
        	}
        }

        return URIUtils.getReference(getURI());
    }
    
    public String getLabel() {
        return this.getLabel(null);
    }
    
    
	public Collection<RelatedDocument> getVideosRelated() {
		Collection<RelatedDocument> videos = new ArrayList<RelatedDocument>();
    	if (getOntResource() == null){
    		return videos;
    	} else {		

			StmtIterator it = ontResource.listProperties(ResourceFactory.createProperty(OG_VIDEO));
			while(it.hasNext()){
				Statement statement = it.nextStatement();
				try{
					RelatedDocument video = new RelatedDocument();
					video.setUri(statement.getObject().asResource().getURI());
					video.setType(TYPE_VIDEO);
					videos.add(video);
				} catch (ResourceRequiredException e)  {
					logger.warn("Ignore triple "+ statement +" because it is not a Object property");
				}
			}
			return videos;
    	}
	}
	
	public Collection<RelatedDocument> getImagesRelated() {
		Collection<RelatedDocument> images = new ArrayList<RelatedDocument>();
    	if (getOntResource() == null){
    		return images;
    	} else {		

			StmtIterator it = ontResource.listProperties(ResourceFactory.createProperty(FOAF_DEPICTION));
			while(it.hasNext()){
				Statement statement = it.nextStatement();
				try{
					RelatedDocument image = new RelatedDocument();
					image.setUri(statement.getObject().asResource().getURI());
					image.setType(TYPE_IMAGE);
					images.add(image);
				} catch (ResourceRequiredException e)  {
					logger.warn("Ignore triple "+ statement +" because it is not a Object property");
				}
			}
			return images;
    	}
	}
	
	public Collection<Label> getLabels(){
   		return getLabels(null);
   	}
	
	public Collection<Label> getLabels(Locale locale){
		
		Collection<Label> labels = new HashSet<Label>();

		Collection<Label> skosXLPrefLabels = getSkosxlLabels(SKOS_XL_PREF_LABEL, locale);
		if (skosXLPrefLabels.isEmpty() == false){
			labels.addAll(skosXLPrefLabels);
		}

		Collection<Label> skosXLAltLabels = getSkosxlLabels(SKOS_XL_ALT_LABEL, locale);
		if (skosXLAltLabels.isEmpty() == false){
			labels.addAll(skosXLAltLabels);
		}

		Collection<Label> skosPrefLabels = getLiteralLabels(SKOS_CORE_PREF_LABEL, locale);
		if (skosPrefLabels.isEmpty() == false){
			labels.addAll(skosPrefLabels);
		}

		
		Collection<Label> skosAltLabels = getLiteralLabels(SKOS_CORE_ALT_LABEL, locale);
		if (skosAltLabels.isEmpty() == false){
			labels.addAll(skosAltLabels);
		}

		Collection<Label> rdfsLabels = getLiteralLabels(RDF_SCHEMA_LABEL, locale);
		if (rdfsLabels.isEmpty() == false){
			labels.addAll(rdfsLabels);
		}
		
		return labels;
	}	

	
	/**
	 * @param the uri of the property used to annotate
	 * @return a collection of literal labels for the uri
	 */
	public Collection<Label> getLiteralLabels(String uri, Locale locale) {
		
		Collection<Label> literalLabels = new HashSet<Label>();

		OntModel ontModel = getOntResource().getOntModel();
		StmtIterator listStatements = ontModel.listStatements(getOntResource(), ResourceFactory.createProperty(uri), (RDFNode) null);
		
		while (listStatements.hasNext()){
			Statement statement = listStatements.next();
			Label literalLabel = new Label();
			literalLabel.setQualifier(uri);
			//It cannot applied skosLabel.setUri()
			literalLabel.setText(statement.getObject().asLiteral().getString());
			String LanguageTag = statement.getObject().asLiteral().getLanguage(); 
			if (LanguageTag.equals("") == false){
				String language = LanguageTag.split("-")[0]; 
				literalLabel.setLocale(new Locale(language)); // FIXME do it more specified using Locale(String language, String country, String variant)
			}
			
			if (locale != null) {
				//compare locales
				if (locale.equals(literalLabel.getLocale())) {
					logger.debug(literalLabel + " is " + uri + " for resource " + getOntResource());
					literalLabels.add(literalLabel);
				} else{
					logger.debug("Not add label  " + literalLabel + " for resource " + getOntResource() + " because its locale " + literalLabel.getLocale() + " does not match with required locale " + locale);
				}
			} else {
				logger.debug(literalLabel + " is " + uri + " for resource " + getOntResource());
				literalLabels.add(literalLabel);
			}

		}
		
		return literalLabels;
	}

	
	/**
	 * @return a collection of labels for the skosXL uri
	 */
	public Collection<Label> getSkosxlLabels(String uri, Locale locale) {

		Collection<Label> skosxlLabels = new HashSet<Label>();
		OntModel ontModel = getOntResource().getOntModel();
		
		StmtIterator listStatements = ontModel.listStatements(getOntResource(), ResourceFactory.createProperty(uri), (RDFNode) null);
		
		while (listStatements.hasNext()){
			Statement statement = listStatements.next();
			Label skosxlLabel = new Label();
			skosxlLabel.setQualifier(uri);
			skosxlLabel.setUri(ontModel.getOntResource(statement.getObject().asResource()).getURI());
			StmtIterator it = ontModel.listStatements(statement.getObject().asResource(), ResourceFactory.createProperty(SKOS_XL_LITERAL_FORM), (RDFNode) null);
			while (it.hasNext()){
				Statement st = it.next();
				skosxlLabel.setText(st.getObject().asLiteral().getString());
				String LanguageTag = st.getObject().asLiteral().getLanguage(); 
				if (LanguageTag.equals("") == false){
					String language = LanguageTag.split("-")[0];
					skosxlLabel.setLocale(new Locale(language));
				}

				if (locale != null) {
					//compare locales
					if (locale.equals(skosxlLabel.getLocale())) {
						logger.debug(skosxlLabel + " is " + uri + " for resource " + getOntResource());
						skosxlLabels.add(skosxlLabel);
					} else{
						logger.debug("Not add label  " + skosxlLabel + " for resource " + getOntResource() + " because its locale " + skosxlLabel.getLocale() + " does not match with required locale " + locale);
					}
				} else {
					logger.debug(skosxlLabel + " is " + uri + " for resource " + getOntResource());
					skosxlLabels.add(skosxlLabel);
				}
			}
		}
		
		return skosxlLabels;
	}
	

	/**
	 * 
	 * @param locale
	 * @return a collection of related documents for this documentable object
	 */
	public Collection<RelatedDocument> getRelatedDocuments(Locale locale) {

		OntModel ontModel = getOntResource().getOntModel();
		Collection<RelatedDocument> relatedDocuments = new HashSet<RelatedDocument>();
		
		// Only labels that are resource labels 
		Collection<Label> labels = new HashSet<Label>();
		for(Label label: getLabels(locale)){
			if (label.getUri() != null){
				labels.add(label);
			}
		}

		for(Label label: labels){
			
			Collection<OntResource> labelOccurrences = new HashSet<OntResource>();
			Collection<OntResource> sentences = new HashSet<OntResource>();
			
			StmtIterator listStatements = ontModel.listStatements((Resource) null, ResourceFactory.createProperty(TELIX_REALIZES), ResourceFactory.createResource(label.getUri()));
			
			while (listStatements.hasNext()){
				Statement statement = listStatements.next();
				labelOccurrences.add(ontModel.getOntResource(statement.getSubject()));
			}
			
			if (labelOccurrences.isEmpty()){
				return relatedDocuments;
			}
			
			for (OntResource labelOcurrence :labelOccurrences){
				listStatements = ontModel.listStatements(labelOcurrence, ResourceFactory.createProperty(LINGKNOW_OCCURS), (RDFNode) null );
				while (listStatements.hasNext()){
					Statement statement = listStatements.next();
					sentences.add(ontModel.getOntResource(statement.getObject().asResource()));
				}
			}
	
			if (sentences.isEmpty()){
				return relatedDocuments;
			}
			
			for (OntResource sentence :sentences){
				listStatements = ontModel.listStatements(sentence, ResourceFactory.createProperty(LINGKNOW_VALUE), (RDFNode) null );
				while (listStatements.hasNext()){
					Statement statement = listStatements.next();
					RelatedDocument relatedDocument = new RelatedDocument();
					relatedDocument.setUri(getSourceDocumentUri(ontModel, sentence.getURI()));
					relatedDocument.setSourceText(statement.getLiteral().getLexicalForm());
					relatedDocument.setType(TYPE_TEXT); // FIXME now it's fixed to plain/text
					relatedDocuments.add(relatedDocument);
				}
			}
		}
		
		//add videos
		relatedDocuments.addAll(getVideosRelated());
		
		//add images
		relatedDocuments.addAll(getImagesRelated());
		return relatedDocuments;
	}

	/**
	 * 
	 * @param model The ontological model
	 * @param uri the uri to search for
	 * @return the uri of the source document
	 */
	public String getSourceDocumentUri(OntModel model, String uri){
		if (model.contains(ResourceFactory.createResource(uri), RDF.type, ResourceFactory.createResource(DC_DCMITYPE_TEXT))){
			return uri;
		} else {
			StmtIterator listStatements = model.listStatements(ResourceFactory.createResource(uri), ResourceFactory.createProperty(DC_TERMS_IS_PART_OF), (RDFNode) null );
			if (listStatements.hasNext()){ // only one iteration
				Statement statement = listStatements.next();
				return getSourceDocumentUri(model, statement.getObject().asResource().getURI());
			} else {
				return null;
			}

		}
	}

}

