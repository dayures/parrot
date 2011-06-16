package es.ctic.parrot.de;

import java.util.Collection;
import java.util.Locale;

import org.apache.log4j.Logger;

import es.ctic.parrot.transformers.DocumentableObjectVisitor;
import es.ctic.parrot.transformers.TransformerException;


/**
 * A datatype to be used as referenced by Parrot. 
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
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
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return null;
	}

	public String getDescription(Locale locale) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return null;
	}

	public Collection<RelatedDocument> getRelatedDocuments(Locale locale) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return null;
	}

	public Collection<Label> getLabels(Locale locale) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return null;
	}

	public Collection<Label> getLabels() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return null;
	}

	public String getUriFragment() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return null;
	}

}
