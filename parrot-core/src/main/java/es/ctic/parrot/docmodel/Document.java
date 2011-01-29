package es.ctic.parrot.docmodel;

import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.SortedSet;
import java.util.TreeSet;

public class Document {
	private String title;
	private final SortedSet<OntologyDetailView> ontologyDetailViews = new TreeSet<OntologyDetailView>(new DetailViewComparator());
	private final SortedSet<OntologyClassDetailView> ontologyClassDetailViews = new TreeSet<OntologyClassDetailView>(new DetailViewComparator());
	private final SortedSet<OntologyPropertyDetailView> ontologyPropertyDetailViews = new TreeSet<OntologyPropertyDetailView>(new DetailViewComparator());
    private final SortedSet<RuleDetailView> ruleDetailViews = new TreeSet<RuleDetailView>(new DetailViewComparator());
    private final SortedSet<RuleSetDetailView> ruleSetDetailViews = new TreeSet<RuleSetDetailView>(new DetailViewComparator());
    private final SortedSet<OntologyIndividualDetailView> ontologyIndividualDetailViews = new TreeSet<OntologyIndividualDetailView>(new DetailViewComparator());
    private final Glossary glossary;
    
    public Document(Locale locale) {
        this.glossary = new Glossary(locale);
    }
	
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
    
    public SortedSet<RuleSetDetailView> getRuleSetDetailViews() {
        return Collections.unmodifiableSortedSet(ruleSetDetailViews);
    }
    
    public SortedSet<OntologyIndividualDetailView> getOntologyIndividualDetailViews() {
    	return Collections.unmodifiableSortedSet(ontologyIndividualDetailViews);
    }

    public void addRuleDetailView(RuleDetailView details) {
        this.ruleDetailViews.add(details);
    }
    public void addRuleSetDetailView(RuleSetDetailView details) {
        this.ruleSetDetailViews.add(details);
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

    public Glossary getGlossary() {
        return glossary;
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

