package es.ctic.parrot.de;

import java.util.Locale;

public interface DocumentableOntologicalObject extends DocumentableObject,Comparable<DocumentableOntologicalObject>{

	public abstract String getURI();
	public abstract String getLabel(Locale locale);
	public abstract String getComment(Locale locale);
	
}
