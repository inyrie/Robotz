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
 * @version 2013-05-19
 */
public class Player extends MobileItem {

	// ////////////////////OBJ. VAR. //////////////////////////

	/** Size of the player object. */
	public static final double PLAYER_SIZE = 0.85;


	/** Value defining the time period for invincibility after the player has taken the ultimate pill of invincibility. */
	private final int invincibilityTime;

	// ////////////////// C T O R ////////////////////////////

	/**
	 * Ctor for a new player.
	 * @param xCoordinate The x coordinate of the player.
	 * @param yCoordinate The y coordinate of the player.
	 * @param playerVelocity The player speed.
	 * @param playerVelocity
	 */
	public Player(final double xCoordinate, final double yCoordinate, final double playerVelocity, final int time) {

		super(xCoordinate, yCoordinate, PLAYER_SIZE, playerVelocity);
		invincibilityTime = time;
	}

	/**
	 * Method sets a new Target.
	 * @param xCoord The new xCoordinate.
	 * @param yCoord The new yCoordinate.
	 */
	public void setDestination(final double xCoord, final double yCoord) {
		super.setDestination(new Target(xCoord, yCoord));

	}

	/**
	 * Method for setting invincibility.
	 */
	public void setInvincibility()
	{
		super.setInvincibility(invincibilityTime);
	}
}
