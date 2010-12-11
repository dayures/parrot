package es.ctic.parrot.reader.jena;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntResource;

import es.ctic.parrot.de.AbstractDocumentableObject;
import es.ctic.parrot.de.DocumentableOntologicalObject;
import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.OntologyClass;
import es.ctic.parrot.de.URIIdentifier;

public abstract class AbstractJenaDocumentableObject extends
		AbstractDocumentableObject {
	
	private OntResource ontResource;

	/**
	 * @return the ontResource
	 */
	public OntResource getOntResource() {
		return ontResource;
	}

	public AbstractJenaDocumentableObject(OntResource ontResource) {
		super();
		this.ontResource = ontResource;
	}
	
	public String getURI() {
		return ontResource.getURI();
	}
	
    public String getLabel(Locale locale) {
        String label = ontResource.getLabel(locale.toString());
        if (label == null) {
            return ontResource.getLabel(null);
        } else {
            return label;
        }
    }
    
    public String getComment(Locale locale) {
        return ontResource.getComment(locale.toString()); 
    }

	public Identifier getIdentifier() {
		return new URIIdentifier(ontResource.getURI());
	}

	public String getURIAbbrev(){
		String ns=ontResource.getModel().getNsURIPrefix(ontResource.getNameSpace());
		if(ns!=null){
			return ns+":"+ontResource.getLocalName();
		}
		return ontResource.getURI();
	}
	
	public int compareTo(DocumentableOntologicalObject o) {
		return getURI().compareTo(o.getURI());
	}

	protected Collection<OntologyClass> ontClassIteratorToOntologyClassList(Iterator<OntClass> it) {
		List<OntologyClass> ontologyClassList = new LinkedList<OntologyClass>();
		while(it.hasNext()){
			OntClass superClass=it.next();
			if(superClass.getURI()!=null){
				OntologyClass _class=new OntologyClassJenaImpl(superClass);
				ontologyClassList.add(_class);
			}
		}
		return ontologyClassList;
	}

}
