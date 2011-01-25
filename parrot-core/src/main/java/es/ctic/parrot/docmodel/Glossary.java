package es.ctic.parrot.docmodel;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import com.ibm.icu.text.Collator;

import es.ctic.parrot.de.DocumentableObject;

public class Glossary {

    private final SortedMap<String, Set<DocumentableObject>> glossaryEntries;
    
    public Glossary(Locale locale) {
        glossaryEntries = new TreeMap<String, Set<DocumentableObject>>(new GlossaryEntryComparator(locale));
    }
    
    public void addReference(String term, DocumentableObject documentableObject) {
        Set<DocumentableObject> references = glossaryEntries.get(term);
        if (references == null) {
            references = new HashSet<DocumentableObject>();
            glossaryEntries.put(term, references);
        }
        references.add(documentableObject);
    }
    
    public SortedMap<String, Set<DocumentableObject>> getGlossaryEntries() {
        return Collections.unmodifiableSortedMap(glossaryEntries);
    }
    
    public boolean isEmpty() {
        return glossaryEntries.isEmpty();
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