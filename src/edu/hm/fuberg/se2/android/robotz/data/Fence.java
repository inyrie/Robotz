/**
 * Munich University for Applied Science,
 * Faculty 07 for Mathematics and Computer Science
 * Softwareentwicklung II, SS2013, Studiengruppe IF1A
 * Windows XP SP3; Java-Version: 1.7.0_17
 * Developing a Java application.
 */

package edu.hm.fuberg.se2.android.robotz.data;

/**
 * The Class describes the fences of the Game.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-04-25
 */
public class Fence extends Item {

	/** The size of the Exit. */
	public static final double FENCE_SIZE = 0.85;

	/**
	 * Ctor for a new fence object.
	 * @param xCoord the X Coordinate of the fence.
	 * @param yCoord the Y Coordinate of the fence.
	 */
	public Fence(final double xCoord, final double yCoord) {

		super(xCoord, yCoord, FENCE_SIZE);
	}
}
