package es.ctic.parrot;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
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
    
    private final Collection<Input> inputs = new LinkedList<Input>();
    private Locale locale;
    
    /**
     * @param lang Report locale
     */
    public DocumentaryProject(Locale locale) {
        this.setLocale(locale);
    }

    public void addInput(Input input) {
        inputs.add(input);
    }
    
    public Collection<Input> getInputs() {
        return Collections.unmodifiableCollection(inputs);
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

}
