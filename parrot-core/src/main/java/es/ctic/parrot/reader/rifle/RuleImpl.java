package es.ctic.parrot.reader.rifle;

import java.util.Collection;
import java.util.Comparator;
import java.util.Locale;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.StmtIterator;

import es.ctic.parrot.de.AbstractDocumentableObject;
import es.ctic.parrot.de.DocumentableObject;
import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.de.DocumentableOntologicalObject;
import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.Label;
import es.ctic.parrot.de.RelatedDocument;
import es.ctic.parrot.de.Rule;
import es.ctic.parrot.de.URIIdentifier;
import es.ctic.parrot.reader.jena.OntResourceAnnotationStrategy;
import es.ctic.parrot.transformers.DocumentableObjectVisitor;
import es.ctic.parrot.transformers.TransformerException;

/**
 * An implementation of the Rule (documentable element) interface.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class RuleImpl extends AbstractDocumentableObject implements Rule {
	
	private net.sourceforge.rifle.ast.Rule rule;
	private OntResource ontResource;
	private DocumentableObject parent;
    private Identifier identifier;
    private OntResourceAnnotationStrategy annotationStrategy;
	
	private static final Logger logger = Logger.getLogger(RuleImpl.class);

	/**
 	 * Constructs a rule.
	 * @param rule the rule.
	 * @param register the register.
	 * @param annotationStrategy the annotation strategy.
	 * @param ontModel the ontModel.
	 */
	public RuleImpl(net.sourceforge.rifle.ast.Rule rule, DocumentableObjectRegister register, OntResourceAnnotationStrategy annotationStrategy, OntModel ontModel) {
		this.setRule(rule);
		this.setAnnotationStrategy(annotationStrategy);
		this.setRegister(register);
		if (rule.getId() == null) {
			// Rule without identifier
		    this.identifier = new RifleAnonymousIdentifier(Integer.toString(rule.getLocalId()));
		} else {
			// Rule with identifier
			this.identifier = new URIIdentifier(rule.getId());
			
			Model auxModel = ModelFactory.createDefaultModel().add(rule.getIriMeta());
			StmtIterator listStatements = auxModel.listStatements(ResourceFactory.createResource(rule.getId()), null, (RDFNode) null);
			ontModel.add(listStatements ); // add metadata ONLY about this rule 
			logger.debug("Added metadata for rule " + rule.getId());
		}
		
    	// REMIND could set resource null
		this.setOntResource(ontModel.getOntResource(getURI()));
	}
	
	/**
	 * Sets the annotation strategy.
	 * @param annotationStrategy the annotation strategy to set.
	 */
	private void setAnnotationStrategy(OntResourceAnnotationStrategy annotationStrategy) {
		this.annotationStrategy = annotationStrategy;
	}

	/**
	 * Returns the annotation strategy.
	 * @return the annotation strategy.
	 */
	public OntResourceAnnotationStrategy getAnnotationStrategy() {
		return annotationStrategy;
	}

	/**
	 * Sets the rule.
	 * @param rule the rule to set.
	 */
	private void setRule(net.sourceforge.rifle.ast.Rule rule) {
		this.rule = rule;
	}

	/**
	 * Returns the rule.
	 * @return the rule.
	 */
	public net.sourceforge.rifle.ast.Rule getRule() {
		return rule;
	}
	
	/**
	 * Sets the ontResource.
	 * @param ontResource the ontResource to set.
	 */
	private void setOntResource(OntResource ontResource) {
		this.ontResource = ontResource;
	}

	/**
	 * Returns the ontResource.
	 * @return the ontResource.
	 */
	public OntResource getOntResource() {
		return ontResource;
	}
	
	/**
	 * Sets the parent element.
	 * @param parent the parent to set.
	 */
	public void setParent(DocumentableObject parent) {
		this.parent = parent;
	}

	/**
	 * Returns the parent
	 * @return the parent
	 */
	public DocumentableObject getParent() {
		return parent;
	}

	public Object accept(DocumentableObjectVisitor visitor) throws TransformerException {
		return visitor.visit(this);
	}

	public Identifier getIdentifier() {
		return identifier;
	}
	
	public String getURI() {
		if (getRule().getId() == null) {
		    return null;
		} else {
			return getIdentifier().toString();
		}	
	}
        
    public String getComment(Locale locale) {
    	return getAnnotationStrategy().getComment(getOntResource(), locale);
    }
    
    public Collection<Label> getLabels(){
   		return getAnnotationStrategy().getLabels(getOntResource(), null);
   	}

    public Collection<Label> getLabels(Locale locale){
   		return getAnnotationStrategy().getLabels(getOntResource(), locale);
   	}

    public String getLabel() {
   		return getLabel(null);
    }

    public String getLabel(Locale locale) {
    	// Anonymous ruleset
    	if (getOntResource() == null){
    		return getKindString() + getIdentifier().toString();
    	} else {
    		return getAnnotationStrategy().getLabel(getOntResource(), locale);
    	}
    }
    
	public Collection<RelatedDocument> getRelatedDocuments(Locale locale) {
        return getAnnotationStrategy().getRelatedDocuments(getOntResource(), locale);
    }
    
	public String getVersion() {
		return getAnnotationStrategy().getVersion(getOntResource());
	}

	public String getDate() {
		return getAnnotationStrategy().getDate(getOntResource());
	}

	public Collection<String> getCreators() {
		return getAnnotationStrategy().getCreators(getOntResource());
	}

	public Collection<String> getContributors() {
		return getAnnotationStrategy().getContributors(getOntResource());
	}
	
	public Collection<String> getPublishers() {
		return getAnnotationStrategy().getPublishers(getOntResource());
	}
	
	public String getRights() {
		return getAnnotationStrategy().getRights(getOntResource());
	}
	
	public String getLicenseLabel() {
		return getAnnotationStrategy().getLicenseLabel(getOntResource());
	}
	
	public int compareTo(DocumentableOntologicalObject o) {
		return getURI().compareTo(o.getURI());
	}

	public Collection<DocumentableOntologicalObject> getReferencedOntologicalObjects() {
		
		Collection<DocumentableOntologicalObject> referencedOntologicalObjects = new TreeSet<DocumentableOntologicalObject>(new DocumentableObjectComparator());
		
		for(String uriConst : getRule().getUriConsts()){
			URIIdentifier uriIdentifier = new URIIdentifier(uriConst);
			try {
				DocumentableOntologicalObject documentableOntologicalObject = (DocumentableOntologicalObject) this.getRegister().findDocumentableObject(uriIdentifier);
				if (documentableOntologicalObject != null){ 
					referencedOntologicalObjects.add(documentableOntologicalObject);
				}
			} catch (ClassCastException e){
				// Ignore references to not Ontological objects 
			}
						
		}
		
		return referencedOntologicalObjects;
		
	}

    public String getKindString() {
        return Kind.RULE.toString();
    }
    
}

class DocumentableObjectComparator implements Comparator<DocumentableObject> {

	public int compare(DocumentableObject arg0, DocumentableObject arg1) {
		if (arg0.getLabel() != null && arg1.getLabel() != null) {
			return arg0.getLabel().toLowerCase().compareTo(arg1.getLabel().toLowerCase());
		} else {
			return 0; // FIXME change comparable method
		}
	}
}
