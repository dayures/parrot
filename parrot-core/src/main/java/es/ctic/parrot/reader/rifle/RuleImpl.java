package es.ctic.parrot.reader.rifle;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.servlet.jsp.tagext.TryCatchFinally;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;

import es.ctic.parrot.de.AbstractDocumentableObject;
import es.ctic.parrot.de.AnonymousIdentifier;
import es.ctic.parrot.de.DocumentableOntologicalObject;
import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.OntologyProperty;
import es.ctic.parrot.de.Rule;
import es.ctic.parrot.de.URIIdentifier;
import es.ctic.parrot.transformers.DocumentableObjectVisitor;

public class RuleImpl extends AbstractDocumentableObject implements Rule {
	
	private static final String DC_DATE = "http://purl.org/dc/terms/date";
	private net.sourceforge.rifle.ast.Rule rule;
	private OntModel iriMeta;

    private Identifier identifier;
	
	private static final Logger logger = Logger.getLogger(RuleImpl.class);

	public RuleImpl(net.sourceforge.rifle.ast.Rule rule) {
		this.rule = rule;
		if (rule.getId() == null) {
		    this.identifier = new AnonymousIdentifier();
		} else {
		    this.identifier = new URIIdentifier(rule.getId());
		}
		
		OntModel ontModel = ModelFactory.createOntologyModel();
    	ontModel.add(iriMeta);
    	
    	this.iriMeta = ontModel;
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
    	
    	if (iriMeta.getOntResource(getURI()) != null)
    		label = iriMeta.getOntResource(getURI()).getLabel(locale.toString());
        
        if (label == null) {
            return iriMeta.getOntResource(getURI()).getLabel(null);
        } else {
            return label;
        }
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

	public Collection<OntologyProperty> getReferencedOntologyProperties() {
		return new LinkedList<OntologyProperty>();//FIXME create proper list
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
    		Property dateProp = iriMeta.getProperty(DC_DATE);

    		RDFNode propertyValue = iriMeta.getOntResource(getURI()).getPropertyValue(dateProp);
			
    		if (propertyValue != null && propertyValue.isLiteral()){
				date = propertyValue.asLiteral().getString();
			}
    	}
    	
        return date;
	}

	public List<String> getEditors() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return null;
	}

	public List<String> getContributors() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return null;
	}

	public List<String> getPublishers() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented yet.");
		//return null;
	}


}
