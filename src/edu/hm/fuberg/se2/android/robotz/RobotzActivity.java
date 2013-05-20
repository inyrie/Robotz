/**
 * Munich University for Applied Science,
 * Faculty 07 for Mathematics and Computer Science
 * Softwareentwicklung II, SS2013, Studiengruppe IF1A
 * Windows XP SP3; Java-Version: 1.7.0_17
 * Developing a Java application.
 */

package edu.hm.fuberg.se2.android.robotz;

import android.app.Activity;
import android.content.res.Configuration;
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
 * @version 2013-05-20
 */
public class RobotzActivity extends Activity {

	/** The assassin object for a new killer Thread. */
	private AppAssassin assassin;

	/** The view object. */
	private RobotzView robotzView;

	@Override protected void onCreate(final Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		// setup for app window
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// instanciating a config object that handles the game setup
		final GameConfig configurator = new GameConfig(this);
		Arena robotzData;

		try {
			robotzData = new Arena(configurator.getGameboard(), configurator.getSpeedPlayer(),
					configurator.getSpeedRobot());
			final RobotzControl robotzControl = new RobotzControl(robotzData);
			robotzView = new RobotzView(this, robotzControl, robotzData);

			setContentView(robotzView);

			// instanciating and starting a new assassin thread that handles the ending of the activity
			assassin = new AppAssassin(this, robotzControl);
			assassin.start();
		}

		catch (final IllegalArgumentException exception) {
			exception.printStackTrace();
		}
	}

	@Override protected void onDestroy() {
		super.onDestroy();
		// onDestroy() also triggers the assassin to finish the activity correctly
		assassin.interrupt();
	}

	/**
	 * Redefinition of onConfigurationChanged to handle orientation changes that occur when the smart device is rotated.
	 * Causes the activity to pause.
	 * @param newConfig The new Configuration object containing the performed changes.
	 */
	@Override public void onConfigurationChanged(final Configuration newConfig) {

		super.onConfigurationChanged(newConfig);
		setContentView(robotzView);
	}
}
