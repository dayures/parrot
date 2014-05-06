package es.ctic.parrot.de;

/**
 * An MIME type.
 * 
 * @author Carlos Tejo Alonso (<a href="http://www.fundacionctic.org">Fundación CTIC</a>)
 * @version 1.0
 * @since 1.0
 *
 */
public class MIMEType {
	
	private String label;
	private String value;

	public MIMEType(String label, String value) {
		super();
		this.setLabel(label);
		this.setValue(value);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}