package es.ctic.parrot.reader.jena;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

import es.ctic.parrot.de.Catalog;
import es.ctic.parrot.de.DocumentableObject;
import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.transformers.DocumentableObjectVisitor;
import es.ctic.parrot.transformers.TransformerException;

public class CatalogJenaImpl extends AbstractJenaDocumentableObject implements Catalog {

   

    private Collection<DocumentableObject> datasets;

    public CatalogJenaImpl(OntResource resource, DocumentableObjectRegister register, OntResourceAnnotationStrategy annotationStrategy) {
        super(resource, register, annotationStrategy);
    }
    
    public Object accept(DocumentableObjectVisitor visitor)
            throws TransformerException {
        return visitor.visit(this);
    }

    public String getKindString() {
        return Kind.CATALOG.toString();
    }

	public String getHomepage() {
		return getAnnotationStrategy().getHomepage(getOntResource());
	}
	
	public void setDatasets(Collection<DocumentableObject> datasets) {
		this.datasets=datasets;
	}
	
	public Collection<DocumentableObject> getDatasets() {
		
		OntModel ontModel = getOntResource().getOntModel();
		Collection <Resource> ds = new HashSet<Resource>();
		
		if(datasets == null){
			
			StmtIterator listStatements = ontModel.listStatements(getOntResource(), ResourceFactory.createProperty(OntResourceAnnotationStrategy.DCAT_DATASET_PROPERTY), (RDFNode) null);
			while (listStatements.hasNext()){
				Statement statement = listStatements.next();
				ds.add(ontModel.getOntResource(statement.getObject().asResource()));
			}
			
			listStatements = ontModel.listStatements(getOntResource(), ResourceFactory.createProperty(OntResourceAnnotationStrategy.DCAT_DERI_DATASET_PROPERTY), (RDFNode) null);
			while (listStatements.hasNext()){
				Statement statement = listStatements.next();
				ds.add(ontModel.getOntResource(statement.getObject().asResource()));
			}
			
			datasets = resourceIteratorToDocumentableObjectList(ds.iterator());
		}
		return Collections.unmodifiableCollection(datasets);
	}
	
	public String getSpatial(){
		return getAnnotationStrategy().getSpatial(getOntResource());
	}
	
	public String getLanguage(){
		return getAnnotationStrategy().getLanguage(getOntResource());
	}
	
}
