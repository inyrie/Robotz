/**
 * Munich University for Applied Science,
 * Faculty 07 for Mathematics and Computer Science
 * Softwareentwicklung II, SS2013, Studiengruppe IF1A
 * Windows XP SP3; Java-Version: 1.7.0_17
 * Developing a Java application.
 */

package edu.hm.fuberg.se2.android.robotz.control;

import java.util.Random;

import edu.hm.fuberg.se2.android.robotz.GameConfig;
import edu.hm.fuberg.se2.android.robotz.data.Arena;
import edu.hm.fuberg.se2.android.robotz.data.GameState;
import edu.hm.fuberg.se2.android.robotz.view.UpdateOnlyView;

//import edu.hm.fuberg.se2.android.robotz.data.InvinciblePill;

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
	/** The game configuration. */
	private final GameConfig configurator;
	/** A checker object. */
	private final Checker checker;
	/** A pill checker object. */
	private PillChecker pillChecker;

	// ////////////// C T O R ///////////////////////////

	/**
	 * Ctor.
	 * @param data the robotz data.
	 */
	public RobotzControl(final Arena data, final GameConfig configurator) {

		robotzData = data;
		checker = new Checker(data);
		this.configurator = configurator;
	}

	// ////////////// S E T T E R ///////////////////////////

	/**
	 * Method for generating and setting a new PillChecker object.
	 * @param pillCheckerObject The pill checker object.
	 */
	private void setPillChecker(final PillChecker pillCheckerObject) {
		pillChecker = pillCheckerObject;
	}

	/**
	 * Method for setting a new target point from an double[] array.
	 * @param coords The coordinates for the new target point as double[].
	 */
	public void createNewTarget(final double[] coords) {

		final double xCoord = coords[0];
		final double yCoord = coords[1];

		final double[] modelSize = {robotzData.getWidth(), robotzData.getHeight()};

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

		// possible creation of a pill of invincibility
		createInvinciblePill();

		// check if the player has gained invincibility.
		if (robotzData.getPlayer().isInvincible()) {
			robotzData.getPlayer().decrementInvincibility((int) elapsedMilis);
		}

		// check if a robot has gained invincibility.
		for (int position = 0; position < robotzData.getRobots().size(); position++) {

			if (robotzData.getRobots().get(position).isInvincible()) {
				robotzData.getRobots().get(position).decrementInvincibility((int) elapsedMilis);
			}
		}

		// // dieser Block ist nur fuer testzwecke...
		// final boolean test = robotzData.getInvinciblePill() != null;
		// Log.d("robotz_invincible", "RobotzControl => evolve() => Pille da: " + test);
		// // ***

		// if a pill currently exists, decrement its lifespan
		if (robotzData.getInvinciblePill() != null) {
			// Log.d("robotz_invincible", "RobotzControl => evolve() => countdown für Pille!");
			robotzData.getInvinciblePill().decrementCountdown((int) elapsedMilis);
		}

		// pillChecker only exists if a pill is actually created!
		if (pillChecker != null) {

			// check if the player has reached the pill
			pillChecker.playerTakesPill();
			// check if a robot has reached the pill
			pillChecker.robotTakesPill();
			// check if an existent pill has reached the end of its lifespan
			pillChecker.isPillLifespanExtended();
		}

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
		for (int position = 0; position < robotzData.getRobots().size(); position++) {

			if (robotzData.getRobots().get(position).isInvincible()){

				robotzData.getRobots().get(position).move((long)configurator.getFactorRobotSpeed() * elapsedMilis, robotzData.getWidth(), robotzData.getHeight());
			}

			else {

				robotzData.getRobots().get(position).move(elapsedMilis, robotzData.getWidth(), robotzData.getHeight());
			}
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

	// ////////////////// P I L L - M E T H O D S ////////////////////

	/**
	 * Method creates randomly an invincible pill.
	 */
	private void createInvinciblePill() {

		if (robotzData.getInvinciblePill() == null) {

			final Random random = new Random();
			final int probability = random.nextInt((int)configurator.getRandomPill());

			if (probability == 0) {
				createPossiblePill(random);
			}
		}
	}

	/**
	 * Method checks if the invincible pill is created on an other item.
	 * @param random The random object.
	 */
	private void createPossiblePill(final Random random) {

		boolean noFreeSlot = true;

		while (noFreeSlot) {

			final int xCoord = random.nextInt((int) robotzData.getWidth());
			final int yCoord = random.nextInt((int) robotzData.getHeight());

			setPillChecker(new PillChecker(robotzData));

			if (!pillChecker.invinciblePillOnItem(xCoord, yCoord)) {

				robotzData.setInvinciblePill(xCoord, yCoord, (int)configurator.getDurationPill());
				noFreeSlot = false;
			}
		}
	}
}
