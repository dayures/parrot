package es.ctic.parrot.generators;

import es.ctic.parrot.docmodel.Document;

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
	 * User profiles in Parrot.
	 * 
	 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
	 * @version 1.0
	 * @since 1.0
     */
    public enum Profile {
    	
    	/**
    	 * an undefinied element.
    	 */
        UNDEFINED("undefined"),
        
        /**
         * an ontology.
         */
        BUSINESS("business"),
        
        /**
         * an ontology class.
         */
        TECHNICAL("technical");
        
        private final String name;
        private Profile(String name) { 
            this.name = name;
        }
        public String toString() {
            return name;
        }
    };
    
    /**
     * Generates output for the documentary model.
     * 
     * @param document The documentary model.
     * @param profile The end user profile.
     */
    public abstract void generateOutput(Document document, Profile profile);

}
