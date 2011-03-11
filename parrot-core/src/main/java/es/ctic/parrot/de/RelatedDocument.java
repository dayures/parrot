package es.ctic.parrot.de;

/**
 * A document that is related to other. 
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 * 
 */
public class RelatedDocument {
	
    public enum Type {
        HTML("text/html"), 
        TEXT("text/plain"),
        IMAGE("image/png"),
        VIDEO("video/mpeg"),
        URI("uri"),
        UNDEFINED("undefined");
        private final String name;
        private Type(String name) { 
            this.name = name;
        }
        public String toString(){
        	return name;
        }
    };
	
	private String title;
	private String uri;
	private Type type;
	private String sourceText;

	/**
	 * Sets the title.
	 * @param title the title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Returns the title.
	 * @return the title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the URI. 
	 * @param uri the URI to set.
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * Returns the URI.
	 * @return the URI.
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * Sets the Internet media type.
	 * @param type the Internet media type to set.
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * Returns the type.
	 * @return the type.
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Sets the source text.
	 * @param sourceText the source text to set.
	 */
	public void setSourceText(String sourceText) {
		this.sourceText = sourceText;
	}

	/**
	 * Returns the source text.
	 * @return the source text.
	 */
	public String getSourceText() {
		return sourceText;
	}
	
}
