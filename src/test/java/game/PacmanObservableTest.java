package game;

import ghost.Ghost;
import ghost.GhostFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class PacmanObservableTest {

    transient Player player;
    transient Ghost randy;

    @BeforeEach
    void setUp() {
        try {
            int x = 0;
            int y = 0;
            player = new Player(x, y);
            randy = GhostFactory.create(GhostFactory.RANDY, 10, 10);

            player.registerObserver(randy);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Test to see if pacman is correctly observable by ghosts. ex. Randy.
     */
    @Test
    void observePacmanTest() {
        // assert that the location of thp
        Assertions.assertEquals(0, player.x);
        Assertions.assertEquals(0, player.y);

        // Assert that randy has no value for the location of the player
        Assertions.assertNull(randy.unitLocations.get(player.getType()));


        // Let the player move down and assert the location differs now
        player.movePlayer(MoveBuilder.DOWN(player.getLocation()));
        Assertions.assertEquals(20, player.y);

        // Notify the observers
        player.notifyObservers();

        // Assert that randy now has a value for pacman  and that the y value equals pacmans position
        Assertions.assertNotNull(randy.unitLocations.get(player.getType()));
        Point location = randy.unitLocations.get(player.getType());
        Assertions.assertEquals(20, location.y);

    }
}
