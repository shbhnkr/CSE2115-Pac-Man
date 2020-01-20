package ghost;

import game.Level;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Point;


class GhostMovementTest {

    private transient Ghost ghost;
    private transient int x = 0;
    private transient int y = 0;
    private transient int height = 100;
    private transient int width = 100;

    @BeforeEach
    @SuppressWarnings("PMD")
    void setUp() {
        try {
            ghost = GhostFactory.create(GhostFactory.RANDY, x, y);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int n = 0;
        Level.setPixels(new char[width][height]);
        for (int i = 0; i < width/20; i++) {
            Level.pixels[i][n] = ' ';
        }
    }

    @Test
    void up() {
        ghost.move(new Point(40, 0));
        ghost.moveUpGhost(height);
        Point wrapAroundNorth = ghost.getLocation();
        Assertions.assertEquals(wrapAroundNorth.getY(), height - 20);

        ghost.moveUpGhost(height);
        Point north = ghost.getLocation();
        Assertions.assertEquals(north.getY(), height - 40);
    }

    @Test
    void down() {        ghost.move(new Point(40, width - 20));
        ghost.moveDownGhost(height);
        Point wrapAroundSouth = ghost.getLocation();
        Assertions.assertEquals(wrapAroundSouth.getY(), 0);

        ghost.moveDownGhost(height);
        Point south = ghost.getLocation();
        Assertions.assertEquals(south.getY(), 20);
    }

    @Test
    void left() {
        ghost.move(new Point(0, 40));
        ghost.moveLeftGhost(width);
        Point  = ghost.getLocation();
        Assertions.assertEquals(west.getX(), width - 20);

        ghost.moveLeftGhost(width);
        Point west = ghost.getLocation();
        Assertions.assertEquals(west.getX(), 20);
    }

    @Test
    void right() {
        ghost.moveRightGhost(width);
        Point east = ghost.getLocation();
        Assertions.assertEquals(east.getX(), 60);
    }

    @Test
    void leftWrapAround() {
    }

    @Test
    void rightWrapAround() {
        try {
            ghost = GhostFactory.create(GhostFactory.CLYDE, x, y);
            ghost.move(new Point(40, 40));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ghost.move(new Point(width - 20, 40));
        ghost.moveRightGhost(width);
        Point east = ghost.getLocation();
        Assertions.assertEquals(east.getX(), 0);
    }
}