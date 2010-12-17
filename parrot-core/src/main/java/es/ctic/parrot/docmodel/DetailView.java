package es.ctic.parrot.docmodel;

import java.util.Collection;

import es.ctic.parrot.de.Identifier;

public interface DetailView {

	public abstract Identifier getIdentifier();
	public abstract String getAnchor();
	public abstract String getLabel();
	public abstract Collection<String> getDepictions();
	public abstract Collection<String> getVideos();
	
}
