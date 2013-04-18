package edu.hm.fuberg.se2.robotz.layered.data;

/**
 * The Class describes the destination of the Player.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 */
public class Target extends Item {

public static final int TARGET_SIZE = 1;

	public Target(final double xCoordinate, final double yCoordinate) {

		super(xCoordinate, yCoordinate,TARGET_SIZE);
	}


}
