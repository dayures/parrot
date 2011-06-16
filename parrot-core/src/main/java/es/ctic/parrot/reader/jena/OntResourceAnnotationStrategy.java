package es.ctic.parrot.reader.jena;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.ResourceRequiredException;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.OWL2;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;

import es.ctic.parrot.de.Agent;
import es.ctic.parrot.de.Label;
import es.ctic.parrot.de.RelatedDocument;
import es.ctic.parrot.de.RelatedDocument.Type;
import es.ctic.parrot.de.Triple;
import es.ctic.parrot.utils.URIUtils;

/**
 * The class <code>OntResourceAnnotationStrategy</code> includes methods for extracting information from ontResources. 
 * It is used to implement the Strategy pattern.
 * Please refer to the Gang of Four book of Design Patterns for more details on the Strategy pattern.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 * 
 */
public class OntResourceAnnotationStrategy {

	private static final Logger logger = Logger.getLogger(OntResourceAnnotationStrategy.class);

	private static final String TRUE = "true";

	private static final String RDF_SCHEMA_IS_DEFINED_BY = "http://www.w3.org/2000/01/rdf-schema#isDefinedBy";
	private static final String CC_LICENSE_DEPRECATED = "http://web.resource.org/cc/license";
	private static final String CC_LICENSE = "http://creativecommons.org/ns#license";
	private static final String RCLN_RULE = "http://lipn.univ-paris13.fr/RCLN/schema#Rule";
	private static final String RCLN_RULE_TEXT = "http://lipn.univ-paris13.fr/RCLN/schema#ruleText";


	private static final String RDF_SCHEMA_LABEL = "http://www.w3.org/2000/01/rdf-schema#label";
	private static final String RDFS_COMMENT = "http://www.w3.org/2000/01/rdf-schema#comment";
	private static final String SKOS_XL_PREF_LABEL = "http://www.w3.org/2008/05/skos-xl#prefLabel";
	private static final String SKOS_XL_ALT_LABEL = "http://www.w3.org/2008/05/skos-xl#altLabel";
	private static final String SKOS_CORE_PREF_LABEL = "http://www.w3.org/2004/02/skos/core#prefLabel";
	private static final String SKOS_CORE_ALT_LABEL = "http://www.w3.org/2004/02/skos/core#altLabel";
	private static final String OG_VIDEO = "http://ogp.me/ns#video";
	private static final String SKOS_XL_LITERAL_FORM = "http://www.w3.org/2008/05/skos-xl#literalForm";
	private static final String LINGKNOW_VALUE = "http://idi.fundacionctic.org/lingknow/value";
	private static final String LINGKNOW_OCCURS = "http://idi.fundacionctic.org/lingknow/occurs";
	private static final String TELIX_REALIZES = "http://ontorule-project.eu/telix#realizes";

	private static final String DC_TITLE = "http://purl.org/dc/elements/1.1/title";
	private static final String DC_PUBLISHER = "http://purl.org/dc/elements/1.1/publisher";
	private static final String DC_CONTRIBUTOR = "http://purl.org/dc/elements/1.1/contributor";
	private static final String DC_CREATOR = "http://purl.org/dc/elements/1.1/creator";
	private static final String DC_DATE = "http://purl.org/dc/elements/1.1/date";
	private static final String DC_DESCRIPTION = "http://purl.org/dc/elements/1.1/description";
	private static final String DC_RIGHTS = "http://purl.org/dc/elements/1.1/rights";
	
	private static final String DCT_DESCRIPTION = "http://purl.org/dc/terms/description";
	private static final String DCT_SOURCE = "http://purl.org/dc/terms/source";
	private static final String DCT_LICENSE = "http://purl.org/dc/terms/license";
	private static final String DCT_DATE = "http://purl.org/dc/terms/date";
	private static final String DCT_TITLE = "http://purl.org/dc/terms/title";
	private static final String DCT_CREATOR = "http://purl.org/dc/terms/creator";
	private static final String DCT_CONTRIBUTOR = "http://purl.org/dc/terms/contributor";
	private static final String DCT_PUBLISHER = "http://purl.org/dc/terms/publisher";
	private static final String DCT_IS_PART_OF = "http://purl.org/dc/terms/isPartOf";

	private static final String FOAF_DEPICTION = "http://xmlns.com/foaf/0.1/depiction";
	private static final String FOAF_NAME = "http://xmlns.com/foaf/0.1/name";
	private static final String FOAF_HOMEPAGE = "http://xmlns.com/foaf/0.1/homepage";

	private static final String DC_DCMITYPE_TEXT = "http://purl.org/dc/dcmitype/Text";
	
	private static final String VANN_PREFERRED_PREFIX = "http://purl.org/vocab/vann/preferredNamespacePrefix";
	private static final String VANN_PREFERRED_NAMESPACE = "http://purl.org/vocab/vann/preferredNamespaceUri";

	private static final String DCTERMS_MODIFIED = "http://purl.org/dc/terms/modified";

	private static final String VOAF_CLASSNUMBER = "http://labs.mondeca.com/vocab/voaf#classNumber";
	private static final String VOAF_PROPERTYNUMBER = "http://labs.mondeca.com/vocab/voaf#propertyNumber";

	private static final String VOID_DATADUMP = "http://rdfs.org/ns/void#dataDump";
	private static final String VOID_SPARQLENDPOINT = "http://rdfs.org/ns/void#sparqlEndpoint";

	
	/**
	 * Constructs a register (Suppress default constructor for noninstantiability).
	 */
	public OntResourceAnnotationStrategy() {
		logger.debug("Created a OntResourceAnnotationStrategy");
	}
	
