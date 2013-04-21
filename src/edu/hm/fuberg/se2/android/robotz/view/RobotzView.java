/**
 *
 */
package edu.hm.fuberg.se2.android.robotz.view;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import edu.hm.fuberg.se2.android.robotz.control.RobotzControl;
import edu.hm.fuberg.se2.android.robotz.data.ReadOnlyArena;
import edu.hm.fuberg.se2.android.robotz.data.Target;

/**
 * ...
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

	/**
	 * Ctor for a new RobotzView.
	 * @param control the robotz control object.
	 * @param data the robotz data object.
	 * @param context the context
	 */
	public RobotzView(final RobotzControl control, final ReadOnlyArena data, final Context context) {

		super(context);
		surfaceHolder = getHolder();
		surfaceHolder.addCallback(this);
		robotzControl = control;
		robotzControl.setRobotzView(this);
		robotzData = data;
	}

	@Override
	public void surfaceChanged(final SurfaceHolder holder, final int format, final int width,	final int height) {

	}

	@Override
	public void surfaceCreated(final SurfaceHolder holder) {


	}

	@Override
	public void surfaceDestroyed(final SurfaceHolder holder) {


	}

	@Override
	public void update() {
	}

	@Override
	public boolean onTouchEvent(final MotionEvent event) {

		if (event.getAction() == MotionEvent.ACTION_DOWN) {

			// Anpassung auf Modellkoordinaten fehlt noch.
			robotzControl.createNewTarget(new Target(event.getX(), event.getY()));
		}

		return true;
	}
}
