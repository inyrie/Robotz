/**
 * Munich University for Applied Science,
 * Faculty 07 for Mathematics and Computer Science
 * Softwareentwicklung II, SS2013, Studiengruppe IF1A
 * Windows XP SP3; Java-Version: 1.7.0_17
 * Developing a Java application.
 */

package edu.hm.fuberg.se2.android.robotz.data;

import java.util.List;

/**
 * The Interface ReadOnlyItem restricts the access to Arena data for other layers.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-05-02
 */
public interface ReadOnlyArena {

	/**
	 * Gets the player object.
	 * @return The player object.
	 */
	Player getPlayer();

	/**
	 * Gets the exit object.
	 * @return The exit object.
	 */
	Exit getExit();

	/**
	 * Gets the robots list.
	 * @return the robot list.
	 */
	List<Robot> getRobots();

	/**
	 * Gets the fences list.
	 * @return the fences list.
	 */
	List<Fence> getFences();

	/**
	 * Gets the invincible pill object.
	 * @return the invincible pill.
	 */
	InvinciblePill getInvinciblePill();

	/**
	 * Gets the state of the game.
	 * @return the state of the game.
	 */
	GameState getState();

	/**
	 * Gets the width of the arena.
	 * @return the width of the arena.
	 */
	double getWidth();

	/**
	 * Gets the height of the arena.
	 * @return the height of the arena.
	 */
	double getHeight();

	/**
	 * Gets the target size.
	 * @return the target size.
	 */
	double getTargetSize();

	/**
	 * Gets the invincible pill size.
	 * @return the invincible pill size.
	 */
	double getPillSize();

	/**
	 * Gets the remaining lifespan for a currently active pill object on the gameboard.
	 * @return the remaining lifespan.
	 */
	int getPillCountdown();
}