	/**
	 * Returns a collection of labels for this ontResource, using the locale element if it is provided.
	 * 
 	 * The preferred order for a label is:
	 * <ol>
	 * 	<li>http://www.w3.org/2008/05/skos-xl#prefLabel</li>
	 *  <li>http://www.w3.org/2004/02/skos/core#prefLabel</li>
	 * 	<li>http://www.w3.org/2008/05/skos-xl#altLabel</li>
	 *  <li>http://www.w3.org/2004/02/skos/core#altLabel</li>
	 *  <li>http://purl.org/dc/terms/title</li>
	 *  <li>http://purl.org/dc/elements/1.1/title</li>
	 *  <li>http://www.w3.org/2000/01/rdf-schema#label</li>
	 * </ol>
	 * 
	 * @param locale the locale.
	 * @param ontResource the ontResource.
	 * @return a collection of labels for this ontResource.
	 */
	public Collection<Label> getLabels(OntResource ontResource, Locale locale){
		
		Collection<Label> labels = new HashSet<Label>();

		Collection<Label> skosXLPrefLabels = getSkosxlLabels(ontResource, SKOS_XL_PREF_LABEL, locale);
		if (skosXLPrefLabels.isEmpty() == false){
			labels.addAll(skosXLPrefLabels);
		}

		Collection<Label> skosPrefLabels = getLiteralLabels(ontResource, SKOS_CORE_PREF_LABEL, locale);
        if (skosPrefLabels.isEmpty() == false ){
            labels.addAll(skosPrefLabels);
        }

		Collection<Label> skosXLAltLabels = getSkosxlLabels(ontResource, SKOS_XL_ALT_LABEL, locale);
		if (skosXLAltLabels.isEmpty() == false){
			labels.addAll(skosXLAltLabels);
		}
		
		Collection<Label> skosAltLabels = getLiteralLabels(ontResource, SKOS_CORE_ALT_LABEL, locale);
		if (skosAltLabels.isEmpty() == false && labels.isEmpty()) { // hidden by the previous labels
			labels.addAll(skosAltLabels);
		}

		Collection<Label> dctermsTitleLabels = getLiteralLabels(ontResource, DCT_TITLE, locale);
        if (dctermsTitleLabels.isEmpty() == false && labels.isEmpty()) { // hidden by the previous labels
            labels.addAll(dctermsTitleLabels);
        }
        
		Collection<Label> dcTitleLabels = getLiteralLabels(ontResource, DC_TITLE, locale);
        if (dcTitleLabels.isEmpty() == false && labels.isEmpty()) { // hidden by the previous labels
            labels.addAll(dcTitleLabels);
        }

        Collection<Label> rdfsLabels = getLiteralLabels(ontResource, RDF_SCHEMA_LABEL, locale);
        if (rdfsLabels.isEmpty() == false && labels.isEmpty()) { // hidden by the previous labels
            labels.addAll(rdfsLabels);
        }
		
		return labels;
	}


	/**
	 * Returns the description of a resource for a locale.
	 * The preferred order for a description is:
	 * <ol>
	 * 	<li>http://purl.org/dc/terms/description</li>
	 * 	<li>http://purl.org/dc/elements/1.1/description</li>
	 *  <li>http://www.w3.org/2000/01/rdf-schema#comment</li>
	 * </ol>
	 *  
	 * @param ontResource the ontology resource.
	 * @param locale the locale.
	 * @return the description or <code>null</code> if there is the resource has no description for that locale.
	 */
	public String getDescription(OntResource ontResource, Locale locale) {

    	if (ontResource == null){
    		return null;
    	}

		String description = getLiteralPropertyValue(ontResource, DCT_DESCRIPTION, locale);
	
		if (description != null){
    		return description;
		}

		description = getLiteralPropertyValue(ontResource, DCT_DESCRIPTION); //not language selected
		
		if (description != null){
    		return description;
		}
		
		description = getLiteralPropertyValue(ontResource, DC_DESCRIPTION, locale);
		if (description != null){
    		return description;
		}

		description = getLiteralPropertyValue(ontResource, DC_DESCRIPTION); //not language selected
		if (description != null){
    		return description;
		}
		
		description = getLiteralPropertyValue(ontResource, RDFS_COMMENT, locale);
		if (description != null){
    		return description;
		}

		return getLiteralPropertyValue(ontResource, RDFS_COMMENT); //not language selected

	}
	
   
	/**
	 * Returns the best label associated, using the locale element if it is provided (or <code>null</code> for an anonymous resource).
	 * 
 	 * The preferred order for a label is:
	 * <ol>
	 * 	<li>http://www.w3.org/2008/05/skos-xl#prefLabel</li>
	 *  <li>http://www.w3.org/2004/02/skos/core#prefLabel</li>
	 *  <li>http://purl.org/dc/terms/title</li>
	 *  <li>http://purl.org/dc/elements/1.1/title</li>
	 *  <li>http://www.w3.org/2000/01/rdf-schema#label</li>
	 *  <li>automatically generated</li>
	 * </ol>
	 * 
	 * @param ontResource the ontology resource.
	 * @param locale the locale.
	 * @return the best label associated (using the locale element if it is provided) or <code>null</code> if the resource is an anonymous resource.
	 */
    public String getLabel(OntResource ontResource, Locale locale) {

    	Collection<Label> labels = getLabels(ontResource, locale);
        
        for (Label label : labels){
        	if (label.getQualifier().equals(SKOS_XL_PREF_LABEL)) {
        		return label.getText();
        	}
        }

        for (Label label : labels){
        	if (label.getQualifier().equals(SKOS_CORE_PREF_LABEL)) {
        		return label.getText();
        	}
        }

        for (Label label : labels){
        	if (label.getQualifier().equals(DCT_TITLE)) {
        		return label.getText();
        	}
        }
        
        for (Label label : labels){
        	if (label.getQualifier().equals(DC_TITLE)) {
        		return label.getText();
        	}
        }
        
        for (Label label : labels){
            if (label.getQualifier().equals(RDF_SCHEMA_LABEL)) {
                return label.getText();
            }
        }
        
        if (locale != null) { // if I hava asked for a locale
        	// give a change without locale
        	return getLabel(ontResource, null);
        } else {
        	// no more chances. do your best.
        	String reference = URIUtils.getReference(ontResource.getURI());
    		if (reference != null) {
    			return replaceUnderscore(splitCamelCase(reference));
    		} else {
    			return ontResource.getURI();
    		}
        }
    }
    
