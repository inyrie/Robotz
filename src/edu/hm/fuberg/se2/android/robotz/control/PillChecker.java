/**
 * Munich University for Applied Science,
 * Faculty 07 for Mathematics and Computer Science
 * Softwareentwicklung II, SS2013, Studiengruppe IF1A
 * Windows XP SP3; Java-Version: 1.7.0_17
 * Developing a Java application.
 */

package edu.hm.fuberg.se2.android.robotz.control;

import edu.hm.fuberg.se2.android.robotz.data.Arena;

/**
 * Class for defining various check methods concerning the ultimate pill for invincibility.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-05-27
 */
class PillChecker {

	/** A data object. */
	final Arena robotzData;

	/** */
	final double potentialXCoord;

	/** */
	final double potentialYCoord;

	/**
	 * Ctor.
	 */
	PillChecker(final Arena dataObject, final double xCoordToCheck, final double yCoordToCheck) {

		robotzData = dataObject;
		potentialXCoord = xCoordToCheck;
		potentialYCoord = yCoordToCheck;
	}

	/**
	 * 
	 */
	void isPillLifespanExtended() {

		if (robotzData.getInvinciblePill() != null && robotzData.getInvinciblePill().getPillCountdown() == 0) {
			robotzData.deleteInvinciblePill();
		}
	}

	/**
	 * 
	 */
	void playerTakesPill() {

		if (robotzData.getPlayer().collides(robotzData.getInvinciblePill())) {
			robotzData.deleteInvinciblePill();
			robotzData.getPlayer().setInvincibility();
		}
	}

	/**
	 * @param xCoord
	 * @param yCoord
	 * @return
	 */
	boolean invinciblePillOnItem(final double xCoord, final double yCoord) {

		return isPillSetOnPlayer(xCoord, yCoord) || isPillSetOnExit(xCoord, yCoord) || isPillSetOnFence(xCoord, yCoord)
				|| isPillSetOnRobot(xCoord, yCoord);
	}

	/**
	 * @param xCoord
	 * @param yCoord
	 */
	boolean isPillSetOnRobot(final double xCoord, final double yCoord) {

		boolean result = false;

		for (int position = 0; position < robotzData.getAmountRobots(); position++) {

			if (robotzData.getRobot(position).collides(xCoord, yCoord, robotzData.getPillSize())) {
				result = true;
			}
		}

		return result;
	}

	/**
	 * @param xCoord
	 * @param yCoord
	 * @return
	 */
	boolean isPillSetOnPlayer(final double xCoord, final double yCoord) {

		return robotzData.getPlayer().collides(xCoord, yCoord, robotzData.getPillSize());
	}

	/**
	 * @param xCoord
	 * @param yCoord
	 * @return
	 */
	final boolean isPillSetOnExit(final double xCoord, final double yCoord) {

		return robotzData.getExit().collides(xCoord, yCoord, robotzData.getPillSize());
	}

	/**
	 * @param xCoord
	 * @param yCoord
	 * @return
	 */
	boolean isPillSetOnFence(final double xCoord, final double yCoord) {

		boolean result = false;

		for (int position = 0; position < robotzData.getAmountFences(); position++) {

			if (robotzData.getFence(position).collides(xCoord, yCoord, robotzData.getPillSize())) {
				result = true;
			}
		}

		return result;
	}
}
