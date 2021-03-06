/**
 * Munich University for Applied Science,
 * Faculty 07 for Mathematics and Computer Science
 * Softwareentwicklung II, SS2013, Studiengruppe IF1A
 * Windows XP SP3; Java-Version: 1.7.0_17
 * Developing a Java application.
 */

package edu.hm.fuberg.se2.android.robotz.data;

import java.util.List;
import java.util.Map;

/**
 * The Interface ReadOnlyItem restricts the access to Arena data for other layers.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-06-03
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
	 * Gets an unmodifiable map containing the arena's every tunnel.
	 * @return The tunnels as unmodifiable map.
	 */
	Map<Integer, Tunnel> getTunnels();

	/**
	 * Gets an unmodifiable list containing the arena's every fence.
	 * @return The fences as unmodifiable list.
	 */
	List<Fence> getFences();

	/**
	 * Gets an unmodifiable list containing the arena's every robot.
	 * @return The robots as unmodifiable list.
	 */
	List<Robot> getRobots();
}