	/**
	 * Returns a collection of literal labels for the URI.
	 * @param ontResource the ontResource.
	 * @param uri the URI of the property used to annotate.
	 * @param locale the locale.
	 * @return a collection of literal labels for the URI.
	 */
	private Collection<Label> getLiteralLabels(OntResource ontResource, String uri, Locale locale) {
		
		Collection<Label> literalLabels = new HashSet<Label>();
		OntModel ontModel = null;
		
		if (ontResource == null){
			return literalLabels;
		} else {
			ontModel = ontResource.getOntModel();
		}

		StmtIterator listStatements = ontModel.listStatements(ontResource, ResourceFactory.createProperty(uri), (RDFNode) null);
		
		while (listStatements.hasNext()){
			Statement statement = listStatements.next();
			Label literalLabel = new Label();
			literalLabel.setQualifier(uri);
			//It cannot applied skosLabel.setUri()
			literalLabel.setText(statement.getObject().asLiteral().getLexicalForm());
			String LanguageTag = statement.getObject().asLiteral().getLanguage(); 
			if (LanguageTag.length() != 0){ //The literal has language tag
				String language = LanguageTag.split("-")[0]; 
				literalLabel.setLocale(new Locale(language)); // FIXME do it more specified using Locale(String language, String country, String variant)
			}
			
			// if there is not locale restriction
			if (locale == null){
				if (literalLabel.getLocale() == null){  //and there is not language tag for the literal
					//logger.debug(literalLabel + " is " + uri + " for resource " + ontResource);
					literalLabels.add(literalLabel);
				} else {
					//logger.debug("Not add label  " + literalLabel + " for resource " + getOntResource() + " because it has language tag=" + literalLabel.getLocale());
					literalLabels.add(literalLabel);
				}
			} else {
				//compare locales
				if (locale.equals(literalLabel.getLocale())) {
					//logger.debug(literalLabel + " is " + uri + " for resource " + getOntResource());
					literalLabels.add(literalLabel);
				} else{
					//logger.debug("Not add label  " + literalLabel + " for resource " + getOntResource() + " because its locale " + literalLabel.getLocale() + " does not match with required locale " + locale);
				}
			}
		}
		
		return literalLabels;
	}

	/**
	 * Returns a collection of labels for the given SKOS-XL URI.
	 * @param ontResource the ontResource.
	 * @param uri the URI of the property used to annotate (SKOS-XL).
	 * @param locale the locale.
	 * @return a collection of labels for the given SKOS-XL URI.
	 */
	private Collection<Label> getSkosxlLabels(OntResource ontResource, String uri, Locale locale) {

		Collection<Label> skosxlLabels = new HashSet<Label>();
		OntModel ontModel = null;
		
		if (ontResource == null){
			return skosxlLabels;
		} else {
			ontModel = ontResource.getOntModel();
		}
		
		StmtIterator listStatements = ontModel.listStatements(ontResource, ResourceFactory.createProperty(uri), (RDFNode) null);
		
		while (listStatements.hasNext()){
			Statement statement = listStatements.next();
			Label skosxlLabel = new Label();
			skosxlLabel.setQualifier(uri);
			skosxlLabel.setUri(ontModel.getOntResource(statement.getObject().asResource()).getURI());
			StmtIterator it = ontModel.listStatements(statement.getObject().asResource(), ResourceFactory.createProperty(SKOS_XL_LITERAL_FORM), (RDFNode) null);
			while (it.hasNext()){
				Statement st = it.next();
				skosxlLabel.setText(st.getObject().asLiteral().getLexicalForm());
				String LanguageTag = st.getObject().asLiteral().getLanguage(); 
				if (LanguageTag.equals("") == false){
					String language = LanguageTag.split("-")[0];
					skosxlLabel.setLocale(new Locale(language));
				}

				if (locale != null) {
					//compare locales
					if (locale.equals(skosxlLabel.getLocale())) {
						//logger.debug(skosxlLabel + " is " + uri + " for resource " + getOntResource());
						skosxlLabels.add(skosxlLabel);
					} else{
						//logger.debug("Not add label  " + skosxlLabel + " for resource " + getOntResource() + " because its locale " + skosxlLabel.getLocale() + " does not match with required locale " + locale);
					}
				} else {
					//logger.debug(skosxlLabel + " is " + uri + " for resource " + getOntResource());
					skosxlLabels.add(skosxlLabel);
				}
			}
		}
		
		return skosxlLabels;
	}
	
