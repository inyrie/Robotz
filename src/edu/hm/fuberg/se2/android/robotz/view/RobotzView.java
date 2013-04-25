package edu.hm.fuberg.se2.android.robotz.view;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import edu.hm.fuberg.se2.android.robotz.control.RobotzControl;
import edu.hm.fuberg.se2.android.robotz.data.ReadOnlyArena;
import edu.hm.fuberg.se2.android.robotz.data.Target;

/**
 * Class coordinates the painting of the Game.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-04-21
 */
public class RobotzView extends SurfaceView implements SurfaceHolder.Callback, UpdateOnlyView {

	/** The surface holder. */
	private final SurfaceHolder surfaceHolder;

	/** The robotz control object. */
	private final RobotzControl robotzControl;

	/** The robotz data object. */
	private final ReadOnlyArena robotzData;

	/** The renderer object. */
	private Renderer renderer;

	/**
	 * Ctor for a new RobotzView.
	 * @param context the context
	 * @param control the robotz control object.
	 * @param data the robotz data object.
	 */
	public RobotzView(final Context context, final RobotzControl control, final ReadOnlyArena data) {

		super(context);
		surfaceHolder = getHolder();
		surfaceHolder.addCallback(this);
		robotzControl = control;
		// robotzControl.setRobotzView(this);
		robotzData = data;
	}

	@Override public void surfaceChanged(final SurfaceHolder holder, final int format, final int width, final int height) {

		renderer = new Renderer(robotzData, surfaceHolder, width, height);
		robotzControl.continueGame(this);
		renderer.update();
	}

	@Override public void surfaceCreated(final SurfaceHolder holder) {
		robotzControl.continueGame(this);
	}

	@Override public void surfaceDestroyed(final SurfaceHolder holder) {
		robotzControl.holdGame();
	}

	@Override public void update() {
		renderer.update();
	}

	@Override public boolean onTouchEvent(final MotionEvent event) {

		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			// Anpassung auf Modellkoordinaten fehlt noch.
			robotzControl.createNewTarget(new Target(event.getX(), event.getY()));
		}

		return true;
	}
}
