package edu.hm.fuberg.se2.android.robotz.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import edu.hm.fuberg.se2.android.robotz.data.Item;
import edu.hm.fuberg.se2.android.robotz.data.ReadOnlyArena;
import edu.hm.fuberg.se2.android.robotz.data.Target;

/**
 * Class is responsible for painting the Game.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-04-25
 */
public class Renderer implements UpdateOnlyView {

	/**
	 * Constant representing the value for stroke width.
	 */
	private static final int STROKE_WIDTH = 5;

	/** The width of the screen. */
	private final int surfaceWidth;

	/** The height of the screen. */
	private final int surfaceHeight;

	/** The surface holder. */
	private final SurfaceHolder surfaceHolder;

	/** The robotz data. */
	private final ReadOnlyArena robotzData;

	/** The canvas. */
	private Canvas canvas;

	/**
	 * Ctor for a new Renderer object.
	 * @param data the robotz data
	 * @param holder the SurfaceHolder.
	 * @param width the width of the screen.
	 * @param height the height of the screen.
	 */
	public Renderer(final ReadOnlyArena data, final SurfaceHolder holder, final int width, final int height) {

		robotzData = data;
		surfaceHolder = holder;
		surfaceWidth = width;
		surfaceHeight = height;
	}

	@Override public void update() {
		canvas = surfaceHolder.lockCanvas();
		canvas.drawColor(Color.BLACK);
		drawPlayer(canvas);
		drawExit(canvas);
		drawTarget(canvas);
		drawRobots(canvas);
		drawFences(canvas);
		surfaceHolder.unlockCanvasAndPost(canvas);
	}

	/**
	 * Method draws a player on the canvas.
	 * @param drawCanvas the canvas.
	 */
	public void drawPlayer(final Canvas drawCanvas) {

		final Item player = robotzData.getPlayer();
		final float radius = modelToPixel(player.getSize()) / 2;

		final double[] playerCoords = modelToPixelCoords(player);
		drawCanvas.drawCircle((float) playerCoords[0], (float) playerCoords[1], radius, defineBrush(Color.GREEN));
	}

	/**
	 * Method draws an exit on the canvas.
	 * @param drawCanvas the canvas.
	 */
	public void drawExit(final Canvas drawCanvas) {

		final Item exit = robotzData.getExit();
		final float radius = modelToPixel(exit.getSize()) / 2;

		final double[] exitCoords = modelToPixelCoords(exit);
		drawCanvas.drawCircle((float) exitCoords[0], (float) exitCoords[1], radius, defineBrush(Color.BLUE));
	}

	/**
	 * Method draws a Target on the canvas.
	 * @param drawCanvas the canvas.
	 */
	public void drawTarget(final Canvas drawCanvas) {

		if (robotzData.getPlayer().getDestination() != null) {

			final Item target = robotzData.getPlayer().getDestination();
			final float radius = modelToPixel(target.getSize()) / 2;

			final double[] targetCoords = modelToPixelCoords(target);
			drawCanvas.drawCircle((float) targetCoords[0], (float) targetCoords[1], radius, defineBrush(Color.WHITE));
		}
	}

	/**
	 * Method draws a robot on the canvas.
	 * @param drawCanvas the canvas.
	 */
	public void drawRobots(final Canvas drawCanvas) {

		for (int position = 0; position < robotzData.getAmountRobots(); position++) {

			final Item robot = robotzData.getRobot(position);
			final float radius = modelToPixel(robot.getSize()) / 2;
			final double[] robotCoords = modelToPixelCoords(robot);
			drawCanvas.drawCircle((float) robotCoords[0], (float) robotCoords[1], radius, defineBrush(Color.RED));
		}
	}

	/**
	 * Method draws a fence on the canvas.
	 * @param drawCanvas the canvas.
	 */
	public void drawFences(final Canvas drawCanvas) {

		for (int position = 0; position < robotzData.getAmountFences(); position++) {

			final Item fence = robotzData.getFence(position);
			final float radius = modelToPixel(fence.getSize()) / 2;

			final double[] fenceCoords = modelToPixelCoords(fence);
			drawCanvas.drawCircle((float) fenceCoords[0], (float) fenceCoords[1], radius, defineBrush(Color.YELLOW));
		}
	}

	/**
	 * Method calculates the pixel coordinates to logical coordinates.
	 * @param event The touch event.
	 * @return the logical coordinates.
	 */
	public Target pixelToModelCoords(final MotionEvent event) {

		final double factorWidth = robotzData.getWidth() / surfaceWidth;
		final double factorHeight = robotzData.getHeight() / surfaceHeight;

		return new Target(event.getX() * factorWidth, event.getY() * factorHeight);
	}

	/**
	 * Method calculates the logical coordinates to pixel coordinates.
	 * @param item The item with logical coordinates.
	 * @return The pixel coordinates as array.
	 */
	private double[] modelToPixelCoords(final Item item) {

		final double factorWidth = surfaceWidth / robotzData.getWidth();
		final double factorHeight = surfaceHeight / robotzData.getHeight();

		// Returning the computed pixel coordinates as double[] array.
		return new double[] {item.getXCoord() * factorWidth, item.getYCoord() * factorHeight};
	}

	/**
	 * Method for ...
	 * @param modelValue Bla
	 * @return Bla
	 */
	private float modelToPixel(final double modelValue) {
		return (float) Math.min(surfaceWidth / robotzData.getWidth(), surfaceHeight / robotzData.getHeight());
	}

	/**
	 * Support method for defining the Paint object with which to draw on the canvas.
	 * @param color The color to draw.
	 * @return Returns a Paint object.
	 */
	private Paint defineBrush(final int color) {

		final Paint paint = new Paint();

		paint.setColor(color);
		paint.setStrokeWidth(STROKE_WIDTH);
		paint.setStrokeCap(Paint.Cap.ROUND);

		return paint;
	}
}
