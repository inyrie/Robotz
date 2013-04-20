package edu.hm.fuberg.se2.android.robotz.control;

import edu.hm.fuberg.se2.android.robotz.data.Arena;
import edu.hm.fuberg.se2.android.robotz.data.GameState;
import edu.hm.fuberg.se2.android.robotz.view.UpdateOnlyView;

/**
 * Class for updating the current game state.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-04-20
 */
public class Updater extends Thread {

	/** The robotz view. */
	private final UpdateOnlyView robotzView;

	/** The robotz data. */
	private final Arena robotzData;

	/**
	 * Instantiates a new updater.
	 * @param robotzView the propeller view
	 * @param robotzData the propeller data
	 */
	Updater(final UpdateOnlyView robotzView, final Arena robotzData) {

		this.robotzView = robotzView;
		this.robotzData = robotzData;
	}

	@Override public void run() {

		long now = System.currentTimeMillis();

		while (robotzData.getState() == GameState.Running) {

			final long currentTime = System.currentTimeMillis();
			evolve(currentTime - now);
			now = currentTime;
		}
	}

	/**
	 * Method evolves the game state for a specified time of milliseconds.
	 * @param l the milliseconds passed since last call.
	 */
	public void evolve(final long l) {

		movePlayer(l);
		moveRobots(l);
		checkPlayerOnExit();
		checkPlayerOnFence();
		checkPlayerOnRobot();
		checkRobotOnFence();
	}

	/**
	 * Method moves the player towards the target for a specific length,
	 * depending on passed milliseconds.
	 * @param l the milliseconds passed since last call.
	 */
	public void movePlayer(final long l) {

		robotzData.getPlayer().move(l);
	}

	/**
	 * Method moves the robots towards the player for a specific length,
	 * depending on passed milliseconds.
	 * @param l the milliseconds passed since last call.
	 */
	public void moveRobots(final long l) {

		// Not yet
	}

	/**
	 * Method checks if the player has reached the Exit.
	 */
	public void checkPlayerOnExit() {

		if (robotzData.getPlayer().collides(robotzData.getExit())) {

			// Ende Gewonnen
		}
	}

	/**
	 * Method checks if the player has run into a fence.
	 */
	public void checkPlayerOnFence() {

		// Not yet
	}

	/**
	 * Method checks if the player has run into a robot.
	 */
	public void checkPlayerOnRobot() {

		// Not yet
	}

	/**
	 * Method checks if a robot has run into a fence.
	 */
	public void checkRobotOnFence() {

		// Not yet
	}
}
