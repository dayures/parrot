package es.ctic.parrot.de;

import es.ctic.parrot.utils.CurieUtils;

/**
 * A triple.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class Triple {
	
	private String subject;
	private String predicate;
	private String predicateLabel;
	private String object;
	private String objectLang="";
	private boolean isObjectResource = false;
	private boolean isObjectBlank = false;

	public String getObjectLang() {
		return objectLang;
	}

	public void setObjectLang(String objectLang) {
		this.objectLang = objectLang;
	}

	
	public static Triple createTripleOR (String subject, String predicate, String object, boolean isObjectBlank) {
		Triple triple = new Triple(subject, predicate, object);
		triple.isObjectResource = true;
		triple.isObjectBlank = isObjectBlank;
		return triple;
	}
	
	/**
	 * Constructs a triple.
	 * @param subject a subject to set.
	 * @param predicate as predicate to set.
	 * @param object a object to set.
	 */
	public Triple(String subject, String predicate, String object) {
		super();
		setSubject(subject);
		setPredicate(predicate);
		setObject(object);
	}

	/**
	 * Constructs a triple (the object is a literal with language tag).
	 * @param subject a subject to set.
	 * @param predicate as predicate to set.
	 * @param object a object to set.
	 */
	public Triple(String subject, String predicate, String object, String objectLang) {
		super();
		setSubject(subject);
		setPredicate(predicate);
		setObject(object);
		setObjectLang(objectLang);
	}

	/**
	 * @param subject the subject to set
	 */
	private void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param predicate the predicate to set
	 */
	private void setPredicate(String predicate) {
		this.predicate = predicate;
		
		String curie = CurieUtils.getCurie(this.getPredicate());
		if (curie != null){
			this.setPredicateLabel(curie);
    	} else {
    		this.setPredicateLabel(this.getPredicate());
    	}

	}
	/**
	 * @return the predicate
	 */
	public String getPredicate() {
		return predicate;
	}
	/**
	 * @param object the object to set
	 */
	private void setObject(String object) {
		this.object = object;
	}
	/**
	 * @return the object
	 */
	public String getObject() {
		return object;
	}

	public String getPredicateLabel() {
		return predicateLabel;
	}

	private void setPredicateLabel(String predicateLabel) {
		this.predicateLabel = predicateLabel;
	}
	
	public boolean isObjectResource(){
		return this.isObjectResource;
	}

	public boolean isObjectBlank() {
		return isObjectBlank;
	}

	private void setObjectBlank(boolean isObjectBlank) {
		this.isObjectBlank = isObjectBlank;
	}
	
}