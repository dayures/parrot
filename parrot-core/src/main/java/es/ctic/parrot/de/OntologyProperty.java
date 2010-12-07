package es.ctic.parrot.de;

public interface OntologyProperty extends DocumentableOntologicalObject {
	public DocumentableObject getRange();
	public void setRange(DocumentableObject range);
	public DocumentableObject getDomain();
	public void setDomain(DocumentableObject domain);
	public int getCardinality();

}
