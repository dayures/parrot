package es.ctic.parrot.reader.jena;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.rdf.model.AnonId;
import com.hp.hpl.jena.rdf.model.Model;

import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.de.Identifier;

public class JenaAnonymousIdentifier implements Identifier {

    private static final Logger logger = Logger.getLogger(DocumentableObjectRegister.class);

	
    private final String id;

	public JenaAnonymousIdentifier(Model model, AnonId anonId) {
		this.id = new Integer (anonId.hashCode()).toString();
		logger.debug("Create anonymous identifier: " + this.id);
	}
	
    @Override
    public String toString() {
        return getId();
    }

	/**
	 * @return the id
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
