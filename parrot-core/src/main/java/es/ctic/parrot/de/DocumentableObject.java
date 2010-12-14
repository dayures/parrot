package es.ctic.parrot.de;

import java.util.Collection;
import java.util.Locale;

import es.ctic.parrot.transformers.DocumentableObjectVisitor;

public interface DocumentableObject {
	
	public Object accept(DocumentableObjectVisitor visitor);
	public Identifier getIdentifier();
	public String getLocalName();
    public void addReference(DocumentableObject documentableObject);
    public Collection<DocumentableObject> getInternalReferences();
	public abstract String getURI();
	public abstract String getLabel(Locale locale);
	public abstract String getComment(Locale locale);    
	
}
