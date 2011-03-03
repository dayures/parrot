package es.ctic.parrot.de;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public abstract class AbstractDocumentableObject implements DocumentableObject {

    private Collection<DocumentableObject> references = new HashSet<DocumentableObject>();
    private DocumentableObjectRegister register;
    
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

	/**
	 * Set the register to this documentable element.
	 * @param register the register to set
	 */
	public void setRegister(DocumentableObjectRegister register) {
		this.register = register;
	}

	/**
	 * Returns the register.
	 * @return the register.
	 */
	public DocumentableObjectRegister getRegister() {
		return register;
	}
	

}
