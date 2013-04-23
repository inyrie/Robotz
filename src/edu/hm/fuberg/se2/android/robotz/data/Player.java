package edu.hm.fuberg.se2.android.robotz.data;

/**
 * The Class describes the Player of the Game.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 */
public class Player extends MobileItem {

	// ////////////////////OBJ. VAR. //////////////////////////

	/** Size of the player object. */
	public static final int PLAYER_SIZE = 1;
	/** Velocity in fields per second. */
	public static final double PLAYER_VELOCITY = 0.2;

	private Item target;

	// ////////////////// C T O R ////////////////////////////

	/**
	 * @param xCoordinate
	 * @param yCoordinate
	 * @param size
	 * @param velocity
	 */
	public Player(final double xCoordinate, final double yCoordinate) {

		super(xCoordinate, yCoordinate, PLAYER_SIZE, PLAYER_VELOCITY);

	}

	// //////////////////////////////////////////////

	/**
	 * @return the target
	 */
	public Item getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(final Item target) {
		this.target = target;
	}
}
