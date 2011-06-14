package es.ctic.parrot.de;

import java.util.Locale;

/**
 * A label.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class Label {
	
	private static final String SKOS_XL_PREF_LABEL = "http://www.w3.org/2008/05/skos-xl#prefLabel";
	private static final String SKOS_CORE_PREF_LABEL = "http://www.w3.org/2004/02/skos/core#prefLabel";

	private static final String SKOS_XL_ALT_LABEL = "http://www.w3.org/2008/05/skos-xl#altLabel";
	private static final String SKOS_CORE_ALT_LABEL = "http://www.w3.org/2004/02/skos/core#altLabel";

	private String qualifier;
	private String uri;
	private String text;
	private Locale locale;
	
	/**
	 * Constructs a label (Suppress default constructor for noninstantiability).
	 */
	public Label(){
	}
	
	/**
	 * Returns the qualifier.
	 * @return the qualifier.
	 */
	public String getQualifier() {
		return qualifier;
	}
	
	/**
	 * Sets the qualifier.
	 * @param qualifier the qualifier to set.
	 */
	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}
	
	/**
	 * Returns the URI.
	 * @return the URI.
	 */
	public String getUri() {
		return uri;
	}
	
	/**
	 * Sets the URI.
	 * @param uri the URI to set.
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	/**
	 * Returns the text.
	 * @return the text.
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * Sets the text.
	 * @param text the text to set.
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * Returns the locale.
	 * @return the locale.
	 */
	public Locale getLocale() {
		return locale;
	}
	
	/**
	 * Sets the locale.
	 * @param locale the locale to set
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	@Override
	public String toString(){
		return text;
		
	}
	
	/**
	 * Returns <code>true</code> if the label is a preferred label, otherwise <code>false</code>.
	 * A preferred label has as URI:
	 * <ul>
	 * <li>http://www.w3.org/2008/05/skos-xl#prefLabel</li>
	 * <li>http://www.w3.org/2004/02/skos/core#prefLabel</li>
	 * </ul>
	 * @return <code>true</code> if the label is a preferred label, otherwise <code>false</code>
	 */
	public boolean isPrefLabel() {
		if (this.getQualifier().equals(SKOS_XL_PREF_LABEL) || this.getQualifier().equals(SKOS_CORE_PREF_LABEL)){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns <code>true</code> if the label is a alternative label, otherwise <code>false</code>.
	 * A alternative label has as URI:
	 * <ul>
	 * <li>http://www.w3.org/2008/05/skos-xl#altLabel</li>
	 * <li>http://www.w3.org/2004/02/skos/core#altLabel</li>
	 * </ul>
	 * @return <code>true</code> if the label is a alternative label, otherwise <code>false</code>
	 */
	public boolean isAltLabel() {
		if (this.getQualifier().equals(SKOS_XL_ALT_LABEL) || this.getQualifier().equals(SKOS_CORE_ALT_LABEL)){
			return true;
		} else {
			return false;
		}
	}

	
}
