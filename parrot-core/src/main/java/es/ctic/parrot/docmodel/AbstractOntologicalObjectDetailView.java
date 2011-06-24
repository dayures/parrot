package es.ctic.parrot.docmodel;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

/**
 * An implementation of the DetailView interface to serve as a basis for implementing ontological detailed views.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public abstract class AbstractOntologicalObjectDetailView extends AbstractVersionableDetailView {

	private Collection<DocumentableObjectReference> inverseRuleReferences = new HashSet<DocumentableObjectReference>();
	private boolean deprecated;


	/**
	 * Sets the rules that reference this ontological element.
	 * @param rules the rules that reference this ontological element.
	 */
	protected void setInverseRuleReferences(Collection<DocumentableObjectReference> rules) {
		this.inverseRuleReferences = rules;
	}

	/**
	 * Returns the rules that reference this ontological element.
	 * @return the rules that reference this ontological element.
	 */
	public Collection<DocumentableObjectReference> getInverseRuleReferences() {
		return Collections.unmodifiableCollection(inverseRuleReferences);
	}

	/**
	 * Sets the value of the deprecation of this ontological element. 
	 * @param deprecated the value of the deprecation of this ontological element.
	 */
	protected void setDeprecated(boolean deprecated) {
		this.deprecated = deprecated;
	}

	/**
     * Returns <code>true</code> if, and only if, this ontological element is deprecated.
     * @return <code>true</code> if this ontological element is deprecated, otherwise <code>false</code>.
     */
	public boolean isDeprecated() {
		return deprecated;
	}

}
