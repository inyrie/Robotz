/**
 * Munich University for Applied Science,
 * Faculty 07 for Mathematics and Computer Science
 * Softwareentwicklung II, SS2013, Studiengruppe IF1A
 * Windows XP SP3; Java-Version: 1.7.0_17
 * Developing a Java application.
 */

package edu.hm.fuberg.se2.android.robotz.control;

import edu.hm.fuberg.se2.android.robotz.data.Arena;
import edu.hm.fuberg.se2.android.robotz.data.GameState;

/**
 * Class for defining various check methods concerning the arena's Items.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-05-20
 */
class Checker {

	/** The robotz data object. */
	private final Arena robotzData;

	/**
	 * Ctor for a new Checker.
	 * @param data the arena data
	 */
	Checker(final Arena data) {
		robotzData = data;
	}

	/**
	 * Uber-Checker method, including all single-checks for player on robot, player on fence, etc.
	 * @return Returns true if game-over is reached, either by winning or by losing.
	 */
	boolean masterChecker() {

		robotOnFence();

		return playerOnExit() || playerOnFence() || playerOnRobot();
	}

	/**
	 * Method checks if the player has reached the Exit.
	 * @return Returns true if the player has reached the exit, otherwise false.
	 */
	private boolean playerOnExit() {

		boolean result = false;

		if (robotzData.getPlayer().collides(robotzData.getExit())) {
			robotzData.setState(GameState.Over);
			result = true;
		}

		return result;
	}

	/**
	 * Method checks if the player has run into a fence.
	 * @return Returns true if the player has run into a fence, else false.
	 */
	private boolean playerOnFence() {

		boolean result = false;

		// Checking all the fences on the gameboard.
		for (int position = 0; position < robotzData.getAmountFences(); position++) {

			if (robotzData.getPlayer().collides(robotzData.getFence(position))) {

				if (robotzData.getPlayer().isInvincible()){
					robotzData.removeFence(position);
				}

				else {

					robotzData.setState(GameState.Over);
					result = true;
				}
			}
		}

		return result;
	}

	/**
	 * Method checks if the player has run into a robot.
	 * @return Returns true, if the player has run into a robot, else false.
	 */
	private boolean playerOnRobot() {

		boolean result = false;

		// Checking all the robots on the gameboard.
		for (int position = 0; position < robotzData.getAmountRobots(); position++) {

			if (robotzData.getPlayer().collides(robotzData.getRobot(position))) {

				if (robotzData.getPlayer().isInvincible()){
					robotzData.removeRobot(position);
				}

				else {

					robotzData.setState(GameState.Over);
					result = true;
				}
			}
		}

		return result;
	}

	/**
	 * Method checks if a robot has run into a fence.
	 */
	private void robotOnFence() {

		boolean unkilledEnemies = true;

		// performing the collision check for robots and fences as long as there are unchecked collisions on the
		// gameboard.
		while (unkilledEnemies) {
			unkilledEnemies = killEnemies();
		}
	}

	/**
	 * Method eliminates colliding fences and robots.
	 * @return Returns true, if there are still possible collisions left to check - else false.
	 */
	private boolean killEnemies() {

		// Checking all the robots on the gameboard.
		for (int robotPosition = 0; robotPosition < robotzData.getAmountRobots(); robotPosition++) {
			// Checking all the fences on the gameboard.
			for (int fencePosition = 0; fencePosition < robotzData.getAmountFences(); fencePosition++) {
				// Checks collision between a robot and a fence on the specified index.
				if (robotzData.getRobot(robotPosition).collides(robotzData.getFence(fencePosition))) {

					robotzData.removeRobot(robotPosition);
					robotzData.removeFence(fencePosition);

					return true;
				}
			}
		}

		return false;
	}
}
