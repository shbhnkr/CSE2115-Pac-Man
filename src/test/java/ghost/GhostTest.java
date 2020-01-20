package ghost;

import game.Level;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Point;


class GhostTest {

    private transient Ghost ghost;
    private transient int height = 100;
    private transient int width = 100;

    @BeforeEach
    @SuppressWarnings("PMD")
    void setUp() {
        int x = 0;
        int y = 0;
        try {
            ghost = GhostFactory.create(GhostFactory.RANDY, x, y);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Level.setPixels(new char[width][height]);
        int n = 0;
        while (n < height/20) {
            for (int i = 0; i < width / 20; i++) {
                Level.pixels[i][n] = ' ';
            }
            n++;
        }
    }

    @Test
    void getSheet() {
    }

    @Test
    void setSheet() {
        
    }

    @Test
    void observe() {
    }

    @Test
    void move() {
    }

    @Test
    void moveUpGhost() {
        ghost.move(new Point(40, 0));
        ghost.moveUpGhost(height);
        Point wrapAroundNorth = ghost.getLocation();
        Assertions.assertEquals(wrapAroundNorth.getY(), height - 20);

        ghost.moveUpGhost(height);
        Point north = ghost.getLocation();
        Assertions.assertEquals(north.getY(), height - 40);

        Level.pixels[(int) north.getX()/20][(int) (north.getY()/20) - 1] = '#';
        ghost.moveUpGhost(height);
        Point northWall = ghost.getLocation();
        Assertions.assertEquals(northWall.getY(), height - 40);
    }

    @Test
    void moveLeftGhost() {
        ghost.move(new Point(0, 40));
        ghost.moveLeftGhost(width);
        Point wrapAroundWest = ghost.getLocation();
        Assertions.assertEquals(wrapAroundWest.getX(), width - 20);

        ghost.moveLeftGhost(width);
        Point west = ghost.getLocation();
        Assertions.assertEquals(west.getX(), width - 40);

        Level.pixels[(int) (west.getX()/20) - 1][(int) (west.getY()/20)] = '#';
        ghost.moveLeftGhost(width);
        Point westWall = ghost.getLocation();
        Assertions.assertEquals(westWall.getX(), width - 40);
    }

    @Test
    void moveDownGhost() {
        ghost.move(new Point(40, width - 20));
        ghost.moveDownGhost(height);
        Point wrapAroundSouth = ghost.getLocation();
        Assertions.assertEquals(wrapAroundSouth.getY(), 0);

        ghost.moveDownGhost(height);
        Point south = ghost.getLocation();
        Assertions.assertEquals(south.getY(), 20);

        Level.pixels[(int) south.getX()/20][(int) (south.getY()/20) + 1] = '#';
        ghost.moveDownGhost(height);
        Point southWall = ghost.getLocation();
        Assertions.assertEquals(southWall.getY(), 20);
    }

    @Test
    void moveRightGhost() {
        ghost.move(new Point(width - 20, 40));
        ghost.moveRightGhost(width);
        Point wrapAroundEast = ghost.getLocation();
        Assertions.assertEquals(wrapAroundEast.getX(), 0);

        ghost.moveRightGhost(width);
        Point east = ghost.getLocation();
        Assertions.assertEquals(east.getX(), 20);

        Level.pixels[(int) (east.getX()/20) + 1][(int) (east.getY()/20)] = '#';
        ghost.moveRightGhost(width);
        Point eastWall = ghost.getLocation();
        Assertions.assertEquals(eastWall.getX(), 20);
    }

    @Test
    void getUnitLocations() {
    }

    @Test
    void setUnitLocations() {
    }

    @Test
    void registerObserver() {
    }

    @Test
    void deregisterObserver() {
    }

    @Test
    void notifyObservers() {
    }

    @Test
    void getObserverCollection() {
    }

    @Test
    void setObserverCollection() {
    }
}