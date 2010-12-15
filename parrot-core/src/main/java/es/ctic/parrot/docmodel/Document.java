package es.ctic.parrot.docmodel;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Document {
	private String title;
	private List<OntologyDetailView> ontologyDetailViews = new LinkedList<OntologyDetailView>();
	private List<OntologyClassDetailView> ontologyClassDetailViews = new LinkedList<OntologyClassDetailView>();
	private List<OntologyPropertyDetailView> ontologyPropertyDetailViews = new LinkedList<OntologyPropertyDetailView>();
    private List<RuleDetailView> ruleDetailViews = new LinkedList<RuleDetailView>();
	private List<OntologyIndividualDetailView> ontologyIndividualDetailViews = new LinkedList<OntologyIndividualDetailView>();
	
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<OntologyDetailView> getOntologyDetailViews() {
		return Collections.unmodifiableList(this.ontologyDetailViews);
	}

	public List<OntologyClassDetailView> getOntologyClassDetailViews() {
		return Collections.unmodifiableList(this.ontologyClassDetailViews);
	}
	
	public List<OntologyPropertyDetailView> getOntologyPropertyDetailViews() {
		return Collections.unmodifiableList(this.ontologyPropertyDetailViews);
	}
	
    public List<RuleDetailView> getRuleDetailViews() {
        return Collections.unmodifiableList(ruleDetailViews);
    }
    
    public List<OntologyIndividualDetailView> getOntologyIndividualDetailViews() {
    	return Collections.unmodifiableList(ontologyIndividualDetailViews);
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
