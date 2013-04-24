package edu.hm.fuberg.se2.android.robotz.data;

/**
 * The Class describes the robots of the Game.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 */
public class Robot extends MobileItem {

	/** Defines a robot's size. */
	public static final int ROBOT_SIZE = 1;
	/** Defines a robot's velocity. */
	public static final int ROBOT_VELOCITY = 1;

	/**
	 * Ctor.
	 * @param xCoordinate
	 * @param yCoordinate
	 */
	public Robot(final double xCoordinate, final double yCoordinate) {
		super(xCoordinate, yCoordinate, ROBOT_SIZE, ROBOT_VELOCITY);
	}
}
