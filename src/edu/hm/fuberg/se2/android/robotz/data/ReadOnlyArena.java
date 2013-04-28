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
 * @version 2013-04-25
 */
public interface ReadOnlyArena {

	/**
	 * Gets the player object.
	 * @return The Player object.
	 */
	Player getPlayer();

	/**
	 * Gets the exit object.
	 * @return The exit object.
	 */
	Exit getExit();

	/**
	 * Gets the width of the Arena.
	 * @return the width of the Arena
	 */
	double getWidth();

	/**
	 * Gets the height of the Arena.
	 * @return the height of the Arena
	 */
	double getHeight();

	/**
	 * Gets the state of the Game.
	 * @return the state of the Game
	 */
	GameState getState();

	int getAmountRobots();

	int getAmountFences();

	Robot getRobot(final int position);

	Fence getFence(final int position);

}
