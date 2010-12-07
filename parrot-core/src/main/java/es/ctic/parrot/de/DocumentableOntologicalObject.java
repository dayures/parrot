package es.ctic.parrot.de;

public interface DocumentableOntologicalObject extends DocumentableObject,Comparable<DocumentableOntologicalObject>{

	public abstract String getURI();
	public abstract String getLabel(String lang);
	public abstract String getComment(String lang);
	
}
