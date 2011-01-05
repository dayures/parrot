package es.ctic.parrot.reader.jena;

import java.util.Collection;
import java.util.Collections;

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
    
	private Collection<OntologyProperty> superProperties;
	private Collection<OntologyProperty> subProperties;

	public OntProperty getOntProperty(){
		return (OntProperty) getOntResource();
	}
    
    public OntologyPropertyJenaImpl(OntProperty ontProperty, DocumentableObjectRegister register) {
    	super(ontProperty, register);
    }
    
    public Object accept(DocumentableObjectVisitor visitor) {
        return visitor.visit(this);
    }

	public DocumentableObject getDomain() {
    	if (domain == null){
    		OntResource _domain = getOntProperty().getDomain();
    		if(_domain != null && _domain.isClass() && _domain.getURI() != null){
    			domain = new OntologyClassJenaImpl(_domain.asClass(), this.getRegister());
    		}
    	}
    	
    	return domain;

	}
	
	public void setDomain(DocumentableObject domain){
		this.domain=domain;
	}

	public DocumentableObject getRange() {
    	if (range == null){
    		OntResource _range = getOntProperty().getRange();
    		if(_range != null && _range.isClass() && _range.getURI() != null) {
    			range = new OntologyClassJenaImpl(_range.asClass(), this.getRegister());
    		}
    	}
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
	public Collection<OntologyProperty> getSuperProperties() {
		
		if(superProperties == null){
			ExtendedIterator<OntProperty> it = (ExtendedIterator<OntProperty>) getOntProperty().listSuperProperties(true);
			superProperties = ontPropertyIteratorToOntologyPropertyList(it);
		}
		return Collections.unmodifiableCollection(superProperties);
	}

	/**
	 * @param subProperties the subProperties to set
	 */
	public void setSuperProperties(Collection<OntologyProperty> superProperties) {
		this.superProperties = superProperties;
	}

	/**
	 * @return the subProperties
	 */
	public Collection<OntologyProperty> getSubProperties() {
		if(subProperties == null){
			ExtendedIterator<OntProperty> it = (ExtendedIterator<OntProperty>) getOntProperty().listSubProperties(true);
			subProperties = ontPropertyIteratorToOntologyPropertyList(it);
		}

		return Collections.unmodifiableCollection(subProperties);	
	}

	/**
	 * @param subProperties the subProperties to set
	 */
	public void setSubProperties(Collection<OntologyProperty> subProperties) {
		this.subProperties = subProperties;
	}	
}
