/**
 * Munich University for Applied Science,
 * Faculty 07 for Mathematics and Computer Science
 * Softwareentwicklung II, SS2013, Studiengruppe IF1A
 * Windows XP SP3; Java-Version: 1.7.0_17
 * Developing a Java application.
 */

package edu.hm.fuberg.se2.android.robotz;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import edu.hm.fuberg.se2.android.robotz.control.RobotzControl;
import edu.hm.fuberg.se2.android.robotz.data.Arena;
import edu.hm.fuberg.se2.android.robotz.view.RobotzView;

/**
 * The Class initiates the Game.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-05-08
 */
public class RobotzActivity extends Activity {

	/** The assassin object for a new killer Thread. */
	private AppAssassin assassin;

	@Override protected void onCreate(final Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		final char[][] arena = new char[][] {

				{'P', '.', '.', '.', '.', '.', '.', '.', '.', 'F', '.', '.', '.', '.', '.', '.', '.', '.', 'R', 'R'},
				{'.', '.', '.', '.', '.', '.', '.', '.', '.', 'F', '.', '.', '.', '.', '.', '.', '.', '.', 'R', 'R'},
				{'.', '.', '.', '.', '.', '.', '.', '.', '.', 'F', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
				{'.', '.', '.', '.', '.', '.', '.', 'R', '.', 'F', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
				{'.', '.', '.', '.', '.', '.', '.', 'R', '.', 'F', '.', '.', '.', '.', 'F', '.', '.', '.', '.', '.'},
				{'.', '.', '.', '.', '.', '.', '.', 'R', '.', 'F', '.', '.', '.', '.', 'F', '.', '.', '.', '.', '.'},
				{'.', '.', '.', '.', '.', '.', '.', 'R', '.', 'F', '.', '.', '.', '.', 'F', '.', '.', '.', '.', '.'},
				{'.', '.', 'F', 'F', 'F', '.', '.', 'R', '.', 'F', '.', '.', '.', '.', 'F', '.', '.', '.', '.', '.'},
				{'.', '.', '.', '.', '.', '.', '.', 'R', '.', 'F', '.', '.', '.', '.', 'F', '.', '.', '.', '.', '.'},
				{'.', '.', '.', '.', '.', '.', '.', 'R', '.', 'F', '.', '.', '.', '.', 'F', '.', '.', '.', '.', '.'},
				{'.', '.', '.', '.', '.', '.', '.', 'R', '.', 'F', '.', '.', '.', '.', 'F', '.', '.', '.', '.', '.'},
				{'.', '.', '.', '.', '.', '.', '.', 'R', '.', '.', '.', '.', '.', '.', 'F', '.', '.', '.', '.', '.'},
				{'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'F', '.', '.', '.', '.', '.'},
				{'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'F', '.', '.', '.', '.', '.'},
				{'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'F', '.', '.', '.', '.', 'E'}};

		Arena robotzData;

		try {
			robotzData = new Arena(arena);

			final RobotzControl robotzControl = new RobotzControl(robotzData);
			final RobotzView robotzView = new RobotzView(this, robotzControl, robotzData);

			setContentView(robotzView);

			assassin = new AppAssassin(this, robotzControl);
			assassin.start();
		}

		catch (final IllegalArgumentException exception) {
			exception.printStackTrace();
		}
	}

	@Override protected void onDestroy() {
		super.onDestroy();
		assassin.interrupt();
	}
}
