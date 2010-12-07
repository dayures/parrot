package es.ctic.parrot;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import es.ctic.parrot.reader.Input;

/**
 * A documentary project represents a unit of work executed by Parrot. It
 * contains the information required by Parrot to generate a document.
 * 
 * @author CTIC
 *
 */
public class DocumentaryProject {
    
    private Collection<Input> inputs = new LinkedList<Input>();
    private OutputStream out;
    private InputStream template;
    private String lang;
    
    /**
     * @param template The template for the output report
     * @param out Stream where the output will be written
     * @param lang Report locale, e.g., "en".
     */
    public DocumentaryProject(InputStream template, OutputStream out, String lang) {
        this.out = out;
        this.template = template;
        this.lang=lang;
    }

    public void addInput(Input input) {
        inputs.add(input);
    }
    
    public Collection<Input> getInputs() {
        return Collections.unmodifiableCollection(inputs);
    }

    public OutputStream getOutputStream() {
        return out;
    }
    
    public InputStream getTemplate() {
        return template;
    }

	public String getLang() {
		return lang;
	}

}
