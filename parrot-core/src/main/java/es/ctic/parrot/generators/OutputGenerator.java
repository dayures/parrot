package es.ctic.parrot.generators;

import es.ctic.parrot.docmodel.Document;
import es.ctic.parrot.project.Profile;

/**
 * Implementations of this interface must transform the documentary model
 * into actual output.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public interface OutputGenerator {
   
    /**
     * Generates output for the documentary model.
     * 
     * @param document The documentary model.
     * @param profile The end user profile.
     */
    public abstract void generateOutput(Document document, Profile profile);

}
