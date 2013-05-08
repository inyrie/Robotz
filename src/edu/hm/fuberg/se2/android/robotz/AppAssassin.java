/**
 * Munich University for Applied Science,
 * Faculty 07 for Mathematics and Computer Science
 * Softwareentwicklung II, SS2013, Studiengruppe IF1A
 * Windows XP SP3; Java-Version: 1.7.0_17
 * Developing a Java application.
 */

package edu.hm.fuberg.se2.android.robotz;

import android.app.Activity;
import edu.hm.fuberg.se2.android.robotz.control.RobotzControl;

/**
 * The Class terminates the Game.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-05-08
 */
public class AppAssassin extends Thread {

	/** The Activity object. */
	private final Activity activity;
	/** The robotzControl object. */
	private final RobotzControl robotzControl;

	/**
	 * Ctor for a new Assassin.
	 * @param activityObject The Activity object.
	 * @param controlObject The RobotzControl object.
	 */
	public AppAssassin(final Activity activityObject, final RobotzControl controlObject) {

		setDaemon(true);
		activity = activityObject;
		robotzControl = controlObject;
	}

	@Override public void run() {
		synchronized (robotzControl) {

			try {
				robotzControl.wait();
			}

			catch (final InterruptedException e) {
				activity.finish();
			}

			activity.finish();
		}
	}
}
