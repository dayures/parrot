package es.ctic.parrot.de;

import java.util.Collection;
import java.util.List;

/**
 * A rule to be documented by Parrot. 
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 * 
 */
public interface Rule extends DocumentableObject {

    public abstract Collection<DocumentableOntologicalObject> getReferencedOntologicalObjects();

    // public Collection<String> getDeclaredVars();
    
	public abstract String getDate();
	public abstract List<String> getCreators();
	public abstract List<String> getContributors();
	public abstract List<String> getPublishers();
	
	public abstract DocumentableObject getParent();
    
}
