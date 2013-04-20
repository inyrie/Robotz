package edu.hm.fuberg.se2.android.robotz.data;

/**
 * The Class describes an Exception for an unsupported Areana type.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 */
public class UnsupportedArenaException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

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
