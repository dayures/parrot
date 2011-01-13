package es.ctic.parrot.reader.jena;

import java.io.IOException;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.xml.sax.SAXParseException;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.ontology.Ontology;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.shared.JenaException;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;

import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.reader.DocumentReader;
import es.ctic.parrot.reader.Input;
import es.ctic.parrot.reader.ReaderException;

public class JenaOWLReader implements DocumentReader {
    
    private static final String XHTML = "XHTML";
	private static final Logger logger = Logger.getLogger(JenaOWLReader.class);
	
	/* (non-Javadoc)
	 * @see es.ctic.parrot.reader.DocumentReader#readDocumentableObjects(es.ctic.parrot.reader.Input, es.ctic.parrot.de.DocumentableObjectRegister)
	 */
	public void readDocumentableObjects(Input input, DocumentableObjectRegister register) throws IOException, ReaderException {
        OntModel model = ModelFactory.createOntologyModel();
        
        try {
        	logger.debug("Parsing " + getJenaFormat(input) + "  file");
        	
        	// Init java-rdfa in jena
			Class.forName("net.rootdev.javardfa.jena.RDFaReader");

	        String base = input.getBase();

        	if (getJenaFormat(input).equals(XHTML)) {
            	model.read(input.openReader(), base == null ? "http://example.org/base#" : base, getJenaFormat(input)); // FIXME fix this adhoc url
            } else {

            	model.read(input.openReader(), base, getJenaFormat(input));	
            }
            
            loadOntology(model, register);
            loadOntClasses(model, register);
            loadOntProperties(model, register);
            loadOntIndividuals(model, register);
        } catch (JenaException e) {
            if (e.getCause() != null && e.getCause() instanceof SAXParseException) {
                throw new ReaderException(input.getMimeType() + " parse error: " + e.getCause().getMessage(), e);
            } else {
                throw new ReaderException("While reading " + input.getMimeType() + " file", e);
            }
        } catch (ClassNotFoundException e) { // When RDFa Reader is not available
             throw new ReaderException("RDFa not supported", e);
        } catch (RuntimeException e) { // RDFaReader throws a RuntimException when a SAXException is catched
             throw new ReaderException("While reading RDFa file", e);
        }
        
	}

	private static String getJenaFormat(Input input) {
	    if ("application/rdf+xml".equals(input.getMimeType())) {
	        return "RDF/XML";
	    } else if ("text/turtle".equals(input.getMimeType())) {
	        return "TURTLE";
	    } else if ("text/n3".equals(input.getMimeType()) || "text/rdf+n3".equals(input.getMimeType())) {
	        return "N3";
	    } else if ("application/xhtml+xml".equals(input.getMimeType())) {
	        return XHTML;
	    } else {
	        return "RDF/XML"; // default
	    }
    }

    private static void loadOntClasses(OntModel model, DocumentableObjectRegister register) {
		Iterator<OntClass> it= model.listNamedClasses();
		while(it.hasNext()){
			OntClass ontclass=it.next();
			if (isDomainSpecific(ontclass)) {
				OntologyClassJenaImpl docObject=new OntologyClassJenaImpl(ontclass, register);
			    register.registerDocumentableObject(docObject);
			}
		}
	}
	
	private static void loadOntProperties(OntModel model, DocumentableObjectRegister register) {
	    Iterator<OntProperty> it = model.listAllOntProperties();
	    while (it.hasNext()) {
	        OntProperty ontProperty = it.next();
	        if (isDomainSpecific(ontProperty)) {
	            OntologyPropertyJenaImpl docObject = new OntologyPropertyJenaImpl(ontProperty, register);
	            register.registerDocumentableObject(docObject);	        
	        }
	    }
	}
	
	private static void loadOntology(OntModel model, DocumentableObjectRegister register) {
	    Iterator<Ontology> it = model.listOntologies();
	    while (it.hasNext()) {
	    	Ontology ontology = it.next();
            OntologyJenaImpl docObject = new OntologyJenaImpl(ontology, register);
            register.registerDocumentableObject(docObject);	        
	    }
	}

	private static void loadOntIndividuals(OntModel model, DocumentableObjectRegister register) {
	    Iterator<Individual> it = model.listIndividuals();
	    while (it.hasNext()) {
	    	Individual individual = it.next();
	    	if (individual.isAnon()){
        		logger.debug("Included anonymous individual: " + individual.getId().toString());
        		OntologyIndividualJenaImpl docObject = new OntologyIndividualJenaImpl(individual, register);
	        	register.registerDocumentableObject(docObject);
	    	}
	    	else {
		    	if (isDomainSpecific(individual) && isClassDomainSpecific(individual)) {
		        	OntologyIndividualJenaImpl docObject = new OntologyIndividualJenaImpl(individual, register);
		        	register.registerDocumentableObject(docObject);
		        } else {
	        		logger.debug("Not included individual: " + individual.getURI() +" because is not domain specific.");
	        	}
	        }
	    }
	}
	
	protected static boolean isDomainSpecific(OntResource ontResource) {
        
       	assert ! ontResource.isAnon() : "Tried to check a domain specific for a blank node";//"this check should be done before"

       	String uri = ontResource.getURI();
       	return !uri.startsWith(RDFS.getURI()) && !uri.startsWith(RDF.getURI()) && !uri.startsWith(OWL.getURI());
    }

	private static boolean isClassDomainSpecific(Individual individual) {
    	for(OntClass ontClass : individual.listOntClasses(true).toList()){
   			if (isDomainSpecific(ontClass)){
   				return true;
    		}
    	}
        return false;
    }

}
