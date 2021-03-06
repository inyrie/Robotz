/**
 * Munich University for Applied Science,
 * Faculty 07 for Mathematics and Computer Science
 * Softwareentwicklung II, SS2013, Studiengruppe IF1A
 * Windows XP SP3; Java-Version: 1.7.0_17
 * Developing a Java application.
 */

package edu.hm.fuberg.se2.android.robotz.control;

import java.util.Iterator;
import java.util.Set;

import edu.hm.fuberg.se2.android.robotz.data.Arena;
import edu.hm.fuberg.se2.android.robotz.data.GameState;

/**
 * Class for defining various check methods concerning the arena's Items.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-06-14
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
		for (int position = 0; position < robotzData.getFences().size(); position++) {

			if (robotzData.getPlayer().collides(robotzData.getFences().get(position))) {

				robotzData.setState(GameState.Over);
				result = true;
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
		for (int position = 0; position < robotzData.getRobots().size(); position++) {

			if (robotzData.getPlayer().collides(robotzData.getRobots().get(position))) {

				robotzData.setState(GameState.Over);
				result = true;
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
		for (int robotPosition = 0; robotPosition < robotzData.getRobots().size(); robotPosition++) {
			// Checking all the fences on the gameboard.
			for (int fencePosition = 0; fencePosition < robotzData.getFences().size(); fencePosition++) {
				// Checks collision between a robot and a fence on the specified index.
				if (robotzData.getRobots().get(robotPosition).collides(robotzData.getFences().get(fencePosition))) {

					robotzData.removeRobot(robotPosition);
					robotzData.removeFence(fencePosition);

					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Method checks if player has reached a tunnel and can teleport.
	 * @param robotzControl A robotzControl object.
	 */
	void checkTeleport(final RobotzControl robotzControl) {

		final Set<Integer> keys = robotzData.getTunnels().keySet();

		// creating a new iterator for running through the keySet
		final Iterator<Integer> cursor = keys.iterator();

		// running through all the tunnels on the gameboard
		while (cursor.hasNext()) {

			final int tunnelNumber = cursor.next();

			// checking both tunnel holes that form a tunnel.
			for (int index = 0; index < 2; index++) {

				// getting a single tunnel hole for collision check
				if (robotzData.getPlayer().collides(
						robotzData.getTunnels().get(tunnelNumber).getTunnelPair().get(index))) {
					robotzControl.teleport(tunnelNumber, index);

					return;
				}
			}
		}
	}

	/**
	 * Method checks if target coordinates are within the gameboard bounds and sets the target accordingly.
	 * @param coords The coordinates to check.
	 * @param modelSize The modelsize of the gameboard.
	 * @param targetSize The target size.
	 */
	void checkPositionTarget(final double[] coords, final double[] modelSize, final double targetSize) {

		final double xCoord = coords[0];
		final double yCoord = coords[1];

		final boolean withinWidth = xCoord <= modelSize[0] - targetSize && xCoord >= 0;
		final boolean withinHeight = yCoord <= modelSize[1] - targetSize && yCoord >= 0;

		if (withinWidth && withinHeight) {
			robotzData.getPlayer().setDestination(xCoord, yCoord);
		}
	}
}
