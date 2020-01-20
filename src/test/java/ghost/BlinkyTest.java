package ghost;

import game.Level;
import game.Types;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Point;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlinkyTest {

    private transient Ghost blinky;
    private transient int height = 100;
    private transient int width = 100;

    @BeforeEach
    void setUp() {
        int x = 0;
        int y = 0;
        try {
            blinky = GhostFactory.create(GhostFactory.BLINKY, x, y);
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
    void moveGhost() {
    }

    @Test
    void getType() {
        Assertions.assertEquals(blinky.getType(), Types.blinkyType());
    }
}