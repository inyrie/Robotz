package edu.hm.fuberg.se2.android.robotz.control;

import edu.hm.fuberg.se2.android.robotz.data.Arena;
import edu.hm.fuberg.se2.android.robotz.data.GameState;
import edu.hm.fuberg.se2.android.robotz.view.UpdateOnlyView;

/**
 * Class for updating the current game state.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-05-20
 */
public class Updater extends Thread {

	/** The robotzView object. */
	private final UpdateOnlyView robotzView;
	/** The robotzData object. */
	private final Arena robotzData;
	/** The robotzControl object. */
	private final RobotzControl robotzControl;

	/**
	 * Ctor.
	 * @param control the control object.
	 * @param view the view object.
	 * @param data the data object.
	 */
	public Updater(final RobotzControl control, final UpdateOnlyView view, final Arena data) {
		super();
		robotzView = view;
		robotzData = data;
		robotzControl = control;
	}

	@Override public void run() {

		// Variable for the System timestamp the update()-method was last
		// called.
		long lastUpdate = System.currentTimeMillis();

		// evolving the game for the milliseconds since this method was last called.
		while (robotzData.getState() == GameState.Running) {

			final long currentTime = System.currentTimeMillis();
			robotzControl.evolve(currentTime - lastUpdate);
			robotzView.update();
			lastUpdate = currentTime;
		}
	}
}
