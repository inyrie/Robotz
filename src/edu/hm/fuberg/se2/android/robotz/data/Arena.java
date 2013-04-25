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

	/** The size of a fence. */
	public static final int NOT_IN_FINAL_VERSION = 20;

	/** The height of the Arena. */
	private final double arenaHeight;

	/** The arenaWidth of the Arena. */
	private final double arenaWidth;

	/** The player. */
	private Player player;

	/** The Exit. */
	private Exit exit;

	/** The current state of the Game, either waiting, running or over. */
	private GameState gameState;

	/** The robots. */
	private final List<Robot> robots = new ArrayList<Robot>();

	/** The fences. */
	private final List<Fence> fences = new ArrayList<Fence>();

	// //////////////////// C T O R /////////////////////

	/**
	 * Ctor with parameters for the arena's size.
	 * @param width Bla.
	 * @param height Bla.
	 */
	public Arena(final int width, final int height) {
		try {
			if (width <= 0 || height <= 0) {
				throw new UnsupportedArenaException("Arena's size parameters are not valid.");
			}
		}

		catch (final UnsupportedArenaException e) {

			e.printStackTrace();
		}

		arenaHeight = height;
		arenaWidth = width;
		gameState = GameState.Waiting;
		player = new Player(0, 0);
		exit = new Exit(NOT_IN_FINAL_VERSION, NOT_IN_FINAL_VERSION);
	}

	/**
	 * Ctor for a new arena.
	 * @param arena the arena config.
	 */
	public Arena(final char[][] arena) {

		try {
			if (arena[0].length <= 0 || arena.length <= 0) {
				throw new UnsupportedArenaException("Arena's size parameters are not valid.");
			}
		}

		catch (final UnsupportedArenaException e) {

			e.printStackTrace();
		}

		arenaHeight = arena.length;
		arenaWidth = arena[0].length;
		gameState = GameState.Waiting;
		player = new Player(0, 0);
		exit = new Exit(NOT_IN_FINAL_VERSION, NOT_IN_FINAL_VERSION);
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

	public Player getPlayer() {
		return player;
	}

	public Exit getExit() {
		return exit;
	}

	/**
	 * Getter for a robot.
	 * @param position the position of the robot in the List.
	 * @return the robot.
	 */
	public Robot getRobot(final int position) {
		return robots.get(position);
	}

	/**
	 * Getter for a fence.
	 * @param position the position of the fence in the List.
	 * @return the fence.
	 */
	public Fence getFence(final int position) {
		return fences.get(position);
	}

	/**
	 * Getter for the amount of Robots.
	 * @return the robot.
	 */
	public int getAmountRobots() {

		return robots.size();
	}

	/**
	 * Getter for the amount of Fences.
	 * @return the fence.
	 */
	public int getAmountFences() {

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
	 */
	public void initializeArena(final char[][] arena) {

		//		try {

		for (int width = 0; width < arena[0].length; width++) {

			for (int height = 0; height < arena[0].length; height++) {

				initializeField(arena[width][height], width, height);
			}
		}
		//		}

		//		catch (final UnsupportedArenaException e) {
		//
		//			e.printStackTrace();
		//		}

		for (final Robot robot : robots) {
			robot.setDestination(player);
		}
	}

	/**
	 * Initializes one arena field at the specified position with the respective
	 * object.
	 * @param symbol the decision which item will be initialized.
	 * @param width the width index.
	 * @param height the height index.
	 */
	private void initializeField(final char symbol, final int width, final int height){
		//private void initializeField(final char symbol, final int width, final int height) throws UnsupportedArenaException {
		//		 * @throws UnsupportedArenaException if more than one player and exits are  created.

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
	 * Method for initializing a Player object on a specified position within
	 * the arena.
	 * @param width the width index.
	 * @param height the height index.
	 */
	private void initializePlayer(final int width, final int height) {
		//	private void initializePlayer(final int width, final int height) throws UnsupportedArenaException {
		//		 * @throws UnsupportedArenaException if two Players are created.
		if (player == null) {
			setPlayer(new Player(width, height));
		}
		//		else {
		//			throw new UnsupportedArenaException("Unsupported amount of players");
		//		}
	}

	/**
	 * Method for initializing an Exit object on a specified position within the arena.
	 * @param width the width index.
	 * @param height the height index.
	 */
	private void initializeExit(final int width, final int height) {
		//	private void initializeExit(final int width, final int height) throws UnsupportedArenaException {
		//		 * @throws UnsupportedArenaException if two Player are created.
		if (exit == null) {
			setExit(new Exit(width, height));
		}
		//		else {
		//			throw new UnsupportedArenaException("Unsupported amount of exits");
		//		}
	}

	/**
	 * Adds one robot to the list.
	 * @param width the width index.
	 * @param height the height index.
	 */
	public void initializeRobot(final int width, final int height) {
		robots.add(new Robot(width, height));
	}

	/**
	 * Removes the robot of the list.
	 * @param position the position of the Robot in the list.
	 */
	public void removeRobot(final int position) {
		robots.remove(position);
	}

	/**
	 * Adds one fence to the list.
	 * @param width the width index.
	 * @param height the height index.
	 */
	public void initializeFence(final int width, final int height) {
		fences.add(new Fence(width, height));
	}

	/**
	 * Removes the fence of the list.
	 * @param position the position of the fence in the list.
	 */
	public void removeFence(final int position) {
		fences.remove(position);
	}

	// /////////////////////// ADDITIONAL METHODS //////////////////

	/**
	 * Ctor for a new Arena
	 * @param gameState The current game gameState - "waiting", "running" or
	 *        "over".
	 */

	// public Arena() {
	//
	// try {
	// final FileReader reader = new FileReader("res/arena/Arena1.txt");
	// final BufferedReader buffered = new BufferedReader(reader);
	// {
	//
	// String line;
	//
	// while ((line = buffered.readLine()) != null) {
	//
	// if (arenaWidth != line.length() && arenaWidth != 0) {
	//
	// throw new UnsupportedArenaException("Unsupported Arena size");
	// }
	//
	// else if (arenaWidth == 0) {
	//
	// arenaWidth = line.length();
	// }
	//
	// arenaHeight++;
	//
	// for (int position = 0; position < line.length(); position++) {
	//
	// final char symbol = line.charAt(position);
	//
	// switch (symbol) {
	//
	// case 'P':
	//
	// if (player == null) {
	// player = new Player(position, arenaHeight, null);
	// }
	//
	// else {
	// throw new UnsupportedArenaException("Unsupported amount of players");
	// }
	// break;
	//
	// case 'E':
	//
	// if (exit == null) {
	// exit = new Exit(position, arenaHeight);
	// }
	//
	// else {
	// throw new UnsupportedArenaException("Unsupported amount of exits");
	// }
	// break;
	//
	// case 'R':
	// addRobot(new Robot(position, arenaHeight, new Item()));
	// break;
	//
	// case 'F':
	// addFence(new Fence(position, arenaHeight));
	// break;
	// }
	// }
	// }
	//
	// gameState = gameState;
	// }
	//
	// reader.close();
	// }
	//
	// catch (final FileNotFoundException e) {
	// e.printStackTrace();
	// }
	//
	// catch (final UnsupportedArenaException e) {
	// e.printStackTrace();
	// }
	//
	// catch (final IOException e) {
	// e.printStackTrace();
	// }
	// }
}