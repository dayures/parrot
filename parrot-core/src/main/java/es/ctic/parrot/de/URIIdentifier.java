package es.ctic.parrot.de;

/**
 * An URI identifier.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 * 
 */

public class URIIdentifier implements Identifier {

    private final String uri;
    
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
        result = prime * result + ((getUri() == null) ? 0 : getUri().hashCode());
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
        if (getUri() == null) {
            if (other.getUri() != null)
                return false;
        } else if (!getUri().equals(other.getUri()))
            return false;
        return true;
    }

    public String getUri() {
        return uri;
    }
    
    
    
}
