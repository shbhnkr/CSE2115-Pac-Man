package game;

import ghost.Ghost;
import ghost.GhostFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Point;

class PacmanObservableTest {

    private transient Player player;
    private transient Ghost ghost;

    @BeforeEach
    void setUp() {
        try {
            int x = 0;
            int y = 0;
            player = new Player(x, y);
            ghost = GhostFactory.create(GhostFactory.RANDY, 10, 10);

            player.registerObserver(ghost);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Test to see if pacman is correctly observable by ghosts. ex. ghost.
     */
    @Test
    void observePacmanTest() {
        // assert that the location of thp
        Assertions.assertEquals(0, player.x);
        Assertions.assertEquals(0, player.y);

        // Assert that ghost has no value for the location of the player
        Assertions.assertNull(ghost.unitLocations.get(player.getType()));


        // Let the player move down and assert the location differs now
        player.movePlayer(MoveBuilder.DOWN(player.getLocation()));
        Assertions.assertEquals(20, player.y);

        // Notify the observers
        player.notifyObservers();

        // Assert that ghost now has a value for pacman and that the y value equals pacmans position
        Assertions.assertNotNull(ghost.unitLocations.get(player.getType()));
        Point location = ghost.unitLocations.get(player.getType());
        Assertions.assertEquals(20, location.y);

    }
}