	/**
	 * Returns a collection of videos related to the ontResource.
	 * @param ontResource the ontResource.
	 * @return a collection of videos related to the ontResource.
	 */
	private Collection<RelatedDocument> getVideosRelated(OntResource ontResource) {
		Collection<RelatedDocument> videos = new ArrayList<RelatedDocument>();
    	if (ontResource == null){
    		return videos;
    	} else {		

			StmtIterator it = ontResource.listProperties(ResourceFactory.createProperty(OG_VIDEO));
			while(it.hasNext()){
				Statement statement = it.nextStatement();
				try{
					RelatedDocument video = new RelatedDocument();
					video.setUri(statement.getObject().asResource().getURI());
					video.setType(Type.VIDEO);
					videos.add(video);
				} catch (ResourceRequiredException e)  {
					logger.warn("Ignore triple "+ statement +" because it is not a Object property");
				}
			}
			return videos;
    	}
	}
	
	/**
	 * Returns a collection of URIs related to the ontResource.
	 * @param ontResource the ontResource.
	 * @return a collection of URIs related to the ontResource.
	 */
	private Collection<RelatedDocument> getUrisRelated(OntResource ontResource) {
		Collection<RelatedDocument> videos = new ArrayList<RelatedDocument>();
    	if (ontResource == null){
    		return videos;
    	} else {		

			StmtIterator it = ontResource.listProperties(RDFS.seeAlso);
			while(it.hasNext()){
				Statement statement = it.nextStatement();
				try{
					RelatedDocument uriLink = new RelatedDocument();
					uriLink.setUri(statement.getObject().asResource().getURI());
					uriLink.setType(Type.URI);
					videos.add(uriLink);
				} catch (ResourceRequiredException e)  {
					logger.warn("Ignore triple "+ statement +" because it is not a Object property");
				}
			}
			return videos;
    	}
	}
	
	/**
	 * Returns a collection of images related to the ontResource.
	 * @param ontResource the ontResource.
	 * @return a collection of images related to the ontResource.
	 */
	private Collection<RelatedDocument> getImagesRelated(OntResource ontResource) {
		Collection<RelatedDocument> images = new ArrayList<RelatedDocument>();
    	if (ontResource == null){
    		return images;
    	} else {		

			StmtIterator it = ontResource.listProperties(ResourceFactory.createProperty(FOAF_DEPICTION));
			while(it.hasNext()){
				Statement statement = it.nextStatement();
				try{
					RelatedDocument image = new RelatedDocument();
					image.setUri(statement.getObject().asResource().getURI());
					image.setType(Type.IMAGE);
					images.add(image);
				} catch (ResourceRequiredException e)  {
					logger.warn("Ignore triple "+ statement +" because it is not a Object property");
				}
			}
			return images;
    	}
	}
	
	/**
	 * Returns a collection of candidate rules related to the ontResource.
	 * @param ontResource the ontResource.
	 * @return a collection of candidate rules related to the ontResource.
	 */	
	private Collection<RelatedDocument> getCandidateRulesRelated(OntResource ontResource) {
		
		Collection<RelatedDocument> candidateRules = new ArrayList<RelatedDocument>();
    	if (ontResource == null){
    		return candidateRules;
    	} else {		

			StmtIterator it = ontResource.listProperties(ResourceFactory.createProperty(DCT_SOURCE));
			while(it.hasNext()){
				Statement statement = it.nextStatement();
				try{
					RelatedDocument candidateRule = new RelatedDocument();

					if (statement.getObject().isLiteral()){
						candidateRule.setType(Type.TEXT);
						candidateRule.setSourceText(statement.getObject().asLiteral().getLexicalForm());
						logger.debug("statement.getObject().asLiteral().getLexicalForm() " + statement.getObject().asLiteral().getLexicalForm());
					} else {

						candidateRule.setUri(statement.getObject().asResource().getURI());

						if (ontResource.getModel().contains(statement.getObject().asResource(), RDF.type, ResourceFactory.createResource(RCLN_RULE))){
						
							StmtIterator listRuleTexts = statement.getObject().asResource().listProperties(ResourceFactory.createProperty(RCLN_RULE_TEXT));
	
							candidateRule.setType(Type.HTML);
							
							if (listRuleTexts.hasNext()){
								String ruleText = listRuleTexts.nextStatement().getObject().asLiteral().getLexicalForm();
		
								// change <a> tag to <span>
								ruleText = ruleText.replaceAll("<a", "<span");
								// change </a> tag to </span>
								ruleText = ruleText.replaceAll("</a>", "</span>");
								
								// remove href="javascript:void();"
								ruleText = ruleText.replaceAll("href=\"javascript:void\\(\\);\"", "");
								// remove onclick="function1('MicroSlipTest');"
								ruleText = ruleText.replaceAll("onclick=\"function1\\(.+?\\);\"","");
								candidateRule.setSourceText(ruleText);
							}
							
						} else {
							candidateRule.setType(Type.URI);
							candidateRule.setSourceText(candidateRule.getUri());
						}
					}
					candidateRules.add(candidateRule);
				} catch (ResourceRequiredException e)  {
					logger.warn("Ignore triple "+ statement +" because it is not a Object property");
				}
			}
			return candidateRules;
    	}
	}

