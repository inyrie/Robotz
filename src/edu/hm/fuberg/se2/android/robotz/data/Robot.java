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
	 */
	public Robot(final double xCoordinate, final double yCoordinate) {
		super(xCoordinate, yCoordinate, ROBOT_SIZE, ROBOT_VELOCITY);
	}

}
