package es.ctic.parrot.transformers;

/**
 * 
 * Signals that a Transformer exception of some sort has occurred
 * 
 * @author Carlos Tejo Alonso (<a href="http://www.fundacionctic.org">Fundación CTIC</a>)
 * @version 1.0
 * @since 1.0
 *
 */
@SuppressWarnings("serial")
public class TransformerException extends Exception{

	/**
	 * Create a new <code>TransformerException</code> wrapping an existing exception.
	 * The existing exception will be embedded in the new one, and its message will become the default message for the <code>TransformerException</code>.
	 * @param e The exception to be wrapped in a <code>TransformerException</code>.
	 */
	public TransformerException(Throwable e) {
		super(e);
	}
	
	/**
	 * Constructs an <code>TransformerException</code> with the specified detail message.
	 * @param message The detail message.
	 */
	public TransformerException(String message) {
		super(message);
	}
	
	/**
	 * Create a new <code>TransformerException</code> from an existing exception.
	 * The existing exception will be embedded in the new one, but the new exception will have its own message.
	 * @param message The detail message.
	 * @param e The exception to be wrapped in a <code>TransformerException</code>.
	 */	
	public TransformerException(String message, Throwable e) {
		super(message, e);
	}
	
}
