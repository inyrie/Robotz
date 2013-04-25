package edu.hm.fuberg.se2.android.robotz.data;

/**
 * The Class describes an Exception for an unsupported Areana type.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-04-25
 */
public class UnsupportedArenaException extends Exception {

	/**
	 * the serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Ctor for a new unsupported arena exception.
	 */
	public UnsupportedArenaException() {
		super();
	}

	/**
	 * Ctor for a new UnsupportedArenaException.
	 * @param message the Error message.
	 */
	public UnsupportedArenaException(final String message) {
		super(message);
	}
}
