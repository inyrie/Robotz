package edu.hm.fuberg.se2.robotz.layered.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import android.content.ClipData.Item;

public class Arena {

	private final double height;
	private final double width;
	private MobileItem player;
	private final ArrayList<Robot> robots = new ArrayList<Robot>();
	private final ArrayList<Fence> fences = new ArrayList<Fence>();
	private State state;

	public Arena(final State state) throws Exception {

		this.state = state;

		final FileReader reader = new FileReader("res/arena/Arena1.txt");
		final BufferedReader buffered = new BufferedReader(reader);
		{

			String line;
			int width = 0;
			int height = 0;

			while ((line = buffered.readLine()) != null) {

				if (width != line.length() && width != 0) {

					throw new Exception("Unsupportet Arena size");
				}

				else if (width == 0) {

					width = line.length();
				}

				height++;

				for (int position = 0; position < line.length(); position++) {

					final char symbol = line.charAt(position);

					switch (symbol) {

					case 'P':

						player = new Player(position, height, null);
						break;

					case 'E':

						final Item exit = new Exit(position, height);
						break;

					case 'R':

						addRobot(new Robot(position, height, new Item()));

						break;

					case 'F':

						addFence(new Fence(position, height));
						break;
					}
				}
			}

			this.state = state;
			this.width = width;
			this.height = height;
		}

		reader.close();
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

	public void addRobot(final Robot robot) {
		robots.add(robot);
	}

	public void removeRobot(final int position) {
		robots.remove(position);
	}

	public Fence getFence(final int position) {
		return fences.get(position);
	}

	public void addFence(final Fence fence) {
		fences.add(fence);
	}

	public void removeFence(final int position) {
		fences.remove(position);
	}
}
