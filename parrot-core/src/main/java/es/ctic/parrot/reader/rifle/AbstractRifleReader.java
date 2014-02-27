package es.ctic.parrot.reader.rifle;

import es.ctic.parrot.reader.DocumentReader;
import es.ctic.parrot.reader.jena.JenaOWLReader;

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