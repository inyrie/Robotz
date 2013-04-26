/**
 * Munich University for Applied Science, 
 * Faculty 07 for Mathematics and Computer Science
 * Softwareentwicklung II, SS2013, Studiengruppe IF1A
 * Windows XP SP3; Java-Version: 1.7.0_17
 * Developing a Java application.
 */

package edu.hm.fuberg.se2.android.robotz.data;

public class ItemTest {
	public static void main(final String... ignored) {
		// Erschaffen eines Spielerobjekts.
		final Player player = new Player(1, 1);
		assert player.getXCoord() == 1 : "Player's xCoordinate should be 1";
		assert player.getYCoord() == 1 : "Player's yCoordinate should be 1";
		assert player.getSize() == 1 : "Player's size should be 1";

		final Exit exit = new Exit(20, 20);
		assert exit.getXCoord() == 20 : "Exit's xCoordinate should  be 20";
		assert exit.getYCoord() == 20 : "Exit's yCoordinate should  be 20";
		assert player.getVelocity() == 0.2 : "Player's velocity should be 0.2";
		assert player.distanceTo(exit) - 26.87 < 0.1 : "Player's distance to exit should be 26.87.";
		assert exit.distanceTo(player) - 26.87 < 0.1 : "Exit's distance to player should be 26.87.";
		assert player.collides(exit) == false : "Player and Exit shouldn't collide.";
		assert player.shift(18, 18).getXCoord() == 18 : "Player's xCoordinate should be 18.";
		assert player.getYCoord() == 18 : "Player's yCoordinate should be 18.";
		assert player.collides(exit) == true : "Player and Exit should collide.";
		player.shift(0, 20);
		player.setDestination(exit);
		player.move(1);
		assert player.getXCoord() == 0.2 : "Player's xCoordinate after move() should be 0.2.";
		assert player.getYCoord() == 20 : "Player's yCoordinate after move() should be 20.";
	}
}
