package es.ctic.parrot.docmodel;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.ibm.icu.text.Collator;

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

    private final SortedMap<String, Set<DocumentableObject>> glossaryEntries;
    
    /**
     * Constructs a glossary for the given locale.
     * @param locale the locale.
     */
    public Glossary(Locale locale) {
        glossaryEntries = new TreeMap<String, Set<DocumentableObject>>(new GlossaryEntryComparator(locale));
    }
    
    /**
     * Adds an entry in the glossary.
     * @param term the term.
     * @param documentableObject the documentable element to be referenced.
     */
    public void addReference(String term, DocumentableObject documentableObject) {
        Set<DocumentableObject> references = glossaryEntries.get(term);
        if (references == null) {
            references = new HashSet<DocumentableObject>();
            glossaryEntries.put(term, references);
        }
        references.add(documentableObject);
    }
    
    /**
     * Returns the glossary entries, sorted alphabetically.
     * @return the glossary entries, sorted alphabetically.
     */
    public SortedMap<String, Set<DocumentableObject>> getGlossaryEntries() {
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

class GlossaryEntryComparator implements Comparator<String> {

    private final Collator collator;
 
    public GlossaryEntryComparator(Locale locale) {
        collator = Collator.getInstance(locale);
    }
    
    public int compare(String arg0, String arg1) {
        return collator.compare(arg0, arg1);
    }
    
    
}