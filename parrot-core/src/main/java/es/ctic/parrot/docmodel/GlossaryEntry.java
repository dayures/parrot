package es.ctic.parrot.docmodel;

public class GlossaryEntry {
	public String label;
	public String localName;
	public String kindString;


	public GlossaryEntry(String label, String anchor, String kindString) {
		this.setLabel(label);
		this.setLocalName(anchor);
		this.setKindString(kindString);
	}


	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}


	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}


	/**
	 * @param localName the localName to set
	 */
	public void setLocalName(String localName) {
		this.localName = localName;
	}


	/**
	 * @return the localName
	 */
	public String getLocalName() {
		return localName;
	}


	/**
	 * @param kindString the kindString to set
	 */
	public void setKindString(String kindString) {
		this.kindString = kindString;
	}


	/**
	 * @return the kindString
	 */
	public String getKindString() {
		return kindString;
	}
}