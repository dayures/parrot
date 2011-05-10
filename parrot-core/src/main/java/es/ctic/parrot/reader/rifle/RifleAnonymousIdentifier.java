package es.ctic.parrot.reader.rifle;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.Identifier;

/**
 * An identifier for a rule or rule set that is anonymous (it has not ID).
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 * 
 */
public class RifleAnonymousIdentifier implements Identifier {
	
    private static final Logger logger = Logger.getLogger(RifleAnonymousIdentifier.class);
	
    private final String id;

    /**
     * Constructs an identifier for an anonymous rule or rule set.
     * @param id the internal identification string.
     */
	public RifleAnonymousIdentifier(String id) {
		this.id = id;
		logger.debug("creating a RifleAnonymousIdentifier with id: " + id);
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
		if (!(obj instanceof RifleAnonymousIdentifier))
			return false;
		RifleAnonymousIdentifier other = (RifleAnonymousIdentifier) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
