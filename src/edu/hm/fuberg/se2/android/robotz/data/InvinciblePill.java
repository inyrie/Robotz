/**
 * Munich University for Applied Science,
 * Faculty 07 for Mathematics and Computer Science
 * Softwareentwicklung II, SS2013, Studiengruppe IF1A
 * Windows XP SP3; Java-Version: 1.7.0_17
 * Developing a Java application.
 */

package edu.hm.fuberg.se2.android.robotz.data;


/**
 * The Class describes the invincible pill of the Game.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-05-30
 */
public class InvinciblePill extends Item {

	/** The size of the invincible pill. */
	static final double INVINCIBLE_PILL_SIZE = 0.85;

	/** Time in ms for the pill's lifespan. It defines how long a generated invincible pill is active on the gameboard. */
	private int pillCountdown;

	/**
	 * Ctor for a new invincible pill object.
	 * @param xCoord The X Coordinate of the invincible pill.
	 * @param yCoord The Y Coordinate of the invincible pill.
	 * @param lifespan The time how long the invincible pill is active.
	 */
	public InvinciblePill(final double xCoord, final double yCoord, final int lifespan) {
		super(xCoord, yCoord, INVINCIBLE_PILL_SIZE);
		pillCountdown = lifespan;
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
	 * Method decrements the invincible time.
	 * @param deltaTime The passed time.
	 */
	public void decrementCountdown(final int deltaTime) {

		int newTimeLeft = getPillCountdown() - deltaTime;

		// Pill lifetime left stops at zero.
		if (newTimeLeft < 0) {
			newTimeLeft = 0;
		}

		setPillCountdown(newTimeLeft);

		//Log.d("robotz_invincible", "decrementCountdown() - pillCountdown = " + getPillCountdown());
	}
}
