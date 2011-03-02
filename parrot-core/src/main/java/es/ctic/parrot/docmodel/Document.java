package es.ctic.parrot.docmodel;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class Document {
	private String title;
	private final Set<OntologyDetailView> ontologyDetailViews = new HashSet<OntologyDetailView>();
	private final Set<OntologyClassDetailView> ontologyClassDetailViews = new HashSet<OntologyClassDetailView>();
	private final Set<OntologyPropertyDetailView> ontologyPropertyDetailViews = new HashSet<OntologyPropertyDetailView>();
    private final Set<RuleDetailView> ruleDetailViews = new HashSet<RuleDetailView>();
    private final Set<RuleSetDetailView> ruleSetDetailViews = new HashSet<RuleSetDetailView>();
    private final Set<OntologyIndividualDetailView> ontologyIndividualDetailViews = new HashSet<OntologyIndividualDetailView>();
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
	
	public Collection<OntologyDetailView> getOntologyDetailViews() {
		List<OntologyDetailView> listOntologyDetailsViews = new LinkedList<OntologyDetailView>(this.ontologyDetailViews);
		Collections.sort(listOntologyDetailsViews, new DetailViewComparator());
		return listOntologyDetailsViews;
	}

	public Collection<OntologyClassDetailView> getOntologyClassDetailViews() {
		List<OntologyClassDetailView> listOntologyClassDetailViews = new LinkedList<OntologyClassDetailView>(this.ontologyClassDetailViews);
		Collections.sort(listOntologyClassDetailViews, new DetailViewComparator());
		return listOntologyClassDetailViews;
	}
	
	public Collection<OntologyPropertyDetailView> getOntologyPropertyDetailViews() {
		List<OntologyPropertyDetailView> listOntologyPropertyDetailViews = new LinkedList<OntologyPropertyDetailView>(this.ontologyPropertyDetailViews);
		Collections.sort(listOntologyPropertyDetailViews, new DetailViewComparator());
		return listOntologyPropertyDetailViews;
	}
	
    public Collection<RuleDetailView> getRuleDetailViews() {
		List<RuleDetailView> listRuleDetailViews = new LinkedList<RuleDetailView>(this.ruleDetailViews);
		Collections.sort(listRuleDetailViews, new DetailViewComparator());
		return listRuleDetailViews;
    }
    
    public Collection<RuleSetDetailView> getRuleSetDetailViews() {
		List<RuleSetDetailView> listRuleSetDetailViews = new LinkedList<RuleSetDetailView>(this.ruleSetDetailViews);
		Collections.sort(listRuleSetDetailViews, new DetailViewComparator());
		return listRuleSetDetailViews;
    }
    
    public Collection<OntologyIndividualDetailView> getOntologyIndividualDetailViews() {
		List<OntologyIndividualDetailView> listOntologyIndividualDetailViews = new LinkedList<OntologyIndividualDetailView>(this.ontologyIndividualDetailViews);
		Collections.sort(listOntologyIndividualDetailViews, new DetailViewComparator());
		return listOntologyIndividualDetailViews;
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

	// This comparator is not consistent with equals()
	public int compare(DetailView arg0, DetailView arg1) {
		if (arg0.getLabel() != null && arg1.getLabel() != null) {
			return  arg0.getLabel().toLowerCase().compareTo(arg1.getLabel().toLowerCase());
		} else {
			return 0; // FIXME change comparable method
		}
	}
	
}

