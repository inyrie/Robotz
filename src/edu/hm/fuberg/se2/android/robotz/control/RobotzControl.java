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
 * @version 2013-05-17
 */
public class RobotzControl {

	// ////////////// OBJECT VARIABLES ///////////////////////////

	/** The robotz data. */
	private final Arena robotzData;
	/** A checker object. */
	private final Checker checker;

	// ////////////// C T O R ///////////////////////////

	/**
	 * Ctor.
	 * @param data the robotz data.
	 */
	public RobotzControl(final Arena data) {

		robotzData = data;
		checker = new Checker(data);
	}

	// ////////////// S E T T E R ///////////////////////////

	/**
	 * Method for setting a new target point.
	 * @param destination the players destination.
	 */
	public void createNewTarget(final Target destination) {

		final double[] coords = new double[] {destination.getXCoord(), destination.getYCoord()};
		createNewTarget(coords);
	}

	public void createNewTarget(final double[] coords) {

		final double xCoord = coords[0];
		final double yCoord = coords[1];

		final double modelSize = Math.min(robotzData.getHeight(), robotzData.getWidth());

		if (xCoord < modelSize && yCoord < modelSize) {
			robotzData.getPlayer().setDestination(new Target(xCoord, yCoord));
		}

		// ALTER CODE AUS DER CONVERTER.PIXELTOMODELCOORDS()!!

		// if (modelX < modelSize && modelY < modelSize) {
		// return new Target(modelX, modelY);
		// }
		//
		// else if (robotzData.getPlayer().getDestination() == null) {
		// return null;
		// }
		//
		// else {
		//
		// return new Target(robotzData.getPlayer().getDestination().getXCoord(), robotzData.getPlayer()
		// .getDestination().getYCoord());
		// }
	}

	// ////////////// GAMESTATE DEPENDABLE METHODS ///////////////////////////

	public void changeGame(final UpdateOnlyView robotzView, final boolean hasChanged) {

		changeGameState(hasChanged);

		final GameState state = robotzData.getState();

		if (state == GameState.Waiting) {
			startGame(robotzView);
		}

		if (state == GameState.Running) {
			holdGame();
		}

	}

	/**
	 * Method for changing the gamestate from waiting to running or from running to over, depending on the current
	 * gamestate.
	 */
	private void changeGameState(final boolean hasChanged) {

		final GameState state = robotzData.getState();

		if (hasChanged && state == GameState.Waiting) {
			robotzData.setState(GameState.Running);
		}
		else if (hasChanged && state == GameState.Running) {
			robotzData.setState(GameState.Over);
		}
	}

	/**
	 * @param robotzView
	 */
	public void startGame(final UpdateOnlyView robotzView) {

		if (robotzData.getState() == GameState.Waiting) {

			changeGameState(true);
			continueGame(robotzView);
		}
	}

	/**
	 * Method for continuing a previously frozen game.
	 * @param robotzView The RobotzView object.
	 */
	public void continueGame(final UpdateOnlyView robotzView) {
		new Updater(this, robotzView, robotzData).start();
	}

	/**
	 * Method for freezing the game, for example when changing from Robotz App to homescreen without closing the App
	 * completely.
	 */
	private void holdGame() {
		// if (robotzData.getState() == GameState.Running) {
		robotzData.setState(GameState.Waiting);
		// }
	}

	// ////////////////////////////////////////////////////////////////////

	/**
	 * Method evolves the game state for a specified time of milliseconds.
	 * @param elapsedMilis the milliseconds passed since last call.
	 */
	public void evolve(final long elapsedMilis) {

		movePlayer(elapsedMilis);
		moveRobots(elapsedMilis);

		// Performing various checks, p.e. if a robot has run into a fence. If any event happens that has an influence
		// on the game state, notifyAll() will trigger a game over (lost AND won).
		if (checker.masterChecker()) {
			synchronized (this) {
				notifyAll();
			}
		}
	}

	// ////////////// M O T I O N - M E T H O D S ///////////////////////////

	/**
	 * Method moves the player towards the target for a specific length, depending on passed milliseconds.
	 * @param elapsedMilis the milliseconds passed since last call.
	 */
	private void movePlayer(final long elapsedMilis) {

		final Player player = robotzData.getPlayer();

		if (player.collides(player.getDestination())) {
			player.setDestination(null);
		}
		else {
			player.move(elapsedMilis);
		}
	}

	/**
	 * Method moves the robots towards the player for a specific length, depending on passed milliseconds.
	 * @param elapsedMilis the milliseconds passed since last call.
	 */
	private void moveRobots(final long elapsedMilis) {

		for (int position = 0; position < robotzData.getAmountRobots(); position++) {
			robotzData.getRobot(position).move(elapsedMilis);
		}
	}
}