	/**
	 * Returns the URI of the source document for the given URI.
	 * @param model The ontological model.
	 * @param uri the URI to search for.
	 * @return the URI of the source document.
	 */
	private String getSourceDocumentUri(OntModel model, String uri){
		if (model.contains(ResourceFactory.createResource(uri), RDF.type, ResourceFactory.createResource(DC_DCMITYPE_TEXT))){
			return uri;
		} else {
			StmtIterator listStatements = model.listStatements(ResourceFactory.createResource(uri), ResourceFactory.createProperty(DCT_IS_PART_OF), (RDFNode) null );
			if (listStatements.hasNext()){ // only one iteration
				Statement statement = listStatements.next();
				return getSourceDocumentUri(model, statement.getObject().asResource().getURI());
			} else {
				return null;
			}

		}
		
	}	

	/**
	 * Returns a collection of documents related to the ontResource.
	 * @param ontResource the ontResource.
	 * @param locale the locale.
	 * @return a collection of documents related to the ontResource.
	 */
	public Collection<RelatedDocument> getRelatedDocuments(OntResource ontResource, Locale locale) {

		Collection<RelatedDocument> relatedDocuments = new HashSet<RelatedDocument>();
		OntModel ontModel = null;
		
		if (ontResource == null){
			return relatedDocuments;
		} else {
			ontModel = ontResource.getOntModel();
		}
		
		// Only labels that are resource labels 
		Collection<Label> labels = new HashSet<Label>();
		for(Label label: getLabels(ontResource, locale)){
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
			
			for (OntResource labelOcurrence :labelOccurrences){
				listStatements = ontModel.listStatements(labelOcurrence, ResourceFactory.createProperty(LINGKNOW_OCCURS), (RDFNode) null );
				while (listStatements.hasNext()){
					Statement statement = listStatements.next();
					sentences.add(ontModel.getOntResource(statement.getObject().asResource()));
				}
			}
	
			for (OntResource sentence :sentences){
				listStatements = ontModel.listStatements(sentence, ResourceFactory.createProperty(LINGKNOW_VALUE), (RDFNode) null );
				while (listStatements.hasNext()){
					Statement statement = listStatements.next();
					RelatedDocument relatedDocument = new RelatedDocument();
					relatedDocument.setUri(getSourceDocumentUri(ontModel, sentence.getURI()));
					relatedDocument.setSourceText(statement.getLiteral().getLexicalForm());
					relatedDocument.setType(Type.TEXT); // FIXME now it's fixed to plain/text
					relatedDocuments.add(relatedDocument);
				}
			}
		}
		
		//add videos
		relatedDocuments.addAll(getVideosRelated(ontResource));
		
		//add images
		relatedDocuments.addAll(getImagesRelated(ontResource));
		
		//add candidate rules
		relatedDocuments.addAll(getCandidateRulesRelated(ontResource));
		
		//add uri links (seeAlso)
		relatedDocuments.addAll(getUrisRelated(ontResource));
		
		return relatedDocuments;
	}

	/**
	 * Returns the version.
	 * @param ontResource the ontResource.
	 * @return the version.
	 */
	public String getVersion(OntResource ontResource) {
		if (ontResource == null){
			return null;
		}
		else{				
			return ontResource.getVersionInfo();
		}
	}

	/**
	 * Returns the preferred prefix.
	 * @param ontResource the ontResource.
	 * @return the preferred prefix.
	 */
	public String getPreferredPrefix(OntResource ontResource) {
		return getLiteralPropertyValue(ontResource, VANN_PREFERRED_PREFIX);
	}

	/**
	 * Returns the preferred namespace.
	 * @param ontResource the ontResource.
	 * @return the preferred namespace.
	 */
	public String getPreferredNamespace(OntResource ontResource) {
		return getLiteralPropertyValue(ontResource, VANN_PREFERRED_NAMESPACE);
	}
	
	/**
	 * Returns the date.
	 * @param ontResource the ontResource.
	 * @return the date.
	 */
	public String getDate(OntResource ontResource) {
		//ontResource.getModel().write(System.out);
		String date = getLiteralPropertyValue(ontResource, DCT_DATE);
		//logger.debug("ontResource="+ontResource+" date DCT="+date);
		if (date != null){
			return date;
		} else {
			//logger.debug("ontResource="+ontResource+" date DC="+date);
			return getLiteralPropertyValue(ontResource, DC_DATE);
		}
	}

	/**
	 * Returns information about the rights.
	 * @param ontResource the ontResource.
	 * @return information about the rights.
	 */
	public String getRights(OntResource ontResource) {
		return getLiteralPropertyValue(ontResource, DC_RIGHTS);
	}
	
	/**
	 * Returns the creators.
	 * @param ontResource the ontResource.
	 * @return the creators.
	 */	
	public Collection<String> getCreators(OntResource ontResource) {
		return getLiteralPropertyValues(ontResource, DC_CREATOR);
	}
	
	/**
	 * Returns the creator agents.
	 * @param ontResource the ontResource.
	 * @return the creator agents.
	 */
	public Collection<Agent> getCreatorAgents(OntResource ontResource) {
		return getAgentsFromObjectProperty(ontResource, DCT_CREATOR);
	}

	/**
	 * Returns the contributors.
	 * @param ontResource the ontResource.
	 * @return the contributors.
	 */
	public Collection<String> getContributors(OntResource ontResource) {
		return getLiteralPropertyValues(ontResource, DC_CONTRIBUTOR);
	}
	
	/**
	 * Returns the contributor agents.
	 * @param ontResource the ontResource.
	 * @return the contributor agents.
	 */
	public Collection<Agent> getContributorAgents(OntResource ontResource) {
		return getAgentsFromObjectProperty(ontResource, DCT_CONTRIBUTOR);
	}
	
