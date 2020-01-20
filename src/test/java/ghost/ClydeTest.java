package ghost;

import game.Types;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Point;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClydeTest {
    private transient Ghost clyde;
    private transient int height = 100;
    private transient int width = 100;

    @BeforeEach
    void setUp() {
        int x = 0;
        int y = 0;
        try {
            clyde = GhostFactory.create(GhostFactory.CLYDE, x, y);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void moveGhost() {
        Point beforeMove = clyde.getLocation();
        clyde.moveGhost(height, width);
        Assertions.assertEquals(beforeMove, clyde.getLocation());
    }

    @Test
    void getType() {
        assertEquals(clyde.getType(), Types.clydeType());
    }
}