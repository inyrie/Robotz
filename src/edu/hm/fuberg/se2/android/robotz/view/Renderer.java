package edu.hm.fuberg.se2.android.robotz.view;

import android.view.SurfaceHolder;

/**
 * Class is responsible for painting the Game.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-04-21
 */
public class Renderer implements UpdateOnlyView {

	private final int width;
	private final int height;
	private final SurfaceHolder holder;

	/**
	 * Ctor for a new Renderer object
	 * @param width the width of the canvas in pixel.
	 * @param height the height of the canvas in pixel.
	 * @param holder the SurfaceHolder.
	 */
	public Renderer(final int width, final int height, final SurfaceHolder holder) {

		this.width = width;
		this.height = height;
		this.holder = holder;
	}

	@Override
	public void update() {
	}
}
