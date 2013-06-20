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

/**
 * Class for defining various check methods concerning the ultimate pill for invincibility.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-06-20
 */
class PillChecker {

	/** The data object. */
	private final Arena robotzData;

	/** The game configuration. */
	private final GameConfig configurator;

	/** Generates random numbers. */
	private final Random random = new Random();

	/**
	 * Ctor.
	 * @param dataObject The data object.
	 * @param configurator The game configuration.
	 */
	PillChecker(final Arena dataObject, final GameConfig configurator) {

		robotzData = dataObject;
		this.configurator = configurator;
	}

	/**
	 * Method creates randomly an invincible pill.
	 */
	void createInvinciblePill() {

		if (robotzData.getInvinciblePill() == null) {

			final int probability = random.nextInt(configurator.getRandomPill());

			if (probability == 0) {
				createPossiblePill();
			}
		}
	}

	/**
	 * Method checks if the invincible pill is created on an other item.
	 * @param random The random object.
	 */
	void createPossiblePill() {

		boolean noFreeSlot = true;

		while (noFreeSlot) {

			final int xCoord = random.nextInt(robotzData.getWidth());
			final int yCoord = random.nextInt(robotzData.getHeight());

			if (!invinciblePillOnItem(xCoord, yCoord)) {

				robotzData.setInvinciblePill(xCoord, yCoord, configurator.getDurationPill());
				noFreeSlot = false;
			}
		}
	}

	/**
	 * Method deletes the invincible pill if the lifespan is extended.
	 */
	void isPillLifespanExtended() {

		if (robotzData.getInvinciblePill() != null && robotzData.getInvinciblePill().getPillCountdown() == 0) {
			robotzData.deleteInvinciblePill();
		}
	}

	/**
	 * Method checks if the player has run into the pill.
	 */
	void playerTakesPill() {

		if (robotzData.getPlayer().collides(robotzData.getInvinciblePill())) {

			robotzData.deleteInvinciblePill();
			robotzData.getPlayer().setInvincibility();
		}
	}

	/**
	 * Method checks if the player has run into the pill.
	 */
	void robotTakesPill() {

		for (int position = 0; position < robotzData.getRobots().size(); position++) {

			if (robotzData.getRobots().get(position).collides(robotzData.getInvinciblePill())) {

				robotzData.deleteInvinciblePill();
				robotzData.getRobots().get(position).setInvincibility();
			}
		}
	}

	/**
	 * Methods checks if the new pill will collide with an existing item.
	 * @param xCoord The possible new x coordinate.
	 * @param yCoord The possible new y coordinate.
	 * @return true if the invincible pill would collide with an item.
	 */
	boolean invinciblePillOnItem(final double xCoord, final double yCoord) {

		return isPillSetOnPlayer(xCoord, yCoord) || isPillSetOnExit(xCoord, yCoord) || isPillSetOnFence(xCoord, yCoord)
				|| isPillSetOnRobot(xCoord, yCoord);
	}

	/**
	 * Methods checks if the new pill will collide with an robot.
	 * @param xCoord The possible new x coordinate.
	 * @param yCoord The possible new y coordinate.
	 * @return true if the invincible pill would collide with an robot.
	 */
	boolean isPillSetOnRobot(final double xCoord, final double yCoord) {

		boolean result = false;

		for (int position = 0; position < robotzData.getRobots().size(); position++) {

			if (robotzData.getRobots().get(position).collides(xCoord, yCoord, robotzData.getPillSize())) {
				result = true;
			}
		}

		return result;
	}

	/**
	 * Methods checks if the new pill will collide with the player.
	 * @param xCoord The possible new x coordinate.
	 * @param yCoord The possible new y coordinate.
	 * @return true if the invincible pill would collide with the player.
	 */
	boolean isPillSetOnPlayer(final double xCoord, final double yCoord) {

		return robotzData.getPlayer().collides(xCoord, yCoord, robotzData.getPillSize());
	}

	/**
	 * Methods checks if the new pill will collide with the exit.
	 * @param xCoord The possible new x coordinate.
	 * @param yCoord The possible new y coordinate.
	 * @return true if the invincible pill would collide with the exit.
	 */
	final boolean isPillSetOnExit(final double xCoord, final double yCoord) {

		return robotzData.getExit().collides(xCoord, yCoord, robotzData.getPillSize());
	}

	/**
	 * Methods checks if the new pill will collide with a fence.
	 * @param xCoord The possible new x coordinate.
	 * @param yCoord The possible new y coordinate.
	 * @return true if the invincible pill would collide with a fence.
	 */
	boolean isPillSetOnFence(final double xCoord, final double yCoord) {

		boolean result = false;

		for (int position = 0; position < robotzData.getFences().size(); position++) {

			if (robotzData.getFences().get(position).collides(xCoord, yCoord, robotzData.getPillSize())) {
				result = true;
			}
		}

		return result;
	}
}
