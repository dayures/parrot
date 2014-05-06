package es.ctic.parrot.reader.rifle;

import es.ctic.parrot.reader.DocumentReader;
import es.ctic.parrot.reader.jena.JenaOWLReader;

/**
 * An implementation of the {@link es.ctic.parrot.reader.DocumentReader} to serve as a basis for implementing various <a href="https://bitbucket.org/fundacionctic/rifle">RIFLE</a> readers.
 * 
 * @author Carlos Tejo Alonso (<a href="http://www.fundacionctic.org">Fundación CTIC</a>)
 * @version 1.0
 * @since 1.0
 *
 */
public abstract class AbstractRifleReader implements DocumentReader {

	private RIFImportResolver importResolver;
	private JenaOWLReader ontologyReader;


	/**
	 * @return the importResolver
	 */
	protected RIFImportResolver getRIFImportResolver() {
		return importResolver;
	}

	/**
	 * @param importResolver the importResolver to set
	 */
	protected void setRIFImportResolver(RIFImportResolver importResolver) {
		this.importResolver = importResolver;
	}

	/**
	 * @return the ontologyReader
	 */
	protected JenaOWLReader getOntologyReader() {
		return ontologyReader;
	}

	/**
	 * @param ontologyReader the ontologyReader to set
	 */
	protected void setOntologyReader(JenaOWLReader ontologyReader) {
		this.ontologyReader = ontologyReader;
	}



}