/**
 *
 */
package edu.hm.fuberg.se2.android.robotz;

import android.app.Activity;
import edu.hm.fuberg.se2.android.robotz.control.RobotzControl;

/**
 * @author Stäff
 */
public class AppAssassin extends Thread {

	/** The Activity object. */
	final Activity activity;
	/** The robotzControl object. */
	final RobotzControl robotzControl;

	/**
	 * Ctor.
	 * @param activityObject The Activity object.
	 * @param controlObject The RobotzControl object.
	 */
	public AppAssassin(final Activity activityObject, final RobotzControl controlObject) {

		setDaemon(true);
		activity = activityObject;
		robotzControl = controlObject;
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run() */
	@Override public void run() {
		synchronized (robotzControl) {

			try {
				robotzControl.wait();
			}

			catch (final InterruptedException e) {
				e.printStackTrace();
			}

			activity.finish();
		}
	}
}
