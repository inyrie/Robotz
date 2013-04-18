package edu.hm.fuberg.se2.android.robotz.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The Class describes the playing field of Robotz.
 * @author Stephanie Ehrenberg
 * @author Robert Fuess
 */
public class Arena {

	/** The height of the Arena. */
	private double height = 0;

	/** The width of the Arena. */
	private double width = 0;

	/** The player. */
	private MobileItem player;

	/** The Exit. */
	private Item exit;

	/** The robots. */
	private final ArrayList<Robot> robots = new ArrayList<Robot>();

	/** The fences. */
	private final ArrayList<Fence> fences = new ArrayList<Fence>();

	/** The state of the Game. */
	private State state;

	/**
	 * Ctor for a new Arena
	 * @param state The current game state - "waiting", "running" or "over".
	 */
	public Arena(final State state) {

		try {

			final FileReader reader = new FileReader("res/arena/Arena1.txt");
			final BufferedReader buffered = new BufferedReader(reader);
			{

				String line;

				while ((line = buffered.readLine()) != null) {

					if (width != line.length() && width != 0) {

						throw new UnsupportedArenaException("Unsupported Arena size");
					}

					else if (width == 0) {

						width = line.length();
					}

					height++;

					for (int position = 0; position < line.length(); position++) {

						final char symbol = line.charAt(position);

						switch (symbol) {

						case 'P':

							if (player == null) {
								player = new Player(position, height, null);
							}

							else {
								throw new UnsupportedArenaException("Unsupported amount of players");
							}
							break;

						case 'E':

							if (exit == null) {
								exit = new Exit(position, height);
							}

							else {
								throw new UnsupportedArenaException("Unsupported amount of exits");
							}
							break;

						case 'R':
							// addRobot(new Robot(position, height, new
							// Item()));
							break;

						case 'F':
							addFence(new Fence(position, height));
							break;
						}
					}
				}

				this.state = state;
			}

			reader.close();
		}

		catch (final FileNotFoundException e) {
			e.printStackTrace();
		}

		catch (final UnsupportedArenaException e) {
			e.printStackTrace();
		}

		catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}

	public State getState() {
		return state;
	}

	public void setState(final State state) {
		this.state = state;
	}

	public Robot getRobot(final int position) {
		return robots.get(position);
	}

	/**
	 * Adds one robot to the list.
	 * @param robot the robot.
	 */
	public void addRobot(final Robot robot) {
		robots.add(robot);
	}

	/**
	 * Removes the robot of the list.
	 * @param position the position of the Robot in the list.
	 */
	public void removeRobot(final int position) {
		robots.remove(position);
	}

	public Fence getFence(final int position) {
		return fences.get(position);
	}

	/**
	 * Adds one fence to the list.
	 * @param fence the fence.
	 */
	public void addFence(final Fence fence) {
		fences.add(fence);
	}

	/**
	 * Removes the fence of the list.
	 * @param position the position of the fence in the list.
	 */
	public void removeFence(final int position) {
		fences.remove(position);
	}
}
