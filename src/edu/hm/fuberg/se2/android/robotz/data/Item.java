/**
 * Munich University for Applied Science,
 * Faculty 07 for Mathematics and Computer Science
 * Softwareentwicklung II, SS2013, Studiengruppe IF1A
 * Windows XP SP3; Java-Version: 1.7.0_17
 * Developing a Java application.
 */

package edu.hm.fuberg.se2.android.robotz.data;

import static java.lang.Math.abs;
import static java.lang.Math.hypot;
import android.util.Log;

/**
 * The Class describes the position and size of an item.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-04-25
 */
public abstract class Item implements ReadOnlyItem {

	// ///////////////// C O N S T A N T S ///////////////////////////

	/** The collision value of an Item. */
	private static final double COLLISION_VALUE = 0.1;

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
	 * @param xCoord the X Coordinate of an Item.
	 * @param yCoord the Y Coordinate of an Item.
	 * @param size the size of an Item.
	 */
	public Item(final double xCoord, final double yCoord, final double size) {

		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.size = size;
	}

	// //////////////////// G E T T E R ////////////////////////

	@Override public double getXCoord() {
		return xCoord;
	}

	@Override public double getYCoord() {
		return yCoord;
	}

	@Override public double getSize() {
		return size;
	}

	// //////////////////// S E T T E R ////////////////////////

	public void setXCoord(final double xCoordinate) {
		xCoord = xCoordinate;
	}

	public void setYCoord(final double yCoordinate) {
		yCoord = yCoordinate;
	}

	// //////////////////// VAR. M E T H O D S ////////////////////////

	/**
	 * Method shifts a point to the new Coordinates.
	 * @param newXCoord the new X Coordinate of the Item.
	 * @param newYCoord the new Y Coordinate of the Item.
	 * @return Returns the Item object that called the method.
	 */
	public Item shift(final double newXCoord, final double newYCoord) {

		setXCoord(newXCoord);
		setYCoord(newYCoord);

		return this;
	}

	/**
	 * Method for calculating the distance between two Item objects in the Arena.
	 * @param item Another Item object (p.e. a Robot or a Fence).
	 * @return The distance between two Item objects.
	 */
	public double distanceTo(final Item item) {

		return hypot(getXCoord() - item.getXCoord(), getYCoord() - item.getYCoord());
	}

	/**
	 * Method for calculating if two Item objects collide. They collide if the distance between them is smaller than a
	 * specified threshold value.
	 * @param item Another Item object.
	 * @return True - the objects collide. False - the objects don't collide.
	 */
	public boolean collides(final Item item) {

		//Log.d("robotz", "Item.collides() => fuer Target!");

		boolean result = false;

		if (item != null) {

			Log.d("robotz","Item.collides() => if-Abfrage auf item != null erreicht!");
			final double combinedRadiens = (getSize() + item.getSize())/2;
			result = abs(distanceTo(item) - combinedRadiens) < COLLISION_VALUE;
		}
		return result;
	}
}
