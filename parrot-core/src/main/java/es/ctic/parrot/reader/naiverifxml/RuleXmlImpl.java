package es.ctic.parrot.reader.naiverifxml;

import java.util.Collection;
import java.util.LinkedList;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import es.ctic.parrot.de.AbstractDocumentableObject;
import es.ctic.parrot.de.DocumentableObject;
import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.OntologyProperty;
import es.ctic.parrot.de.Rule;
import es.ctic.parrot.de.URIIdentifier;
import es.ctic.parrot.transformers.DocumentableObjectVisitor;

public class RuleXmlImpl extends AbstractDocumentableObject implements Rule {

    private static final Logger logger = Logger.getLogger(RuleXmlImpl.class);
    
    private Element sentenceElement;
    private final DocumentableObjectRegister register;
    private final Identifier identifier;
    
    public RuleXmlImpl(Element sentenceElement, int index, DocumentableObjectRegister register) {
        this.sentenceElement = sentenceElement;
        this.register = register;
        this.identifier = createIdentifier(index);
        logger.debug("Created rule");
    }

    private Identifier createIdentifier(int index) {
        try {
            Element metaElement = (Element) sentenceElement.getElementsByTagName("meta").item(0);
            Element objectElement = (Element) metaElement.getElementsByTagName("object").item(0);
            Element constElement = (Element) objectElement.getElementsByTagName("Const").item(0);
            return new URIIdentifier(constElement.getTextContent());
        } catch (NullPointerException e) {
            return new RIFIdentifier(index);
        }
    }

    public Object accept(DocumentableObjectVisitor visitor) {
        return visitor.visit(this);
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public Collection<OntologyProperty> getReferencedOntologyProperties() {
        Collection<OntologyProperty> referencedOntologyProperties = new LinkedList<OntologyProperty>();
        NodeList slotElementList = sentenceElement.getElementsByTagName("slot");
        logger.debug("Found " + slotElementList.getLength() + " slots in rule " + this);
        for ( int i = 0 ; i < slotElementList.getLength() ; i++ ) {
            Element slotElement = (Element) slotElementList.item(i);
            String propertyUri = slotElement.getElementsByTagName("Const").item(0).getTextContent();
            Identifier propertyIdentifier = new URIIdentifier(propertyUri);
            logger.debug("Found reference to documentary object with identifier '" + propertyIdentifier + "'");
            DocumentableObject propertyDocumentableObject = register.findDocumentableObject(propertyIdentifier);
            logger.debug("Found referred object " + propertyDocumentableObject);
            // FIXME 1: the result may not be a ontology property
            // FIXME 2: the result may be null (broken reference)
            if (propertyDocumentableObject != null) {
                referencedOntologyProperties.add((OntologyProperty) propertyDocumentableObject);
            } else {
                logger.debug("Ignoring referred object " + propertyUri + " because its documentation object cannot be found");
            }
        }
        return referencedOntologyProperties;
    }

	public String getLocalName() {
		return "Rule"+getIdentifier().hashCode();
	}
	
	public Collection<String> getDeclaredVars() {
        Collection<String> declaredVars = new LinkedList<String>();
	    NodeList declareNodeList = sentenceElement.getElementsByTagName("declare");
	    for ( int i = 0 ; i < declareNodeList.getLength() ; i++ ) {
	        Element declareElement = (Element) declareNodeList.item(i);
            declaredVars.add(declareElement.getFirstChild().getTextContent());
	    }
	    return declaredVars;
	}

}
