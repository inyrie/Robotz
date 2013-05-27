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
 * @version 2013-05-24
 */
public class InvinciblePill extends Item {

	/** The size of the invincible pill. */
	public static final double INVINCIBLE_PILL_SIZE = 0.85;

	/**
	 * Ctor for a new invincible pill object.
	 * @param xCoord the X Coordinate of the invincible pill.
	 * @param yCoord the Y Coordinate of the invincible pill.
	 */
	public InvinciblePill(final double xCoord, final double yCoord) {
		super(xCoord, yCoord, INVINCIBLE_PILL_SIZE);
	}
}
