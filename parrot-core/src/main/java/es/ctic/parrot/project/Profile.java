package es.ctic.parrot.project;

/**
 * User profiles in Parrot.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
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
