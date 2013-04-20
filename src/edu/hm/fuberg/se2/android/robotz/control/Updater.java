package edu.hm.fuberg.se2.android.robotz.control;

import edu.hm.fuberg.se2.android.robotz.data.Arena;
import edu.hm.fuberg.se2.android.robotz.data.GameState;

/**
 * The Class Updater updates the state of the Game.
 *
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 1.0
 */
public class Updater extends Thread {

	/** The robotz view. */
	private final UpdateableView robotzView;

	/** The robotz data. */
	private final Arena robotzData;

	/**
	 * Instantiates a new updater.
	 *
	 * @param robotzView the propeller view
	 * @param robotzData the propeller data
	 */
	Updater(final UpdateableView robotzView, final Arena robotzData) {

		this.robotzView = robotzView;
		this.robotzData = robotzData;
	}

	@Override public void run() {

		long previousTime = System.currentTimeMillis();

		while(robotzData.getState() == GameState.Running){

			final long currentTime = System.currentTimeMillis();

			envolve((int)(currentTime - previousTime));

			previousTime = currentTime;
		}
	}

	/**
	 * Method envolves the game state for a specific time of milliseconds.
	 *
	 * @param millis the milliseconds passed since last call.
	 */
	public void envolve(final int millis){

		movePlayer(millis);
		moveRobots(millis);
		checkPlayerOnExit();
		checkPlayerOnFence();
		checkPlayerOnRobot();
		checkRobotOnFence();
	}

	/**
	 * Method moves the player towards the target for a specific length, depending on passed milliseconds.
	 *
	 * @param millis the milliseconds passed since last call.
	 */
	public void movePlayer(final int millis){

		robotzData.getPlayer().move(millis);
	}

	/**
	 * Method moves the robots towards the player for a specific length, depending on passed milliseconds.
	 *
	 * @param millis the milliseconds passed since last call.
	 */
	public void moveRobots(final int millis){

		// Not yet
	}

	/**
	 * Method checks if the player has reached the Exit.
	 */
	public void checkPlayerOnExit(){

		if (robotzData.getPlayer().collides(robotzData.getExit())){

			// Ende Gewonnen
		}
	}

	/**
	 * Method checks if the player has run into a fence.
	 */
	public void checkPlayerOnFence(){

		// Not yet
	}

	/**
	 * Method checks if the player has run into a robot.
	 */
	public void checkPlayerOnRobot(){

		// Not yet
	}

	/**
	 * Method checks if a robot has run into a fence.
	 */
	public void checkRobotOnFence(){

		// Not yet
	}
}

