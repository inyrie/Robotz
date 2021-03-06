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
 * @version 2013-06-15
 */
class Renderer implements UpdateOnlyView {

	// /////////////// OBJECT VARIABLES /////////////////

	/** The surface holder. */
	private final SurfaceHolder surfaceHolder;

	/** The robotz data. */
	private final ReadOnlyArena robotzData;

	/** A converter object, responsible for converting model to pixel values and vice versa. */
	private final Converter converter;

	/** A renderer object solely for drawing tunnels. */
	private final TunnelRenderer tunnelRenderer;

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
		tunnelRenderer = new TunnelRenderer(data, this);
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
			tunnelRenderer.drawAllTunnels(canvas);
			drawTarget(canvas);
			drawPlayer(canvas);
			drawExit(canvas);
			drawRobots(canvas);
			drawFences(canvas);
			surfaceHolder.unlockCanvasAndPost(canvas);
		}
	}

	// /////////////// DRAWING METHODS /////////////////

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

		for (int position = 0; position < robotzData.getRobots().size(); position++) {

			final double halfSize = robotzData.getRobots().get(position).getSize() / 2;
			final float radius = converter.modelToPixelValues(halfSize);
			final double[] robotCoords = converter.modelToPixelCoords(robotzData.getRobots().get(position).getXCoord(),
					robotzData.getRobots().get(position).getYCoord(), halfSize);
			drawCanvas.drawCircle((float) robotCoords[0], (float) robotCoords[1], radius, defineBrush(Color.RED));
		}
	}

	/**
	 * Method draws a fence on the canvas.
	 * @param drawCanvas the canvas.
	 */
	private void drawFences(final Canvas drawCanvas) {

		for (int position = 0; position < robotzData.getFences().size(); position++) {

			final double halfSize = robotzData.getFences().get(position).getSize() / 2;
			final float radius = converter.modelToPixelValues(halfSize);

			final double[] fenceCoords = converter.modelToPixelCoords(robotzData.getFences().get(position).getXCoord(),
					robotzData.getFences().get(position).getYCoord(), halfSize);
			drawCanvas.drawCircle((float) fenceCoords[0], (float) fenceCoords[1], radius, defineBrush(Color.YELLOW));
		}
	}

	/**
	 * Method for defining the Paint object with which to draw on the canvas.
	 * @param color The color to draw.
	 * @return Returns a Paint object.
	 */
	Paint defineBrush(final int color) {

		final Paint paint = new Paint();
		paint.setColor(color);

		return paint;
	}
}
