/**
 * Munich University for Applied Science,
 * Faculty 07 for Mathematics and Computer Science
 * Softwareentwicklung II, SS2013, Studiengruppe IF1A
 * Windows XP SP3; Java-Version: 1.7.0_17
 * Developing a Java application.
 */

package edu.hm.fuberg.se2.android.robotz.data;

/**
 * The Class describes the Player of the Game.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-04-25
 */
public class Player extends MobileItem {

	// ////////////////////OBJ. VAR. //////////////////////////

	/** Size of the player object. */
	public static final double PLAYER_SIZE = 0.85;

	// ////////////////// C T O R ////////////////////////////

	/**
	 * Ctor for a new player.
	 * @param xCoordinate the x coordinate of the player.
	 * @param yCoordinate the y coordinate of the player.
	 * @param playerVelocity
	 */
	public Player(final double xCoordinate, final double yCoordinate, final double playerVelocity) {

		super(xCoordinate, yCoordinate, PLAYER_SIZE, playerVelocity);
	}
}
