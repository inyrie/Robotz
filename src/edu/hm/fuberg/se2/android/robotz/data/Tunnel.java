/**
 * Munich University for Applied Science,
 * Faculty 07 for Mathematics and Computer Science
 * Softwareentwicklung II, SS2013, Studiengruppe IF1A
 * Windows XP SP3; Java-Version: 1.7.0_17
 * Developing a Java application.
 */

package edu.hm.fuberg.se2.android.robotz.data;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The Class represents a tunnel on the gameboard, consisting of two separate tunnel holes. A tunnel is created on the
 * initialization of the gameboard with arbitrary coordinates for the respective holes, teleporting a Player from one
 * hole to another. The direction of the player's motion does not change when teleported. The Player's target point
 * shifts exactly the same as the Player's position does when teleported.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-04-29
 */
public class Tunnel {

	/** The two holes forming a tunnel. */
	private final TunnelHole[] tunnelHoles = new TunnelHole[2];

	/**
	 * Ctor for a tunnel, consisting of two separate tunnel holes.
	 * @param entry One end of the tunnel.
	 * @param exit The other end of the tunnel.
	 */
	public Tunnel(final TunnelHole entry, final TunnelHole exit) {

		tunnelHoles[0] = entry;
		tunnelHoles[1] = exit;
	}

	/**
	 * Getter for the tunnel pair, consisting of two holes. The getter returns the corresponding array as unmodifiable
	 * list.
	 * @return The two tunnel holes forming a tunnel.
	 */
	public List<TunnelHole> getTunnelPair() {
		return Collections.unmodifiableList(Arrays.asList(tunnelHoles));
	}
}
