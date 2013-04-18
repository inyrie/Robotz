package edu.hm.fuberg.se2.robotz.layered.data;

import android.content.ClipData.Item;

public class Fence extends Item {

	public static final int FENCE_SIZE = 1;

	/**
	 * @param xCoordinate
	 * @param yCoordinate
	 * @param size
	 */
	public Fence(final double xCoordinate, final double yCoordinate) {

		super(xCoordinate, yCoordinate, FENCE_SIZE);
	}
}
