package edu.hm.fuberg.se2.android.robotz.data;

import static java.lang.Math.abs;
import static java.lang.Math.hypot;

/**
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 */
public class Item implements ReadonlyItem {

	// ///////////////// C O N S T A N T S ///////////////////////////

	private static final double COLLISION_VALUE = 1;

	// /////////////////OBJ. V A R I A B L E S //////////////////////

	/** The X coordinate of an Item. */
	private double xCoord;
	/** The Y coordinate of an Item. */
	private double yCoord;
	/** The size of an Item. */
	private final double size;

	// //////////////////// C T O R ////////////////////////

	/**
	 * Constructor initializing an Item object.
	 * @param xCoord
	 * @param yCoord
	 * @param size
	 */
	public Item(final double xCoord, final double yCoord, final double size) {

		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.size = size;
	}

	public Item() {

		this(0, 0, 0);
	}

	// //////////////////// G E T T E R ////////////////////////

	/**
	 * @return the xCoord
	 */
	@Override public double getXCoord() {
		return xCoord;
	}

	/**
	 * @return the yCoord
	 */
	@Override public double getYCoord() {
		return yCoord;
	}

	/**
	 * @return the size
	 */
	@Override public double getSize() {
		return size;
	}

	// //////////////////// S E T T E R ////////////////////////

	/**
	 * @param xCoord the xCoord to set
	 */
	public void setXCoord(final double xCoord) {
		this.xCoord = xCoord;
	}

	/**
	 * @param yCoord the yCoord to set
	 */
	public void setYCoord(final double yCoord) {
		this.yCoord = yCoord;
	}

	// //////////////////// VAR. M E T H O D S ////////////////////////

	/**
	 * Method shifts a point to the new Coordinates.
	 * @param newXCoord
	 * @param newYCoord
	 */
	public void shift(final double newXCoord, final double newYCoord) {

		setXCoord(newXCoord);
		setYCoord(newYCoord);
	}

	/**
	 * Method for calculating the distance between two Item objects in the
	 * Arena.
	 * @param item Another Item object (p.e. a Robot or a Fence)
	 * @return The distance between two Item objects.
	 */
	public double distanceTo(final Item item) {

		return hypot(getXCoord() - item.getXCoord(), getYCoord() - item.getYCoord());
	}

	/**
	 * Method for calculating if two Item objects collide. They collide if the
	 * distance between them is smaller than a specified treshold value.
	 * @param item Another Item object.
	 * @return
	 */
	public boolean collides(final Item item) {

		final double combinedRadiens = getSize() + item.getSize();
		return abs(distanceTo(item) - combinedRadiens) < COLLISION_VALUE;
	}
}
