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
	public static final double TARGET_SIZE = 0.85;

	private static final double COLLISION_VALUE = 0.01;

	/**
	 * Ctor.
	 * @param xCoordinate The target's x-Coordinate.
	 * @param yCoordinate The target's y-Coordinate.
	 */
	public Target(final double xCoordinate, final double yCoordinate) {
		super(xCoordinate, yCoordinate, TARGET_SIZE);
	}

	/**
	 * Redefinition of Item's collides() to work with a lower collision treshold value and ignore when the player only
	 * overlaps the target.
	 * @param item The Item object for collision check.
	 */
	@Override public boolean collides(final Item item) {

		boolean result = false;

		if (item != null) {
			result = objectTouches(item.getXCoord(), item.getYCoord(), 0);
		}
		return result;
	}

	/**
	 * Redefinition of Item's objectTouches() to work only with the distance between the target object's and another
	 * object's center coordinates.
	 * @param xCoordinate The other Item's x-coordinate.
	 * @param yCoordinate The other Item's y-coordinate.
	 * @param combinedRadiens The two object's combined radiens. Unused for the redefined objectTouches().
	 */
	@Override public boolean objectTouches(final double xCoordinate, final double yCoordinate,
			final double combinedRadiens) {

		return distanceTo(xCoordinate, yCoordinate) < COLLISION_VALUE;
	}
}
