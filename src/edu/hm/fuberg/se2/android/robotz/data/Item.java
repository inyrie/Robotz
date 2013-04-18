package edu.hm.fuberg.se2.android.robotz.data;

/**
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 */
public class Item {

	// /////////////////OBJ. V A R I A B L E S ///////////////////////////

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

		this (0,0,0);
	}
	// //////////////////// G E T T E R ////////////////////////

	/**
	 * @return the xCoord
	 */
	public double getXCoord() {
		return xCoord;
	}

	/**
	 * @return the yCoord
	 */
	public double getYCoord() {
		return yCoord;
	}

	/**
	 * @return the size
	 */
	public double getSize() {
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

	public void shift(final double deltaX, final double deltaY) {

		setXCoord(getXCoord() + deltaX);
		setYCoord(getYCoord() + deltaY);
	}

	public double distanceTo(final Item item)
	{
		final double distance = 0;

		final double xCoord = getXCoord();
		final double yCoord = getYCoord();

		final double xCoordAim = item.getXCoord();
		final double yCoordAim = item.getYCoord();

		distance = Math.hypot(Math.abs(xCoord, yCoord), Math.abs(xCoordAim, yCoordAim));

		return distance;
	}
}
