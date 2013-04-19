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
	private final double height;

	/** The width of the Arena. */
	private final double width;

	/** The player. */
	private Player player;

	/** The Exit. */
	private Exit exit;

	/** The current state of the Game, either waiting, running or over. */
	private GameState gameState;

	// //////////////////// C T O R /////////////////////

	/**
	 * Ctor.
	 * @param gameState
	 * @param robots
	 * @param fences
	 */
	public Arena(final GameState gameState, final double height, final double width) {

		if (width <= 0 || height <= 0) {
			throw new RuntimeException("Arena's size parameters are not valid.");
		}
		this.height = height;
		this.width = width;
		this.gameState = gameState;
	}

	// //////////////////// G E T T E R /////////////////////

	@Override public double getHeight() {
		return height;
	}

	@Override public double getWidth() {
		return width;
	}

	@Override public GameState getState() {
		return gameState;
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

	public void initializeArena(final FileReader reader, final BufferedReader bufferedReader) {
		try {
			// in der Klasse instanziieren, die die initializeArena() aufruft!

			// final FileReader reader = new FileReader("res/arena/Arena1.txt");
			// final BufferedReader bufferedReader = new BufferedReader(reader);

			int height = 0;
			final String readLine = bufferedReader.readLine();

			while (readLine != null && readLine.length() == getWidth()) {

				for (int position = 0; position < getWidth(); position++) {
					initializeField(readLine.charAt(position), position, height);
				}
				height++;
			}
			reader.close();
		}

		catch (final FileNotFoundException fileNotFound) {
			fileNotFound.printStackTrace();
		}

		catch (final UnsupportedArenaException e) {
			e.printStackTrace();
		}

		catch (final IOException e) {
			e.printStackTrace();
		}
	}

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
		// initializeRobot(position, height, null);
		// break;

		// case 'F':
		// initializeFence(position, height);
		// break;

		default:
			break;
		}
	}

	private void initializePlayer(final int position, final int height) throws UnsupportedArenaException {

		if (player != null) {
			throw new UnsupportedArenaException("Unsupported amount of players");
		}

		setPlayer(new Player(position, height, null));
	}

	private void initializeExit(final int position, final int height) throws UnsupportedArenaException {

		if (exit != null) {
			throw new UnsupportedArenaException("Unsupported amount of exits");
		}

		setExit(new Exit(position, height));
	}

	// /////////////////////// ADDITIONAL METHODS //////////////////

	// Spaeter coden, wenn Roboter und Zaeune auf dem Feld gebraucht werden!

	// private void initializeRobot(final int position, final int height, final
	// Item haeh) {
	// // Check, ob im Array ueberhaupt noch Platz fuer einen weiteren Roboter
	// // ist!
	// final Robot robot = new Robot(position, height, new Item());
	// addRobot(hereBeArrayIndex);
	// }
	//
	// private void initializeFence(final int position, final int height) {
	// // Check, ob im Array ueberhaupt noch Platz fuer einen weiteren Roboter
	// // ist!
	// final Fence fence = new Fence(position, height);
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
	// if (width != line.length() && width != 0) {
	//
	// throw new UnsupportedArenaException("Unsupported Arena size");
	// }
	//
	// else if (width == 0) {
	//
	// width = line.length();
	// }
	//
	// height++;
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
	// player = new Player(position, height, null);
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
	// exit = new Exit(position, height);
	// }
	//
	// else {
	// throw new UnsupportedArenaException("Unsupported amount of exits");
	// }
	// break;
	//
	// case 'R':
	// addRobot(new Robot(position, height, new Item()));
	// break;
	//
	// case 'F':
	// addFence(new Fence(position, height));
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
