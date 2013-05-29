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

	/** The player. */
	private Player player;

	/** The exit. */
	private Exit exit;

	/** The invincible pill. */
	private InvinciblePill invinciblePill;

	/** The current state of the Game, either waiting, running or over. */
	private GameState gameState;

	// //////////////////// C T O R /////////////////////

	/**
	 * Ctor for a new arena.
	 * @param configurator The game configuration.
	 * @throws IllegalArgumentException If parameters for width or height are zero or less.
	 */
	public Arena(final GameConfig configurator) {

		final List<String> arena = configurator.getGameboard();

		if (arena == null || arena.size() <= 0 || arena.get(0).length() <= 0) {
			throw new IllegalArgumentException("Arena's size parameters are not valid.");
		}

		arenaHeight = arena.size();
		arenaWidth = arena.get(0).length();
		gameState = GameState.Waiting;

		final Initializer initializer = new Initializer(this, arena, configurator.getSpeedPlayer(), configurator.getSpeedRobot());
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
	 * Getter for a the robot list.
	 * @return the robot list.
	 */
	@Override public List<Robot> getRobots() {
		return Collections.unmodifiableList(robots);
	}

	/**
	 * Getter for the fence list.
	 * @return the fence list.
	 */
	@Override public List<Fence> getFences() {
		return Collections.unmodifiableList(fences);
	}

	@Override public double getTargetSize() {

		return Target.TARGET_SIZE;
	}

	@Override public InvinciblePill getInvinciblePill() {
		return invinciblePill;
	}

	@Override public double getPillSize() {
		return InvinciblePill.INVINCIBLE_PILL_SIZE;
	}

	@Override public int getPillCountdown() {
		return invinciblePill.getPillCountdown();
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
	 * Setter for the invincible pill object.
	 * @param xCoord The x coordinate of the invincible pill.
	 * @param yCoord The y coordinate of the invincible pill.
	 */
	public void setInvinciblePill(final double xCoord, final double yCoord) {
		invinciblePill = new InvinciblePill(xCoord, yCoord);

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

	/**
	 * Method deletes the invincible pill.
	 */
	public void deleteInvinciblePill() {
		invinciblePill = null;
	}
}