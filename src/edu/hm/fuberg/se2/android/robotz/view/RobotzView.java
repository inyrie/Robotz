/**
 *
 */
package edu.hm.fuberg.se2.android.robotz.view;

import android.content.Context;
import android.view.SurfaceView;
import edu.hm.fuberg.se2.android.robotz.control.RobotzControl;
import edu.hm.fuberg.se2.android.robotz.data.ReadOnlyArena;

/**
 * ...
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-04-21
 */
public class RobotzView extends SurfaceView implements UpdateOnlyView {

	/**
	 * @param context
	 */
	public RobotzView(final RobotzControl control, final ReadOnlyArena data, final Context context) {
		super(context);
	}

	/* (non-Javadoc)
	 * @see edu.hm.fuberg.se2.android.robotz.view.UpdateOnlyView#update() */
	@Override public void update() {

	}

}
