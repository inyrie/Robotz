/**
 * Munich University for Applied Science,
 * Faculty 07 for Mathematics and Computer Science
 * Softwareentwicklung II, SS2013, Studiengruppe IF1A
 * Windows XP SP3; Java-Version: 1.7.0_17
 * Developing a Java application.
 */

package edu.hm.fuberg.se2.android.robotz.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.hm.fuberg.se2.android.robotz.GameConfig;

/**
 * The Class describes the playing arena of Robotz.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-05-19
 */
public final class Arena implements ReadOnlyArena {

	/** The height of the Arena. */
	private final double arenaHeight;

	/** The arenaWidth of the Arena. */
	private final double arenaWidth;

	/** The robots. */
	private final List<Robot> robots = new ArrayList<Robot>();

	/** The fences. */
	private final List<Fence> fences = new ArrayList<Fence>();

	/** The tunnels on the gameboard. */
	private final List<Tunnel> tunnels = new ArrayList<Tunnel>();

	/** The player. */
	private Player player;

	/** The Exit. */
	private Exit exit;

	/** The current state of the Game, either waiting, running or over. */
	private GameState gameState;

	// //////////////////// C T O R /////////////////////

	/**
	 * Ctor for a new arena.
	 * @param configurator A configurator object, responsible for parsing data from an external config-file.
	 */
	public Arena(final GameConfig configurator) {

		final List<String> arena = configurator.getGameboard();

		if (arena == null || arena.size() <= 0 || arena.get(0).length() <= 0) {
			throw new IllegalArgumentException("Arena's size parameters are not valid.");
		}

		arenaHeight = arena.size();
		arenaWidth = arena.get(0).length();

		gameState = GameState.Waiting;

		final Initializer initializer = new Initializer(this, configurator);
		initializer.initializeArena();
	}

	// //////////////////// G E T T E R /////////////////////

	@Override public double getHeight() {
		return arenaHeight;
	}

	@Override public double getWidth() {
		return arenaWidth;
	}

	@Override public GameState getState() {
		return gameState;
	}

	@Override public Player getPlayer() {
		return player;
	}

	@Override public Exit getExit() {
		return exit;
	}

	/**
	 * Getter for a robot.
	 * @param position the position of the robot in the List.
	 * @return the robot.
	 */
	@Override public Robot getRobot(final int position) {
		return robots.get(position);
	}

	/**
	 * Getter for a fence.
	 * @param position the position of the fence in the List.
	 * @return the fence.
	 */
	@Override public Fence getFence(final int position) {
		return fences.get(position);
	}

	/**
	 * Getter for the amount of Robots.
	 * @return the robot.
	 */
	@Override public int getAmountRobots() {
		return robots.size();
	}

	/**
	 * Getter for the amount of Fences.
	 * @return the fence.
	 */
	@Override public int getAmountFences() {
		return fences.size();
	}

	/**
	 * Getter for the amount of tunnels.
	 * @return
	 */
	@Override public List<Tunnel> getTunnels() {

		return Collections.unmodifiableList(tunnels);
	}

	@Override public double getTargetSize() {

		return Target.TARGET_SIZE;
	}

	// //////////////////// S E T T E R /////////////////////

	/**
	 * Setter for the Player object.
	 * @param player The Player object to set.
	 */
	public void setPlayer(final Player player) {
		this.player = player;
	}

	/**
	 * Setter for the Exit object.
	 * @param exit the Exit object to set.
	 */
	public void setExit(final Exit exit) {
		this.exit = exit;
	}

	/**
	 * Setter for the game's current state.
	 * @param state The current game state.
	 */
	public void setState(final GameState state) {
		gameState = state;
	}

	/**
	 * Method adds a new robot to the list.
	 * @param robot The robot.
	 */
	void addRobot(final Robot robot) {
		robots.add(robot);
	}

	/**
	 * Method adds a new fence to the list.
	 * @param fence The fence.
	 */
	void addFence(final Fence fence) {
		fences.add(fence);
	}

	/**
	 * Method adds a tunnel consisting of two tunnel holes to the list.
	 * @param tunnel A tunnel, consisting of two tunnel holes.
	 */
	public void createTunnel(final double[][] coordinates) {

		final TunnelHole entry = new TunnelHole(coordinates[0]);
		final TunnelHole exit = new TunnelHole(coordinates[1]);

		tunnels.add(new Tunnel(entry, exit));
	}

	// /////////////////////// VAR. METHODS //////////////////

	/**
	 * Removes one robot from the list.
	 * @param position the position of the Robot in the list.
	 */
	public void removeRobot(final int position) {

		if (robots.get(position) != null) {
			robots.remove(position);
		}
	}

	/**
	 * Removes one fence from the list.
	 * @param position the position of the fence in the list.
	 */
	public void removeFence(final int position) {

		if (fences.get(position) != null) {
			fences.remove(position);
		}
	}
}