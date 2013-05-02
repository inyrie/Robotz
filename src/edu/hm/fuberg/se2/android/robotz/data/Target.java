/**
 * Munich University for Applied Science,
 * Faculty 07 for Mathematics and Computer Science
 * Softwareentwicklung II, SS2013, Studiengruppe IF1A
 * Windows XP SP3; Java-Version: 1.7.0_17
 * Developing a Java application.
 */

package edu.hm.fuberg.se2.android.robotz.data;

/**
 * The Class describes a target point on the game field.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-04-21
 */
public class Target extends Item {

	/** Constant defining the target's size. */
	public static final double TARGET_SIZE = 1;

	/**
	 * Ctor.
	 * @param xCoordinate The target's x-Coordinate.
	 * @param yCoordinate The target's y-Coordinate.
	 */
	public Target(final double xCoordinate, final double yCoordinate) {
		super(xCoordinate, yCoordinate, TARGET_SIZE);
	}

}
