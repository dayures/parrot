package es.ctic.parrot.de;

import java.util.Collection;
import java.util.Locale;

import org.apache.log4j.Logger;

import es.ctic.parrot.transformers.DocumentableObjectVisitor;
import es.ctic.parrot.transformers.TransformerException;


/**
 * A datatype to be used as referenced by Parrot. 
 * 
 * @author Carlos Tejo Alonso (<a href="http://www.fundacionctic.org">Fundación CTIC</a>)
 * @version 1.0
 * @since 1.0
 * 
 */
public class DataType extends AbstractDocumentableObject {

	private static final Logger logger = Logger.getLogger(DataType.class);

    
    /**
     * Constructs a datatype for the given URI.
     * @param uri the URI.
     */
	public DataType(String uri) {
		super();
		this.uri = uri;
	}

	private String uri;
	
	public Identifier getIdentifier() {
		return new URIIdentifier(this.getURI());
	}

	public String getURI() {
		return uri;
	}

	public String getLabel(Locale locale) {
		return this.getURI();
	}

	public String getLabel() {
        return this.getLabel(null);
	}
	
	/** (non-Javadoc)
	 * @see es.ctic.parrot.de.DocumentableObject#getLocalName()
	 * @return an unique anchor for the element
	 */
	public String getLocalName() {
		return "anchor"+getIdentifier().hashCode(); // FIXME: should it has a localName?
	}

    public String getKindString() {
        return Kind.DATATYPE.toString();
    }

	public Object accept(DocumentableObjectVisitor visitor)
			throws TransformerException {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public String getDescription(Locale locale) {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public Collection<RelatedDocument> getRelatedDocuments(Locale locale) {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public Collection<Label> getLabels(Locale locale) {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public Collection<Label> getLabels() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

	public String getUriFragment() {
		throw new UnsupportedOperationException("Method not implemented yet.");
	}

}
