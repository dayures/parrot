package es.ctic.parrot.de;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public abstract class AbstractDocumentableObject implements DocumentableObject {

    private Collection<DocumentableObject> references = new HashSet<DocumentableObject>();
    
    public void addReference(DocumentableObject documentableObject) {
        references.add(documentableObject);
    }
    
    public Collection<DocumentableObject> getInternalReferences() {
        return Collections.unmodifiableCollection(references);
    }
    
    @Override
    public String toString() {
        return getIdentifier().toString();
    }
    
	public String getLocalName() {
		return "anchor"+getIdentifier().hashCode();
	}

}
