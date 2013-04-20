package edu.hm.fuberg.se2.android.robotz.control;

import edu.hm.fuberg.se2.android.robotz.data.Arena;
import edu.hm.fuberg.se2.android.robotz.data.Target;
import edu.hm.fuberg.se2.android.robotz.view.UpdateOnlyView;

public class RobotzControl {

	/** The robotzView object. */
	private UpdateOnlyView robotzView;

	/** The robotz data. */
	private final Arena robotzData;

	/**
	 * Ctor.
	 * @param robotzData the robotz data.
	 */
	public RobotzControl(final Arena robotzData) {

		this.robotzData = robotzData;
	}

	/**
	 * Sets the robotzView object.
	 * @param robotzView The robotzView object.
	 * @throws IllegalArgumentException if this.robotzView != null.
	 */
	public void setRobotzView(final UpdateOnlyView robotzView) {

		if (this.robotzView == null) {
			this.robotzView = robotzView;
			new Updater(robotzView, robotzData).start();
		}
		else {
			throw new IllegalArgumentException("View cannot be set twice!");
		}
	}

	/**
	 * Method for setting a new target point.
	 * @param destination
	 * @return
	 */
	public Target createNewTarget(final Target destination) {
		robotzData.getPlayer().setDestination(destination);
		return destination;
	}

	/**
	 * Method for freezing the game, for example when changing from Robotz App
	 * to homescreen without closing the App completely.
	 */
	public void holdGame() {
		// here be magical code!
		// end Thread Updater.
	}

	/** Method for continuing a previously frozen game. */
	public void continueGame() {
		// here be magical code, too!
		// create new Thread Updater!
	}
}
