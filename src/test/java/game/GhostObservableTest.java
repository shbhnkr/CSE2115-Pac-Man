package game;

import ghost.Ghost;
import ghost.GhostFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

class GhostObservableTest {

    transient Ghost randy;
    transient Player player;

    @BeforeEach
    void setUp() {
        int x = 0;
        int y = 0;
        player = new Player(x, y);
        try {
            randy = GhostFactory.create(GhostFactory.RANDY, 10, 10);
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
        Assertions.assertEquals(0, player.getLocation().x);
        Assertions.assertEquals(0, player.getLocation().y);

        // Assert that randy has no value for the location of the player
        Assertions.assertNull(randy.unitLocations.get(player.getType()));

        // Register randy as an observer of player
        player.registerObserver(randy);

        // Let the player move down and assert the location differs now
        player.movePlayer(new Point(100, 100));
        Assertions.assertEquals(100, player.y);

        // Notify the observers
        player.notifyObservers();

        // Assert that randy now has a value for pacman and that the y value equals pacmans position
        Assertions.assertNotNull(randy.unitLocations.get(player.getType()));
        Point location = randy.unitLocations.get(player.getType());
        Assertions.assertEquals(100, location.y);

    }
}
