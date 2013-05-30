/**
 * Munich University for Applied Science,
 * Faculty 07 for Mathematics and Computer Science
 * Softwareentwicklung II, SS2013, Studiengruppe IF1A
 * Windows XP SP3; Java-Version: 1.7.0_17
 * Developing a Java application.
 */

package edu.hm.fuberg.se2.android.robotz.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import edu.hm.fuberg.se2.android.robotz.data.ReadOnlyArena;

/**
 * Class is responsible for painting the Game.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-05-30
 */
class Renderer implements UpdateOnlyView {

	// /////////////// OBJECT VARIABLES /////////////////

	/** The surface holder. */
	private final SurfaceHolder surfaceHolder;

	/** The robotz data. */
	private final ReadOnlyArena robotzData;

	/** A converter object, responsible for converting model to pixel values and vice versa. */
	private final Converter converter;

	/** A list of colors to pick from when creating multiple tunnels. */
	private final int[] colorList;

	// /////////////// C T O R /////////////////

	/**
	 * Ctor for a new Renderer object.
	 * @param data the robotz data.
	 * @param holder the SurfaceHolder.
	 * @param width the width of the screen.
	 * @param height the height of the screen.
	 */
	Renderer(final ReadOnlyArena data, final SurfaceHolder holder, final int width, final int height) {

		robotzData = data;
		surfaceHolder = holder;
		converter = new Converter(robotzData, Math.min(width, height));
		colorList = createColorList();
	}

	// /////////////// G E T T E R /////////////////

	Converter getConverter() {
		return converter;
	}

	// /////////////// ///////////////// /////////////////

	@Override public void update() {
		final Canvas canvas = surfaceHolder.lockCanvas();

		if (canvas != null) {

			canvas.drawColor(Color.BLACK);
			canvas.drawRect(0, 0, converter.modelToPixelValuesX(robotzData.getWidth()),
					converter.modelToPixelValuesY(robotzData.getHeight()), defineBrush(Color.GRAY));
			drawTunnels(canvas);
			drawPlayer(canvas);
			drawExit(canvas);
			drawTarget(canvas);
			drawRobots(canvas);
			drawFences(canvas);
			surfaceHolder.unlockCanvasAndPost(canvas);
		}
	}

	// /////////////// DRAWING METHODS /////////////////

	/**
	 * Method for creating a list of possible color values for tunnels.
	 * @return An array of color values to choose from.
	 */
	private int[] createColorList() {

		return new int[] {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.DKGRAY, Color.MAGENTA,
				Color.WHITE};
	}

	/**
	 * Method draws a player on the canvas.
	 * @param drawCanvas the canvas.
	 */
	private void drawPlayer(final Canvas drawCanvas) {

		final double halfSize = robotzData.getPlayer().getSize() / 2;
		final float radius = converter.modelToPixelValues(halfSize);

		final double[] playerCoords = converter.modelToPixelCoords(robotzData.getPlayer().getXCoord(), robotzData
				.getPlayer().getYCoord(), halfSize);
		drawCanvas.drawCircle((float) playerCoords[0], (float) playerCoords[1], radius, defineBrush(Color.GREEN));
	}

	/**
	 * Method draws an exit on the canvas.
	 * @param drawCanvas the canvas.
	 */
	private void drawExit(final Canvas drawCanvas) {

		final double halfSize = robotzData.getExit().getSize() / 2;
		final float radius = converter.modelToPixelValues(halfSize);

		final double[] exitCoords = converter.modelToPixelCoords(robotzData.getExit().getXCoord(), robotzData.getExit()
				.getYCoord(), halfSize);
		drawCanvas.drawCircle((float) exitCoords[0], (float) exitCoords[1], radius, defineBrush(Color.BLUE));
	}

	/**
	 * Method draws a Target on the canvas.
	 * @param drawCanvas the canvas.
	 */
	private void drawTarget(final Canvas drawCanvas) {

		if (robotzData.getPlayer().getDestination() != null) {

			final double halfSize = robotzData.getPlayer().getDestination().getSize() / 2;
			final float radius = converter.modelToPixelValues(halfSize);

			final double[] targetCoords = converter.modelToPixelCoords(robotzData.getPlayer().getDestination()
					.getXCoord(), robotzData.getPlayer().getDestination().getYCoord(), halfSize);
			drawCanvas.drawCircle((float) targetCoords[0], (float) targetCoords[1], radius, defineBrush(Color.WHITE));
		}
	}

	/**
	 * Method draws a robot on the canvas.
	 * @param drawCanvas the canvas.
	 */
	private void drawRobots(final Canvas drawCanvas) {

		for (int position = 0; position < robotzData.getAmountRobots(); position++) {

			final double halfSize = robotzData.getRobot(position).getSize() / 2;
			final float radius = converter.modelToPixelValues(halfSize);
			final double[] robotCoords = converter.modelToPixelCoords(robotzData.getRobot(position).getXCoord(),
					robotzData.getRobot(position).getYCoord(), halfSize);
			drawCanvas.drawCircle((float) robotCoords[0], (float) robotCoords[1], radius, defineBrush(Color.RED));
		}
	}

	/**
	 * Method draws a fence on the canvas.
	 * @param drawCanvas the canvas.
	 */
	private void drawFences(final Canvas drawCanvas) {

		for (int position = 0; position < robotzData.getAmountFences(); position++) {

			final double halfSize = robotzData.getFence(position).getSize() / 2;
			final float radius = converter.modelToPixelValues(halfSize);

			final double[] fenceCoords = converter.modelToPixelCoords(robotzData.getFence(position).getXCoord(),
					robotzData.getFence(position).getYCoord(), halfSize);
			drawCanvas.drawCircle((float) fenceCoords[0], (float) fenceCoords[1], radius, defineBrush(Color.YELLOW));
		}
	}

	/**
	 * Method to draw a tunnel, consisting of two tunnel holes.
	 * @param drawCanvas The canvas to draw on.
	 */
	private void drawTunnels(final Canvas drawCanvas) {

		// running through all the tunnels on the gameboard.
		for (int tunnelNumber = 0; tunnelNumber < robotzData.getTunnels().size(); tunnelNumber++) {

			final double halfSize = robotzData.getTunnels().get(tunnelNumber).getTunnelPair().get(0).getSize() / 2;
			final float radius = converter.modelToPixelValues(halfSize);

			// drawing each of the two tunnel holes that form the tunnel
			for (int index = 0; index < 2; index++) {

				final double[] entryCoords = converter.modelToPixelCoords(robotzData.getTunnels().get(tunnelNumber)
						.getTunnelPair().get(index).getXCoord(), robotzData.getTunnels().get(tunnelNumber)
						.getTunnelPair().get(index).getYCoord(), halfSize);

				// draw two overlaying circles to mark tunnel holes that belong together.
				drawCanvas.drawCircle((float) entryCoords[0], (float) entryCoords[1], radius,
						defineBrush(pickColor(tunnelNumber)));
				drawCanvas.drawCircle((float) entryCoords[0], (float) entryCoords[1], radius - 2,
						defineBrush(Color.BLACK));
			}
		}
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

	/**
	 * Method for defining the Paint object with which to draw on the canvas.
	 * @param color The color to draw.
	 * @return Returns a Paint object.
	 */
	private Paint defineBrush(final int color) {

		final Paint paint = new Paint();
		paint.setColor(color);

		return paint;
	}
}
