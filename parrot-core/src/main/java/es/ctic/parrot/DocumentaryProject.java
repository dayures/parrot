package es.ctic.parrot;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;

import es.ctic.parrot.reader.Input;

/**
 * A documentary project represents a unit of work executed by Parrot. It
 * contains the information required by Parrot to generate a document.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class DocumentaryProject {
    
    private final Collection<Input> inputs = new HashSet<Input>();
    private Locale locale;
    private String prologueURL;
    private String appendixURL;

    /** Constructs a new Documentary project with the specified locale. 
     * @param locale A locale (language) to be set.
     */
    public DocumentaryProject(Locale locale) {
        this.setLocale(locale);
    }

    /**
     * Adds the specified input to this set if it is not already present (optional operation).
     * @param input input to be added.
     * @return <code>true</code> if the input has been added. 
     */
    public boolean addInput(Input input) {
        return inputs.add(input);
    }
    
    /**
     * Adds the specified collection of inputs to this set if it is not already present (optional operation).
     * @param collection collection of inputs to be added.
     * @return <code>true</code> if the collection of inputs has been added. 
     */
    public boolean addAllInput(Collection<Input> collection) {
        return inputs.addAll(collection);
    }
    
    /**
     * Returns the inputs .
     * @return the collection of inputs.
     */
    public Collection<Input> getInputs() {
        return Collections.unmodifiableCollection(inputs);
    }

	/** 
	 * Set the locale.
	 * @param locale the locale to set.
	 */
	private void setLocale(Locale locale) {
		this.locale = locale;
	}

	/**
	 * Returns the locale.
	 * @return the locale.
	 */
	public Locale getLocale() {
		return locale;
	}

	public void setPrologueURL(String prologueURL) {
		this.prologueURL = prologueURL;
	}

	public String getPrologueURL() {
		return prologueURL;
	}

	/**
	 * @param appendixURL the appendixURL to set
	 */
	public void setAppendixURL(String appendixURL) {
		this.appendixURL = appendixURL;
	}

	/**
	 * @return the appendixURL
	 */
	public String getAppendixURL() {
		return appendixURL;
	}


}
