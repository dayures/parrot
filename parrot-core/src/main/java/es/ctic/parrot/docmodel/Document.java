package es.ctic.parrot.docmodel;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * 
 * A document is a group of detailed views that will be used by a generator.
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class Document {
	private String title;
	private final Set<OntologyDetailView> ontologyDetailViews = new HashSet<OntologyDetailView>();
	private final Set<OntologyClassDetailView> ontologyClassDetailViews = new HashSet<OntologyClassDetailView>();
	private final Set<OntologyPropertyDetailView> ontologyPropertyDetailViews = new HashSet<OntologyPropertyDetailView>();
    private final Set<RuleDetailView> ruleDetailViews = new HashSet<RuleDetailView>();
    private final Set<RuleSetDetailView> ruleSetDetailViews = new HashSet<RuleSetDetailView>();
    private final Set<OntologyIndividualDetailView> ontologyIndividualDetailViews = new HashSet<OntologyIndividualDetailView>();
    private final Glossary glossary;
    
    /**
     * Constructs a document using the given locale.
     * @param locale the locale.
     */
    public Document(Locale locale) {
        this.glossary = new Glossary(locale);
    }
	
    /**
     * Returns the title of this document.
     * @return the title of this document.
     */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Set the title.
	 * @param title the title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Returns the sorted collection of ontology views for this document. Elements are sorted alphabetically by label.
	 * @return the sorted collection of ontology views for this document.
	 */
	public Collection<OntologyDetailView> getOntologyDetailViews() {
		List<OntologyDetailView> listOntologyDetailsViews = new LinkedList<OntologyDetailView>(this.ontologyDetailViews);
		Collections.sort(listOntologyDetailsViews, new DetailViewComparator());
		return listOntologyDetailsViews;
	}

	/**
	 * Returns the sorted collection of class views for this document. Elements are sorted alphabetically by label.
	 * @return the sorted collection of class views for this document.
	 */
	public Collection<OntologyClassDetailView> getOntologyClassDetailViews() {
		List<OntologyClassDetailView> listOntologyClassDetailViews = new LinkedList<OntologyClassDetailView>(this.ontologyClassDetailViews);
		Collections.sort(listOntologyClassDetailViews, new DetailViewComparator());
		return listOntologyClassDetailViews;
	}

	/**
	 * Returns the sorted collection of property views for this document. Elements are sorted alphabetically by label.
	 * @return the sorted collection of property views for this document.
	 */
	public Collection<OntologyPropertyDetailView> getOntologyPropertyDetailViews() {
		List<OntologyPropertyDetailView> listOntologyPropertyDetailViews = new LinkedList<OntologyPropertyDetailView>(this.ontologyPropertyDetailViews);
		Collections.sort(listOntologyPropertyDetailViews, new DetailViewComparator());
		return listOntologyPropertyDetailViews;
	}
	
	/**
	 * Returns the sorted collection of rule views for this document. Elements are sorted alphabetically by label.
	 * @return the sorted collection of rule views for this document.
	 */
    public Collection<RuleDetailView> getRuleDetailViews() {
		List<RuleDetailView> listRuleDetailViews = new LinkedList<RuleDetailView>(this.ruleDetailViews);
		Collections.sort(listRuleDetailViews, new DetailViewComparator());
		return listRuleDetailViews;
    }
    
	/**
	 * Returns the sorted collection of rule set views for this document. Elements are sorted alphabetically by label.
	 * @return the sorted collection of rule set views for this document.
	 */
    public Collection<RuleSetDetailView> getRuleSetDetailViews() {
		List<RuleSetDetailView> listRuleSetDetailViews = new LinkedList<RuleSetDetailView>(this.ruleSetDetailViews);
		Collections.sort(listRuleSetDetailViews, new DetailViewComparator());
		return listRuleSetDetailViews;
    }
    
	/**
	 * Returns the sorted collection of individual views for this document. Elements are sorted alphabetically by label.
	 * @return the sorted collection of individual views for this document.
	 */
    public Collection<OntologyIndividualDetailView> getOntologyIndividualDetailViews() {
		List<OntologyIndividualDetailView> listOntologyIndividualDetailViews = new LinkedList<OntologyIndividualDetailView>(this.ontologyIndividualDetailViews);
		Collections.sort(listOntologyIndividualDetailViews, new DetailViewComparator());
		return listOntologyIndividualDetailViews;
    }

    /** 
     * Adds a given detailed ontology view into this document.
     * @param details the detailed ontology view.
     */
    public void addOntologyDetailView(OntologyDetailView details) {
        this.ontologyDetailViews.add(details);
    }
    
    /** 
     * Adds a given detailed class view into this document.
     * @param details the detailed class view.
     */
    public void addOntologyClassDetailView(OntologyClassDetailView details) {
        this.ontologyClassDetailViews.add(details);
    }
    
    /** 
     * Adds a given detailed property view into this document.
     * @param details the detailed property view.
     */
    public void addOntologyPropertyDetailView(OntologyPropertyDetailView details) {
        this.ontologyPropertyDetailViews.add(details);
    }
    
    /** 
     * Adds a given detailed individual view into this document.
     * @param details the detailed individual view.
     */
	public void addOntologyIndividualDetailView(OntologyIndividualDetailView details) {
		this.ontologyIndividualDetailViews.add(details);
	}
	
    /** 
     * Adds a given detailed rule view into this document.
     * @param details the detailed rule view.
     */
    public void addRuleDetailView(RuleDetailView details) {
        this.ruleDetailViews.add(details);
    }
    
    /** 
     * Adds a given detailed rule set view into this document.
     * @param details the detailed rule set view.
     */
    public void addRuleSetDetailView(RuleSetDetailView details) {
        this.ruleSetDetailViews.add(details);
    }

	/**
	 * Returns the glossary.
	 * @return the glossary.
	 */
    public Glossary getGlossary() {
        return glossary;
    }
    
}
/**
 * Compares alphabetically by label of each <code>DetailView</code> 
 *
 */
class DetailViewComparator implements Comparator<DetailView> {

	// This comparator is not consistent with equals()
	public int compare(DetailView arg0, DetailView arg1) {
		if (arg0.getLabel() != null && arg1.getLabel() != null) {
			return  arg0.getLabel().toLowerCase().compareTo(arg1.getLabel().toLowerCase());
		} else {
			return 0; // FIXME change comparable method
		}
	}
	
}

