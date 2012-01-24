package es.ctic.parrot.de;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

/**
 * An implementation of the DocumentableObject interface to serve as a basis for implementing various kinds of documentable elements.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public abstract class AbstractDocumentableObject implements DocumentableObject {

    private Collection<DocumentableObject> internalReferences = new HashSet<DocumentableObject>();
    private DocumentableObjectRegister register;
    
    public void addReference(DocumentableObject documentableObject) {
        internalReferences.add(documentableObject);
    }
    
    public Collection<DocumentableObject> getInternalReferences() {
        return Collections.unmodifiableCollection(internalReferences);
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
