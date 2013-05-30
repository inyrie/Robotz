/**
 * Munich University for Applied Science,
 * Faculty 07 for Mathematics and Computer Science
 * Softwareentwicklung II, SS2013, Studiengruppe IF1A
 * Windows XP SP3; Java-Version: 1.7.0_17
 * Developing a Java application.
 */

package edu.hm.fuberg.se2.android.robotz.data;

import java.util.Random;



/**
 * The Class describes the robots of the Game.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-05-30
 */
public class Robot extends MobileItem {

	/** Defines a robot's size. */
	public static final double ROBOT_SIZE = 0.85;

	/** Time after a invincible robot will change the direction.  */
	public static final int CHANGE_TARGET = 1500;

	/** Value defining the time period for invincibility after the robot has taken the ultimate pill of invincibility. */
	private final int invincibilityTime;

	/** Time till a invincible robot will change the direction. */
	private int changeTarget;

	/**
	 * Ctor for a new Robot.
	 * @param xCoordinate The x coordinate of the robot.
	 * @param yCoordinate The y coordinate of the robot.
	 * @param robotVelocity The robot speed.
	 * @param time The time how long a player can be invincible.
	 */
	public Robot(final double xCoordinate, final double yCoordinate, final double robotVelocity, final int time) {
		super(xCoordinate, yCoordinate, ROBOT_SIZE, robotVelocity);
		invincibilityTime = time;
	}

	/**
	 * Method for setting invincibility.
	 */
	public void setInvincibility(){
		super.setInvincibility(invincibilityTime);
	}

	private int getChangeTarget() {
		return changeTarget;
	}

	private void setChangeTarget(final int changeTarget) {
		this.changeTarget = changeTarget;
	}

	public void decrementInvincibility(final int deltaTime, final Player player) {

		super.decrementInvincibility(deltaTime);

		if (getInvincibility() - deltaTime < 0) {
			setDestination(player);
		}
	}

	public void changeDirection (final int deltaTime, final double width, final double height) {

		final int newTime = getChangeTarget() - deltaTime;

		setChangeTarget(newTime);

		if (newTime < 0) {

			final Random random = new Random();
			final int newXCoordinate = random.nextInt((int)width);
			final int newYCoordinate = random.nextInt((int)width);

			setChangeTarget(CHANGE_TARGET);
			setDestination(new Target(newXCoordinate,newYCoordinate));


		}
	}
}
