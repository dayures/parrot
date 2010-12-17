package es.ctic.parrot.generators;

import es.ctic.parrot.docmodel.Document;

/**
 * Implementations of this interface must transform the documentary model
 * into actual output.
 * 
 */
public interface OutputGenerator {
    
    /**
     * Generate output for the documentary model
     * 
     * @param document
     */
    public void generateOutput(Document document);

}
