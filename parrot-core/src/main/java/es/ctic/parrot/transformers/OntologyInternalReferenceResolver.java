package es.ctic.parrot.transformers;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.DocumentableObject;
import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.de.OntologyClass;
import es.ctic.parrot.de.OntologyProperty;

public class OntologyInternalReferenceResolver extends
		AbstractDocumentableObjectVisitor {
	private final DocumentableObjectRegister register;
	
	private static final Logger logger = Logger.getLogger(OntologyInternalReferenceResolver.class);

	public OntologyInternalReferenceResolver(DocumentableObjectRegister register){
		this.register=register;
	}
	
	public Object visit(OntologyProperty property) throws TransformerException {
    	logger.debug("Resolving internal references of property "+property);

		DocumentableObject domain = property.getDomain();
	    if(domain!=null){
	        domain=register.findDocumentableObject(domain.getIdentifier());
	        property.setDomain(domain);
	    }
	    
	    DocumentableObject range = property.getRange();
	    if(range!=null){
	        range=register.findDocumentableObject(range.getIdentifier());
	        property.setRange(range);
	    }

	    Collection<OntologyProperty> superProperties = property.getSuperProperties();
	    Collection<OntologyProperty> cleanSuperProperties = new HashSet<OntologyProperty>();

	    for(DocumentableObject superProperty: superProperties){
	    	DocumentableObject _superProperty = register.findDocumentableObject(superProperty.getIdentifier());
	    	if (_superProperty != null){
	    		cleanSuperProperties.add((OntologyProperty) _superProperty);
	    	}
	    }
	    property.setSuperProperties(cleanSuperProperties);
	    

	    Collection<OntologyProperty> subProperties = property.getSubProperties();
	    Collection<OntologyProperty> cleanSubProperties = new HashSet<OntologyProperty>();

	    for(DocumentableObject subProperty: subProperties){
	    	DocumentableObject _subProperty = register.findDocumentableObject(subProperty.getIdentifier());
	    	if (_subProperty != null){
	    		cleanSubProperties.add((OntologyProperty) _subProperty);
	    	}
	    }
	    property.setSubProperties(cleanSubProperties);
	    
	    return null;
	}
    
	public Object visit(OntologyClass clazz) throws TransformerException {
    	logger.debug("Resolving internal references of class "+clazz);
		
    	Collection<OntologyClass> subClasses = clazz.getSubClasses();
	    List<OntologyClass> registersSubClasses = new LinkedList<OntologyClass>();
	    for(OntologyClass subclass:subClasses){
	        OntologyClass registerClass=(OntologyClass)register.findDocumentableObject(subclass.getIdentifier());
	        if(registerClass!=null){
	            registersSubClasses.add(registerClass);
	        }
	    }
	    clazz.setSubClasses(registersSubClasses);

	    Collection<OntologyClass> superClasses = clazz.getSuperClasses();
	    List<OntologyClass> registersSuperClasses = new LinkedList<OntologyClass>();
	    for(OntologyClass superclass:superClasses){
	        OntologyClass registerClass=(OntologyClass)register.findDocumentableObject(superclass.getIdentifier());
	        if(registerClass!=null){
	            registersSuperClasses.add(registerClass);
	        }
	    }
	    clazz.setSuperClasses(registersSuperClasses);
	    
	    return null;
	}

}
