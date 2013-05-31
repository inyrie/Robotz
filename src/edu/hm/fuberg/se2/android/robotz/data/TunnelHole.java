/**
 * Munich University for Applied Science,
 * Faculty 07 for Mathematics and Computer Science
 * Softwareentwicklung II, SS2013, Studiengruppe IF1A
 * Windows XP SP3; Java-Version: 1.7.0_17
 * Developing a Java application.
 */

package edu.hm.fuberg.se2.android.robotz.data;

/**
 * The Class represents a tunnel hole on the gameboard. It is created on the initialization of the gameboard with
 * arbitrary coordinates and is always connected to another tunnel hole, teleporting a Player from one hole to another.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-04-29
 */
public class TunnelHole extends Item {

	/** Constant defining the size of a TunnelHole object. */
	private static final double TUNNEL_SIZE = 1;

	/**
	 * Ctor.
	 * @param coordinates The coordinates for the tunnel hole as double[] array.
	 */
	TunnelHole(final double[] coordinates) {

		super(coordinates[0], coordinates[1], TUNNEL_SIZE);
	}
}
