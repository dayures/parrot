package es.ctic.parrot.reader.jena;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.ResourceRequiredException;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.RDF;

import es.ctic.parrot.de.Label;
import es.ctic.parrot.de.RelatedDocument;
import es.ctic.parrot.utils.URIUtils;

public class OntResourceAnnotationStrategy {

	private static final Logger logger = Logger.getLogger(OntResourceAnnotationStrategy.class);

	private static final String DC_TITLE = "http://purl.org/dc/elements/1.1/title";
	private static final String DC_TERMS_IS_PART_OF = "http://purl.org/dc/terms/isPartOf";
	private static final String DC_DCMITYPE_TEXT = "http://purl.org/dc/dcmitype/Text";
	private static final String RDF_SCHEMA_LABEL = "http://www.w3.org/2000/01/rdf-schema#label";
	private static final String SKOS_XL_PREF_LABEL = "http://www.w3.org/2008/05/skos-xl#prefLabel";
	private static final String SKOS_XL_ALT_LABEL = "http://www.w3.org/2008/05/skos-xl#altLabel";
	private static final String SKOS_CORE_PREF_LABEL = "http://www.w3.org/2004/02/skos/core#prefLabel";
	private static final String SKOS_CORE_ALT_LABEL = "http://www.w3.org/2004/02/skos/core#altLabel";
	private static final String FOAF_DEPICTION = "http://xmlns.com/foaf/0.1/depiction";
	private static final String OG_VIDEO = "http://ogp.me/ns#video";
	private static final String SKOS_XL_LITERAL_FORM = "http://www.w3.org/2008/05/skos-xl#literalForm";
	private static final String LINGKNOW_VALUE = "http://idi.fundacionctic.org/lingknow/value";
	private static final String LINGKNOW_OCCURS = "http://idi.fundacionctic.org/lingknow/occurs";
	private static final String TELIX_REALIZES = "http://ontorule-project.eu/telix#realizes";
	
	private static final String TYPE_VIDEO = "video/mpeg";
	private static final String TYPE_IMAGE = "image/png";
	private static final String TYPE_TEXT = "text/plain";
	
	public String getComment(OntResource ontResource, Locale locale) {
	    String comment = ontResource.getComment(locale.toString());
	    if (comment == null) {
	        return ontResource.getComment(null);
	    } else {
	        return comment;
	    }
	}
	
	public Collection<RelatedDocument> getVideosRelated(OntResource ontResource) {
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
					video.setType(TYPE_VIDEO);
					videos.add(video);
				} catch (ResourceRequiredException e)  {
					logger.warn("Ignore triple "+ statement +" because it is not a Object property");
				}
			}
			return videos;
    	}
	}
	
	public Collection<RelatedDocument> getImagesRelated(OntResource ontResource) {
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
					image.setType(TYPE_IMAGE);
					images.add(image);
				} catch (ResourceRequiredException e)  {
					logger.warn("Ignore triple "+ statement +" because it is not a Object property");
				}
			}
			return images;
    	}
	}
	
	public Collection<Label> getLabels(OntResource ontResource){
   		return getLabels(ontResource, null);
   	}
	
	public Collection<Label> getLabels(OntResource ontResource, Locale locale){
		
		Collection<Label> labels = new HashSet<Label>();

		Collection<Label> skosXLPrefLabels = getSkosxlLabels(ontResource, SKOS_XL_PREF_LABEL, locale);
		if (skosXLPrefLabels.isEmpty() == false){
			labels.addAll(skosXLPrefLabels);
		}

		Collection<Label> skosXLAltLabels = getSkosxlLabels(ontResource, SKOS_XL_ALT_LABEL, locale);
		if (skosXLAltLabels.isEmpty() == false){
			labels.addAll(skosXLAltLabels);
		}
		
		Collection<Label> skosAltLabels = getLiteralLabels(ontResource, SKOS_CORE_ALT_LABEL, locale);
		if (skosAltLabels.isEmpty() == false){
			labels.addAll(skosAltLabels);
		}
        
		Collection<Label> skosPrefLabels = getLiteralLabels(ontResource, SKOS_CORE_PREF_LABEL, locale);
        if (skosPrefLabels.isEmpty() == false && labels.isEmpty()) { // hidden by the previous labels
            labels.addAll(skosPrefLabels);
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
	 * @param the uri of the property used to annotate
	 * @return a collection of literal labels for the uri
	 */
	public Collection<Label> getLiteralLabels(OntResource ontResource, String uri, Locale locale) {
		
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
			literalLabel.setText(statement.getObject().asLiteral().getString());
			String LanguageTag = statement.getObject().asLiteral().getLanguage(); 
			if (LanguageTag.equals("") == false){
				String language = LanguageTag.split("-")[0]; 
				literalLabel.setLocale(new Locale(language)); // FIXME do it more specified using Locale(String language, String country, String variant)
			}
			
			if (locale != null) {
				//compare locales
				if (locale.equals(literalLabel.getLocale())) {
					//logger.debug(literalLabel + " is " + uri + " for resource " + getOntResource());
					literalLabels.add(literalLabel);
				} else{
					//logger.debug("Not add label  " + literalLabel + " for resource " + getOntResource() + " because its locale " + literalLabel.getLocale() + " does not match with required locale " + locale);
				}
			} else {
				//logger.debug(literalLabel + " is " + uri + " for resource " + getOntResource());
				literalLabels.add(literalLabel);
			}

		}
		
		return literalLabels;
	}

	
	/**
	 * @return a collection of labels for the skosXL uri
	 */
	public Collection<Label> getSkosxlLabels(OntResource ontResource, String uri, Locale locale) {

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
				skosxlLabel.setText(st.getObject().asLiteral().getString());
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
	 * 
	 * @param locale
	 * @return a collection of related documents for this documentable object
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
					relatedDocument.setType(TYPE_TEXT); // FIXME now it's fixed to plain/text
					relatedDocuments.add(relatedDocument);
				}
			}
		}
		
		//add videos
		relatedDocuments.addAll(getVideosRelated(ontResource));
		
		//add images
		relatedDocuments.addAll(getImagesRelated(ontResource));
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
	
    public String getLabel(OntResource ontResource, Locale locale) {
        
        Collection<Label> labels = getLabels(ontResource, locale);
        
        /* Preferred order:
         * 
         * http://www.w3.org/2008/05/skos-xl#prefLabel
         * http://www.w3.org/2008/05/skos-xl#altLabel
         * http://www.w3.org/2004/02/skos/core#prefLabel
         * http://www.w3.org/2004/02/skos/core#altLabel
         * http://purl.org/dc/elements/1.1/title
         * http://www.w3.org/2000/01/rdf-schema#label
         * 
         * 
         */
        
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
            if (label.getQualifier().equals(SKOS_XL_ALT_LABEL)) {
                return label.getText();
            }
        }

        for (Label label : labels){
        	if (label.getQualifier().equals(SKOS_CORE_ALT_LABEL)) {
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
        
        return URIUtils.getReference(ontResource.getURI());
    }
    
    public String getLabel(OntResource ontResource) {
        return this.getLabel(ontResource, null);
    }
    
    


}
