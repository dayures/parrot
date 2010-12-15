package es.ctic.parrot.reader.rifle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.StmtIterator;

import es.ctic.parrot.de.AbstractDocumentableObject;
import es.ctic.parrot.de.AnonymousIdentifier;
import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.de.DocumentableOntologicalObject;
import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.Rule;
import es.ctic.parrot.de.URIIdentifier;
import es.ctic.parrot.transformers.DocumentableObjectVisitor;

public class RuleImpl extends AbstractDocumentableObject implements Rule {
	
	private static final String DC_PUBLISHER = "http://purl.org/dc/terms/publisher";
	private static final String DC_CONTRIBUTOR = "http://purl.org/dc/terms/contributor";
	private static final String DC_CREATOR = "http://purl.org/dc/terms/creator";
	private static final String DC_DATE = "http://purl.org/dc/terms/date";
	private net.sourceforge.rifle.ast.Rule rule;
	private OntModel iriMeta = ModelFactory.createOntologyModel();

    private Identifier identifier;
	
	private static final Logger logger = Logger.getLogger(RuleImpl.class);
	private DocumentableObjectRegister register;

	public RuleImpl(net.sourceforge.rifle.ast.Rule rule, DocumentableObjectRegister register) {
		this.rule = rule;
		this.register = register;
		if (rule.getId() == null) {
		    this.identifier = new AnonymousIdentifier();
		} else {
		    this.identifier = new URIIdentifier(rule.getId());
		}
		
    	iriMeta.add(rule.getIriMeta());
	}

	public Object accept(DocumentableObjectVisitor visitor) {
		return visitor.visit(this);
	}

	public Identifier getIdentifier() {
		return identifier;
	}
	
	public String getURI() {
		return getIdentifier().toString();
	}
	
    public String getLabel(Locale locale) {
    	
    	String label = null;

    	if (locale !=null) {
    		if (iriMeta.getOntResource(getURI()) != null){
    			label = iriMeta.getOntResource(getURI()).getLabel(locale.toString());
    		}
    	}
    	
        if (label == null) {
            return iriMeta.getOntResource(getURI()).getLabel(null);
        } else {
            return label;
        }
    }
    
    public String getLabel() {
        return this.getLabel(null);
    }    
        
    public String getComment(Locale locale) {
        String comment = null;
    	
    	if (iriMeta.getOntResource(getURI()) != null)
    		comment = iriMeta.getOntResource(getURI()).getComment(locale.toString());
        
        if (comment == null) {
            return iriMeta.getOntResource(getURI()).getComment(null);
        } else {
            return comment;
        }
    }
    

	public Collection<String> getDeclaredVars() {
		return new LinkedList<String>();//FIXME create proper list	
	}
	
	public int compareTo(DocumentableOntologicalObject o) {
		return getURI().compareTo(o.getURI());
	}

	public String getDate() {
        String date = null;
    	
    	if (iriMeta.getOntResource(getURI()) != null){

    		RDFNode propertyValue = iriMeta.getOntResource(getURI()).getPropertyValue(ResourceFactory.createProperty(DC_DATE));
			
    		if (propertyValue != null && propertyValue.isLiteral()){
				date = propertyValue.asLiteral().getString();
			}
    	}
    	
        return date;
	}

	public List<String> getCreators() {
		
		ArrayList<String> creators = new ArrayList<String>();
		StmtIterator it = iriMeta.getOntResource(getURI()).listProperties(ResourceFactory.createProperty(DC_CREATOR));
		while(it.hasNext()){
			creators.add(it.nextStatement().getLiteral().getString());
		}
		return creators;
	}

	public List<String> getContributors() {
		ArrayList<String> contributors = new ArrayList<String>();
		StmtIterator it = iriMeta.getOntResource(getURI()).listProperties(ResourceFactory.createProperty(DC_CONTRIBUTOR));
		while(it.hasNext()){
			contributors.add(it.nextStatement().getLiteral().getString());
		}
		return contributors;
	}

	public List<String> getPublishers() {
		ArrayList<String> publishers = new ArrayList<String>();
		StmtIterator it = iriMeta.getOntResource(getURI()).listProperties(ResourceFactory.createProperty(DC_PUBLISHER));
		while(it.hasNext()){
			publishers.add(it.nextStatement().getLiteral().getString());
		}
		return publishers;
	}
	
	public Collection<DocumentableOntologicalObject> getReferencedOntologicalObjects() {
		
		Set<DocumentableOntologicalObject> referencedOntologicalObjects = new HashSet<DocumentableOntologicalObject>();
		
		for(String uriConst : rule.getUriConsts()){
			URIIdentifier uriIdentifier = new URIIdentifier(uriConst);
			try {
				DocumentableOntologicalObject documentableOntologicalObject = (DocumentableOntologicalObject) register.findDocumentableObject(uriIdentifier);
				if (documentableOntologicalObject != null){ 
					referencedOntologicalObjects.add(documentableOntologicalObject);
				}
			} catch (ClassCastException e){
				// Ignore references to not Ontological objects 
			}
						
		}
		
		return referencedOntologicalObjects;
		
	}


}
