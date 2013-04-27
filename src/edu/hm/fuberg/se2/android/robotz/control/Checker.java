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
 * @version 2013-04-27
 */
public class Checker {

	private final Arena robotzData;

	/**
	 * Ctor.
	 * @param data
	 */
	public Checker(final Arena data) {
		robotzData = data;
	}

	/**
	 * Method checks if the player has reached the Exit.
	 */
	public void playerOnExit() {

		if (robotzData.getPlayer().collides(robotzData.getExit())) {
			robotzData.setState(GameState.Over);
		}
	}

	/**
	 * Method checks if the player has run into a fence.
	 */
	public void playerOnFence() {

		for (int position = 0; position < robotzData.getAmountFences(); position++) {

			if (robotzData.getPlayer().collides(robotzData.getFence(position))) {

				robotzData.setState(GameState.Over);
			}
		}
	}

	/**
	 * Method checks if the player has run into a robot.
	 */
	public void playerOnRobot() {

		for (int position = 0; position < robotzData.getAmountRobots(); position++) {

			if (robotzData.getPlayer().collides(robotzData.getRobot(position))) {

				robotzData.setState(GameState.Over);
			}
		}
	}

	/**
	 * Method checks if a robot has run into a fence.
	 */
	public void robotOnFence() {

		for (int robotPosition = 0; robotPosition < robotzData.getAmountRobots(); robotPosition++) {

			for (int fencePosition = 0; fencePosition < robotzData.getAmountRobots(); fencePosition++) {

				if (robotzData.getRobot(robotPosition).collides(robotzData.getFence(fencePosition))) {

					robotzData.removeRobot(robotPosition);
					robotzData.removeFence(fencePosition);
				}
			}
		}
	}
}
