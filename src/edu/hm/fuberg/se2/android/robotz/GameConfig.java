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
 * @version 2013-05-20
 */

import hm.edu.fuberg.se2.android.robotz.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.util.Log;

/**
 * The Class configurates the game from an external config.txt-File.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 * @version 2013-05-20
 */
public class GameConfig {

	/** Constant for converting the velocity values from the txt-file into the actual values needed for the game. */
	private static final double CONVERTING_FACTOR = 0.001;
	/** The activity context. */
	private final Context context;

	/** List with gameboard of configuration file. */
	private final List<String> gameboard = new ArrayList<String>();

	/** The value defining a robot's velocity. */
	private double speedRobot;

	/** The value defining the player's velocity. */
	private double speedPlayer;

	/**
	 * Ctor.
	 * @param context The Arena context.
	 */
	GameConfig(final Context context) {

		this.context = context;
		// starting the whole procedere of reading the external config-file and parsing stuff.
		loadExternalFile();
	}

	public List<String> getGameboard() {
		return Collections.unmodifiableList(gameboard);
	}

	public double getSpeedRobot() {
		return speedRobot;
	}

	public double getSpeedPlayer() {
		return speedPlayer;
	}

	/**
	 * Method prepares reading the config file and starts the setup of the actual gameboard.
	 */
	final void loadExternalFile() {

		try {
			// standard voodoo for getting text from an input stream
			final InputStream inputStream = context.getResources().openRawResource(R.raw.arena1);
			final Reader reader = new InputStreamReader(inputStream);
			final BufferedReader bufferedReader = new BufferedReader(reader);

			setupGame(bufferedReader);

			// closes everything down to input stream
			bufferedReader.close();
		}

		catch (final IOException ex) {
			Log.e("dummy", "failed reading resource file: " + ex.getMessage());
		}
	}

	/**
	 * Method reads Data from the config file.
	 * @param bufferedReader The reader.
	 * @throws IOException If file don't exist.
	 */
	private void setupGame(final BufferedReader bufferedReader) throws IOException {

		// gets the first line of the config-file
		String line = bufferedReader.readLine();

		// continue as long as there are lines to read in the file.
		while (line != null) {

			// Decision if the line contains information concerning the velocity values of player and robot objects or
			// if it is part of the gameboard design.
			if (line.startsWith("player")) {
				speedPlayer = parseVelocity(line);
			}

			else if (line.startsWith("robot")) {
				speedRobot = parseVelocity(line);
			}

			else if (!line.isEmpty()) {

				gameboard.add(line);
				checkLine(line);
			}
			// reads the next line from the config file.
			line = bufferedReader.readLine();
		}
	}

	/**
	 * Method parses velocity values and converts them to the correct values.
	 * @param line The current line read by the bufferedReader.
	 * @return The parsed velocity value.
	 */
	private double parseVelocity(final String line) {

		final String substring = line.substring(line.indexOf('=') + 1).trim();
		return CONVERTING_FACTOR * Double.parseDouble(substring);
	}

	/**
	 * Method checks if two gameboard lines have different length.
	 * @param lineToCheck The red line.
	 * @throws IllegalArgumentException If the lines for the gameboard design do not have the same length.
	 */
	private void checkLine(final String lineToCheck) {

		if (lineToCheck.length() != gameboard.get(0).length()) {
			throw new IllegalArgumentException("Arena contains lines of unequal lenght. Not supported.");
		}
	}
}