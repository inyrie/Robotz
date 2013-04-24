package edu.hm.fuberg.se2.android.robotz.data;

/**
 * The Class describes the fences of the Game.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 */
public class Fence extends Item {

	/** The size of the Exit. */
	public static final int FENCE_SIZE = 1;

	/**
	 * @param xCoord the X Coordinate of the fence.
	 * @param yCoord the Y Coordinate of the fence.
	 */
	public Fence(final double xCoord, final double yCoord) {

		super(xCoord, yCoord, FENCE_SIZE);
	}
}
