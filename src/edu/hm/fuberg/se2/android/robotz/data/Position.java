package edu.hm.fuberg.se2.android.robotz.data;

/**
 * The Class Location contains the X and Y Coordinate of an Item.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 */
public abstract class Position {

	/** The X coordinate of an Item. */
	private double xCoordinate;

	/** The Y coordinate of an Item. */
	private double yCoordinate;

	// ////////////////////////////////////////////////////////////////

	/**
	 * @param xCoordinate
	 * @param yCoordinate
	 */
	public Position(final double xCoordinate, final double yCoordinate) {

		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}

	// ////////////////////////////////////////////////////////////

	public double getXCoordinate() {
		return xCoordinate;
	}

	public void setXCoordinate(final double xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public double getYCoordinate() {
		return yCoordinate;
	}

	public void setYCoordinate(final double yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
}
