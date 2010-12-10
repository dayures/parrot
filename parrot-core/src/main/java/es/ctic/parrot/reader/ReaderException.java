package es.ctic.parrot.reader;

public class ReaderException extends Exception {

	public ReaderException(Throwable e) {
		super(e);
	}
	
	public ReaderException(String message) {
		super(message);
	}
	
	public ReaderException(String message, Throwable e) {
		super(message, e);
	}
	
}
