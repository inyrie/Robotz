package edu.hm.fuberg.se2.android.robotz.data;

/**
 * The Class describes the robots of the Game.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 */
public class Robot extends MobileItem {

	public static final int ROBOT_SIZE = 1;
	public static final int ROBOT_VELOCITY = 1;

	/**
	 * @param xCoordinate
	 * @param yCoordinate
	 * @param destination
	 * @param size
	 * @param velocity
	 */
	public Robot(final double xCoordinate, final double yCoordinate, final Item destination) {

		super(xCoordinate, yCoordinate, destination, 1, 1);
	}

}
