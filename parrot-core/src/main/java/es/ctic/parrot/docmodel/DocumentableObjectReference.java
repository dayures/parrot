package es.ctic.parrot.docmodel;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.DocumentableObject;
import es.ctic.parrot.utils.CurieUtils;

/**
 * 
 * A reference to a documentable element.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class DocumentableObjectReference implements Comparable<DocumentableObjectReference> {
    
	private static final Logger logger = Logger.getLogger(DocumentableObjectReference.class);

    private String label;
    private String localName;
    private String kindString;
    private String uri;
    private boolean externalLink;
    
    /**
     * Constructs a reference to a documentable element. 
     * @param documentableObject the documentable element. 
     * @param locale the locale.
     */
    private DocumentableObjectReference(DocumentableObject documentableObject, Locale locale) {

        setExternalLink(documentableObject);

        setLabel(documentableObject, locale);
        this.localName = documentableObject.getLocalName();
        this.kindString = documentableObject.getKindString();
        this.uri = documentableObject.getURI();
    }
    
    /**
     * Returns an ordered collection of references to a documentable elements (ordered by reference label).
     * @param documentableObjects the collection of documentable elements to reference.
     * @param locale the locale.
     * @return an ordered collection of references to a documentable elements (ordered by reference label).
     */
    public static Collection<DocumentableObjectReference> createReferences(Collection<? extends DocumentableObject> documentableObjects, Locale locale) {
        List<DocumentableObjectReference> result = new ArrayList<DocumentableObjectReference>(documentableObjects.size());
        for (DocumentableObject documentableObject : documentableObjects) {
            result.add(DocumentableObjectReference.createReference(documentableObject, locale));
        }
        Collections.sort(result);
        return result;
    }
    
    /**
     * Returns a reference to a documentable element.
     * @param documentableObject the documentable element to reference.
     * @param locale the locale.
     * @return a reference to a documentable element.
     */
    public static DocumentableObjectReference createReference(DocumentableObject documentableObject, Locale locale) {
    	if (documentableObject != null){
    		return new DocumentableObjectReference(documentableObject, locale);
    	} else {
    		return null;
    	}
    }

    /**
     * Returns the label for the referenced documentable element..
     * @return the label for the referenced documentable element.
     */
    public String getLabel() {
        return this.label;
    }
    
	/**
	 * Returns the unique anchor for the referenced documentable element.
	 * @return the unique anchor for the referenced documentable element.
	 */
    public String getLocalName() {
        return this.localName;
    }
    
	/**
	 * Returns the kind string for the referenced documentable element. 
	 * @return the kind string for the referenced documentable element.
	 */
    public String getKindString() {
        return this.kindString;
    }
    
	/**
	 * Returns the URI for the referenced documentable element. 
	 * @return the URI for the referenced documentable element.
	 */
    public String getUri() {
        return this.uri;
    }
    
	/**
	 * Returns the URLencode URI for the referenced documentable element. 
	 * @return the URLencode URI for the referenced documentable element.
	 */
    public String getEncodedUri() {
        try {
			return java.net.URLEncoder.encode(this.uri,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			return this.uri;
		}

    }
    
    /**
     * Compare using lower case.
     * 
     */
    public int compareTo(DocumentableObjectReference o) {
        return this.getLabel().toLowerCase().compareTo(o.getLabel().toLowerCase()) ;
    }

    private void  setExternalLink(DocumentableObject documentableObject){
        if (documentableObject.getRegister() != null && documentableObject.getRegister().containsIdentifier(documentableObject.getIdentifier())){
        	logger.debug("Internal link="+documentableObject.getIdentifier());
        	this.externalLink = false;
        } else {
        	logger.debug("External link="+documentableObject.getIdentifier());
            this.externalLink = true;
        }
    }
    
    public boolean isExternalLink(){
    	return externalLink;
    }
    
    private void setLabel(DocumentableObject documentableObject, Locale locale){

    	if (isExternalLink()){
    		String curie = CurieUtils.getCurie(documentableObject.getURI());
    		if (curie != null){
    			this.label = curie;
        	} else {
        		this.label = documentableObject.getLabel(locale);
        	}
    	} else {
    		this.label = documentableObject.getLabel(locale);
    	}
    }


}
