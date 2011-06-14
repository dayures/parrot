package es.ctic.parrot.de;

import java.util.Collection;

import com.hp.hpl.jena.ontology.OntResource;

import es.ctic.parrot.reader.jena.OntResourceAnnotationStrategy;

/**
 * An implementation of the Versionable interface..
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public abstract class AbstractVersionable extends AbstractDocumentableObject implements Versionable{

    private OntResourceAnnotationStrategy annotationStrategy;
	private OntResource ontResource;

	/**
	 * Sets the ontResource.
	 * @param ontResource the ontResource to set.
	 */
	protected void setOntResource(OntResource ontResource) {
		this.ontResource = ontResource;
	}

	/**
	 * Sets the annotation strategy.
	 * @param annotationStrategy the annotation strategy to set.
	 */
	protected void setAnnotationStrategy(OntResourceAnnotationStrategy annotationStrategy) {
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
	 * Returns the ontResource.
	 * @return the ontResource.
	 */
	public OntResource getOntResource() {
		return ontResource;
	}
	
	
	public String getDate() {
		return getAnnotationStrategy().getDate(getOntResource());
	}
    
	public String getVersion() {
		return getAnnotationStrategy().getVersion(getOntResource());
	}

	public String getRights() {
		return getAnnotationStrategy().getRights(getOntResource());
	}
	
	public String getLicenseLabel() {
		return getAnnotationStrategy().getLicenseLabel(getOntResource());
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
	
    public Collection<Agent> getCreatorAgents() {
    	return getAnnotationStrategy().getCreatorAgents(getOntResource());
    }

    public Collection<Agent> getContributorAgents() {
    	return getAnnotationStrategy().getContributorAgents(getOntResource());
    }

    public Collection<Agent> getPublisherAgents() {
    	return getAnnotationStrategy().getPublisherAgents(getOntResource());
    }

}
