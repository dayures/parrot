package es.ctic.parrot.docmodel;

import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.Ontology;

public class OntologyDetailView extends AbstractOntologicalObjectDetailView implements DetailView{
	
    private static final Logger logger = Logger.getLogger(OntologyDetailView.class);

	private String version;
	private List<String> editors;
	private List<String> contributors;
	private String preferredPrefix;
	private String date;
	
	private OntologyDetailView(){
		logger.debug("Created " + this.getClass());
	}
	
	public void setVersion(String version) {
		this.version = version;
	}

	public String getVersion() {
		return version;
	}

	public void setEditors(List<String> editors) {
		this.editors = editors;
	}

	public List<String> getEditors() {
		return editors;
	}

	public void setPreferredPrefix(String preferredPrefix) {
		this.preferredPrefix = preferredPrefix;
	}

	public String getPreferredPrefix() {
		return preferredPrefix;
	}

	public void setContributors(List<String> contributors) {
		this.contributors = contributors;
	}

	public List<String> getContributors() {
		return contributors;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}
	
    public static OntologyDetailView createFromOntology(Ontology object, Locale locale) {
    	
	    OntologyDetailView details = new OntologyDetailView();
		
	    details.setUri(object.getURI());
		details.setLabel(object.getLabel(locale));
		details.setComment(object.getComment(locale));
		details.setVersion(object.getVersion());
		details.setEditors(object.getEditors());
		details.setDate(object.getDate());
		details.setContributors(object.getContributors());
		details.setPreferredPrefix(object.getPreferredPrefix());
		details.setLabels(object.getLabels());
		details.setRelatedDocuments(object.getRelatedDocuments(locale));
		
		details.setAnchor(object.getLocalName());
		details.setIdentifier(object.getIdentifier());
		
		return details;

    }


}
