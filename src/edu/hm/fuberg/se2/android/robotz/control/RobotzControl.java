/**
 * Munich University for Applied Science,
 * Faculty 07 for Mathematics and Computer Science
 * Softwareentwicklung II, SS2013, Studiengruppe IF1A
 * Windows XP SP3; Java-Version: 1.7.0_17
 * Developing a Java application.
 */

package edu.hm.fuberg.se2.android.robotz.control;

import java.util.List;
import java.util.Random;

import edu.hm.fuberg.se2.android.robotz.data.Arena;
import edu.hm.fuberg.se2.android.robotz.data.GameState;
import edu.hm.fuberg.se2.android.robotz.view.UpdateOnlyView;

/**
 * Class for controlling the robotz data.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-05-20
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
	 * Method for setting a new target point from an double[] array.
	 * @param coords The coordinates for the new target point as double[].
	 */
	public void createNewTarget(final double[] coords) {

		final double xCoord = coords[0];
		final double yCoord = coords[1];

		final double modelSize[] = {robotzData.getWidth(), robotzData.getHeight()};

		// Check if the target coodinates are within arena bounds.
		checkPosition(xCoord, yCoord, modelSize, robotzData.getTargetSize());
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

		if (robotzData.getPlayer().collides(robotzData.getPlayer().getDestination())) {
			robotzData.getPlayer().setDestination(null);
		}
		else {
			robotzData.getPlayer().move(elapsedMilis, robotzData.getWidth(), robotzData.getHeight());
		}
	}

	/**
	 * Method moves the robots towards the player for a specific length, depending on passed milliseconds.
	 * @param elapsedMilis the milliseconds passed since last call.
	 */
	private void moveRobots(final long elapsedMilis) {

		// running through all the robots on the gameboard
		for (int position = 0; position < robotzData.getAmountRobots(); position++) {
			robotzData.getRobot(position).move(elapsedMilis, robotzData.getWidth(), robotzData.getHeight());
		}
	}

	/**
	 * Method checks if target coordinates are within the gameboard bounds and sets the target accordingly.
	 * @param xCoord The x coordinate to check.
	 * @param yCoord The y coordinate to check.
	 * @param modelSize The modelsize of the gameboard.
	 * @param targetSize The target size.
	 */
	private void checkPosition(final double xCoord, final double yCoord, final double[] modelSize,
			final double targetSize) {

		if (xCoord < modelSize[0] - targetSize && yCoord < modelSize[1] - targetSize && xCoord > 0 && yCoord > 0) {
			robotzData.getPlayer().setDestination(xCoord, yCoord);
		}
	}

	// //////////////////// TUNNEL-METHODS //////////////////////////////////

	private void createTunnel(final List<String> freeSlots) {

		final double[][] tunnelCoords = generateTunnelCoords(freeSlots);
		robotzData.createTunnel(tunnelCoords);
	}

	/**
	 * Method for generating the coordinates for a complete tunnel, consisting of two holes.
	 * @param freeSlots
	 * @return The coordinates for the two tunnel holes as double[][] array.
	 */
	private double[][] generateTunnelCoords(final List<String> freeSlots) {

		final double[] entryCoords = generateHoleCoords(freeSlots);
		final double[] exitCoords = generateHoleCoords(freeSlots);

		return new double[][] {entryCoords, exitCoords};
	}

	/**
	 * Method for choosing an arbitrary free slot on the gameboard for creating one tunnel hole.
	 * @param freeSlots A List of available slots where a tunnel could be placed.
	 * @return The coordinates for one tunnel hole as double[] array.
	 */
	private double[] generateHoleCoords(final List<String> freeSlots) {

		// new Random object for generating random integer values.
		final Random random = new Random();
		final int freeSlotIndex = random.nextInt(freeSlots.size());

		// choose one random entry from a list of possible slots.
		// format of entry in array list will be "x-y", x and y being one or two digits.
		final String chosenSlot = freeSlots.get(freeSlotIndex);

		// create two substrings from entry, the first representing the x-coordinate, the other the y-coordinate.
		final String[] substrings = chosenSlot.split("/");

		// finally parsing the values for the coordinates.
		final double xCoord = Double.parseDouble(substrings[0]);
		final double yCoord = Double.parseDouble(substrings[1]);

		// deleting the slot now occupied by a tunnel hole from the list of available free slots on the gameboard.
		freeSlots.remove(freeSlotIndex);

		return new double[] {xCoord, yCoord};
	}
}
