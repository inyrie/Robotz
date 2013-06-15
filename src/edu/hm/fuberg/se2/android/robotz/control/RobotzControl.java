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
 * @version 2013-05-30
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
	private final PillChecker pillChecker;

	// ////////////// C T O R ///////////////////////////

	/**
	 * Ctor.
	 * @param data The robotz data.
	 * @param configurator The game configuration.
	 */
	public RobotzControl(final Arena data, final GameConfig configurator) {

		robotzData = data;
		checker = new Checker(data);
		this.configurator = configurator;
		pillChecker = new PillChecker(robotzData, configurator);
	}

	// ////////////// S E T T E R ///////////////////////////

	/**
	 * Method for setting a new target point from an double[] array.
	 * @param coords The coordinates for the new target point as double[].
	 */
	public void createNewTarget(final double[] coords) {

		final double xCoord = coords[0];
		final double yCoord = coords[1];

		final double[] modelSize = {robotzData.getWidth(), robotzData.getHeight()};

		// Check if the target coodinates are within arena bounds.
		checker.checkPosition(xCoord, yCoord, modelSize, robotzData.getTargetSize());
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
		pillChecker.createInvinciblePill();

		// check if the player has gained invincibility.
		if (robotzData.getPlayer().isInvincible()) {
			robotzData.getPlayer().decrementInvincibility((int) elapsedMilis);
		}

		// check if a robot has gained invincibility.
		for (int position = 0; position < robotzData.getRobots().size(); position++) {

			if (robotzData.getRobots().get(position).isInvincible()) {
				// Reduction of invincible time.
				robotzData.getRobots().get(position).decrementInvincibility((int) elapsedMilis, robotzData.getPlayer());
				// Set random Target.
				robotzData.getRobots().get(position).changeDirection((int) elapsedMilis, robotzData.getWidth(), robotzData.getHeight());
			}
		}

		// if a pill currently exists, decrement its lifespan
		if (robotzData.getInvinciblePill() != null) {
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

				robotzData.getRobots().get(position).move(configurator.getFactorRobotSpeed() * elapsedMilis, robotzData.getWidth(), robotzData.getHeight());
			}

			else {

				robotzData.getRobots().get(position).move(elapsedMilis, robotzData.getWidth(), robotzData.getHeight());
			}
		}
	}
}
