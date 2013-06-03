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
 * @version 2013-06-03
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
	 * Getter for an unmodifiable list containing the arena's every robot.
	 * @return The robots as unmodifiable list.
	 */
	@Override public List<Robot> getRobots() {
		return Collections.unmodifiableList(robots);
	}

	/**
	 * Getter for an unmodifiable list containing the arena's every fence.
	 * @return The fences as unmodifiable list.
	 */
	@Override public List<Fence> getFences() {
		return Collections.unmodifiableList(fences);
	}

	/**
	 * Getter for the amount of tunnels.
	 * @return The amount of tunnels.
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
	 * @param coordinates The coordinates for both tunnel holes.
	 */
	public void createTunnel(final double[][] coordinates) {

		final TunnelHole entryHole = new TunnelHole(coordinates[0]);
		final TunnelHole exitHole = new TunnelHole(coordinates[1]);

		tunnels.add(new Tunnel(entryHole, exitHole));
	}

	// /////////////////////// VAR. METHODS //////////////////

	/**
	 * Method removes a tunnel from the list.
	 * @param position The tunnel's position in the list.
	 */
	public void removeTunnel(final int position) {
		tunnels.remove(position);
	}

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

	/**
	 * Method for teleporting the player while keeping his direction.
	 * @param tunnelNumber The tunnel number, identifying a tunnel pair.
	 * @param index The hole index, used to differ between the entry and the exit.
	 */
	public void teleport(final int tunnelNumber, final int index) {

		final double entryXCoords = getTunnels().get(tunnelNumber).getTunnelPair().get(index).getXCoord();
		final double entryYCoords = getTunnels().get(tunnelNumber).getTunnelPair().get(index).getYCoord();

		// getting the other hole of the tunnelpair by manipulating the indices.
		final double exitXCoords = getTunnels().get(tunnelNumber).getTunnelPair().get(Math.abs(index - 1)).getXCoord();
		final double exitYCoords = getTunnels().get(tunnelNumber).getTunnelPair().get(Math.abs(index - 1)).getYCoord();

		// to prevent access to a null destination if player reaches his destination before he teleports.
		if (getPlayer().getDestination() != null) {

			// shifting the target coordinates to the new coordinates after teleportation
			final double newTargetX = getPlayer().getDestination().getXCoord() + exitXCoords - entryXCoords;
			final double newTargetY = getPlayer().getDestination().getYCoord() + exitYCoords - entryYCoords;
			getPlayer().getDestination().shift(newTargetX, newTargetY);
		}

		// shifting the player coordinates to the coordinates of the tunnel exit.
		getPlayer().shift(exitXCoords, exitYCoords);

		// deleting the just used tunnel.
		removeTunnel(tunnelNumber);
	}
}