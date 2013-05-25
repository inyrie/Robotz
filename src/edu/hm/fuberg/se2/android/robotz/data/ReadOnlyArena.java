/**
 * Munich University for Applied Science,
 * Faculty 07 for Mathematics and Computer Science
 * Softwareentwicklung II, SS2013, Studiengruppe IF1A
 * Windows XP SP3; Java-Version: 1.7.0_17
 * Developing a Java application.
 */

package edu.hm.fuberg.se2.android.robotz.data;

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
	 * Gets the robot at the position in the list.
	 * @param position The position in the list.
	 * @return the robot.
	 */
	Robot getRobot(final int position);

	/**
	 * Gets the fence at the position in the list.
	 * @param position The position in the list.
	 * @return the fence.
	 */
	Fence getFence(final int position);

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
	 * Gets the amount of robots.
	 * @return the amount of robots.
	 */
	int getAmountRobots();

	/**
	 * Gets the amount of fences.
	 * @return the amount of fences.
	 */
	int getAmountFences();

	/**
	 * Gets the target size.
	 * @return the target size.
	 */
	double getTargetSize();

	/**
	 * Gets the invincible pill size.
	 * @return the invincible pill size.
	 */
	double getInvinciblePillSize();
}
