package game;

import ghost.Ghost;
import ghost.GhostFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class GhostObservableTest {

    transient Ghost randy;
    transient Ghost inky;

    @BeforeEach
    void setUp() {
        try {
            randy = GhostFactory.create(GhostFactory.RANDY, 10, 10);
            inky = GhostFactory.create(GhostFactory.INKY, 5, 5);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    /**
//     * Test to see if pacman is correctly observable by ghosts. ex. Randy.
//     */
//    @Test
//    void observePacmanTest() {
//        // assert that the location of thp
//        Assertions.assertEquals(5, inky.x);
//        Assertions.assertEquals(5, inky.y);
//
//        // Assert that randy has no value for the location of the inky
//        Assertions.assertNull(randy.unitLocations.get(inky.getType()));
//
//
//        // Let the inky move down and assert the location differs now
//        inky.moveGhost(100, 100);
//        Assertions.assertEquals(20, inky.y);
//
//        // Notify the observers
//        inky.notifyObservers();
//
//        // Assert that randy now has a value for pacman  and that the y value equals pacmans position
//        Assertions.assertNotNull(randy.unitLocations.get(inky.getType()));
//        Point location = randy.unitLocations.get(inky.getType());
//        Assertions.assertEquals(20, location.y);
//
//    }
}
