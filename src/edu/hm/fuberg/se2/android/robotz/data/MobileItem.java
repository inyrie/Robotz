/**
 * Munich University for Applied Science,
 * Faculty 07 for Mathematics and Computer Science
 * Softwareentwicklung II, SS2013, Studiengruppe IF1A
 * Windows XP SP3; Java-Version: 1.7.0_17
 * Developing a Java application.
 */

package edu.hm.fuberg.se2.android.robotz.data;

/**
 * The Class describes the velocity and destination of an Item.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-05-29
 */
public abstract class MobileItem extends Item {

	// ///////////////////// OBJ. VAR. // /////////////////////////////////////

	/** The velocity of an Item. */
	private double velocity;

	/** Time in ms how long the Mobile Item is still invincible. */
	private int invincibility;

	/** The destination of an Item. */
	private Item destination;

	// //////////////////////// C T O R ///////////////////////////////////////

	/**
	 * Ctor for a new mobile item.
	 * @param xCoordinate the x coordinate of the item.
	 * @param yCoordinate the y coordinate of the item.
	 * @param size the size of the Item.
	 * @param velocity the velocity of the Item.
	 */
	public MobileItem(final double xCoordinate, final double yCoordinate, final double size, final double velocity) {

		super(xCoordinate, yCoordinate, size);
		this.velocity = velocity;
		destination = null;
	}

	// /////////////////////////// G E T T E R ///////////////////////////////

	public double getVelocity() {
		return velocity;
	}

	public boolean isInvincible() {
		return invincibility > 0;
	}

	public int getInvincibility() {
		return invincibility;
	}

	public Item getDestination() {
		return destination;
	}

	// //////////////////////// S E T T E R ///////////////////////////////

	void setInvincibility(final int invincibilityTime) {
		invincibility = invincibilityTime;
	}

	/**
	 * Method decrements the invincibility time.
	 * @param deltaTime The passed time.
	 */
	public void decrementInvincibility(final int deltaTime) {

		int newTimeLeft = getInvincibility() - deltaTime;

		// Time of invincibility left stops at zero.
		if (newTimeLeft < 0) {
			newTimeLeft = 0;
		}

		setInvincibility(newTimeLeft);
	}

	public void setVelocity(final double velocity) {
		this.velocity = velocity;
	}

	/**
	 * Method for defining a target point.
	 * @param destination The target point to set.
	 */
	public void setDestination(final Item destination) {
		this.destination = destination;
	}

	/**
	 * Method moves the MobileItem object in the direction of a destination Object for a specified time.
	 * @param milliseconds The elapsed milliseconds after the last update.
	 * @param arenaWidth The model width of the arena.
	 * @param arenaHeight The model height of the arena.
	 * @return The MobileItem object calling the method.
	 */
	public MobileItem move(final long milliseconds, final double arenaWidth, final double arenaHeight) {

		if (destination != null) {

			final double step = milliseconds * getVelocity() / distanceTo(destination);

			final double newXCoord = getXCoord() + step * (destination.getXCoord() - getXCoord());
			final double newYCoord = getYCoord() + step * (destination.getYCoord() - getYCoord());

			checkPosition(newXCoord, newYCoord, arenaWidth, arenaHeight);
		}

		return this;
	}

	/**
	 * Method checks if the new coordinates are within the gameboard bounds.
	 * @param newXCoord The shifted x coordinate.
	 * @param newYCoord The shifted y coordinate.
	 * @param arenaWidth The model width of the arena.
	 * @param arenaHeight The model height of the arena.
	 */
	private void checkPosition(final double newXCoord, final double newYCoord, final double arenaWidth,
			final double arenaHeight) {

		if (newXCoord < arenaWidth - getSize() && newYCoord < arenaHeight - getSize()) {
			shift(newXCoord, newYCoord);
		}

		else {
			destination = null;
		}
	}
}
