package ghost;

import game.Types;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Point;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PinkyTest {

    private transient Ghost pinky;

    private transient int height = 100;
    private transient int width = 100;

    @BeforeEach
    void setUp() {
        int x = 0;
        int y = 0;
        try {
            pinky = GhostFactory.create(GhostFactory.PINKY, x, y);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void moveGhost() {
        Point beforeMove = pinky.getLocation();
        pinky.moveGhost(height, width);
        Assertions.assertEquals(beforeMove, pinky.getLocation());
    }

    @Test
    void getType() {
        assertEquals(pinky.getType(), Types.pinkyType());
    }
}