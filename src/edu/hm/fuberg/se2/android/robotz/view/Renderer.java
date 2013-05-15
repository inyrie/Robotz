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

	/** The surface holder. */
	private final SurfaceHolder surfaceHolder;

	/** The robotz data. */
	private final ReadOnlyArena robotzData;

	/** Defines the smaller side of the surface. Relevant for keeping objects inside the arena, even if it is smaller than the actual surface size. */
	private final int surfaceSizePixel;

	/**	Defines the factor for the width to calculate from model to pixel coordinates. */
	private double modelToPixelFactorX;

	/**	Defines the factor for the height to calculate from model to pixel coordinates. */
	private double modelToPixelFactorY;

	/**	Defines the factor for the width to calculate from pixel to model coordinates. */
	private double pixelToModelFactorX;

	/**	Defines the factor for the height to calculate from pixel to model coordinates. */
	private double pixelToModelFactorY;

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
		surfaceSizePixel = Math.min(width, height);

		computeScaleFactors(width, height);
	}


	@Override public void update() {
		final Canvas canvas = surfaceHolder.lockCanvas();

		if(canvas != null){

			canvas.drawColor(Color.BLACK);
			drawPlayer(canvas);
			drawExit(canvas);
			drawTarget(canvas);
			drawRobots(canvas);
			drawFences(canvas);
			surfaceHolder.unlockCanvasAndPost(canvas);
		}
	}

	/**
	 * Method draws a player on the canvas.
	 * @param drawCanvas the canvas.
	 */
	public void drawPlayer(final Canvas drawCanvas) {

		final Item player = robotzData.getPlayer();
		final double halfSize = player.getSize() / 2;
		final float radius = modelToPixel(halfSize);

		final double[] playerCoords = modelToPixelCoords(player, halfSize);
		drawCanvas.drawCircle((float) playerCoords[0], (float) playerCoords[1], radius, defineBrush(Color.GREEN));
	}

	/**
	 * Method draws an exit on the canvas.
	 * @param drawCanvas the canvas.
	 */
	public void drawExit(final Canvas drawCanvas) {

		final Item exit = robotzData.getExit();
		final double halfSize = exit.getSize() / 2;
		final float radius = modelToPixel(halfSize);

		final double[] exitCoords = modelToPixelCoords(exit, halfSize);
		drawCanvas.drawCircle((float) exitCoords[0], (float) exitCoords[1], radius, defineBrush(Color.BLUE));
	}

	/**
	 * Method draws a Target on the canvas.
	 * @param drawCanvas the canvas.
	 */
	public void drawTarget(final Canvas drawCanvas) {

		if (robotzData.getPlayer().getDestination() != null) {

			final Item target = robotzData.getPlayer().getDestination();
			final double halfSize = target.getSize() / 2;
			final float radius = modelToPixel(halfSize);

			final double[] targetCoords = modelToPixelCoords(target, halfSize);
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
			final double halfSize = robot.getSize() / 2;
			final float radius = modelToPixel(halfSize);
			final double[] robotCoords = modelToPixelCoords(robot, halfSize);
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
			final double halfSize = fence.getSize() / 2;
			final float radius = modelToPixel(halfSize);

			final double[] fenceCoords = modelToPixelCoords(fence, halfSize);
			drawCanvas.drawCircle((float) fenceCoords[0], (float) fenceCoords[1], radius, defineBrush(Color.YELLOW));
		}
	}

	/**
	 * Method calculates the pixel coordinates to logical coordinates.
	 * @param event The touch event.
	 * @return the logical coordinates.
	 */
	public Target pixelToModelCoords(final MotionEvent event) {

		final double halfSize = Target.TARGET_SIZE / 2;

		return new Target((event.getX() * pixelToModelFactorX)-halfSize, (event.getY() * pixelToModelFactorY)-halfSize);
	}

	/**
	 * Method calculates the logical coordinates to pixel coordinates.
	 * @param item The item with logical coordinates.
	 * @return The pixel coordinates as array.
	 */
	private double[] modelToPixelCoords(final Item item) {

		// Returning the computed pixel coordinates as double[] array.
		return new double[] {item.getXCoord() * modelToPixelFactorX, item.getYCoord() * modelToPixelFactorY};
	}

	/**
	 * Method calculates the logical coordinates to pixel coordinates.
	 * @param item The item with logical coordinates.
	 * @param shift The delta for which the coordinates are shifted.
	 * @return The pixel coordinates as array.
	 */
	private double[] modelToPixelCoords(final Item item, final double shift) {

		// Returning the computed pixel coordinates as double[] array.

		return new double[] {(item.getXCoord() + shift) * modelToPixelFactorX, (item.getYCoord() + shift) * modelToPixelFactorY};
	}

	/**
	 * Method for computing pixel values from model values (used for drawing).
	 * @param modelValue The model value to convert into pixel values.
	 * @return The pixel values.
	 */
	private float modelToPixel(final double modelValue) {
		return (float) (modelValue * Math.min(modelToPixelFactorX, modelToPixelFactorY));
	}


	/**
	 * Method for computing scaling factors for setting coordinates correctly.
	 * @param width The surface width.
	 * @param height The surface height.
	 */
	private void computeScaleFactors(final int width, final int height)	{

		modelToPixelFactorX = surfaceSizePixel / robotzData.getWidth();
		modelToPixelFactorY = surfaceSizePixel / robotzData.getHeight();

		pixelToModelFactorX = robotzData.getWidth() / surfaceSizePixel;
		pixelToModelFactorY = robotzData.getHeight() / surfaceSizePixel;
	}

	/**
	 * Support method for defining the Paint object with which to draw on the canvas.
	 * @param color The color to draw.
	 * @return Returns a Paint object.
	 */
	private Paint defineBrush(final int color) {

		final Paint paint = new Paint();

		paint.setColor(color);

		return paint;
	}
}
