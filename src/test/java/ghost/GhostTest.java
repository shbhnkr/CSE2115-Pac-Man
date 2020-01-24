package ghost;

import game.Game;
import game.SpriteSheet;
import game.Observer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static game.RenderLevel.pixels;
import static game.RenderLevel.setPixels;


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
        setPixels(new char[width][height]);
        int n = 0;
        while (n < height / 20) {
            for (int i = 0; i < width / 20; i++) {
                pixels[i][n] = ' ';
            }
            n++;
        }
    }

    @Test
    void getSetSheet() {
        ghost.setSheet(Game.randySprite);
        SpriteSheet sheet = ghost.getSheet();
        Assertions.assertEquals(sheet, Game.randySprite);
    }

    @Test
    void getSetUnitLocations() {
        LinkedHashMap<String, Point> unitLocations = new LinkedHashMap<>();
        unitLocations.put("player", new Point(60, 60));
        ghost.setUnitLocations(unitLocations);
        LinkedHashMap<String, Point> getUnitLocations = ghost.getUnitLocations();
        Assertions.assertEquals(getUnitLocations, unitLocations);
    }

    @Test
    @SuppressWarnings("PMD")
    void observer() {
        int x = 0;
        int y = 0;
        Ghost inky = null;
        try {
            inky = GhostFactory.create(GhostFactory.INKY, x, y);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ghost.registerObserver(inky);
        Assertions.assertTrue(ghost.getObserverCollection().get(0) instanceof Inky);

        ghost.setLocation(x + 40, y + 20);
        ghost.notifyObservers();

        if (inky != null) {
            Assertions.assertEquals(inky.getUnitLocations().get("r"), ghost.getLocation());
        }

        ghost.deregisterObserver(inky);
        Assertions.assertTrue(ghost.getObserverCollection().isEmpty());

        List<Observer> observers = new ArrayList<>();
        observers.add(inky);
        ghost.setObserverCollection(observers);
        Assertions.assertEquals(ghost.getObserverCollection().get(0), inky);
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

        pixels[(int) north.getX() / 20][(int) (north.getY() / 20) - 1] = '#';
        ghost.moveUpGhost(height);
        Point northWall = ghost.getLocation();
        Assertions.assertEquals(northWall.getY(), height - 40);
    }

    @Test
    void moveUpFailGhost() {
        ghost.move(new Point(40, 0));
        Point beforeMove = ghost.getLocation();
        pixels[beforeMove.x / 20][(height - 20) / 20] = '#';
        ghost.moveUpGhost(height);
        Assertions.assertEquals(ghost.getLocation(), beforeMove);
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

        pixels[(int) (west.getX() / 20) - 1][(int) (west.getY() / 20)] = '#';
        ghost.moveLeftGhost(width);
        Point westWall = ghost.getLocation();
        Assertions.assertEquals(westWall.getX(), width - 40);
    }

    @Test
    void moveLeftFailGhost() {
        ghost.move(new Point(0, 40));
        Point beforeMove = ghost.getLocation();
        pixels[(width - 20) / 20][beforeMove.y / 20] = '#';
        ghost.moveLeftGhost(width);
        Assertions.assertEquals(ghost.getLocation(), beforeMove);
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

        pixels[(int) south.getX() / 20][(int) (south.getY() / 20) + 1] = '#';
        ghost.moveDownGhost(height);
        Point southWall = ghost.getLocation();
        Assertions.assertEquals(southWall.getY(), 20);
    }

    @Test
    void moveDownFailGhost() {
        ghost.move(new Point(40, height - 20));
        Point beforeMove = ghost.getLocation();
        pixels[beforeMove.x / 20][0] = '#';
        ghost.moveDownGhost(height);
        Assertions.assertEquals(ghost.getLocation(), beforeMove);
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

        pixels[(int) (east.getX() / 20) + 1][(int) (east.getY() / 20)] = '#';
        ghost.moveRightGhost(width);
        Point eastWall = ghost.getLocation();
        Assertions.assertEquals(eastWall.getX(), 20);
    }

    @Test
    void moveRightFailGhost() {
        ghost.move(new Point(width - 20, 40));
        Point beforeMove = ghost.getLocation();
        pixels[0][beforeMove.y / 20] = '#';
        ghost.moveRightGhost(width);
        Assertions.assertEquals(ghost.getLocation(), beforeMove);
    }
}