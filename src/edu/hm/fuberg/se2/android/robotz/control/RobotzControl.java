package edu.hm.fuberg.se2.android.robotz.control;

import edu.hm.fuberg.se2.android.robotz.data.Arena;


public class RobotzControl {

	/**  */
	private UpdateableView robotzView;

	/** The robotz data. */
	private final Arena robotzData;

	/**
	 * Sets the robotsData.
	 * 
	 * @param robotzData the robotz data.
	 */
	public RobotzControl(final Arena robotzData) {

		this.robotzData = robotzData;
	}

	/**
	 * Sets the robotz view.
	 *
	 * @param robotzView the new robotz view.
	 * @throws IllegalArgumentException if this.robotzView != null.
	 */
	public void setPropellerView(final UpdateableView robotzView) {

		if (this.robotzView != null) {

			throw new IllegalArgumentException("view set twice");
		}

		this.robotzView = robotzView;
		new Updater(robotzView, robotzData).start();
	}
}
