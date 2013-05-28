/**
 * Munich University for Applied Science,
 * Faculty 07 for Mathematics and Computer Science
 * Softwareentwicklung II, SS2013, Studiengruppe IF1A
 * Windows XP SP3; Java-Version: 1.7.0_17
 * Developing a Java application.
 */

package edu.hm.fuberg.se2.android.robotz.data;

/**
 * The Class describes the robots of the Game.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-05-19
 */
public class Robot extends MobileItem {

	/** Defines a robot's size. */
	public static final double ROBOT_SIZE = 0.85;

	/** Value defining the time period for invincibility after a robot has taken the ultimate pill of invincibility.  */
	private static final int INVINCIBILITY_TIME = 10000;

	/**
	 * Ctor for a new Robot.
	 * @param xCoordinate The x coordinate of the robot.
	 * @param yCoordinate The y coordinate of the robot.
	 * @param robotVelocity The robot speed.
	 */
	public Robot(final double xCoordinate, final double yCoordinate, final double robotVelocity) {
		super(xCoordinate, yCoordinate, ROBOT_SIZE, robotVelocity);
	}

	/**
	 * Method for setting invincibility.
	 */
	public void setInvincibility()
	{
		super.setInvincibility(INVINCIBILITY_TIME);
	}
}
