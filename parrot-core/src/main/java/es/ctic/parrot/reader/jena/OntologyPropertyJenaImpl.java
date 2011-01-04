package es.ctic.parrot.reader.jena;

import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.RDFNode;

import es.ctic.parrot.de.DocumentableObject;
import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.de.OntologyProperty;
import es.ctic.parrot.transformers.DocumentableObjectVisitor;

public class OntologyPropertyJenaImpl extends AbstractJenaDocumentableObject implements OntologyProperty {
    private DocumentableObject domain;
    private DocumentableObject range;
    
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


}
