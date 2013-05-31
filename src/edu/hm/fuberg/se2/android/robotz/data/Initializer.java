/**
 * Munich University for Applied Science,
 * Faculty 07 for Mathematics and Computer Science
 * Softwareentwicklung II, SS2013, Studiengruppe IF1A
 * Windows XP SP3; Java-Version: 1.7.0_17
 * Developing a Java application.
 */

package edu.hm.fuberg.se2.android.robotz.data;

import java.util.List;
import java.util.Random;

import edu.hm.fuberg.se2.android.robotz.GameConfig;

/**
 * The Class initializes the gameboard.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-05-20
 */
public class Initializer {

	/** The gameboard list. */
	private final List<String> arena;

	/** The player speed. */
	private final double playerVelocity;

	/** The robot speed. */
	private final double robotVelocity;

	/** The arena data. */
	private final Arena data;

	/** A GameConfig-object responsible to parse config-data from an external file. */
	private final GameConfig configurator;

	/**
	 * Ctor.
	 * @param data The Arena object.
	 * @param configurator A GameConfig-object responsible to parse config-data from an external file.
	 */
	public Initializer(final Arena data, final GameConfig configurator) {

		this.configurator = configurator;
		arena = configurator.getGameboard();
		playerVelocity = configurator.getSpeedPlayer();
		robotVelocity = configurator.getSpeedRobot();
		this.data = data;
	}

	/**
	 * Initializes the complete arena field.
	 * @throws IllegalArgumentException If initializeField() throws Exception.
	 */
	public void initializeArena() {

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

		createTunnel(configurator.getAmountTunnels());

		// Setting the player as target point for every robot on the field.
		for (int position = 0; position < data.getAmountRobots(); position++) {
			data.getRobot(position).setDestination(data.getPlayer());
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

		if (data.getPlayer() == null) {
			data.setPlayer(new Player(width, height, playerVelocity));
		}

		else {
			throw new IllegalArgumentException("Unsupported amount of players");
		}
	}

	/**
	 * Method for initializing an Exit object on a specified position within the arena.
	 * @param width the width index.
	 * @param height the height index.
	 * @throws IllegalArgumentException if two Exits are created.
	 */
	private void initializeExit(final int width, final int height) {

		if (data.getExit() == null) {
			data.setExit(new Exit(width, height));
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
		data.addRobot(new Robot(width, height, robotVelocity));
	}

	/**
	 * Adds one fence to the list.
	 * @param width the width index.
	 * @param height the height index.
	 */
	private void initializeFence(final int width, final int height) {
		data.addFence(new Fence(width, height));
	}

	// ///////////////////// TUNNEL-METHODS //////////////////////////////

	/**
	 * Method for initiating the creation of a tunnel in robotzData from arbitrary coordinates.
	 * @param tunnelCount Amount of tunnels that are to be created.
	 */
	private void createTunnel(final int tunnelCount) {

		for (int number = 0; number < tunnelCount; number++) {

			final double[][] tunnelCoords = generateTunnelCoords();
			data.createTunnel(tunnelCoords);
		}
	}

	/**
	 * Method for generating the coordinates for a complete tunnel, consisting of two holes.
	 * @return The coordinates for the two tunnel holes as double[][] array.
	 */
	private double[][] generateTunnelCoords() {

		final double[] entryCoords = generateHoleCoords();
		final double[] exitCoords = generateHoleCoords();

		return new double[][] {entryCoords, exitCoords};
	}

	/**
	 * Method for choosing an arbitrary free slot on the gameboard for creating one tunnel hole.
	 * @return The coordinates for one tunnel hole as double[] array.
	 */
	private double[] generateHoleCoords() {

		final List<String> freeSlots = configurator.getFreeSlots();

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
