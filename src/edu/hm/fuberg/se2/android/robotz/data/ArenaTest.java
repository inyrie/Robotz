
/**
 *
 */
package edu.hm.fuberg.se2.android.robotz.data;

/**
 * @author Stäff
 */
public class ArenaTest {

	public static void main(final String... ignored) {
		// final Arena arena = new Arena(0, 0); // wirft USA-Exception!
		final Arena arena = new Arena(20, 20);
		assert arena.getWidth() == 20 : "Width should be 20.";
		assert arena.getHeight() == 20 : "Height should be 20";
		// assert arena.getExit() == null :
		// "There should be no Exit object yet.";
		assert arena.getExit().getXCoord() == 20 : "Exit's xCoordinate should be 20.";
		assert arena.getExit().getYCoord() == 20 : "Exit's yCoordinate should be 20.";
		assert arena.getPlayer().getXCoord() == 0 : "Player's xCoordinate should be 0.";
		assert arena.getPlayer().getYCoord() == 0 : "Player's yCoordinate should be 0.";
		assert arena.getPlayer().getDestination() == null : "Player shouldn't have a destination yet.";
		assert arena.getState() == GameState.Waiting : "Gamestate should be Waiting.";
		// final Player player = new Player(5, 5);
		// arena.setPlayer(player);
		arena.getPlayer().shift(5, 5);
		assert arena.getPlayer().getVelocity() == 0.2 : "Player's velocity should be 0.2.";
		final Exit exit = new Exit(20, 20);
		arena.setExit(exit);
		assert arena.getExit().getXCoord() == 20 : "Exit's xCoordinate should be 20.";
		assert arena.getExit().getYCoord() == 20 : "Exit's yCoordinate should be 0.";
		arena.getPlayer().move(10);
		assert arena.getPlayer().getXCoord() == 5 : "Player's xCoordinate should be 5, he shouldn't have moved.";
		assert arena.getPlayer().getYCoord() == 5 : "Player's yCoordinate should be 5, he shouldn't have moved.";
		arena.getPlayer().setDestination(exit);
		arena.getPlayer().move(10);
		assert arena.getPlayer().getXCoord() - 6.414 < 0.1 : "Player's xCoordinate should be 6.414.";
		assert arena.getPlayer().getYCoord() - 6.414 < 0.1 : "Player's yCoordinate should be 6.414.";
	}
}
