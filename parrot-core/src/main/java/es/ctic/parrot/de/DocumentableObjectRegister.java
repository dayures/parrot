package es.ctic.parrot.de;

import java.util.Collection;
import java.util.HashMap;

import org.apache.log4j.Logger;

public class DocumentableObjectRegister {

    private static final Logger logger = Logger.getLogger(DocumentableObjectRegister.class);
    
    private HashMap<Identifier, DocumentableObject> documentableObjects = new HashMap<Identifier, DocumentableObject>();
    
    public void registerDocumentableObject(DocumentableObject documentableObject) {
        logger.debug("Registering documentable object [" +documentableObject.getClass().getSimpleName() + "] " + documentableObject);
        documentableObjects.put(documentableObject.getIdentifier(), documentableObject);
    }
    
    public DocumentableObject findDocumentableObject(Identifier identifier) {
        return documentableObjects.get(identifier);
    }

    public Collection<DocumentableObject> getDocumentableObjects() {
        return documentableObjects.values();
    }

}
