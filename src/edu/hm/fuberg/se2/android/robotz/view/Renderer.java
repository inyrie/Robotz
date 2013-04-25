package edu.hm.fuberg.se2.android.robotz.view;

import android.view.SurfaceHolder;
import edu.hm.fuberg.se2.android.robotz.data.ReadOnlyArena;

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

	/**
	 * Ctor for a new Renderer object.
	 *
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

		// BULLSHIT CODE for PMD
		System.out.println(surfaceWidth +  surfaceHeight + surfaceHolder.toString() + robotzData.toString());
	}
}
