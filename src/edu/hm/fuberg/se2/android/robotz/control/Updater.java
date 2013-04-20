package edu.hm.fuberg.se2.android.robotz.control;

import edu.hm.fuberg.se2.android.robotz.data.Arena;
import edu.hm.fuberg.se2.android.robotz.data.GameState;
import edu.hm.fuberg.se2.android.robotz.view.UpdateOnlyView;

/**
 * Class for updating the current game state.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-04-20
 */
public class Updater extends Thread {

	/** The robotz view. */
	private final UpdateOnlyView robotzView;

	/** The robotz data. */
	private final Arena robotzData;

	/**
	 * Instantiates a new updater.
	 * @param robotzView the propeller view
	 * @param robotzData the propeller data
	 */
	Updater(final UpdateOnlyView robotzView, final Arena robotzData) {

		this.robotzView = robotzView;
		this.robotzData = robotzData;
	}

	@Override public void run() {

		long now = System.currentTimeMillis();

		while (robotzData.getState() == GameState.Running) {

			final long currentTime = System.currentTimeMillis();
			evolve(currentTime - now);
			now = currentTime;
			robotzView.update();
		}
	}

}
