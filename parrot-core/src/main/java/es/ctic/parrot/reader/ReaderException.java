package es.ctic.parrot.reader;
/**
 * 
 * Signals that a Reader exception of some sort has occurred
 * 
 * @author Carlos Tejo Alonso (<a href="http://www.fundacionctic.org">Fundación CTIC</a>)
 * @version 1.0
 * @since 1.0
 *
 */
@SuppressWarnings("serial")
public class ReaderException extends Exception {

	/**
	 * Create a new <code>ReaderException</code> wrapping an existing exception.
	 * The existing exception will be embedded in the new one, and its message will become the default message for the <code>ReaderException</code>.
	 * @param e The exception to be wrapped in a <code>ReaderException</code>.
	 */
	public ReaderException(Throwable e) {
		super(e);
	}

	/**
	 * Constructs an <code>ReaderException</code> with the specified detail message.
	 * @param message The detail message.
	 */
	public ReaderException(String message) {
		super(message);
	}

	/**
	 * Create a new <code>ReaderException</code> from an existing exception.
	 * The existing exception will be embedded in the new one, but the new exception will have its own message.
	 * @param message The detail message.
	 * @param e The exception to be wrapped in a <code>ReaderException</code>.
	 */
	public ReaderException(String message, Throwable e) {
		super(message, e);
	}
	
}
