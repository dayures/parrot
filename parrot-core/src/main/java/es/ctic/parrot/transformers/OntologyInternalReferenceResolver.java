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
 * @author Carlos Tejo Alonso (<a href="http://www.fundacionctic.org">Fundación CTIC</a>)
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
     * Link ontology properties to ontological elements and viceversa, using the register.
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

	    // link domain to the registered documentable object (and create the inverse reference)
	    DocumentableObject domain = property.getDomain();
	    if ( (domain != null) && (register.containsIdentifier(domain.getIdentifier())) ){
	    	DocumentableObject domainInRegister = register.findDocumentableObject(domain.getIdentifier());
        	property.setDomain(domainInRegister); // link to a register Documentable Object
        	domainInRegister.addReference(property);
	    }
	    
	    
	    // link range to the registered documentable object (and create the inverse reference)
	    DocumentableObject range = property.getRange();
	    if ( (range != null) && (register.containsIdentifier(range.getIdentifier())) ){
	    	DocumentableObject rangeInRegister = register.findDocumentableObject(range.getIdentifier());
        	property.setRange(rangeInRegister); // link to a register Documentable Object
        	rangeInRegister.addReference(property);
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
     * Link ontology classes to ontological elements and viceversa, using the register.
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
    	Collection<DocumentableObject> subClasses = clazz.getSubClasses();
	    List<DocumentableObject> _subClasses = new LinkedList<DocumentableObject>();
	    for(DocumentableObject subclass:subClasses){
	    	DocumentableObject registerClass = register.getDocumentableObject(subclass.getIdentifier());
            _subClasses.add(registerClass);
	    }
	    clazz.setSubClasses(_subClasses);

	    
	    // set superclasses
	    Collection<DocumentableObject> superClasses = clazz.getSuperClasses();
	    List<DocumentableObject> _superClasses = new LinkedList<DocumentableObject>();
	    for(DocumentableObject superclass:superClasses){
	    	DocumentableObject registerClass = register.getDocumentableObject(superclass.getIdentifier());
            _superClasses.add(registerClass);
	    }
	    clazz.setSuperClasses(_superClasses);
	    
	    return null;
	}

}
