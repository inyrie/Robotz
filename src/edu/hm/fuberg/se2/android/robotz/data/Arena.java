package edu.hm.fuberg.se2.android.robotz.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * The Class describes the playing arena of Robotz.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 */
public final class Arena implements ReadOnlyArena {

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

	// //////////////////// C T O R /////////////////////

	/**
	 * Ctor.
	 * @param state Bla.
	 * @param height Bla.
	 * @param width Bla.
	 * @throws UnsupportedArenaException Bla.
	 */
	public Arena(final GameState state, final double height, final double width) throws UnsupportedArenaException {

		if (width <= 0 || height <= 0) {
			throw new UnsupportedArenaException("Arena's size parameters are not valid.");
		}
		arenaHeight = height;
		arenaWidth = width;
		gameState = state;
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
	 */
	public void initializeArena() {
		try {
			final FileReader reader = new FileReader("res/arena/Arena1.txt");
			final BufferedReader bufferedReader = new BufferedReader(reader);

			int height = 0;
			final String readLine = bufferedReader.readLine();

			// Running through all the lines provided by initial .txt-file
			// defining the arena's setup.
			while (readLine != null && readLine.length() == getWidth()) {

				// Running through the line's every character checking for
				// Objects to initialize.
				for (int position = 0; position < getWidth(); position++) {
					initializeField(readLine.charAt(position), position, height);
				}
				height++;
			}
			reader.close(); // guck nochmal wegen der Syntax mit den Readern und
			// dem close(), bin mir grad unsicher...
		}

		catch (final FileNotFoundException fileNotFound) {
			fileNotFound.printStackTrace();
		}

		catch (final UnsupportedArenaException unsupportedArena) {
			unsupportedArena.printStackTrace();
		}

		catch (final IOException ioException) {
			ioException.printStackTrace();
		}
	}

	/**
	 * Initializes one arena field at the specified position with the respective
	 * object.
	 * @param symbol bla.
	 * @param position bla.
	 * @param height bla.
	 * @throws UnsupportedArenaException bla.
	 */
	private void initializeField(final char symbol, final int position, final int height)
			throws UnsupportedArenaException {

		switch (symbol) {
		case 'P':
			initializePlayer(position, height);
			break;

		case 'E':
			initializeExit(position, height);
			break;

			// case 'R':
			// initializeRobot(position, arenaHeight, null);
			// break;

			// case 'F':
			// initializeFence(position, arenaHeight);
			// break;

		default:
		}
	}

	/**
	 * Method for initializing a Player object on a specified position within
	 * the arena.
	 * @param position bla.
	 * @param height bla.
	 * @throws UnsupportedArenaException bla.
	 */
	private void initializePlayer(final int position, final int height) throws UnsupportedArenaException {

		if (player != null) {
			throw new UnsupportedArenaException("Unsupported amount of players");
		}

		setPlayer(new Player(position, height, null));
	}

	/**
	 * Method for initializing an Exit object on a specified position within the
	 * arena.
	 * @param position bla.
	 * @param height bla.
	 * @throws UnsupportedArenaException bla.
	 */
	private void initializeExit(final int position, final int height) throws UnsupportedArenaException {

		if (exit != null) {
			throw new UnsupportedArenaException("Unsupported amount of exits");
		}

		setExit(new Exit(position, height));
	}

	// /////////////////////// ADDITIONAL METHODS //////////////////

	// Spaeter coden, wenn Roboter und Zaeune auf dem Feld gebraucht werden!

	// private void initializeRobot(final int position, final int arenaHeight,
	// final
	// Item haeh) {
	// // Check, ob im Array ueberhaupt noch Platz fuer einen weiteren Roboter
	// // ist!
	// final Robot robot = new Robot(position, arenaHeight, new Item());
	// addRobot(hereBeArrayIndex);
	// }
	//
	// private void initializeFence(final int position, final int arenaHeight) {
	// // Check, ob im Array ueberhaupt noch Platz fuer einen weiteren Roboter
	// // ist!
	// final Fence fence = new Fence(position, arenaHeight);
	// addFence(hereBeArrayIndex);
	// }

	// /**
	// * Puts one more fence to the arena.
	// * @param fence the fence.
	// */
	// public Arena addFence(final Fence fence) {
	// // here be magical code:
	// // copy this.Fence[] into local Fence[fences.length + 1]-Array.
	// // Fence[length-1] = fence
	// // overwrite reference from OV (?)
	// return this;
	// }

	// /**
	// * Removes the fence from the arena.
	// * @param position the position of the fence in the list.
	// */
	// public Arena removeFence(final int position) {
	// // here be magical code:
	// // copy this.Fence[] into local Fence[] variable
	// // set Fence[position] = null;
	// return this;
	// }

	// /** Adds one more Robot to the arena. */
	// public Arena addRobot(final Robot robot) {
	// // here be magical code:
	// // copy this.Robot[] into local Robot[robots.length + 1]-Array.
	// // Fence[length-1] = fence
	// // overwrite reference from OV (?)
	// return this;
	// }

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

	// ... jaja, wie war das? Ich trau mich nicht, den Code zu loeschen, deshalb
	// kommentier ich ihn noch aus... ^^
	// Sind nur ein paar grobe Ueberlegungen ;)
}
