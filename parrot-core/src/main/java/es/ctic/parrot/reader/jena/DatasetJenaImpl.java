package es.ctic.parrot.reader.jena;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

import es.ctic.parrot.de.Dataset;
import es.ctic.parrot.de.Distribution;
import es.ctic.parrot.de.DocumentableObject;
import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.transformers.DocumentableObjectVisitor;
import es.ctic.parrot.transformers.TransformerException;

public class DatasetJenaImpl extends AbstractJenaDocumentableObject implements Dataset {

    public static final String VOID_NS = "http://rdfs.org/ns/void#";
    public static final String DCAT_NS = "http://www.w3.org/ns/dcat#";
    public static final String DCAT_DERI_NS = "http://vocab.deri.ie/dcat#";
    
	private static final String DCAT_DATASET_PROPERTY = "http://www.w3.org/ns/dcat#dataset";
	private static final String DCAT_DERI_DATASET_PROPERTY = "http://vocab.deri.ie/dcat#dataset";

	private static final String DCAT_DISTRIBUTION_PROPERTY = "http://www.w3.org/ns/dcat#distribution";
	private static final String DCAT_DERI_DISTRIBUTION_PROPERTY = "http://vocab.deri.ie/dcat#distribution";

    private static final Logger logger = Logger.getLogger(DatasetJenaImpl.class);

	
    private Collection<DocumentableObject> catalogs;
    private Collection<Distribution> distributions = new HashSet<Distribution>();

    
    public DatasetJenaImpl(OntResource resource, DocumentableObjectRegister register, OntResourceAnnotationStrategy annotationStrategy) {
        super(resource, register, annotationStrategy);
        setDistributions();
    }
    
    public Object accept(DocumentableObjectVisitor visitor)
            throws TransformerException {
        return visitor.visit(this);
    }

    public String getKindString() {
        return Kind.DATASET.toString();
    }

	public String getdataDump() {
		return getAnnotationStrategy().getDataDump(getOntResource());
	}

	public String getSparqlEndpoint() {
		return getAnnotationStrategy().getSparqlEndpoint(getOntResource());
	}

	public String getHomepage() {
		return getAnnotationStrategy().getHomepage(getOntResource());
	}
	
	public Collection<String> getVocabularies() {
		return getAnnotationStrategy().getVocabularies(getOntResource());
	}
	
	public String getDcIdentifier(){
		return getAnnotationStrategy().getDcIdentifier(getOntResource());
	}
	
	public String getLandingPage(){
		return getAnnotationStrategy().getLandingPage(getOntResource());
	}
	
	public Collection<String> getKeywords() {
		return getAnnotationStrategy().getKeywords(getOntResource());
	}
	
	public void setCatalogs(Collection<DocumentableObject> catalogs) {
		this.catalogs=catalogs;
	}
	
	public Collection<DocumentableObject> getCatalogs() {
		
		OntModel ontModel = getOntResource().getOntModel();
		Collection <Resource> cs = new HashSet<Resource>();
		
		if(catalogs == null){
			
			StmtIterator listStatements = ontModel.listStatements(null, ResourceFactory.createProperty(DCAT_DATASET_PROPERTY), getOntResource());
			while (listStatements.hasNext()){
				Statement statement = listStatements.next();
				cs.add(ontModel.getOntResource(statement.getSubject().asResource()));
			}
			
			listStatements = ontModel.listStatements(null, ResourceFactory.createProperty(DCAT_DERI_DATASET_PROPERTY), getOntResource());
			while (listStatements.hasNext()){
				Statement statement = listStatements.next();
				cs.add(ontModel.getOntResource(statement.getSubject().asResource()));
			}
			
			catalogs = resourceIteratorToDocumentableObjectList(cs.iterator());
		}
		
		logger.debug(getOntResource()+" is in ("+catalogs.size()+") catalogs");
		
		return Collections.unmodifiableCollection(catalogs);
	}
	
	public String getSpatial(){
		return getAnnotationStrategy().getSpatial(getOntResource());
	}
	
	public Collection<Distribution> getDistributions() {
		return Collections.unmodifiableCollection(distributions);
	}

	public void setDistributions() {
	
		OntModel ontModel = getOntResource().getOntModel();
		Collection <OntResource> cs = new HashSet<OntResource>();
	
		StmtIterator listStatements = ontModel.listStatements(getOntResource(), ResourceFactory.createProperty(DCAT_DISTRIBUTION_PROPERTY), (RDFNode) null);
		while (listStatements.hasNext()){
			Statement statement = listStatements.next();
			cs.add(ontModel.getOntResource(statement.getObject().asResource()));
		}
		
		listStatements = ontModel.listStatements(getOntResource(), ResourceFactory.createProperty(DCAT_DERI_DISTRIBUTION_PROPERTY), (RDFNode) null);
		while (listStatements.hasNext()){
			Statement statement = listStatements.next();
			cs.add(ontModel.getOntResource(statement.getObject().asResource()));
		}	
		
		logger.debug(getOntResource()+" has ("+cs.size()+") distributions");
		
		for (OntResource resource : cs) {
		    DistributionJenaImpl distributionJenaImpl = new DistributionJenaImpl(resource, getRegister(), getAnnotationStrategy());
		    getRegister().registerDocumentableObject(distributionJenaImpl);
		    distributions.add(distributionJenaImpl);		    
		}
	    
	}


}
