package es.ctic.parrot.reader.jena;

import es.ctic.parrot.reader.jena.JenaOWLReader;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

import es.ctic.parrot.de.DocumentableObject;
import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.de.OntologyProperty;
import es.ctic.parrot.transformers.DocumentableObjectVisitor;

public class OntologyPropertyJenaImpl extends AbstractJenaDocumentableObject implements OntologyProperty {
    
	private static final Logger logger = Logger.getLogger(OntologyPropertyJenaImpl.class);
	
	private DocumentableObject domain;
    private DocumentableObject range;
    
	private Collection<DocumentableObject> superProperties = new HashSet<DocumentableObject>();

	public OntProperty getOntProperty(){
		return (OntProperty) getOntResource();
	}
    
    public OntologyPropertyJenaImpl(OntProperty ontProperty, DocumentableObjectRegister register) {
    	super(ontProperty, register);
        
    	OntResource domain=ontProperty.getDomain();
		if(domain!=null && domain.isClass() && domain.getURI()!=null)
			this.domain=new OntologyClassJenaImpl(domain.asClass(), register);
		
		OntResource range=ontProperty.getRange();
		if(range !=null && range.isClass() && range.getURI()!=null)
			this.range=new OntologyClassJenaImpl(range.asClass(), register);
		
		ExtendedIterator<? extends OntProperty> listSuperProperties = ontProperty.listSuperProperties(true);
		while (listSuperProperties.hasNext()){
		    OntProperty jenaSuperProperty = listSuperProperties.next();
		    
		    if (JenaOWLReader.isDomainSpecific(jenaSuperProperty) == true){
		        OntologyProperty superProperty = new OntologyPropertyJenaImpl(jenaSuperProperty, register);
		        if (superProperty != null) {
		        	superProperties.add(superProperty);
		        }
		    }
		}
		
    }
    
    public Object accept(DocumentableObjectVisitor visitor) {
        return visitor.visit(this);
    }

	public DocumentableObject getDomain() {
		return domain;
	}
	
	public void setDomain(DocumentableObject domain){
		this.domain=domain;
	}

	public DocumentableObject getRange() {
		return range;
	}

	public void setRange(DocumentableObject range) {
		this.range=range;
	}

	public int getCardinality() {
		return getOntProperty().getModel().listStatements(null, this.getOntProperty(), (RDFNode) null).toList().size();
	}

	/**
	 * @return the subProperties
	 */
	public Collection<DocumentableObject> getSuperProperties() {
		return Collections.unmodifiableCollection(superProperties);
	}

	/**
	 * @param subProperties the subProperties to set
	 */
	public void setSuperProperties(Collection<DocumentableObject> superProperties) {
		this.superProperties = superProperties;
	}
	
}
