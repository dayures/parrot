package es.ctic.parrot.reader.jena;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.util.iterator.Filter;

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