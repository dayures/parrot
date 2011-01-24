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
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.ResourceRequiredException;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
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
import es.ctic.parrot.utils.URIUtils;

public abstract class AbstractJenaDocumentableObject extends
		AbstractDocumentableObject {
	
	private static final String DC_TERMS_IS_PART_OF = "http://purl.org/dc/terms/isPartOf";
	private static final String DC_DCMITYPE_TEXT = "http://purl.org/dc/dcmitype/Text";
	private static final String RDF_SCHEMA_LABEL = "http://www.w3.org/2000/01/rdf-schema#label";
	private static final String FOAF_DEPICTION = "http://xmlns.com/foaf/0.1/depiction";
	private static final String OG_VIDEO = "http://ogp.me/ns#video";
	private static final String LINGKNOW_VALUE = "http://idi.fundacionctic.org/lingknow/value";
	private static final String LINGKNOW_OCCURS = "http://idi.fundacionctic.org/lingknow/occurs";
	private static final String TELIX_REALIZES = "http://ontorule-project.eu/telix#realizes";
	private static final String SKOS_XL_PREF_LABEL = "http://www.w3.org/2008/05/skos-xl#prefLabel";
	private static final String SKOS_XL_ALT_LABEL = "http://www.w3.org/2008/05/skos-xl#altLabel";
	private static final String SKOS_XL_LITERAL_FORM = "http://www.w3.org/2008/05/skos-xl#literalForm";
	private static final String SKOS_CORE_PREF_LABEL = "http://www.w3.org/2004/02/skos/core#prefLabel";
	private static final String SKOS_CORE_ALT_LABEL = "http://www.w3.org/2004/02/skos/core#altLabel";
	
	private static final Logger logger = Logger.getLogger(AbstractJenaDocumentableObject.class);
	private static final String TYPE_TEXT = "text/plain";
	
	private OntResource ontResource;
	private Collection<Rule> inverseRuleReferences = new HashSet<Rule>();

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

			if (_resource != null) { // do not add null elements in the list 
				logger.debug("Added (not null) " + _resource);
				documentableObjectList.add(_resource);
			} else {
				logger.debug("Not found in register: " + identifier);
				logger.debug("resource.getURI() "+ resource.getURI());
				if (isDomainSpecific(resource.getURI())) {
					_resource = (TR) new UndefinedOntologyDocumentableObject(resource.getURI());
					documentableObjectList.add(_resource);
				} else {
					logger.debug("Not added " + identifier + " (not domain specific)");
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
					videos.add(statement.getObject().asResource().getURI());
				} catch (ResourceRequiredException e)  {
					logger.warn("Ignore triple "+ statement +" because it is not a Object property");
				}
			}
			return videos;
    	}
	}
	
	/**
	 * 
	 * @param uri The URI to check
	 * @return true if the URI is domain specific, false if not
	 */
	public static boolean isDomainSpecific(String uri) {
       	return !uri.startsWith(RDFS.getURI()) && !uri.startsWith(RDF.getURI()) && !uri.startsWith(OWL.getURI());
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
					relatedDocument.setUri(getSourceDocumentUri(ontModel, sentence.getURI())); // FIXME
					relatedDocument.setSourceText(statement.getLiteral().getLexicalForm());
					relatedDocument.setType(TYPE_TEXT); // FIXME now it's fixed to plain/text
					relatedDocuments.add(relatedDocument);
				}
			}
		}
		
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
