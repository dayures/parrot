package es.ctic.parrot.transformers;

import java.util.Collection;
import java.util.Locale;

import es.ctic.parrot.de.DocumentableObject;
import es.ctic.parrot.de.Label;
import es.ctic.parrot.de.Ontology;
import es.ctic.parrot.de.OntologyClass;
import es.ctic.parrot.de.OntologyIndividual;
import es.ctic.parrot.de.OntologyProperty;
import es.ctic.parrot.de.Rule;
import es.ctic.parrot.de.RuleSet;
import es.ctic.parrot.docmodel.Document;
import es.ctic.parrot.docmodel.Glossary;


public class GlossaryVisitor extends AbstractDocumentableObjectVisitor {

    private final Glossary glossary;
    private final Locale locale;
    
    public GlossaryVisitor(Document document, Locale locale) {
        this.glossary = document.getGlossary();
        this.locale = locale;
    }

    @Override
    public Object visit(OntologyClass object) throws TransformerException {
        return visitDocumentableObject(object);
    }

    @Override
    public Object visit(OntologyProperty object) throws TransformerException {
        return visitDocumentableObject(object);
    }

    @Override
    public Object visit(Rule object) throws TransformerException {
        return visitDocumentableObject(object);
    }

    @Override
    public Object visit(Ontology ontology) throws TransformerException {
        return visitDocumentableObject(ontology);
    }

    @Override
    public Object visit(OntologyIndividual ontologyIndividual)
            throws TransformerException {
        return visitDocumentableObject(ontologyIndividual);
    }

    @Override
    public Object visit(RuleSet ruleSet) throws TransformerException {
        return visitDocumentableObject(ruleSet);
    }

    private Object visitDocumentableObject(DocumentableObject object) {
        Collection<Label> labels = object.getLabels(locale);
        for (Label label : labels) {
            glossary.addReference(label.getText(), object);
        }
        return null;
    }


}
