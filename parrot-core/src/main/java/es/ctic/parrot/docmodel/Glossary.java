package es.ctic.parrot.docmodel;

import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.DocumentableObject;

/**
 * 
 * This class represents a glossary (an alphabetical list of terms with the references for those terms)
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 * 
 */
public class Glossary {

    private final SortedMap<String, Set<GlossaryEntry>> glossaryEntries;
    private final Locale locale;
    
    private static final Logger logger = Logger.getLogger(Glossary.class);

    
    /**
     * Constructs a glossary for the given locale.
     * @param locale the locale.
     */
    public Glossary(Locale locale) {
    	this.locale = locale;
        glossaryEntries = new TreeMap<String, Set<GlossaryEntry>>();
    }
    
    /**
     * Adds an entry in the glossary.
     * @param term the term.
     * @param documentableObject the documentable element to be referenced.
     */
    public void addReference(String term, DocumentableObject documentableObject) {
        Set<GlossaryEntry> references = glossaryEntries.get(term);
        
        if (references == null) {
            references = new HashSet<GlossaryEntry>();
            glossaryEntries.put(term, references);
        }
        references.add(new GlossaryEntry(documentableObject.getLabel(locale), documentableObject.getLocalName(), documentableObject.getKindString()));
    }
    
    /**
     * Returns the glossary entries, sorted alphabetically.
     * @return the glossary entries, sorted alphabetically.
     */
    public SortedMap<String, Set<GlossaryEntry>> getGlossaryEntries() {
        return Collections.unmodifiableSortedMap(glossaryEntries);
    }
    
    /**
     * Returns <code>true</code> if, and only if, number of entries of this glossary is 0.
     * @return <code>true</code> if number of entries of this glossary is 0, otherwise <code>false</code>.
     */
    public boolean isEmpty() {
        return glossaryEntries.isEmpty();
    }
    
    /**
     * Returns the number of entries of this glossary.
     * @return the number of entries of this glossary.
     */
    public int size() {
        return glossaryEntries.size();
    }
}
