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

/**
 * 
 * Visitor that resolve references between ontology elements.
 * <code>OntologyInternalReferenceResolver</code> is an implementation of the Visitor pattern.
 * Please refer to the Gang of Four book of Design Patterns for more details on the Visitor pattern.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class OntologyInternalReferenceResolver extends AbstractDocumentableObjectVisitor {
	
	private final DocumentableObjectRegister register;
	
	private static final Logger logger = Logger.getLogger(OntologyInternalReferenceResolver.class);

	/**
	 * Constructs a ontology internal reference resolver.
	 * @param register the register to set.
	 */
	public OntologyInternalReferenceResolver(DocumentableObjectRegister register){
		this.register=register;
	}

    /**
     * Adds reference from the ontological elements referenced by a property to that property.
     * The references checked are:
     * <ul>
     * <li>range</li>
     * <li>domain</li>
     * <li>super properties</li>
     * <li>sub properties</li>
     * </ul>
     * @param property the property to visit.
     * @return always null. 
     */
	@Override
	public Object visit(OntologyProperty property) throws TransformerException {
    	
		logger.debug("Resolving internal references of property "+property);

    	// set domain (and create an inverse reference)
		DocumentableObject domain = property.getDomain();
	    if(domain != null){
	        domain=register.findDocumentableObject(domain.getIdentifier());
	        if (domain != null){
	        	property.setDomain(domain);
	        	domain.addReference(property);
	        }
	    }
	    
	    
	    // set range (and create an inverse reference)
	    DocumentableObject range = property.getRange();
	    if(range != null){
	        range=register.findDocumentableObject(range.getIdentifier());
	        if (range != null){
	        	property.setRange(range);
	        	range.addReference(property);
	        }
	    }
	    
	    
	    //set superproperties
	    Collection<DocumentableObject> superProperties = property.getSuperProperties();
	    Collection<DocumentableObject> cleanSuperProperties = new HashSet<DocumentableObject>();

	    for(DocumentableObject superProperty: superProperties){
	    	DocumentableObject _superProperty = register.findDocumentableObject(superProperty.getIdentifier());
	    	if (_superProperty != null){
	    		cleanSuperProperties.add(_superProperty);
	    	}
	    }
	    property.setSuperProperties(cleanSuperProperties);

	    
	    //set subproperties
	    Collection<DocumentableObject> subProperties = property.getSubProperties();
	    Collection<DocumentableObject> cleanSubProperties = new HashSet<DocumentableObject>();

	    for(DocumentableObject subProperty: subProperties){
	    	DocumentableObject _subProperty = register.findDocumentableObject(subProperty.getIdentifier());
	    	if (_subProperty != null){
	    		cleanSubProperties.add( _subProperty);
	    	}
	    }
	    property.setSubProperties(cleanSubProperties);
	    
	    return null;
	}
    
    /**
     * Adds reference from the ontological elements referenced by a property to that property.
     * The references checked are:
     * <ul>
     * <li>super classes</li>
     * <li>sub classes</li>
     * </ul>
     * @param clazz the class to visit.
     * @return always null. 
     */
	@Override
	public Object visit(OntologyClass clazz) throws TransformerException {
    	logger.debug("Resolving internal references of class "+clazz);
		
    	// set subclasses
    	Collection<OntologyClass> subClasses = clazz.getSubClasses();
	    List<OntologyClass> registersSubClasses = new LinkedList<OntologyClass>();
	    for(OntologyClass subclass:subClasses){
	        OntologyClass registerClass=(OntologyClass)register.findDocumentableObject(subclass.getIdentifier());
	        if(registerClass!=null){
	            registersSubClasses.add(registerClass);
	        }
	    }
	    clazz.setSubClasses(registersSubClasses);

	    
	    // set superclasses
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
