package es.ctic.parrot.de;

/**
 * An URI identifier.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 * 
 */

public class URIIdentifier implements NamedIdentifier {

    private final String uri;
    
    /**
     * Constructs a URI identifier using the given URI.
     * @param uri the URI.
     */
    public URIIdentifier(String uri) {
        assert uri != null;
        assert uri.trim().length() > 0;
        this.uri = uri;
    }
    
    @Override
    public String toString() {
        return uri;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        URIIdentifier other = (URIIdentifier) obj;
        if (getName() == null) {
            if (other.getName() != null)
                return false;
        } else if (!getName().equals(other.getName()))
            return false;
        return true;
    }

    /**
     * Returns the URI.
     * @return the URI.
     */
    public String getName() {
        return uri;
    }
    
    
    
}
