package edu.hm.fuberg.se2.android.robotz.data;

/**
 * The Interface ReadOnlyItem restricts the access to Item data for other
 * layers.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 */
public interface ReadOnlyItem {

	/**
	 * Gets the X coordinate of the Item.
	 * @return the X coordinate of the Item.
	 */
	double getXCoord();

	/**
	 * Gets the Y coordinate of the Item.
	 * @return the Y coordinate of the Item.
	 */
	double getYCoord();

	/**
	 * Gets the size of the Item.
	 * @return The size of the Item.
	 */
	double getSize();

}
