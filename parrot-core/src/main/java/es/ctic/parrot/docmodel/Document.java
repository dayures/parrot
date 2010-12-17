package es.ctic.parrot.docmodel;

import java.util.Collections;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class Document {
	private String title;
	private SortedSet<OntologyDetailView> ontologyDetailViews = new TreeSet<OntologyDetailView>(new DetailViewComparator());
	private SortedSet<OntologyClassDetailView> ontologyClassDetailViews = new TreeSet<OntologyClassDetailView>(new DetailViewComparator());
	private SortedSet<OntologyPropertyDetailView> ontologyPropertyDetailViews = new TreeSet<OntologyPropertyDetailView>(new DetailViewComparator());
    private SortedSet<RuleDetailView> ruleDetailViews = new TreeSet<RuleDetailView>(new DetailViewComparator());
	private SortedSet<OntologyIndividualDetailView> ontologyIndividualDetailViews = new TreeSet<OntologyIndividualDetailView>(new DetailViewComparator());
	
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public SortedSet<OntologyDetailView> getOntologyDetailViews() {
		return Collections.unmodifiableSortedSet(this.ontologyDetailViews);
	}

	public SortedSet<OntologyClassDetailView> getOntologyClassDetailViews() {
		return Collections.unmodifiableSortedSet(this.ontologyClassDetailViews);
	}
	
	public SortedSet<OntologyPropertyDetailView> getOntologyPropertyDetailViews() {
		return Collections.unmodifiableSortedSet(this.ontologyPropertyDetailViews);
	}
	
    public SortedSet<RuleDetailView> getRuleDetailViews() {
        return Collections.unmodifiableSortedSet(ruleDetailViews);
    }
    
    public SortedSet<OntologyIndividualDetailView> getOntologyIndividualDetailViews() {
    	return Collections.unmodifiableSortedSet(ontologyIndividualDetailViews);
    }

    public void addRuleDetailView(RuleDetailView detail) {
        this.ruleDetailViews.add(detail);
    }

    public void addOntologyClassDetailView(OntologyClassDetailView details) {
        this.ontologyClassDetailViews.add(details);
    }
    
    public void addOntologyPropertyDetailView(OntologyPropertyDetailView details) {
        this.ontologyPropertyDetailViews.add(details);
    }

    public void addOntologyDetailView(OntologyDetailView details) {
        this.ontologyDetailViews.add(details);
    }

	public void addOntologyIndividualDetailView(OntologyIndividualDetailView details) {
		this.ontologyIndividualDetailViews.add(details);
	}
    
}

class DetailViewComparator implements Comparator<DetailView> {

	public int compare(DetailView arg0, DetailView arg1) {
		if (arg0.getLabel() != null && arg1.getLabel() != null) {
			return arg0.getLabel().toLowerCase().compareTo(arg1.getLabel().toLowerCase());
		} else {
			return 0; // FIXME change comparable method
		}
	}
	
}