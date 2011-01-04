package es.ctic.parrot.de;

public class AnonymousIdentifier implements Identifier {

    private final int internalId;
    
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
