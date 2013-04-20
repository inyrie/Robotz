package edu.hm.fuberg.se2.android.robotz.control;

import edu.hm.fuberg.se2.android.robotz.data.Arena;
import edu.hm.fuberg.se2.android.robotz.data.GameState;
import edu.hm.fuberg.se2.android.robotz.data.Player;
import edu.hm.fuberg.se2.android.robotz.data.Target;
import edu.hm.fuberg.se2.android.robotz.view.UpdateOnlyView;

public class RobotzControl {

	// ////////////// OBJECT VARIABLES ///////////////////////////

	/** The robotz data. */
	private final Arena robotzData;
	/** The robotzView object. */
	private UpdateOnlyView robotzView;

	// ////////////// C T O R ///////////////////////////

	/**
	 * Ctor.
	 * @param data the robotz data.
	 */
	public RobotzControl(final Arena data) {
		robotzData = data;
	}

	// ////////////// S E T T E R ///////////////////////////

	/**
	 * Sets the robotzView object.
	 * @param view The robotzView object.
	 * @throws IllegalArgumentException if this.robotzView != null.
	 */
	public void setRobotzView(final UpdateOnlyView view) {

		if (robotzView == null) {
			robotzView = view;
			new Updater(this, view, robotzData).start();
		}
		else {
			throw new IllegalArgumentException("View cannot be set twice!");
		}
	}

	/**
	 * Method for setting a new target point.
	 * @param destination
	 * @return
	 */
	public Target createNewTarget(final Target destination) {
		robotzData.getPlayer().setDestination(destination);
		return destination;
	}

	// ////////////// GAMESTATE DEPENDABLE METHODS ///////////////////////////

	/**
	 * Method for freezing the game, for example when changing from Robotz App
	 * to homescreen without closing the App completely.
	 */
	public void holdGame() {
		// here be magical code!
		// end Thread Updater.
	}

	/** Method for continuing a previously frozen game. */
	public void continueGame() {
		// here be magical code, too!
		// create new Thread Updater!
	}

	// ////////////// E V O L V E - M E T H O D S ///////////////////////////

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
	 * @param elapsedMilis the milliseconds passed since last call.
	 */
	public void movePlayer(final long elapsedMilis) {

		final Player player = robotzData.getPlayer();

		if (!player.collides(robotzData.getPlayer().getDestination())) {
			player.move(elapsedMilis);
		}
	}

	/**
	 * Method checks if the player has reached the Exit.
	 */
	public GameState checkPlayerOnExit() {

		GameState state = GameState.Running;

		if (robotzData.getPlayer().collides(robotzData.getExit())) {
			state = GameState.Over;
		}
		return state;
	}

	/**
	 * Method for changing the gamestate from waiting to running or from running
	 * to over, depending on the current gamestate.
	 */
	public void changeGameState() {

		final GameState state = robotzData.getState();

		if (state == GameState.Waiting) {
			robotzData.setState(GameState.Running);
		}
		else if (state == GameState.Running) {
			robotzData.setState(GameState.Over);
		}
	}

	// /////////// METHODS FOR LATER IMPLEMENTATION ///////////////////

	/**
	 * Method moves the robots towards the player for a specific length,
	 * depending on passed milliseconds.
	 * @param elapsedMilis the milliseconds passed since last call.
	 */
	public void moveRobots(final long elapsedMilis) {
		// Not yet
	}

	/**
	 * Method checks if the player has run into a fence.
	 * @return The gamestate - Running, if the Player doesn't collide with a
	 *         Fence, Over, if he does.
	 */
	public GameState checkPlayerOnFence() {
		final GameState state = GameState.Running;

		// if(robotzData.getPlayer().collides(fence)
		// {
		// state = GameState.Over;
		// }
		// wie fragt man alle Fence-Objekte ab, ob der Spieler mit einem davon
		// kollidiert?

		return state;
	}

	/**
	 * Method checks if the player has run into a robot.
	 * @return The gamestate - Running, if the Player doesn't collide with a
	 *         Robot, Over, if he does.
	 */
	public GameState checkPlayerOnRobot() {
		final GameState state = GameState.Running;

		// if(robotzData.getPlayer().collides(robot)
		// {
		// state = GameState.Over;
		// }
		// wie fragt man alle Roboter-Objekte ab, ob der Spieler mit einem davon
		// kollidiert?

		return state;
	}

	/**
	 * Method checks if a robot has run into a fence.
	 */
	public void checkRobotOnFence() {
		// Not yet
	}
}
