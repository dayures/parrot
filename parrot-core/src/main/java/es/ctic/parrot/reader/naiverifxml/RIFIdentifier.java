package es.ctic.parrot.reader.naiverifxml;

import es.ctic.parrot.de.Identifier;

public class RIFIdentifier implements Identifier {

    private int indexWithinFile;
    
    public RIFIdentifier(int indexWithinFile) {
        this.indexWithinFile = indexWithinFile;
    }
    
    
    @Override
    public String toString() {
        return "RifIdentifier_" + indexWithinFile;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RIFIdentifier other = (RIFIdentifier) obj;
        if (indexWithinFile != other.indexWithinFile)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + indexWithinFile;
        return result;
    }
    
}
