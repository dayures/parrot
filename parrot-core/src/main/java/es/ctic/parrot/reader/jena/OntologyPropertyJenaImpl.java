package es.ctic.parrot.reader.jena;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.RDFList;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.shared.JenaException;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.vocabulary.OWL2;
import com.hp.hpl.jena.vocabulary.RDF;

import es.ctic.parrot.de.DataType;
import es.ctic.parrot.de.DocumentableObject;
import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.de.OntologyProperty;
import es.ctic.parrot.transformers.DocumentableObjectVisitor;
import es.ctic.parrot.transformers.TransformerException;

/**
 * An implementation of {@link es.ctic.parrot.de.OntologyProperty} coupled to <a href="http://openjena.org/">Jena</a>.
 * 
 * @author Carlos Tejo Alonso (<a href="http://www.fundacionctic.org">Fundación CTIC</a>)
 * @version 1.0
 * @since 1.0
 *
 */
public class OntologyPropertyJenaImpl extends AbstractJenaDocumentableObject implements OntologyProperty {
    
	private static final Logger logger = Logger.getLogger(OntologyPropertyJenaImpl.class);
	
	private Collection<DocumentableObject> domains;
    private Collection<DocumentableObject> ranges;
    
    private Collection<DocumentableObject> complexDomain;

    /**
	 * Kinds of complex domains
	 * 
	 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
	 * @version 1.0
	 * @since 1.0
     */
    public enum complexDomainType {
    	
    	/**
    	 * an union.
    	 */
        UNION("union of"),
       
        /**
         * an intersection.
         */
        INTERSECTION("intersection of"),
        
        /**
         * a complement.
         */
        COMPLEMENT("complement of"),
 
        /**
         * a restriction.
         */
        UNDEFINED("undefined");

        private final String name;
        private complexDomainType(String name) { 
            this.name = name;
        }
        public String toString() {
            return name;
        }
    };

    private Collection<DocumentableObject> complexRange;

    /**
	 * Kinds of complex domains
	 * 
	 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
	 * @version 1.0
	 * @since 1.0
     */
    public enum complexRangeType {
    	
    	/**
    	 * an union.
    	 */
        UNION("union of"),
       
        /**
         * an intersection.
         */
        INTERSECTION("intersection of"),
        
        /**
         * a complement.
         */
        COMPLEMENT("complement of"),
 
        /**
         * a restriction.
         */
        UNDEFINED("undefined");

