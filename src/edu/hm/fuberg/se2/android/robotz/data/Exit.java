/**
 * Munich University for Applied Science,
 * Faculty 07 for Mathematics and Computer Science
 * Softwareentwicklung II, SS2013, Studiengruppe IF1A
 * Windows XP SP3; Java-Version: 1.7.0_17
 * Developing a Java application.
 */

package edu.hm.fuberg.se2.android.robotz.data;

/**
 * The Class describes the Exit of the Game.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-04-25
 */
public class Exit extends Item {

	/** The size of a fence. */
	public static final double EXIT_SIZE = 0.85;

	/**
	 * Ctor for a new exit object.
	 * @param xCoord the X Coordinate of the exit.
	 * @param yCoord tye Y Coordinate of the exit.
	 */
	public Exit(final double xCoord, final double yCoord) {

		super(xCoord, yCoord, EXIT_SIZE);
	}
}
