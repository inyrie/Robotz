package edu.hm.fuberg.se2.android.robotz.view;

import android.graphics.Canvas;
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

	/** */
	private final Canvas canvas;

	/** The width of the screen. */
	private final int surfaceWidth;

	/** The height of the screen. */
	private final int surfaceHeight;

	/** The surface holder. */
	private final SurfaceHolder surfaceHolder;

	/** The robotz data. */
	private final ReadOnlyArena robotzData;

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
		canvas = surfaceHolder.lockCanvas();
	}

	@Override public void update() {

		// BULLSHIT CODE for PMD
		System.out.println(surfaceWidth + surfaceHeight + surfaceHolder.toString() + robotzData.toString());
	}

	/**
	 * Bla.
	 * @param event Bla.
	 * @return Bla.
	 */
	public Target pixelToModelCoords(final MotionEvent event) {

		final double factorWidth = robotzData.getWidth() / canvas.getWidth();
		final double factorHeight = robotzData.getHeight() / canvas.getHeight();

		return new Target(event.getX() * factorWidth, event.getY() * factorHeight);
	}

	/**
	 * Bla.
	 * @param item Bla.
	 * @return Bla.
	 */
	public double[] modelToPixelCoords(final Item item) {

		final double factorWidth = canvas.getWidth() / robotzData.getWidth();
		final double factorHeight = canvas.getHeight() / robotzData.getHeight();

		// Returning the computed pixel coordinates as double[] array.
		return new double[] {item.getXCoord() * factorWidth, item.getYCoord() * factorHeight};
	}
}
