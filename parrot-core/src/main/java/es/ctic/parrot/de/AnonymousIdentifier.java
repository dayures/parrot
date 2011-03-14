package es.ctic.parrot.de;

/**
 * An identifier for an element that is anonymous (it has not URI).
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 * 
 */
public class AnonymousIdentifier implements Identifier {

    private final int internalId;
    
    
    /**
     * Constructs an anonymous identifier.
     */
    public AnonymousIdentifier() {
        this.internalId = super.hashCode();
    }

    @Override
    public String toString() {
        return ("_anon" + internalId);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + internalId;
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
        AnonymousIdentifier other = (AnonymousIdentifier) obj;
        if (internalId != other.internalId)
            return false;
        return true;
    }
    
    
    
}
