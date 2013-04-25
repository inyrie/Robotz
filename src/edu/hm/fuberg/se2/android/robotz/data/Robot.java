package edu.hm.fuberg.se2.android.robotz.data;

/**
 * The Class describes the robots of the Game.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-04-25
 */
public class Robot extends MobileItem {

	/** Defines a robot's size. */
	public static final int ROBOT_SIZE = 1;

	/** Defines a robot's velocity. */
	public static final int ROBOT_VELOCITY = 1;

	/**
	 * Ctor for a new Robot.
	 * @param xCoordinate the x coordinate of the robot.
	 * @param yCoordinate the y coordinate of the robot.
	 */
	public Robot(final double xCoordinate, final double yCoordinate) {
		super(xCoordinate, yCoordinate, ROBOT_SIZE, ROBOT_VELOCITY);
	}
}
