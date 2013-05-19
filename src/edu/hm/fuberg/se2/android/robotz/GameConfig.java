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

	/** The activity context. */
	private final Context context;
	/** List with gameboard of configuration file. */

	private final List<String> gameboard = new ArrayList<String>();

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
		loadExternalFile();
	}

	List<String> getGameboard() {
		return gameboard;
	}

	public double getSpeedRobot() {
		return speedRobot;
	}

	public double getSpeedPlayer() {
		return speedPlayer;
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

			// final String line = bufferedReader.readLine();
			setupGame(bufferedReader);

			// closes everything down to input stream
			bufferedReader.close();
		}

		catch (final IOException ex) {
			Log.e("dummy", "failed reading resource file: " + ex.getMessage());
		}
	}

	private void setupGame(final BufferedReader bufferedReader) throws IOException {

		String line = bufferedReader.readLine();

		while (line != null) {

			if (line.startsWith("player")) {
				speedPlayer = parseVelocity(line);
			}

			else if (line.startsWith("robot")) {
				speedRobot = parseVelocity(line);
			}

			// call to string.isEmpty() is not supported for Android API < 9!
			else if (line.length() > 0) {

				gameboard.add(line);
				checkLine(line);
			}

			line = bufferedReader.readLine();
		}
	}

	private double parseVelocity(final String line) {

		final String substring = line.substring(line.indexOf("=") + 1).trim();
		return Double.parseDouble(substring);
	}

	private void checkLine(final String lineToCheck) {

		if (lineToCheck.length() != gameboard.get(0).length()) {
			throw new IllegalArgumentException("Arena contains lines of unequal lenght. Not supported.");
		}
	}
}