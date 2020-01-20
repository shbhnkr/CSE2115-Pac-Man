package ghost;

import game.Level;
import game.Types;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Point;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RandyTest {

    private transient Ghost randy;

    private transient int height = 100;
    private transient int width = 100;

    @BeforeEach
    void setUp() {
        int x = 0;
        int y = 0;
        try {
            randy = GhostFactory.create(GhostFactory.RANDY, x, y);
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
        randy.move(new Point(40, 40));
    }

    @Test
    void moveUpGhost() {
        randy.setRandom(1, 0);
        randy.moveGhost(height, width);
        Assertions.assertEquals(randy.getLocation().getY(), 20);
    }
    @Test
    void moveLeftGhost() {
        randy.setRandom(1, 1);
        randy.moveGhost(height, width);
        Assertions.assertEquals(randy.getLocation().getX(), 20);
    }
    @Test
    void moveDownGhost() {
        randy.setRandom(1, 2);
        randy.moveGhost(height, width);
        Assertions.assertEquals(randy.getLocation().getY(), 60);
    }
    @Test
    void moveRightGhost() {
        randy.setRandom(1, 3);
        randy.moveGhost(height, width);
        Assertions.assertEquals(randy.getLocation().getX(), 60);
    }

    @Test
    void moveGhost() {
        Point beforeLocation = randy.getLocation();
        randy.moveGhost(height, width);
        Assertions.assertNotEquals(randy.getLocation(), beforeLocation);
    }

    @Test
    void getType() {
        assertEquals(randy.getType(), Types.randyType());
    }
}