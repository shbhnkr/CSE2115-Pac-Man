package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.awt.*;


class MoveBuilderTest {

    transient Player player;

    @BeforeEach
    void setUp(){
        int x = 0;
        int y = 0;
        player = new Player(x,y);
    }

    @Test
    void UP() {
        Point north = MoveBuilder.UP(player.getLocation());
        Assertions.assertEquals(north.getY(), -20);
    }

    @Test
    void DOWN() {
        Point south = MoveBuilder.DOWN(player.getLocation());
        Assertions.assertEquals(south.getY(), 20);
    }

    @Test
    void LEFT() {
        Point west = MoveBuilder.LEFT(player.getLocation());
        Assertions.assertEquals(west.getX(), -20);
    }

    @Test
    void RIGHT() {
        Point east = MoveBuilder.RIGHT(player.getLocation());
        Assertions.assertEquals(east.getX(), 20);
    }
}