	/**
	 * Returns the publishers.
	 * @param ontResource the ontResource.
	 * @return the publishers.
	 */
	public Collection<String> getPublishers(OntResource ontResource) {
		return getLiteralPropertyValues(ontResource, DC_PUBLISHER);
	}
	
	/**
	 * Returns the publisher agents.
	 * @param ontResource the ontResource.
	 * @return the publisher agents.
	 */
	public Collection<Agent> getPublisherAgents(OntResource ontResource) {
		return getAgentsFromObjectProperty(ontResource, DCT_PUBLISHER);
	}
	
	private Collection<Agent> getAgentsFromObjectProperty(OntResource ontResource, String property) {
		Collection<Agent> agents = new HashSet<Agent>();
		StmtIterator it = ontResource.listProperties(ResourceFactory.createProperty(property));
		while (it.hasNext()) {
			
			Statement st = it.next();
			Resource object = st.getResource();

			String name = object.asResource().getURI();
			String homepage = object.asResource().getURI();
			
			Statement homepageStatement = object.getProperty(ResourceFactory.createProperty(FOAF_HOMEPAGE));
			if (homepageStatement != null){
				homepage = homepageStatement.getObject().asResource().getURI();
			}

			Statement nameStatement = object.getProperty(ResourceFactory.createProperty(FOAF_NAME));
			if (nameStatement != null){
				name = nameStatement.getObject().asLiteral().getLexicalForm();
			}

			if (name != null && homepage != null){
				agents.add(new Agent(name, homepage));
			} else {
				logger.debug("not added "+property +" Agent for "+ontResource+" because it doen't have neither name or homepage");
			}
		}
		return agents;
	}

	/**
	 * Returns the reference where this resource is defined.
	 * @param ontResource the ontResource.
	 * @return the reference where this resource is defined.
	 */
	public String getIsDefinedBy(OntResource ontResource) {
		return getObjectPropertyURI(ontResource, RDF_SCHEMA_IS_DEFINED_BY);
	}
	
	/**
	 * Returns the URI of the license associated or <code>null</code> if there is no license URI associated.
	 * @param ontResource the ontResource.	
	 * @return the URI of the license associated or <code>null</code> if there is no license URI associated.
	 */
	public String getLicenseLabel(OntResource ontResource) {

    	if (ontResource == null){
    		return null;
    	}
    	
		StmtIterator it = ontResource.listProperties(ResourceFactory.createProperty(DCT_LICENSE));
		if(it.hasNext()){
			Statement statement = it.nextStatement();
			return statement.getObject().asResource().getURI();
		}
		
		it = ontResource.listProperties(ResourceFactory.createProperty(CC_LICENSE));
		if(it.hasNext()){
			Statement statement = it.nextStatement();
			return statement.getObject().asResource().getURI();
		}
		
		it = ontResource.listProperties(ResourceFactory.createProperty(CC_LICENSE_DEPRECATED)); // deprecated 
		if(it.hasNext()){
			Statement statement = it.nextStatement();
			return statement.getObject().asResource().getURI();
		}

		return null;
		
		
//	while(it.hasNext()){
//		Statement statement = it.nextStatement();
//		try{
//			Model model = ModelFactory.createDefaultModel();
//
//			// Option 1. XHTML + RDFa
//			Resource license = statement.getObject().asResource();
//			model.read(license.getURI(), "HTML"); // even it should be a XHTML page, it is not.
//        	StmtIterator listStatements = model.listStatements(license, ResourceFactory.createProperty(DCT_TITLE), (RDFNode) null);
//
//        	// Option 2
//			//model.read("http://code.creativecommons.org/viewgit/license.rdf.git/plain/cc/licenserdf/rdf/index.rdf", "RDF/XML");
//        	//StmtIterator listStatements = model.listStatements(license, ResourceFactory.createProperty(DC_TITLE), (RDFNode) null);
//        	
//        	// Read only one license 
//        	if (listStatements.hasNext()){
//        		Statement st = listStatements.next();
//        		licenseLabel = st.getObject().asLiteral().getLexicalForm();
//        		return licenseLabel;
//        	} else {
//        		return licenseLabel;
//        	}
//		} catch (ResourceRequiredException e)  {
//			logger.warn("Ignore triple "+ statement +" because it is not a Object property");
//		}
//	}
//	
//	return licenseLabel;

	}
	
	/**
	 * Returns a list of strings, the lexical values of the literal properties. 
	 * @param ontResource the ontResource.
	 * @param property the URI of the property.
	 * @return a list of strings, the lexical values of the literal properties.
	 */
	private Collection<String> getLiteralPropertyValues(OntResource ontResource, String property) {
		return getLiteralPropertyValues(ontResource, property, null);
	}
	
	/**
	 * Returns a list of strings, the lexical values of the literal properties. 
	 * @param ontResource the ontResource.
	 * @param property the URI of the property.
	 * @param locale the locale
	 * @return a list of strings, the lexical values of the literal properties.
	 */
	private Collection<String> getLiteralPropertyValues(OntResource ontResource, String property, Locale locale) {
    	
		if (ontResource == null){
    		return new ArrayList<String>();
    	}

		ArrayList<String> values = new ArrayList<String>();
		StmtIterator it = ontResource.listProperties(ResourceFactory.createProperty(property));
		while(it.hasNext()){
			Statement st = it.nextStatement();
			if (st.getObject().isLiteral()) {
				String extractLiteral = extractLiteral(st.getObject().asLiteral(), locale);
				if ( extractLiteral != null){				
					values.add(extractLiteral);
				} else {
					if (locale == null){
						//logger.debug("Literal not added " + st.toString());
					} else {
						//logger.debug("Literal not added " + st.toString() + " due to language issue (locale="+locale.toString()+", literalLanguage="+st.getObject().asLiteral().getLanguage()+")");
					}
				}
			} else {
				//logger.debug("As is not a literal, not added " + st.toString() );
			}
		}
		return values;
	}
	
