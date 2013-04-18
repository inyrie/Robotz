package edu.hm.fuberg.se2.robotz.layered.data;

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

	// //////////////////// G E T T E R ////////////////////////

	/**
	 * @return the xCoord
	 */
	public double getxCoord() {
		return xCoord;
	}

	/**
	 * @return the yCoord
	 */
	public double getyCoord() {
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
	public void setxCoord(final double xCoord) {
		this.xCoord = xCoord;
	}

	/**
	 * @param yCoord the yCoord to set
	 */
	public void setyCoord(final double yCoord) {
		this.yCoord = yCoord;
	}
}
