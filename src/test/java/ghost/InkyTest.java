package ghost;

import game.Types;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Point;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InkyTest {

    private transient Ghost inky;

    private transient int height = 100;
    private transient int width = 100;

    @BeforeEach
    void setUp() {
        int x = 0;
        int y = 0;
        try {
            inky = GhostFactory.create(GhostFactory.INKY, x, y);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void moveGhost() {
        Point beforeMove = inky.getLocation();
        inky.moveGhost(height, width);
        Assertions.assertEquals(beforeMove, inky.getLocation());
    }

    @Test
    void getType() {
        assertEquals(inky.getType(), Types.inkyType());
    }
}