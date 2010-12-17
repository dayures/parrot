package es.ctic.parrot.transformers;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import es.ctic.parrot.de.DocumentableObject;
import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.de.OntologyClass;
import es.ctic.parrot.de.OntologyProperty;

public class OntologyInternalReferenceResolver extends
		AbstractDocumentableObjectVisitor {
	private final DocumentableObjectRegister register;

	public OntologyInternalReferenceResolver(DocumentableObjectRegister register){
		this.register=register;
	}
	
	public Object visit(OntologyProperty property) {
	    DocumentableObject domain = property.getDomain();
	    if(domain!=null){
	        domain=register.findDocumentableObject(domain.getIdentifier());
	        property.setDomain(domain);
	    }
	    DocumentableObject range= property.getRange();
	    if(range!=null){
	        range=register.findDocumentableObject(range.getIdentifier());
	        property.setRange(range);
	    }
	    return null;
	}
    
	public Object visit(OntologyClass clazz){
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