        private final String name;
        private complexRangeType(String name) { 
            this.name = name;
        }
        public String toString() {
            return name;
        }
    };

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
    	this.setDomains(retrieveDomains());
    	this.setRanges(retrieveRanges());
    }

	public Object accept(DocumentableObjectVisitor visitor) throws TransformerException {
        try{
        	return visitor.visit(this);
        }catch (JenaException e) {
        	throw new TransformerException(e);
        }
    }

	public Collection<DocumentableObject> getDomains() {
    	return domains;
	}
	
	public void setDomains(Collection<DocumentableObject> domains){
		this.domains=domains;
	}
	
	private Collection<DocumentableObject> retrieveDomains() {
		Collection<DocumentableObject> retrievedDomains = new HashSet<DocumentableObject>();
		
		ExtendedIterator<? extends OntResource> it = getOntProperty().listDomain();
		
		while (it.hasNext()){
			OntResource potentialDomain = it.next();
			if(potentialDomain != null && potentialDomain.isClass() && potentialDomain.isAnon() == false){
				retrievedDomains.add(new OntologyClassJenaImpl(potentialDomain.asClass(), this.getRegister(), getAnnotationStrategy()));
			}
		}
		
		return retrievedDomains;
	}
	


	public Collection<DocumentableObject> getRanges() {
		return ranges;
	}
	
	public void setRanges(Collection<DocumentableObject> ranges) {
		this.ranges=ranges;
	}

	private Collection<DocumentableObject> retrieveRanges() {
		Collection<DocumentableObject> retrievedRanges = new HashSet<DocumentableObject>();
		
		ExtendedIterator<? extends OntResource> it = getOntProperty().listRange();
		
		while (it.hasNext()){
			OntResource potentialRange = it.next();
			if(potentialRange != null && potentialRange.isAnon() == false){
	    		if (getOntProperty().isDatatypeProperty()){
	    			retrievedRanges.add(new DataType(potentialRange.getURI()));	
	    		} else {
	    			retrievedRanges.add(new OntologyClassJenaImpl(potentialRange.asClass(), this.getRegister(), getAnnotationStrategy()));
	    		}
			}
		}
		
		return retrievedRanges;

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

	public Collection<DocumentableObject> getComplexDomain() {

		if(complexDomain == null){
			complexDomain = new HashSet<DocumentableObject>();
			OntResource _domain = getOntProperty().getDomain();
			if(_domain != null && _domain.isClass() && isComplexClass(_domain) == true){
				if (_domain.asClass().isUnionClass()){
					ExtendedIterator<? extends OntClass> it = _domain.asClass().asUnionClass().listOperands();
					complexDomain = resourceIteratorToDocumentableObjectList(it);
				}
				if (_domain.asClass().isIntersectionClass()){
					ExtendedIterator<? extends OntClass> it = _domain.asClass().asIntersectionClass().listOperands();
					complexDomain = resourceIteratorToDocumentableObjectList(it);
				}
				if (_domain.asClass().isComplementClass()){
					ExtendedIterator<? extends OntClass> it = _domain.asClass().asComplementClass().listOperands();
					complexDomain = resourceIteratorToDocumentableObjectList(it);
				}
			}
		}

		return Collections.unmodifiableCollection(complexDomain);		
	}
	
	public void setComplexDomain(Collection<DocumentableObject> complexDomain) {
		this.complexRange = complexDomain;
	}

	/**
	 * @return the complexDomainType
	 */
	public String getComplexDomainType() {
		OntResource _domain = getOntProperty().getDomain();
		if(_domain != null && _domain.isClass() && isComplexClass(_domain) == true){
			if (_domain.asClass().isUnionClass()){
				return complexDomainType.UNION.toString();
			}
			if (_domain.asClass().isIntersectionClass()){
				return complexDomainType.INTERSECTION.toString();
			}
			if (_domain.asClass().isComplementClass()){
				return complexDomainType.COMPLEMENT.toString();
			}
			/*
			if (_domain.asClass().isEnumeratedClass()){
				return complexDomainType.ENUMERATED.toString();
			}
			if (_domain.asClass().isRestriction()){
				return complexDomainType.RESTRICTION.toString();
			}
			*/
		}
		return complexDomainType.UNDEFINED.toString();
	}

	public Collection<DocumentableObject> getComplexRange() {

		if(complexRange == null){
			complexRange = new HashSet<DocumentableObject>();
			OntResource _range = getOntProperty().getRange();
			if(_range != null && _range.isClass() && isComplexClass(_range) == true){
				if (_range.asClass().isUnionClass()){
					ExtendedIterator<? extends OntClass> it = _range.asClass().asUnionClass().listOperands();
					complexRange = resourceIteratorToDocumentableObjectList(it);
				}
				if (_range.asClass().isIntersectionClass()){
					ExtendedIterator<? extends OntClass> it = _range.asClass().asIntersectionClass().listOperands();
					complexRange = resourceIteratorToDocumentableObjectList(it);
				}
				if (_range.asClass().isComplementClass()){
					ExtendedIterator<? extends OntClass> it = _range.asClass().asComplementClass().listOperands();
					complexRange = resourceIteratorToDocumentableObjectList(it);
				}
			}
		}

		return Collections.unmodifiableCollection(complexRange);		
	}
	
	public void setComplexRange(Collection<DocumentableObject> complexRange) {
		this.complexRange = complexRange;
	}

	/**
	 * @return the complexDomainType
	 */
	public String getComplexRangeType() {
		OntResource _range = getOntProperty().getRange();
		if(_range != null && _range.isClass() && isComplexClass(_range) == true){
			if (_range.asClass().isUnionClass()){
				return complexRangeType.UNION.toString();
			}
			if (_range.asClass().isIntersectionClass()){
				return complexRangeType.INTERSECTION.toString();
			}
			if (_range.asClass().isComplementClass()){
				return complexRangeType.COMPLEMENT.toString();
			}
			/*
			if (_domain.asClass().isEnumeratedClass()){
				return complexRangeType.ENUMERATED.toString();
			}
			if (_domain.asClass().isRestriction()){
				return complexRangeType.RESTRICTION.toString();
			}
			*/
		}
		return complexDomainType.UNDEFINED.toString();
	}

	private boolean isComplexClass(OntResource ontResource){
		OntClass ontclass = ontResource.asClass();
		if (ontclass.isUnionClass() || 
			ontclass.isIntersectionClass() || 
			ontclass.isComplementClass()) {
			/* 
			 * TODO add future support for
			 * ontclass.isEnumeratedClass() 
			 * ontclass.isRestriction()
			*/
			return true;
		} else { 
			return false;
		}
	}

}
