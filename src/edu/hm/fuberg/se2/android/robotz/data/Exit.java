package edu.hm.fuberg.se2.robotz.layered.data;

import android.content.ClipData.Item;

/**
 * The Class describes the Exit of the Game.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 */
public class Exit extends Item {

	public static final int EXIT_SIZE = 1;

	// //////////////////////////////////////////////////////////////////

	/**
	 * @param xCoordinate
	 * @param yCoordinate
	 * @param size
	 */
	public Exit(final double xCoordinate, final double yCoordinate) {

		super(xCoordinate, yCoordinate, EXIT_SIZE);
	}
}
