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
 * @author CTIC
 *
 */
public class DocumentaryProject {
    
    private final Collection<Input> inputs = new HashSet<Input>();
    private Locale locale;
    
    /** Create a new Documentary project 
     * @param locale A locale (language) to be set 
     */
    public DocumentaryProject(Locale locale) {
        this.setLocale(locale);
    }

    /**
     * Adds the specified input to this set if it is not already present (optional operation).
     * @param input input to be added
     */
    public void addInput(Input input) {
        inputs.add(input);
    }
    
    /**
     * Returns the inputs 
     * @return the collection of inputs
     */
    public Collection<Input> getInputs() {
    	System.err.println("tamaño de inputs: " + inputs.size());
        return Collections.unmodifiableCollection(inputs);
    }

	/** 
	 * Set the locale
	 * @param locale the locale to set
	 */
	private void setLocale(Locale locale) {
		this.locale = locale;
	}

	/**
	 * Returns the locale
	 * @return the locale
	 */
	public Locale getLocale() {
		return locale;
	}


}
