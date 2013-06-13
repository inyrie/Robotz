/**
 * Munich University for Applied Science,
 * Faculty 07 for Mathematics and Computer Science
 * Softwareentwicklung II, SS2013, Studiengruppe IF1A
 * Windows XP SP3; Java-Version: 1.7.0_17
 * Developing a Java application.
 */
package edu.hm.fuberg.se2.android.robotz.view;

import java.util.Iterator;
import java.util.Set;

import android.graphics.Canvas;
import android.graphics.Color;
import edu.hm.fuberg.se2.android.robotz.data.ReadOnlyArena;

/**
 * Class is responsible for doing all rendering work concerning telporter tunnels.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-06-04
 */
public class TunnelRenderer {

	// /////////////////////// OBJECT-VARIABLES ///////////////////

	/** A converter object for converting model to pixel coords and vice versa. */
	private final Converter converter;

	/** A rendering object, responsible for drawing. */
	private final Renderer renderer;

	/** A list of colors to pick from when creating multiple tunnels. */
	private final int[] colorList;

	/** A data object containing all object properties for drawing, like their respective size. */
	private final ReadOnlyArena robotzData;

	// /////////////////// C T O R ////////////////////////////

	/**
	 * Ctor.
	 * @param robotzData A data object, holding all game data.
	 * @param renderer A renderer object, responsible for drawing the gameboard (except the tunnels, of course).
	 */
	TunnelRenderer(final ReadOnlyArena robotzData, final Renderer renderer) {

		this.renderer = renderer;
		converter = renderer.getConverter();
		colorList = createColorList();
		this.robotzData = robotzData;
	}

	/**
	 * Method for creating a list of possible color values for tunnels.
	 * @return An array of color values to choose from.
	 */
	private int[] createColorList() {

		return new int[] {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.DKGRAY, Color.MAGENTA,
				Color.WHITE};
	}

	// ALTE DRAW-METHODE FUER ARRAYLIST-ZUGRIFF AUF TUNNEL!

	/**
	 * Method to draw a tunnel, consisting of two tunnel holes.
	 * @param drawCanvas The canvas to draw on.
	 */
	void drawAllTunnels(final Canvas drawCanvas) {

		// Neuer Ansatz mit Zugriff ueber HashMap-keys...
		final Set<Integer> keys = robotzData.getTunnels().keySet();

		// creating a new iterator for running through the keySet
		final Iterator<Integer> cursor = keys.iterator();

		// running through all the tunnels on the gameboard via their respective key.
		while (cursor.hasNext()) {
			drawTunnel(drawCanvas, cursor.next());
		}

		// // running through all the tunnels on the gameboard.
		// for (int tunnelNumber = 0; tunnelNumber < robotzData.getTunnels().size(); tunnelNumber++) {
		// drawTunnel(drawCanvas, tunnelNumber);
		// }
	}

	/**
	 * Method for drawing one tunnel consisting of two separate TunnelHoles.
	 * @param drawCanvas The canvas to draw on.
	 * @param tunnelNumber The index of the tunnel to draw.
	 */
	private void drawTunnel(final Canvas drawCanvas, final int tunnelNumber) {

		// drawing each of the two tunnel holes that form the tunnel
		for (int index = 0; index < 2; index++) {
			drawTunnelHole(drawCanvas, tunnelNumber, index);
		}
	}

	/**
	 * Method for drawing one single tunnel hole belonging to a Tunnel.
	 * @param drawCanvas The canvas to draw on.
	 * @param tunnelNumber The index of the tunnel to draw.
	 * @param index The index of the specific tunnel hole - "entry" or "exit".
	 */
	private void drawTunnelHole(final Canvas drawCanvas, final int tunnelNumber, final int index) {

		final double halfSize = robotzData.getTunnels().get(tunnelNumber).getTunnelPair().get(0).getSize() / 2;
		final float radius = converter.modelToPixelValues(halfSize);

		final double[] entryCoords = converter.modelToPixelCoords(robotzData.getTunnels().get(tunnelNumber)
				.getTunnelPair().get(index).getXCoord(),
				robotzData.getTunnels().get(tunnelNumber).getTunnelPair().get(index).getYCoord(), halfSize);

		// draw two overlaying circles to mark tunnel holes that belong together.
		drawCanvas.drawCircle((float) entryCoords[0], (float) entryCoords[1], radius,
				renderer.defineBrush(pickColor(tunnelNumber)));
		drawCanvas.drawCircle((float) entryCoords[0], (float) entryCoords[1], radius - 2,
				renderer.defineBrush(Color.BLACK));
	}

	/**
	 * Method to choose a color from the colorList.
	 * @param listIndex The index for the color.
	 * @return The corresponding color.
	 */
	private int pickColor(final int listIndex) {

		// if the amount of tunnels to draw is greater than the colors to choose from...
		// well, you gotta start all over again :)
		return colorList[listIndex % colorList.length];
	}
}
