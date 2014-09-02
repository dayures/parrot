package es.ctic.parrot.docmodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.Label;

public class LexicalInformation {
	
    private static final Logger logger = Logger.getLogger(LexicalInformation.class);

	private Set<Label> labels = new HashSet<Label>();

	/**
	 * Constructs a lexical information (Suppress default constructor for noninstantiability).
	 */
	private LexicalInformation() {
        logger.debug("Created " + this.getClass());
    }
	
	static public LexicalInformation createFromLabels(Collection<Label> labels, String bestLabel, Locale locale){

		LexicalInformation lexicalInformation = new LexicalInformation();
		
		for (Label label: labels){
			lexicalInformation.insertLabel(label);
		}
		
// 		remove the Used label
		lexicalInformation.removeLabel(bestLabel, locale);

		return lexicalInformation;
	}
	
	/**
	 * Returns the labels.
	 * @return the labels.
	 */
	public Set<Label> getLabels() {
		return labels;
	}
	
	private boolean insertLabel(Label label){
		
		if (label.getLocale() != null) {
			Iterator<Label> it = this.getLabels().iterator();
			boolean insert = true;
			Label previousLabel = null;
			
			while (it.hasNext()){
				Label labelInSet = it.next();
				if (label.getLocale().equals(labelInSet.getLocale()) && label.getText().equals(labelInSet.getText())) {
					// Mirar si hay preferencia
					if (label.isPrefLabel() && (labelInSet.isPrefLabel() == false)){
						previousLabel = labelInSet;
					} else {
						insert = false;
					}
				} 
			}
			
			if (previousLabel != null) {
				getLabels().remove(previousLabel);
			}

			if (insert){
				getLabels().add(label);
				return true;
			}
			
			return false;
			
		} else {
			logger.debug("Not added label="+label.getText()+" because it has not a locale");
			return false;
		}
	}
	
	private boolean removeLabel(String label, Locale locale){
		
		Iterator<Label> it = getLabels().iterator();
		boolean remove = false;
		Label previousLabel = null;
		
		while (it.hasNext()){
			Label labelInSet = it.next();
			if (locale.equals(labelInSet.getLocale()) && label.equals(labelInSet.getText())) {
				remove = true;
				previousLabel = labelInSet;
			} 
		}
		
		if (remove){
			getLabels().remove(previousLabel);
			return true;
		}
		
		return false;
	}
	
	public Collection<Locale> getLocales(){
		Collection<Locale> locales = new HashSet<Locale>();
		for(Label label : getLabels()){
			if (label.getLocale() != null){
				locales.add(label.getLocale());
			}
		}
		
		List<Locale> localeList = new ArrayList<Locale>(locales);
		Collections.sort(localeList, new LocaleComparator());
		return localeList;
	}
	
	public Collection<Label> findLabelsByLocale(Locale locale){
		Collection<Label> labels = new HashSet<Label>();
		for(Label label : getLabels()){
			if (label.getLocale().equals(locale)){
				labels.add(label);
			}
		}
		
		List<Label> labelList = new ArrayList<Label>(labels);
		Collections.sort(labelList, new LabelComparator());
		return labelList;
	}

}

/**
 * Compares locales using only the language 
 *
 */
class LocaleComparator implements Comparator<Locale> {

	// This comparator is not consistent with equals()
	public int compare(Locale arg0, Locale arg1) {
		if (arg0 != null && arg1 != null) {
			return  arg0.getLanguage().compareTo(arg1.getLanguage());
		} else {
			return 0; // FIXME change comparable method
		}
	}
	
}

/**
 * Compares labels using only the text 
 *
 */
class LabelComparator implements Comparator<Label> {

	// This comparator is not consistent with equals()
	public int compare(Label arg0, Label arg1) {
		if (arg0 != null && arg1 != null) {
			return  arg0.getText().toLowerCase().compareTo(arg1.getText().toLowerCase());
		} else {
			return 0; // FIXME change comparable method
		}
	}
	
}