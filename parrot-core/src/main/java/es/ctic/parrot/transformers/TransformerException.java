package es.ctic.parrot.transformers;

@SuppressWarnings("serial")
public class TransformerException extends Exception{

	public TransformerException(Throwable e) {
		super(e);
	}
	
	public TransformerException(String message) {
		super(message);
	}
	
	public TransformerException(String message, Throwable e) {
		super(message, e);
	}
	
}
