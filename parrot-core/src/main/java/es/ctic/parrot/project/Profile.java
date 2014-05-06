package es.ctic.parrot.project;

/**
 * User profiles in Parrot.
 * 
 * @author Carlos Tejo Alonso (<a href="http://www.fundacionctic.org">Fundación CTIC</a>)
 * @version 1.0
 * @since 1.0
 */
public enum Profile {
	
    /**
     * a business profile.
     */
    BUSINESS("business"),
    
    /**
     * an technical profile.
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
