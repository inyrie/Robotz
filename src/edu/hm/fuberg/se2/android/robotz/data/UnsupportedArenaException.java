/**
 * Munich University for Applied Science,
 * Faculty 07 for Mathematics and Computer Science
 * Softwareentwicklung II, SS2013, Studiengruppe IF1A
 * Windows XP SP3; Java-Version: 1.7.0_17
 * Developing a Java application.
 */

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
