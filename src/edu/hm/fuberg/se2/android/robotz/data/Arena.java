/**
 * Munich University for Applied Science,
 * Faculty 07 for Mathematics and Computer Science
 * Softwareentwicklung II, SS2013, Studiengruppe IF1A
 * Windows XP SP3; Java-Version: 1.7.0_17
 * Developing a Java application.
 */

package edu.hm.fuberg.se2.android.robotz.data;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class describes the playing arena of Robotz.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-04-25
 */
public final class Arena implements ReadOnlyArena {

	/** Temporary constant for the x- and y-coordinate of the exit. */
	public static final int EXIT_POSITION_TMP = 20;

	/** The height of the Arena. */
	private final double arenaHeight;
	/** The arenaWidth of the Arena. */
	private final double arenaWidth;
	/** The robots. */
	private final List<Robot> robots = new ArrayList<Robot>();
	/** The fences. */
	private final List<Fence> fences = new ArrayList<Fence>();

	/** The player. */
	private Player player;
	/** The Exit. */
	private Exit exit;
	/** The current state of the Game, either waiting, running or over. */
	private GameState gameState;

	// //////////////////// C T O R /////////////////////

	/**
	 * Ctor with parameters for the arena's size for directly initializing a gamefield with the according size. Only for
	 * testing purposes, will be removed eventually.
	 * @param width The arena's width.
	 * @param height The arena's height.
	 * @throws IllegalArgumentException If width or height parameters are zero or less.
	 */
	public Arena(final int width, final int height) {

		if (width <= 0 || height <= 0) {
			throw new IllegalArgumentException("Arena's size parameters are not valid.");
		}

		arenaHeight = height;
		arenaWidth = width;
		gameState = GameState.Waiting;
		player = new Player(0, 0);
		exit = new Exit(EXIT_POSITION_TMP, EXIT_POSITION_TMP);
	}

	/**
	 * Ctor for a new arena.
	 * @param arena the arena config.
	 * @throws IllegalArgumentException If parameters for width or height are zero or less.
	 */
	public Arena(final List<String> arena) {

		if (arena == null || arena.size() <= 0 || arena.get(0).length() <= 0) {
			throw new IllegalArgumentException("Arena's size parameters are not valid.");
		}

		arenaHeight = arena.size();
		arenaWidth = arena.get(0).length();
		gameState = GameState.Waiting;

		initializeArena(arena);
	}

	// //////////////////// G E T T E R /////////////////////

	@Override public double getHeight() {
		return arenaHeight;
	}

	@Override public double getWidth() {
		return arenaWidth;
	}

	@Override public GameState getState() {
		return gameState;
	}

	@Override public Player getPlayer() {
		return player;
	}

	@Override public Exit getExit() {
		return exit;
	}

	/**
	 * Getter for a robot.
	 * @param position the position of the robot in the List.
	 * @return the robot.
	 */
	@Override public Robot getRobot(final int position) {
		return robots.get(position);
	}

	/**
	 * Getter for a fence.
	 * @param position the position of the fence in the List.
	 * @return the fence.
	 */
	@Override public Fence getFence(final int position) {
		return fences.get(position);
	}

	/**
	 * Getter for the amount of Robots.
	 * @return the robot.
	 */
	@Override public int getAmountRobots() {
		return robots.size();
	}

	/**
	 * Getter for the amount of Fences.
	 * @return the fence.
	 */
	@Override public int getAmountFences() {
		return fences.size();
	}

	// //////////////////// S E T T E R /////////////////////

	/**
	 * Setter for the Player object.
	 * @param player The Player object to set.
	 */
	public void setPlayer(final Player player) {
		this.player = player;
	}

	/**
	 * Setter for the Exit object.
	 * @param exit the Exit object to set.
	 */
	public void setExit(final Exit exit) {
		this.exit = exit;
	}

	/**
	 * Setter for the game's current state.
	 * @param state The current game state.
	 */
	public void setState(final GameState state) {
		gameState = state;
	}

	// /////////////////////// VAR. METHODS //////////////////

	/**
	 * Initializes the complete arena field.
	 * @param arena the GameBoard.
	 * @throws IllegalArgumentException If initializeField() throws Exception.
	 */
	private void initializeArena(final List<String> arena) {

		try {
			// Running through the List object for the gamefield column-wise for initialization.
			for (int colIndex = 0; colIndex < arena.size(); colIndex++) {

				// Running through every row of the List object for single field initialization.
				for (int rowIndex = 0; rowIndex < arena.get(0).length(); rowIndex++) {

					initializeField(arena.get(colIndex).charAt(rowIndex), rowIndex, colIndex);
				}
			}
		}

		catch (final IllegalArgumentException e) {
			e.printStackTrace();
		}

		// Setting the player as target point for every robot on the field.
		for (final Robot robot : robots) {
			robot.setDestination(player);
		}
	}

	/**
	 * Initializes one arena field at the specified position with the respective object.
	 * @param symbol the decision which item will be initialized.
	 * @param width the width index.
	 * @param height the height index.
	 * @throws IllegalArgumentException if more than one player and exits are created.
	 */
	private void initializeField(final char symbol, final int width, final int height) {

		// Deciding which object has to be initialized, depending on the symbolic character in the gamefield-array.
		switch (symbol) {

		case 'P':
			initializePlayer(width, height);
			break;

		case 'E':
			initializeExit(width, height);
			break;

		case 'R':
			initializeRobot(width, height);
			break;

		case 'F':
			initializeFence(width, height);
			break;

		default:
		}
	}

	/**
	 * Method for initializing a Player object on a specified position within the arena.
	 * @param width the width index.
	 * @param height the height index.
	 * @throws IllegalArgumentException if two Players are created.
	 */
	private void initializePlayer(final int width, final int height) {

		if (player == null) {
			setPlayer(new Player(width, height));
		}

		else {
			throw new IllegalArgumentException("Unsupported amount of players");
		}
	}

	/**
	 * Method for initializing an Exit object on a specified position within the arena.
	 * @param width the width index.
	 * @param height the height index.
	 * @throws IllegalArgumentException if two Player are created.
	 */
	private void initializeExit(final int width, final int height) {

		if (exit == null) {
			setExit(new Exit(width, height));
		}
		else {
			throw new IllegalArgumentException("Unsupported amount of exits");
		}
	}

	/**
	 * Adds one robot to the list.
	 * @param width the width index.
	 * @param height the height index.
	 */
	private void initializeRobot(final int width, final int height) {
		robots.add(new Robot(width, height));
	}

	/**
	 * Adds one fence to the list.
	 * @param width the width index.
	 * @param height the height index.
	 */
	private void initializeFence(final int width, final int height) {
		fences.add(new Fence(width, height));
	}

	/**
	 * Removes one robot from the list.
	 * @param position the position of the Robot in the list.
	 */
	public void removeRobot(final int position) {

		if (robots.get(position) != null) {
			robots.remove(position);
		}
	}

	/**
	 * Removes one fence from the list.
	 * @param position the position of the fence in the list.
	 */
	public void removeFence(final int position) {

		if (fences.get(position) != null) {
			fences.remove(position);
		}
	}
}