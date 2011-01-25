package es.ctic.parrot.de;

import java.util.Collection;
import java.util.Locale;

import es.ctic.parrot.transformers.DocumentableObjectVisitor;
import es.ctic.parrot.transformers.TransformerException;

public interface DocumentableObject {
    
    public enum Kind {
        UNDEFINED("undefined"), 
        ONTOLOGY("ontology"),
        ONTOLOGY_CLASS("class"),
        ONTOLOGY_PROPERTY("property"),
        ONTOLOGY_INDIVIDUAL("individual"),
        RULE("rule"),
        RULE_SET("ruleset");
        private final String name;
        private Kind(String name) { 
            this.name = name;
        }
        public String toString() {
            return name;
        }
    };
	
	public abstract Object accept(DocumentableObjectVisitor visitor) throws TransformerException;
	public abstract Identifier getIdentifier();
	public abstract String getLocalName();
    public abstract void addReference(DocumentableObject documentableObject);
    public abstract Collection<DocumentableObject> getInternalReferences();
	public abstract String getURI();
	public abstract String getLabel(Locale locale);
	public abstract String getLabel();
	public abstract String getComment(Locale locale);    
	public abstract Collection<RelatedDocument> getRelatedDocuments(Locale locale);
	public abstract Collection<Label> getLabels(Locale locale);
	public abstract Collection<Label> getLabels();
	public abstract String getKindString();

}
