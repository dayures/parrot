package es.ctic.parrot.reader.jena;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.util.iterator.Filter;

/**
 * Filter by namespace.
 * 
 * @author Carlos Tejo Alonso (<a href="http://www.fundacionctic.org">Fundación CTIC</a>)
 * @version 1.0
 * @since 1.0
 *
 */
class FilterByNamespace extends Filter<OntClass> {
	
	private String namespace;
	
	public FilterByNamespace(String namespace) {
		super();
		this.namespace = namespace;
	}

	@Override
	public boolean accept(OntClass o) {
		if (o.getURI() != null)
			return o.getURI().startsWith(this.namespace);
		else
			return false;
	}
}