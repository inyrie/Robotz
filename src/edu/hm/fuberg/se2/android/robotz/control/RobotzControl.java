package edu.hm.fuberg.se2.android.robotz.control;

import edu.hm.fuberg.se2.android.robotz.data.Arena;
import edu.hm.fuberg.se2.android.robotz.data.GameState;
import edu.hm.fuberg.se2.android.robotz.data.Player;
import edu.hm.fuberg.se2.android.robotz.data.Target;
import edu.hm.fuberg.se2.android.robotz.view.UpdateOnlyView;

/**
 * Class for controlling the robotz data.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-04-25
 */
public class RobotzControl {

	// ////////////// OBJECT VARIABLES ///////////////////////////

	/** The robotz data. */
	private final Arena robotzData;

	// ////////////// C T O R ///////////////////////////

	/**
	 * Ctor.
	 * @param data the robotz data.
	 */
	public RobotzControl(final Arena data) {
		robotzData = data;
	}

	// ////////////// S E T T E R ///////////////////////////

	// /**
	// * Sets the robotzView object.
	// * @param view The robotzView object.
	// * @throws IllegalArgumentException if this.robotzView != null.
	// */
	// public void setRobotzView(final UpdateOnlyView view) {
	//
	// if (robotzView == null) {
	// robotzView = view;
	// new Updater(this, view, robotzData).start();
	// }
	// else {
	// throw new IllegalArgumentException("View cannot be set twice!");
	// }
	// }

	/**
	 * Method for setting a new target point.
	 * @param destination the players destination.
	 */
	public void createNewTarget(final Target destination) {

		robotzData.getPlayer().setDestination(destination);
	}

	// ////////////// GAMESTATE DEPENDABLE METHODS ///////////////////////////

	/**
	 * Method for freezing the game, for example when changing from Robotz App
	 * to homescreen without closing the App completely.
	 */
	public void holdGame() {
		if (robotzData.getState() == GameState.Running) {
			robotzData.setState(GameState.Waiting);
		}
	}

	/**
	 * Method for continuing a previously frozen game.
	 * @param robotzView The RobotzView object.
	 */
	public void continueGame(final UpdateOnlyView robotzView) {
		new Updater(this, robotzView, robotzData).start();
	}

	// ////////////// E V O L V E - M E T H O D S ///////////////////////////

	/**
	 * Method evolves the game state for a specified time of milliseconds.
	 * @param elapsedMilis the milliseconds passed since last call.
	 */
	public void evolve(final long elapsedMilis) {
		moveMobileItems(elapsedMilis);
		checkPlayerOnExit();
		checkPlayerOnFence();
		checkRobotOnFence();
		checkPlayerOnRobot();
	}

	// ////////////// M O T I O N - M E T H O D S ///////////////////////////

	/**
	 * Method moves the MobileItems towards the target for a specific length,
	 * depending on passed milliseconds.
	 * @param elapsedMilis the milliseconds passed since last call.
	 */
	public void moveMobileItems(final long elapsedMilis) {

		final Player player = robotzData.getPlayer();

		if (player.collides(player.getDestination())) {
			player.setDestination(null);
		}
		else {
			player.move(elapsedMilis);
		}

		for (int position = 0; position < robotzData.getAmountRobots(); position++) {

			robotzData.getRobot(position).move(elapsedMilis);
		}
	}

	// ////////////// C H E C K - M E T H O D S ///////////////////////////

	/**
	 * Method checks if the player has reached the Exit.
	 */
	public void checkPlayerOnExit() {

		if (robotzData.getPlayer().collides(robotzData.getExit())) {
			robotzData.setState(GameState.Over);
		}
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
	 * Method checks if the player has run into a fence.
	 */
	public void checkPlayerOnFence() {

		for (int position = 0; position < robotzData.getAmountFences(); position++) {

			if (robotzData.getPlayer().collides(robotzData.getFence(position))) {

				robotzData.setState(GameState.Over);
			}
		}
	}

	/**
	 * Method checks if the player has run into a robot.
	 */
	public void checkPlayerOnRobot() {

		for (int position = 0; position < robotzData.getAmountRobots(); position++) {

			if (robotzData.getPlayer().collides(robotzData.getRobot(position))) {

				robotzData.setState(GameState.Over);
			}
		}
	}

	/**
	 * Method checks if a robot has run into a fence.
	 */
	public void checkRobotOnFence() {

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


///**
// * Method moves the player towards the target for a specific length,
// * depending on passed milliseconds.
// * @param elapsedMilis the milliseconds passed since last call.
// */
//public void movePlayer(final long elapsedMilis) {
//
//	final Player player = robotzData.getPlayer();
//
//	if (player.collides(player.getDestination())) {
//		player.setDestination(null);
//	}
//	else {
//		player.move(elapsedMilis);
//	}
//}
//
///**
// * Method moves the robots towards the player for a specific length,
// * depending on passed milliseconds.
// * @param elapsedMilis the milliseconds passed since last call.
// */
//public void moveRobots(final long elapsedMilis) {
//
//	for (int position = 0; position < robotzData.getAmountRobots(); position++) {
//
//		robotzData.getRobot(position).move(elapsedMilis);
//	}
//}
