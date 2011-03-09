package es.ctic.parrot.de;

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
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param uri the uri to set
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @param sourceText the sourceText to set
	 */
	public void setSourceText(String sourceText) {
		this.sourceText = sourceText;
	}

	/**
	 * @return the sourceText
	 */
	public String getSourceText() {
		return sourceText;
	}
	
}
