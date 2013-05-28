/**
 * Munich University for Applied Science,
 * Faculty 07 for Mathematics and Computer Science
 * Softwareentwicklung II, SS2013, Studiengruppe IF1A
 * Windows XP SP3; Java-Version: 1.7.0_17
 * Developing a Java application.
 */

package edu.hm.fuberg.se2.android.robotz.data;

import android.util.Log;

/**
 * The Class describes the invincible pill of the Game.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-05-28
 */
public class InvinciblePill extends Item {

	/** The size of the invincible pill. */
	static final double INVINCIBLE_PILL_SIZE = 0.85;

	/** Constant for the initial value for the pill's lifespan. */
	private static final int COUNTDOWN_INITIAL_VALUE = 15000;

	/** Time in ms for the pill's lifespan. It defines how long a generated invincible pill is active on the gameboard. */
	private int pillCountdown = COUNTDOWN_INITIAL_VALUE;

	/**
	 * Ctor for a new invincible pill object.
	 * @param xCoord the X Coordinate of the invincible pill.
	 * @param yCoord the Y Coordinate of the invincible pill.
	 */
	public InvinciblePill(final double xCoord, final double yCoord) {
		super(xCoord, yCoord, INVINCIBLE_PILL_SIZE);
	}

	// ///////////////////// G E T T E R & S E T T E R ///////////////////////

	public int getPillCountdown() {
		return pillCountdown;
	}

	void setPillCountdown(final int newValue) {
		pillCountdown = newValue;
	}

	// ///////////////////// C O U N T D O W N - M E T H O D S ///////////////////////

	/**
	 * @param deltaTime
	 */
	public void decrementCountdown(final int deltaTime) {

		int newTimeLeft = getPillCountdown() - deltaTime;

		// Pill lifetime left stops at zero.
		if (newTimeLeft < 0) {
			newTimeLeft = 0;
		}

		setPillCountdown(newTimeLeft);

		Log.d("robotz_invincible", "decrementCountdown() - pillCountdown = " + getPillCountdown());
	}
}
