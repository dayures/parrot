package es.ctic.parrot.reader.jena;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.rdf.model.AnonId;

import es.ctic.parrot.de.AnonymousIdentifier;

/**
 * An identifier for a Jena node that is anonymous (it has not URI).
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 * 
 */
public class JenaAnonymousIdentifier implements AnonymousIdentifier {

    private static final Logger logger = Logger.getLogger(JenaAnonymousIdentifier.class);
	
    private final String id;

    /**
     * Constructs an identifier for an anonymous element.
     * @param anonId the anonymous element.
     */
	public JenaAnonymousIdentifier(AnonId anonId) {
		this.id = Integer.valueOf(anonId.hashCode()).toString();
	}
	
    @Override
    public String toString() {
        return getId();
    }

	/**
	 * Returns the internal id.
	 * @return the internal id.
	 */
	public String getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof JenaAnonymousIdentifier))
			return false;
		JenaAnonymousIdentifier other = (JenaAnonymousIdentifier) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
