package es.ctic.parrot.reader.jena;

import java.io.IOException;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.xml.sax.SAXParseException;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.ontology.Ontology;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.shared.JenaException;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;

import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.reader.DocumentReader;
import es.ctic.parrot.reader.Input;
import es.ctic.parrot.reader.ReaderException;

/**
 * A reader for OWL, RDF and RDFa input documents that uses <a href="http://www.openjena.org/">Jena</a>. 
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 * 
 */
public class JenaOWLReader implements DocumentReader {
    
    private static final String XHTML = "XHTML";
    private static final String HTML = "HTML";
    private static final Logger logger = Logger.getLogger(JenaOWLReader.class);
    private OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM); // by default it is OntModelSpec.OWL_DL_MEM_RDFS_INF
    private OntResourceAnnotationStrategy annotationStrategy = new OntResourceAnnotationStrategy();
    
    /**
     * Constructs a reader.
     */
    public JenaOWLReader(){ 
    	getOntModel().setStrictMode(false);
    	getOntModel().getDocumentManager().setProcessImports(false); // do NOT the imports
    }

	public void readDocumentableObjects(Input input, DocumentableObjectRegister register) throws IOException, ReaderException {
        
        try {
        	String jenaFormat = getJenaFormat(input);
			logger.debug("Parsing " + jenaFormat + "  file");
        	
        	// Init java-rdfa in jena
			Class.forName("net.rootdev.javardfa.jena.RDFaReader");

	        String base = input.getBase();

        	if (XHTML.equals(jenaFormat) || HTML.equals(jenaFormat)) {
        		logger.debug("Requesting (X)HTML based on the mimetype detected (" + jenaFormat + ")");
        		if (input.isReaderProof()) {
        			ontModel.read(input.openReader(), base == null ? "http://example.org/base#" : base, jenaFormat); // FIXME fix this adhoc url
        		} else {
        			ontModel.read(input.getInputStream(), base == null ? "http://example.org/base#" : base, jenaFormat); // FIXME fix this adhoc url
        		}        			
            } else {
            	logger.debug("Requesting any other type based on the mimetype detected (" + jenaFormat + ")");
            	if (input.isReaderProof()) {
            		ontModel.read(input.openReader(), base, jenaFormat);
            	} else {
            		ontModel.read(input.getInputStream(), base, jenaFormat);
            	}
            }
        	
            loadOntology(ontModel, register);
            loadOntClasses(ontModel, register);
            loadOntProperties(ontModel, register);
            loadOntIndividuals(ontModel, register);
            loadVocabularies(ontModel, register);
            loadDatasets(ontModel, register);
        } catch (JenaException e) {
            if (e.getCause() != null && e.getCause() instanceof SAXParseException) {
                throw new ReaderException(input.getMimeType() + " parse error: " + e.getCause().getMessage(), e);
            } else {
                throw new ReaderException("While reading " + input.getMimeType() + " file", e);
            }
        } catch (ClassNotFoundException e) { // When RDFa Reader is not available
             throw new ReaderException("RDFa not supported", e);
        } catch (RuntimeException e) { // RDFaReader throws a RuntimException when a SAXException is caught
             throw new ReaderException("While reading RDFa file", e);
        }
        
	}
	
	/**
     * Returns the language of the serialization for Jena.
     * @param input the input
     * @return the language of the serialization for Jena.
	 */
	private static String getJenaFormat(Input input) {
	    if ("application/rdf+xml".equals(input.getMimeType())) {
	        return "RDF/XML";
	    } else if ("text/turtle".equals(input.getMimeType())) {
	        return "TURTLE";
	    } else if ("text/n3".equals(input.getMimeType()) || "text/rdf+n3".equals(input.getMimeType())) {
	        return "N3";
	    } else if ("application/xhtml+xml".equals(input.getMimeType())) {
	        return XHTML;
	    } else if ("text/html".equals(input.getMimeType())) {
	        return HTML;
	    } else {
	        return "RDF/XML"; // default
	    }
    }

	/**
     * Register the classes (only domain specified) that are in the model.
     * @param model the model to query.
     * @param register the register.
	 */
    private void loadOntClasses(OntModel model, DocumentableObjectRegister register) {
		
    	// owl:Class
    	Iterator<OntClass> it= model.listNamedClasses();
		while(it.hasNext()){
			OntClass ontclass=it.next();
			if (isDomainSpecific(ontclass.getURI())) {
				OntologyClassJenaImpl docObject=new OntologyClassJenaImpl(ontclass, register, getAnnotationStrategy());
			    register.registerDocumentableObject(docObject);
			}
		}
		
		// rdfs:Class
		ResIterator resIterator= model.listResourcesWithProperty(RDF.type, RDFS.Class);
		while(resIterator.hasNext()){
			Resource resource=resIterator.next();
			if (isDomainSpecific(resource.getURI())) {
				OntClass ontclass = model.createClass(resource.getURI()); // Important. This method add the triple URI rdf:type owl:Class in the model if it is not present.
				OntologyClassJenaImpl docObject=new OntologyClassJenaImpl(ontclass, register, getAnnotationStrategy());
			    register.registerDocumentableObject(docObject);
			}
			
		}		
		
	}
	
	/**
     * Register the properties (only domain specified) that are in the model.
     * @param model the model to query.
     * @param register the register.
	 */
	private void loadOntProperties(OntModel model, DocumentableObjectRegister register) {
	    Iterator<OntProperty> it = model.listAllOntProperties();
	    while (it.hasNext()) {
	        OntProperty ontProperty = it.next();
	        if (isDomainSpecific(ontProperty.getURI())) {
	            OntologyPropertyJenaImpl docObject = new OntologyPropertyJenaImpl(ontProperty, register, getAnnotationStrategy());
	            register.registerDocumentableObject(docObject);	        
	        }
	    }
	}
	
	/**
     * Register the ontologies that are in the model.
     * @param model the model to query.
     * @param register the register.
	 */
	private void loadOntology(OntModel model, DocumentableObjectRegister register) {
	    Iterator<Ontology> it = model.listOntologies();
	    while (it.hasNext()) {
	    	Ontology ontology = it.next();
            OntologyJenaImpl docObject = new OntologyJenaImpl(ontology, register, getAnnotationStrategy());
            register.registerDocumentableObject(docObject);	        
	    }
	}

	/**
     * Register the individuals (only domain specified) that are in the model.
     * @param model the model to query.
     * @param register the register.
	 */
	private void loadOntIndividuals(OntModel model, DocumentableObjectRegister register) {

		// Add the triple (res, rdf:type, owl:thing) to all resources which has an rdf type.
		ResIterator iter = model.listResourcesWithProperty(RDF.type);
        while (iter.hasNext()) {
        	Resource res = iter.next();
        	model.add(res, RDF.type, OWL.Thing);
        }

	    Iterator<Individual> it = model.listIndividuals();
	    while (it.hasNext()) {
	    	Individual individual = it.next();
	    	
	    	if (isClassDomainSpecific(individual) == false) {
	    		logger.debug("Not included individual: " + individual.getURI() +" because is not class domain specific.");
	    	} else {
		    	if (individual.isAnon()){ // is anonymous
	        		logger.debug("Included anonymous individual: " + individual.getId().toString());
	        		OntologyIndividualJenaImpl docObject = new OntologyIndividualJenaImpl(individual, register, getAnnotationStrategy());
		        	register.registerDocumentableObject(docObject);
		    	} else { // has URI (not anonymous)
			    	if (isClassDomainSpecific(individual) == false) {
			    		logger.debug("Not included individual: " + individual.getURI() +" because is not class domain specific.");		    		
			    	} else {
			        	OntologyIndividualJenaImpl docObject = new OntologyIndividualJenaImpl(individual, register, getAnnotationStrategy());
			        	register.registerDocumentableObject(docObject);
		        	}
		        }
	    	}
	    }
	}
	
	private void loadVocabularies(OntModel model, DocumentableObjectRegister register) {
		ExtendedIterator<Individual> vocabularyIterator = model.listIndividuals(ResourceFactory.createResource(VocabularyJenaImpl.VOAF_NS + "Vocabulary"));
		while (vocabularyIterator.hasNext()) {
	        OntResource vocabularyInstance = vocabularyIterator.next();
	        register.registerDocumentableObject(new VocabularyJenaImpl(vocabularyInstance, register, getAnnotationStrategy()));
	    }
	}
	
	private void loadDatasets(OntModel model, DocumentableObjectRegister register) {
		ExtendedIterator<Individual> datasetIterator = model.listIndividuals(ResourceFactory.createResource(DatasetJenaImpl.VOID_NS + "Dataset"));
		while (datasetIterator.hasNext()) {
	        OntResource datasetInstance = datasetIterator.next();
	        register.registerDocumentableObject(new DatasetJenaImpl(datasetInstance, register, getAnnotationStrategy()));
	    }
	}
	
	/**
     * Returns <code>true</code> if, and only if, the URI is domain specified.
     * @param uri the URI.
     * @return <code>true</code> if the URI is domain specified, otherwise <code>false</code>.
	 */
	private static boolean isDomainSpecific(String uri) {
		if (uri != null){
			return !uri.startsWith(RDFS.getURI()) && !uri.startsWith(RDF.getURI()) && !uri.startsWith(OWL.getURI());
		} else 
			return false;
    }
	
    /**
     * Returns <code>true</code> if, and only if, the individual is an instance of a domain specified class.
     * @param individual the individual.
     * @return <code>true</code> if the individual is an instance of a domain specified class, otherwise <code>false</code>.
     */
	private static boolean isClassDomainSpecific(Individual individual) {
    	for(OntClass ontClass : individual.listOntClasses(true).toList()){
   			if (isDomainSpecific(ontClass.getURI())){
   				return true;
    		}
    	}
        return false;
    }

	/**
	 * Sets the annotation strategy.
	 * @param annotationStrategy the annotation strategy to set.
	 */
	public void setAnnotationStrategy(OntResourceAnnotationStrategy annotationStrategy) {
		this.annotationStrategy = annotationStrategy;
	}

	/**
	 * Returns the annotation strategy
	 * @return the annotation strategy
	 */
	public OntResourceAnnotationStrategy getAnnotationStrategy() {
		return annotationStrategy;
	}

	/**
	 * Returns the ontModel.
	 * @return the ontModel.
	 */
	public OntModel getOntModel() {
		return ontModel;
	}
	
}
