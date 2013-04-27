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
		drawStuff(canvas);
		surfaceHolder.unlockCanvasAndPost(canvas);
	}

	public void drawStuff(final Canvas canvas) {
		final Item exit = robotzData.getExit();
		final double[] exitCoords = modelToPixelCoords(exit);
		canvas.drawCircle((float) exitCoords[0], (float) exitCoords[1], 10, defineBrush());
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
	private Paint defineBrush() {

		final Paint paint = new Paint();

		paint.setColor(Color.RED);
		paint.setStrokeWidth(5);
		paint.setStrokeCap(Paint.Cap.ROUND);

		return paint;
	}

}
