package es.ctic.parrot.reader.jena;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.RDFList;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.shared.JenaException;
import com.hp.hpl.jena.vocabulary.OWL2;
import com.hp.hpl.jena.vocabulary.RDF;

import es.ctic.parrot.de.DataType;
import es.ctic.parrot.de.DocumentableObject;
import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.de.OntologyProperty;
import es.ctic.parrot.transformers.DocumentableObjectVisitor;
import es.ctic.parrot.transformers.TransformerException;

/**
 * An implementation of the OntologyProperty (documentable element) interface coupled to <a href="http://openjena.org/">Jena</a>.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class OntologyPropertyJenaImpl extends AbstractJenaDocumentableObject implements OntologyProperty {
    
	private static final Logger logger = Logger.getLogger(OntologyPropertyJenaImpl.class);
	
	private DocumentableObject domain;
    private DocumentableObject range;
    
	private Collection<DocumentableObject> superProperties;
	private Collection<DocumentableObject> subProperties;
	private Collection<DocumentableObject> equivalentProperties;
	private Collection<DocumentableObject> disjointProperties;

	private DocumentableObject inverseOf;

	private OntProperty getOntProperty(){
		return getOntResource().asProperty();
	}
    
	/**
	 * Constructs an ontology property.
	 * @param ontProperty the Jena ontProperty to set.
	 * @param register the register to set
	 * @param annotationStrategy the annotation strategy to set.
	 */
    public OntologyPropertyJenaImpl(OntProperty ontProperty, DocumentableObjectRegister register, OntResourceAnnotationStrategy annotationStrategy) {
    	super(ontProperty, register, annotationStrategy);
    	this.setDomain(retrieveDomain());
    	this.setRange(retrieveRange());
    }

	public Object accept(DocumentableObjectVisitor visitor) throws TransformerException {
        try{
        	return visitor.visit(this);
        }catch (JenaException e) {
        	throw new TransformerException(e);
        }
    }

	public DocumentableObject getDomain() {
    	return domain;

	}
	
	public void setDomain(DocumentableObject domain){
		this.domain=domain;
	}
	
	private DocumentableObject retrieveDomain() {
		DocumentableObject retrievedDomain = null;
		
		OntResource _domain = getOntProperty().getDomain();
		if(_domain != null && _domain.isClass() && _domain.isAnon() == false){
			retrievedDomain = new OntologyClassJenaImpl(_domain.asClass(), this.getRegister(), getAnnotationStrategy());
		}
		
		return retrievedDomain;
	}
	


	public DocumentableObject getRange() {
		return range;
	}
	
	public void setRange(DocumentableObject range) {
		this.range=range;
	}

	private DocumentableObject retrieveRange() {
		DocumentableObject retrievedRange = null;

		OntResource _range = getOntProperty().getRange();
		if(_range != null && _range.isAnon() == false) {
    		if (getOntProperty().isDatatypeProperty()){
    			retrievedRange = new DataType(_range.getURI());	
    		} else {
    			retrievedRange = new OntologyClassJenaImpl(_range.asClass(), this.getRegister(), getAnnotationStrategy());
    		}
		}
		
		return retrievedRange;
	}



	public int getOccurrences() {
		return getOntProperty().getModel().listStatements(null, this.getOntProperty(), (RDFNode) null).toList().size();
	}

	/**
	 * Returns the superproperties
	 * @return the superProperties
	 */
	public Collection<DocumentableObject> getSuperProperties() {
		
		if(superProperties == null){
			superProperties = resourceIteratorToDocumentableObjectList(getOntProperty().listSuperProperties(true));
		}
		
		return Collections.unmodifiableCollection(superProperties);
	}

	/**
	 * @param superProperties the superProperties to set
	 */
	public void setSuperProperties(Collection<DocumentableObject> superProperties) {
		this.superProperties = superProperties;
	}

	/**
	 * Returns the subproperties
	 * @return the subProperties
	 */
	public Collection<DocumentableObject> getSubProperties() {
		if(subProperties == null){
			subProperties = resourceIteratorToDocumentableObjectList(getOntProperty().listSubProperties(true));
		}

		return Collections.unmodifiableCollection(subProperties);	
	}

	/**
	 * @param subProperties the subProperties to set
	 */
	public void setSubProperties(Collection<DocumentableObject> subProperties) {
		this.subProperties = subProperties;
	}
	
	public DocumentableObject getInverseOf() {

    	if (inverseOf == null){
    		
    		OntResource _inverseOf = getOntProperty().getInverseOf();
    		if(_inverseOf != null ){
    			inverseOf = new OntologyPropertyJenaImpl (_inverseOf.asProperty(), this.getRegister(), getAnnotationStrategy());
    		} else {
    			_inverseOf = getOntProperty().getInverse();
        		if(_inverseOf != null ){
        			inverseOf = new OntologyPropertyJenaImpl (_inverseOf.asProperty(), this.getRegister(), getAnnotationStrategy());
        		}	
    		}
    	}

    	return inverseOf;

	}

	public boolean isDatatypeProperty() {
		return getOntProperty().isDatatypeProperty();
	}
	
	public boolean isObjectProperty() {
		return getOntProperty().isObjectProperty();
	}

	public boolean isAnnotationProperty() {
		return getOntProperty().hasRDFType(OWL2.AnnotationProperty,true);
	}
	
	public boolean isTransitiveProperty() {
		return getOntProperty().isTransitiveProperty();
	}

	public boolean isSymmetricProperty() {
		return getOntProperty().isSymmetricProperty();
	}

	public boolean isFunctionalProperty() {
		return getOntProperty().isFunctionalProperty();
	}
	
	public boolean isInverseFunctionalProperty() {
		return getOntProperty().isInverseFunctionalProperty();
	}

	public boolean isReflexiveProperty() {
		return getOntProperty().hasRDFType(OWL2.ReflexiveProperty,true);
	}
	
	public boolean isIrreflexiveProperty() {
		return getOntProperty().hasRDFType(OWL2.IrreflexiveProperty,true);
	}
	
	public boolean isAsymmetricProperty(){
		return getOntProperty().hasRDFType(OWL2.AsymmetricProperty,true);
	}

	/**
	 * @param equivalentProperties the equivalentProperties to set
	 */
	public void setEquivalentProperties(Collection<DocumentableObject> equivalentProperties) {
		this.equivalentProperties = equivalentProperties;
	}

	/**
	 * @return the equivalentProperties
	 */
	public Collection<DocumentableObject> getEquivalentProperties() {
		
		OntModel ontModel = getOntProperty().getOntModel();
		Collection <Resource> equivalents = new HashSet<Resource>();

		// Both equivalent: Datatype and Object properties 
		
		if(equivalentProperties == null){
			
			StmtIterator listStatements = ontModel.listStatements(getOntProperty(), OWL2.equivalentProperty, (RDFNode) null);
			while (listStatements.hasNext()){
				Statement statement = listStatements.next();
				equivalents.add(ontModel.getOntResource(statement.getObject().asResource()));
			}
			
			listStatements = ontModel.listStatements(null, OWL2.equivalentProperty, getOntProperty());
			while (listStatements.hasNext()){
				Statement statement = listStatements.next();
				equivalents.add(ontModel.getOntResource(statement.getSubject().asResource()));
			}
			
			equivalentProperties = resourceIteratorToDocumentableObjectList(equivalents.iterator());
		}
		return Collections.unmodifiableCollection(equivalentProperties);
	}

    /**
	 * @return the disjointProperties
	 */
	public Collection<DocumentableObject> getDisjointProperties() {
		
		OntModel ontModel = getOntProperty().getOntModel();
		Collection <Resource> disjoints = new HashSet<Resource>();

		// Both disjoint: Datatype and Object properties
		
		if(disjointProperties == null){
			
			StmtIterator listStatements = ontModel.listStatements(getOntProperty(), OWL2.propertyDisjointWith, (RDFNode) null);
			while (listStatements.hasNext()){
				Statement statement = listStatements.next();
				disjoints.add(ontModel.getOntResource(statement.getObject().asResource()));
			}
			
			listStatements = ontModel.listStatements(null, OWL2.propertyDisjointWith, getOntProperty());
			while (listStatements.hasNext()){
				Statement statement = listStatements.next();
				disjoints.add(ontModel.getOntResource(statement.getSubject()));
			}
			
			listStatements = ontModel.listStatements(null, RDF.type, OWL2.AllDisjointProperties);
			while (listStatements.hasNext()){
				Statement statement = listStatements.next();
				RDFList listDisjointProperties = statement.getSubject().getPropertyResourceValue(OWL2.members).as(RDFList.class);
				if (listDisjointProperties.contains(getOntProperty())){
					Set<RDFNode> rdfNodeSet = listDisjointProperties.iterator().toSet();
					Set<Resource> ontPropertySet = new HashSet<Resource>();
					for(RDFNode node : rdfNodeSet){
						ontPropertySet.add(node.asResource());
					}
					ontPropertySet.remove(getOntProperty());
					disjoints.addAll(ontPropertySet);
				}
				
			}
			
			disjointProperties = resourceIteratorToDocumentableObjectList(disjoints.iterator());
		}
		return Collections.unmodifiableCollection(disjointProperties);
	}

	/**
	 * @param disjointProperties the disjointProperties to set
	 */
	public void setDisjointProperties(
			Collection<DocumentableObject> disjointProperties) {
		this.disjointProperties = disjointProperties;
	}

    public String getKindString() {
        return Kind.ONTOLOGY_PROPERTY.toString();
    }
    
}