	/**
	 * Returns the string if the literal has the same locale (if presents)
	 * @param literal the literal.
	 * @param locale the locale
	 * @return the string if the literal has the same locale or <code>null</code> if it is not the same. If not locale is present, it returns the string of the literal. 
	 */
	private static String extractLiteral(Literal literal, Locale locale) {
		if (locale == null){
			//if getLanguage is an empty string means that it has not language associated
			if (literal.getLanguage().length() == 0){
				return literal.getLexicalForm();
			} else {
				logger.debug("Not extracted "+ literal.getLexicalForm());
				return null;
			}
		}
		
		if (locale.toString().equals(literal.getLanguage())){
			return literal.getLexicalForm();
		} 
		return null;	
	}

	/**
	 * Returns the value of the literal or <code>null</code> if the resource has not this property associated
	 * @param ontResource the ontResource.
	 * @param property the URI of the property.
	 * @return the value of the literal or <code>null</code> if the resource has not this property associated
	 */
	private String getLiteralPropertyValue(OntResource ontResource, String property) {
		return getLiteralPropertyValue(ontResource, property, null);
	}

	/**
	 * Returns the value of the literal or <code>null</code> if the resource has not this property associated
	 * @param ontResource the ontResource.
	 * @param property the URI of the property.
	 * @param locale the locale.
	 * @return the value of the literal or <code>null</code> if the resource has not this property associated
	 */
	private String getLiteralPropertyValue(OntResource ontResource, String property, Locale locale) {
        
		Collection<String> literalPropertyValues = getLiteralPropertyValues(ontResource, property, locale);

        if (literalPropertyValues.isEmpty()){
        	return null;
        } else {
        	return literalPropertyValues.iterator().next(); // Take the first one
        }

	}

	/**
	 * Returns the URI or <code>null</code> if the resource has not this property associated.
	 * @param ontResource the ontResource.
	 * @param property the URI of the property.
	 * @return the URI or <code>null</code> if the resource has not this property associated.
	 */
	private String getObjectPropertyURI(OntResource ontResource, String property) {
		String uri = null;
    	if (ontResource == null){
    		return uri;
    	} else {		

			StmtIterator it = ontResource.listProperties(ResourceFactory.createProperty(property));
			if (it.hasNext()){
				Statement statement = it.nextStatement();
				try{
					uri = statement.getObject().asResource().getURI();
				} catch (ResourceRequiredException e)  {
					logger.warn("Ignore triple "+ statement +" because it is not a Object property");
				}
			}
			return uri;
    	}
	}
	
//	/**
//	 * Returns the collection of URIs or <code>null</code> if the resource has not this property associated.
//	 * @param ontResource the ontResource.
//	 * @param property the URI of the property.
//	 * @return a collection of URIs or <code>null</code> if the resource has not this property associated.
//	 */
//	private Collection<String> getObjectPropertyURIs(OntResource ontResource, String property) {
//		Collection<String> uris = new HashSet<String>();
//    	if (ontResource == null){
//    		return uris;
//    	} else {		
//
//			StmtIterator it = ontResource.listProperties(ResourceFactory.createProperty(property));
//			while (it.hasNext()){
//				Statement statement = it.nextStatement();
//				try{
//					uris.add(statement.getObject().asResource().getURI());
//				} catch (ResourceRequiredException e)  {
//					logger.warn("Ignore triple "+ statement +" because it is not a Object property");
//				}
//			}
//			return uris;
//    	}
//	}

	/**
	 * Returns <code>true</code> if the ontology resource is deprecated, otherwise <code>false</code>.
	 *  A ontology resource is deprecated if :
	 *  <ul>
	 *  <li><code>resource owl:deprecated "true"^^xsd:boolean</code></li>
	 *  <li><code>resource rdf:type owl:DeprecatedProperty</code></li> 
	 *  <li><code>resource rdf:type owl:DeprecatedClass</code></li>
	 *  </ul>
	 * @param ontResource the ontology resource.
	 * @return <code>true</code> if the ontology resource is deprecated, otherwise <code>false</code>.
	 */
	public boolean isDeprecated(OntResource ontResource) {
		
		if (ontResource == null){
			return false;
		}
		
		if (ontResource.isProperty() && ontResource.asProperty().hasRDFType(OWL.DeprecatedProperty)){
			return true;
		}

		if (ontResource.isClass() && ontResource.asClass().hasRDFType(OWL.DeprecatedClass)){
			return true;
		}

		if (ontResource.isProperty() && ontResource.getOntModel().listStatements(ontResource, OWL2.deprecated, ResourceFactory.createTypedLiteral(TRUE, XSDDatatype.XSDboolean)).hasNext()) {
			return true;
		}

		return false;
				
	}

	/**
	 * Converts a CamelCase string into an human-readable string
	 * From http://stackoverflow.com/questions/2559759/how-do-i-convert-camelcase-into-human-readable-names-in-java
	 * @param s The string to convert.
	 * @return The result string.
	 */
	private static String splitCamelCase(String s) {
		   return s.replaceAll(
		      String.format("%s|%s|%s",
		         "(?<=[A-Z])(?=[A-Z][a-z])",
		         "(?<=[^A-Z])(?=[A-Z])",
		         "(?<=[A-Za-z])(?=[^A-Za-z])"
		      ),
		      " "
		   );
		}

