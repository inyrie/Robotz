/**
 * Munich University for Applied Science,
 * Faculty 07 for Mathematics and Computer Science
 * Softwareentwicklung II, SS2013, Studiengruppe IF1A
 * Windows XP SP3; Java-Version: 1.7.0_17
 * Developing a Java application.
 */

package edu.hm.fuberg.se2.android.robotz;

/**
 * The Class configures the arena on app start.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-05-18
 */
package edu.hm.fuberg.se2.android.robotz;

import hm.edu.fuberg.se2.android.robotz.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.util.Log;

/**
 * The Class configurates the game from an external config.txt-File.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-05-18
 */
class GameConfig {

	private final Context context;
	/** List with lines of configuration file. */
	private final List<String> lines = new ArrayList<String>();

	/** The value defining a robot's velocity. */
	private double speedRobot;

	/** The value defining the player's velocity. */
	private double speedPlayer;

	/**
	 * @param context
	 * @param speedRobot
	 * @param speedPlayer
	 */
	GameConfig(final Context context) {

		this.context = context;
		// this.speedRobot = speedRobot;
		// this.speedPlayer = speedPlayer;
	}

	/**
	 * @throws NotFoundException
	 */
	void loadExternalFile() throws NotFoundException {
		try {
			// standard voodoo for getting text from an input stream
			final InputStream inputStream = context.getResources().openRawResource(R.raw.arena1);
			final Reader reader = new InputStreamReader(inputStream);
			final BufferedReader bufferedReader = new BufferedReader(reader);

			String line = bufferedReader.readLine();

			// continue until no lines left
			while (line != null) {
				lines.add(line);
				line = bufferedReader.readLine();
			}

			// closes everything down to input stream
			bufferedReader.close();
		}

		catch (final IOException ex) {
			Log.e("dummy", "failed reading resource file: " + ex.getMessage());
		}
	}
}
