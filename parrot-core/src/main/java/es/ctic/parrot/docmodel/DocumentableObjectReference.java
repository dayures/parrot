package es.ctic.parrot.docmodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import es.ctic.parrot.de.DocumentableObject;

public class DocumentableObjectReference {
    
    private final String label;
    private String localName;
    private String kindString;
    
    public DocumentableObjectReference(DocumentableObject documentableObject, Locale locale) {
        this.label = documentableObject.getLabel(locale);
        this.localName = documentableObject.getLocalName();
        this.kindString = documentableObject.getKindString();
    }
    
    public static Collection<DocumentableObjectReference> createReferences(Collection<? extends DocumentableObject> documentableObjects, Locale locale) {
        Collection<DocumentableObjectReference> result = new ArrayList<DocumentableObjectReference>(documentableObjects.size());
        for (DocumentableObject documentableObject : documentableObjects) {
            result.add(new DocumentableObjectReference(documentableObject, locale));
        }
        return result;
    }

    public String getLabel() {
        return this.label;
    }
    
    public String getLocalName() {
        return this.localName;
    }
    
    public String getKindString() {
        return this.kindString;
    }
    
}