	/**
	 * Replaces all underscore '_' to blank space ' '.
	 * @param s The string to convert.
	 * @return The result string.
	 */
	private static String replaceUnderscore(String s) {
		   return s.replace('_' , ' ');
		}

	public Collection<Triple> getTriplesAsSubject(OntResource ontResource) {
		Collection<Triple> triples = new HashSet<Triple>();
		OntModel ontModel = null;
		
		if (ontResource == null){
			return triples;
		} else {
			ontModel = ontResource.getOntModel();
		}

		StmtIterator it = ontModel.listStatements(ontResource, null, (RDFNode) null);
		while(it.hasNext()){
			Statement statement = it.nextStatement();
			
			String subject = ontResource.getURI();
			String predicate = statement.getPredicate().getURI();
			String object = "";
			RDFNode ob = statement.getObject();
			if (ob.isResource()){
				object = ob.asResource().getURI();
			} else if (ob.isLiteral()){
				object = ob.asLiteral().getLexicalForm();
			} else {
				object = "";
			}
			// no rdf:type
			if (isWellKnown(predicate) == false ){
				triples.add(new Triple(subject, predicate, object));
				logger.debug("added triple ["+subject+", "+predicate+", "+object+"]");
			} else {
				logger.debug("NO added triple ["+subject+", "+predicate+", "+object+"]");
			}
		}
		
		return triples;

	}
	public Collection<Triple> getTriplesAsObject(OntResource ontResource) {
		Collection<Triple> triples = new HashSet<Triple>();
		OntModel ontModel = null;
		
		if (ontResource == null){
			return triples;
		} else {
			ontModel = ontResource.getOntModel();
		}

		StmtIterator it = ontModel.listStatements(null, null, ontResource);
		while(it.hasNext()){
			Statement statement = it.nextStatement();
			
			String subject = statement.getSubject().getURI();
			String predicate = statement.getPredicate().getURI();
			String object = ontResource.getURI();
			
			if (isWellKnown(predicate) == false ){
				triples.add(new Triple(subject, predicate, object));
				logger.debug("added triple ["+subject+", "+predicate+", "+object+"]");
			} else {
				logger.debug("NO added triple ["+subject+", "+predicate+", "+object+"]");
			}		
		}	
		
		return triples;

	}

	private boolean isWellKnown(String predicate) {
		if ( predicate.equals(CC_LICENSE)
			 || predicate.equals(CC_LICENSE_DEPRECATED)
			 || predicate.equals(DC_CONTRIBUTOR)
			 || predicate.equals(DC_CREATOR)
			 || predicate.equals(DC_DATE)
			 || predicate.equals(DC_DESCRIPTION)
			 || predicate.equals(DC_PUBLISHER)
			 || predicate.equals(DC_RIGHTS)
			 || predicate.equals(DCT_IS_PART_OF)
			 || predicate.equals(DC_TITLE)
			 || predicate.equals(DCT_DESCRIPTION)
			 || predicate.equals(DCT_LICENSE)
			 || predicate.equals(FOAF_DEPICTION)
			 || predicate.equals(OG_VIDEO)
			 || predicate.equals(RDF_SCHEMA_IS_DEFINED_BY)
			 || predicate.equals(RDF_SCHEMA_LABEL)
			 || predicate.equals(RDFS_COMMENT)
			 || predicate.equals(OWL.versionInfo.getURI())			 
			 || predicate.equals(RDF.type.getURI())) {
			return true;
		} else {
			return false;
		}
	}


	/**
	 * Returns the modified date.
	 * @param ontResource the ontResource.
	 * @return the modified date.
	 */
	public String getModifiedDate(OntResource ontResource) {
		return getLiteralPropertyValue(ontResource, DCTERMS_MODIFIED);

	}

	/**
	 * Returns the number of classes defined in the vocabulary namespace.
	 * @param ontResource the ontResource.
	 * @return the number of classes defined in the vocabulary namespace.
	 */
	public String getClassNumber(OntResource ontResource) {
		return getLiteralPropertyValue(ontResource, VOAF_CLASSNUMBER);
	}

	/**
	 * Returns the number of properties defined in the vocabulary namespace.
	 * @param ontResource the ontResource.
	 * @return the number of properties defined in the vocabulary namespace.
	 */
	public String getPropertyNumber(OntResource ontResource) {
		return getLiteralPropertyValue(ontResource, VOAF_PROPERTYNUMBER);
	}

	/**
	 * Returns the homepage.
	 * @param ontResource the ontResource.
	 * @return the homepage.
	 */
	public String getHomepage(OntResource ontResource) {
		return getObjectPropertyURI(ontResource, FOAF_HOMEPAGE);
	}

	/**
	 * Returns the dataDump.
	 * @param ontResource the ontResource.
	 * @return the dataDump.
	 */
	public String getDataDump(OntResource ontResource) {
		return getObjectPropertyURI(ontResource, VOID_DATADUMP);
	}
	
	/**
	 * Returns the SPARQL endpoint.
	 * @param ontResource the ontResource.
	 * @return the SPARQL endpoint.
	 */
	public String getSparqlEndpoint(OntResource ontResource) {
		return getObjectPropertyURI(ontResource, VOID_SPARQLENDPOINT);
	}
	
}

