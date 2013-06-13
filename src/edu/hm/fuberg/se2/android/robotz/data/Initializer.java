/**
 * Munich University for Applied Science,
 * Faculty 07 for Mathematics and Computer Science
 * Softwareentwicklung II, SS2013, Studiengruppe IF1A
 * Windows XP SP3; Java-Version: 1.7.0_17
 * Developing a Java application.
 */

package edu.hm.fuberg.se2.android.robotz.data;

import java.util.List;

import edu.hm.fuberg.se2.android.robotz.GameConfig;

/**
 * The Class initializes the gameboard.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-06-13
 */
public class Initializer {

	/** The gameboard list. */
	private final List<String> arena;

	/** The player speed. */
	private final double playerVelocity;

	/** The robot speed. */
	private final double robotVelocity;

	/** The arena robotzData. */
	private final Arena robotzData;

	/**
	 * Ctor.
	 * @param robotzData The Arena object.
	 * @param configurator A GameConfig-object responsible to parse config-robotzData from an external file.
	 */
	public Initializer(final Arena data, final GameConfig configurator) {

		arena = configurator.getGameboard();
		playerVelocity = configurator.getSpeedPlayer();
		robotVelocity = configurator.getSpeedRobot();
		robotzData = data;
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

		// Setting the player as target point for every robot on the field.
		for (int position = 0; position < robotzData.getRobots().size(); position++) {
			robotzData.getRobots().get(position).setDestination(robotzData.getPlayer());
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
			// if parsed symbol does not match a specified case, just do nothing.
		}
	}

	/**
	 * Method for initializing a Player object on a specified position within the arena.
	 * @param width the width index.
	 * @param height the height index.
	 * @throws IllegalArgumentException if two Players are created.
	 */
	private void initializePlayer(final int width, final int height) {

		if (robotzData.getPlayer() == null) {
			robotzData.setPlayer(new Player(width, height, playerVelocity));
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

		if (robotzData.getExit() == null) {
			robotzData.setExit(new Exit(width, height));
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
		robotzData.addRobot(new Robot(width, height, robotVelocity));
	}

	/**
	 * Adds one fence to the list.
	 * @param width the width index.
	 * @param height the height index.
	 */
	private void initializeFence(final int width, final int height) {
		robotzData.addFence(new Fence(width, height));
	}

}
