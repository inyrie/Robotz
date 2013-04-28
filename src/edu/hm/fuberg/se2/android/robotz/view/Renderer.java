package edu.hm.fuberg.se2.android.robotz.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
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

	/** The width of the screen. */
	private final int surfaceWidth;
	/** The height of the screen. */
	private final int surfaceHeight;
	/** The surface holder. */
	private final SurfaceHolder surfaceHolder;
	/** The robotz data. */
	private final ReadOnlyArena robotzData;
	/** */
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
		//drawFences(canvas);
		surfaceHolder.unlockCanvasAndPost(canvas);
	}

	public void drawPlayer(final Canvas canvas) {
		final Item player = robotzData.getPlayer();
		final double[] playerCoords = modelToPixelCoords(player);
		canvas.drawCircle((float) playerCoords[0], (float) playerCoords[1], 10, defineBrush(Color.GREEN));
	}

	public void drawExit(final Canvas canvas) {
		final Item exit = robotzData.getExit();
		final double[] exitCoords = modelToPixelCoords(exit);
		canvas.drawCircle((float) exitCoords[0], (float) exitCoords[1], 10, defineBrush(Color.BLUE));
	}

	public void drawTarget(final Canvas canvas) {

		if (robotzData.getPlayer().getDestination() != null){

			final Item target = robotzData.getPlayer().getDestination();
			final double[] targetCoords = modelToPixelCoords(target);
			canvas.drawCircle((float) targetCoords[0], (float) targetCoords[1], 10, defineBrush(Color.WHITE));
		}
	}

	public void drawRobots(final Canvas canvas) {

		for (int position = 0; position < robotzData.getAmountRobots(); position++) {

			final Item robot = robotzData.getRobot(position);
			final double[] robotCoords = modelToPixelCoords(robot);
			canvas.drawCircle((float) robotCoords[0], (float) robotCoords[1], 10, defineBrush(Color.RED));
		}
	}

	public void drawFences(final Canvas canvas) {

		for (int position = 0; position < robotzData.getAmountFences(); position++) {

			final Item fence = robotzData.getFence(position);
			final double[] fenceCoords = modelToPixelCoords(fence);
			canvas.drawCircle((float) fenceCoords[0], (float) fenceCoords[1], 10, defineBrush(Color.YELLOW));
		}
	}
	// /////////////////////////////////////////////////////////////////

	/**
	 * Bla.
	 * @param event Bla.
	 * @return Bla.
	 */
	public Target pixelToModelCoords(final MotionEvent event) {

		final double factorWidth = robotzData.getWidth() / surfaceWidth;
		final double factorHeight = robotzData.getHeight() / surfaceHeight;
		//Log.d("robotz",robotzData.getPlayer().getXCoord() + " " + robotzData.getPlayer().getYCoord());
		Log.d("robotz",event.getX() * factorWidth + " " + event.getY() * factorHeight);
		return new Target(event.getX() * factorWidth, event.getY() * factorHeight);

	}

	/**
	 * Bla.
	 * @param item Bla.
	 * @return Bla.
	 */
	private double[] modelToPixelCoords(final Item item) {

		final double factorWidth = surfaceWidth / robotzData.getWidth();
		final double factorHeight = surfaceHeight / robotzData.getHeight();

		// Returning the computed pixel coordinates as double[] array.
		return new double[] {item.getXCoord() * factorWidth, item.getYCoord() * factorHeight};
	}

	/**
	 * Support method for defining the Paint object with which to draw on the canvas.
	 * @return Returns a Paint object.
	 */
	private Paint defineBrush(final int color) {

		final Paint paint = new Paint();

		paint.setColor(color);
		paint.setStrokeWidth(5);
		paint.setStrokeCap(Paint.Cap.ROUND);

		return paint;
	}

}
