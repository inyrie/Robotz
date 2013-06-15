/**
 * Munich University for Applied Science,
 * Faculty 07 for Mathematics and Computer Science
 * Softwareentwicklung II, SS2013, Studiengruppe IF1A
 * Windows XP SP3; Java-Version: 1.7.0_17
 * Developing a Java application.
 */

package edu.hm.fuberg.se2.android.robotz.control;

import edu.hm.fuberg.se2.android.robotz.GameConfig;
import edu.hm.fuberg.se2.android.robotz.data.Arena;
import edu.hm.fuberg.se2.android.robotz.data.GameState;
import edu.hm.fuberg.se2.android.robotz.view.UpdateOnlyView;

/**
 * Class for controlling the robotz data.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-06-13
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
	 * @param data The robotz data.
	 * @param configurator A config object, responsible for parsing data for game setup from an external config file.
	 */
	public RobotzControl(final Arena data, final GameConfig configurator) {

		robotzData = data;
		checker = new Checker(data);
		// creating a tunnelCreator automatically generates tunnels on the gameboard.
		new TunnelCreator(data, configurator);
	}

	// ////////////// S E T T E R ///////////////////////////

	/**
	 * Method for setting a new target point from an double[] array.
	 * @param coords The coordinates for the new target point as double[].
	 */
	public void createNewTarget(final double[] coords) {

		final double[] modelSize = new double[] {robotzData.getWidth(), robotzData.getHeight()};

		// Check if the target coodinates are within arena bounds.
		checker.checkPositionTarget(coords, modelSize, robotzData.getTargetSize());
	}

	// ////////////// GAMESTATE DEPENDABLE METHODS ///////////////////////////

	/**
	 * Bundling method for several game modifications, depending on actions from View-Layer and corresponding calls to
	 * the according callback methods.
	 * @param robotzView The view object.
	 * @param stateHasChanged Flag if the gamestate is to be changed.
	 * @param shouldStart Flag to detect the true game state after initializing the app.
	 */
	public void changeGame(final UpdateOnlyView robotzView, final boolean stateHasChanged, final boolean shouldStart) {

		if (robotzData.getState() == GameState.Waiting && shouldStart) {
			startGame(robotzView);
		}

		else if (!shouldStart && stateHasChanged && robotzData.getState() == GameState.Running) {
			holdGame();
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
	 * Method for changing the gamestate from waiting to running or from running to over, depending on the current
	 * gamestate.
	 */
	private void changeGameState() {

		final GameState state = robotzData.getState();

		if (state == GameState.Waiting) {
			robotzData.setState(GameState.Running);
		}

		else if (state == GameState.Running) {
			robotzData.setState(GameState.Over);
		}
	}

	/**
	 * Method for starting the game at first app start.
	 * @param robotzView The view object.
	 */
	private void startGame(final UpdateOnlyView robotzView) {

		if (robotzData.getState() == GameState.Waiting) {

			changeGameState();
			continueGame(robotzView);
		}
	}

	/**
	 * Method for freezing the game, for example when changing from Robotz App to homescreen without closing the App
	 * completely.
	 */
	private void holdGame() {
		robotzData.setState(GameState.Waiting);
	}

	// ////////////////////////////////////////////////////////////////////

	/**
	 * Method evolves the game state for a specified time of milliseconds.
	 * @param elapsedMilis the milliseconds passed since last call.
	 */
	void evolve(final long elapsedMilis) {

		movePlayer(elapsedMilis);
		moveRobots(elapsedMilis);
		checker.checkTeleport(this);

		// Performing various checks, p.e. if a robot has run into a fence. If any event happens that has an influence
		// on the game state, notifyAll() will trigger a game-over (lost AND won).
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

		if (robotzData.getPlayer().getDestination() != null) {

			if (robotzData.getPlayer().getDestination().collides(robotzData.getPlayer())) {
				robotzData.getPlayer().setDestination(null);
			}
			else {
				robotzData.getPlayer().move(elapsedMilis, robotzData.getWidth(), robotzData.getHeight());
			}
		}
	}

	/**
	 * Method moves the robots towards the player for a specific length, depending on passed milliseconds.
	 * @param elapsedMilis the milliseconds passed since last call.
	 */
	private void moveRobots(final long elapsedMilis) {

		// running through all the robots on the gameboard
		for (int position = 0; position < robotzData.getRobots().size(); position++) {
			robotzData.getRobots().get(position).move(elapsedMilis, robotzData.getWidth(), robotzData.getHeight());
		}
	}

	/**
	 * Method for teleporting the player while keeping his direction.
	 * @param tunnelNumber The tunnel number, identifying a tunnel pair.
	 * @param tunnelIndex The hole tunnelIndex, used to differ between the entry and the exit.
	 */
	void teleport(final int tunnelNumber, final int tunnelIndex) {

		final double entryXCoords = robotzData.getTunnels().get(tunnelNumber).getTunnelPair().get(tunnelIndex)
				.getXCoord();
		final double entryYCoords = robotzData.getTunnels().get(tunnelNumber).getTunnelPair().get(tunnelIndex)
				.getYCoord();

		// getting the other hole of the tunnelpair by manipulating the indices.
		final double exitXCoords = robotzData.getTunnels().get(tunnelNumber).getTunnelPair()
				.get(Math.abs(tunnelIndex - 1)).getXCoord();
		final double exitYCoords = robotzData.getTunnels().get(tunnelNumber).getTunnelPair()
				.get(Math.abs(tunnelIndex - 1)).getYCoord();

		// to prevent access to a null destination if player reaches his destination before he teleports.
		if (robotzData.getPlayer().getDestination() != null) {

			// shifting the target coordinates to the new coordinates after teleportation
			final double newTargetX = robotzData.getPlayer().getDestination().getXCoord() + exitXCoords - entryXCoords;
			final double newTargetY = robotzData.getPlayer().getDestination().getYCoord() + exitYCoords - entryYCoords;
			robotzData.getPlayer().getDestination().shift(newTargetX, newTargetY);
		}

		// shifting the player coordinates to the coordinates of the tunnel exit.
		robotzData.getPlayer().shift(exitXCoords, exitYCoords);

		// deleting the just used tunnel.
		robotzData.removeTunnel(tunnelNumber);
	}
}
