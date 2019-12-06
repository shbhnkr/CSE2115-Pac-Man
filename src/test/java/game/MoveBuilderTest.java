package game;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.awt.*;


class MoveBuilderTest {

    @Test
    void UPTest() {
        int x = 0;
        int y = 0;
        Player player = new Player(x,y);
        Point north = MoveBuilder.UP(player.getLocation());
        Assertions.assertEquals(north.getY(), -20);
    }

    @Test
    void DOWNTest() {
        int x = 0;
        int y = 0;
        Player player = new Player(x,y);
        Point south = MoveBuilder.DOWN(player.getLocation());
        Assertions.assertEquals(south.getY(), 20);
    }

    @Test
    void LEFTTest() {
        int x = 0;
        int y = 0;
        Player player = new Player(x,y);
        Point west = MoveBuilder.LEFT(player.getLocation());
        Assertions.assertEquals(west.getX(), -20);
    }

    @Test
    void RIGHTTest() {
        int x = 0;
        int y = 0;
        Player player = new Player(x,y);
        Point east = MoveBuilder.RIGHT(player.getLocation());
        Assertions.assertEquals(east.getX(), 20);
    }
}