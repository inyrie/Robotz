package edu.hm.fuberg.se2.android.robotz.data;

/**
 * The Class describes the velocity and destination of an Item.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 */
public abstract class MobileItem extends Item {

	// ///////////////////// OBJ. VAR. // /////////////////////////////////////

	/** The velocity of an Item. */
	private final double velocity;
	/** The destination of an Item */
	private Item destination;

	// //////////////////////// C T O R ///////////////////////////////////////

	/**
	 * @param xCoordinate
	 * @param yCoordinate
	 * @param destination
	 * @param size
	 * @param velocity
	 */
	public MobileItem(final double xCoordinate, final double yCoordinate, final Item destination, final double size,
			final double velocity) {

		super(xCoordinate, yCoordinate, size);
		this.velocity = velocity;
		this.destination = destination;
	}

	// /////////////////////////// G E T T E R ///////////////////////////////

	public double getVelocity() {
		return velocity;
	}

	public Item getDestination() {
		return destination;
	}

	// //////////////////////// S E T T E R ///////////////////////////////

	/**
	 * Method for defining a target point.
	 * @param destination
	 */
	public void setDestination(final Item destination) {

		this.destination = destination;
	}
}
