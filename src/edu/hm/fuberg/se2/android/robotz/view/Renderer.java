package edu.hm.fuberg.se2.android.robotz.view;

import android.view.SurfaceHolder;
import edu.hm.fuberg.se2.android.robotz.data.ReadOnlyArena;

/**
 * Class is responsible for painting the Game.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-04-21
 */
public class Renderer implements UpdateOnlyView {

	private final int surfaceWidth;
	private final int surfaceHeight;
	private final SurfaceHolder surfaceHolder;
	private final ReadOnlyArena robotzData;

	/**
	 * Ctor for a new Renderer object
	 * @param surfaceWidth the surfaceWidth of the canvas in pixel.
	 * @param surfaceHeight the surfaceHeight of the canvas in pixel.
	 * @param holder the SurfaceHolder.
	 */
	public Renderer(final ReadOnlyArena data, final SurfaceHolder holder, final int width, final int height) {

		robotzData = data;
		surfaceHolder = holder;
		surfaceWidth = width;
		surfaceHeight = height;
	}

	@Override public void update() {
	}
}
