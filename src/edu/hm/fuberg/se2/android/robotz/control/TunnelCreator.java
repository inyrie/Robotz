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

import edu.hm.fuberg.se2.android.robotz.GameConfig;
import edu.hm.fuberg.se2.android.robotz.data.Arena;

/**
 * The Class initializes the gameboard.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-06-13
 */
public class TunnelCreator {

	/** An Arena object that holds all game data. */
	private final Arena data;
	/** A config object that is responsible for parsing config data needed to set up the gameboard. */
	private final GameConfig configurator;
	/** A Random object for generating random integer values. */
	private final Random random;

	/**
	 * Ctor.
	 * @param data A data object holding the game data.
	 * @param configurator A config object holding all information parsed from an external config file to set up the
	 *        gameboard.
	 */
	TunnelCreator(final Arena data, final GameConfig configurator) {
		this.data = data;
		this.configurator = configurator;
		random = new Random();
		createTunnel(configurator.getAmountTunnels());
	}

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
		final int freeSlotIndex = random.nextInt(freeSlots.size());

		// choose one random entry from a list of possible slots.
		// format of entry in array list will be "x/y", x and y being one or two digits.
		final String chosenSlot = freeSlots.get(freeSlotIndex);

		// create two substrings from entry, the first representing the x-coordinate, the other the y-coordinate.
		final String[] substrings = chosenSlot.split("/");

		// parsing the values for the coordinates.
		final double xCoord = Double.parseDouble(substrings[0]);
		final double yCoord = Double.parseDouble(substrings[1]);

		// deleting the slot now occupied by a tunnel hole from the list of available free slots on the gameboard.
		freeSlots.remove(freeSlotIndex);

		return new double[] {xCoord, yCoord};

	}
}
