package edu.hm.fuberg.se2.android.robotz.data;

/**
 * The Class describes the Exit of the Game.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 */
public class Exit extends Item {

	/** The size of a fence. */
	public static final int EXIT_SIZE = 1;

	/**
	 * @param xCoord the X Coordinate of the exit.
	 * @param yCoord tye Y Coordinate of the exit.
	 */
	public Exit(final double xCoord, final double yCoord) {

		super(xCoord, yCoord, EXIT_SIZE);
	}
}
