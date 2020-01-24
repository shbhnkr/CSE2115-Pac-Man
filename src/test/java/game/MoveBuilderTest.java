package game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Point;


class MoveBuilderTest {

    private transient Player player;

    @BeforeEach
    void setUp() {
        int x = 0;
        int y = 0;
        player = new Player(x, y);
    }

    @Test
    void up() {
        Point north = MoveBuilder.up(player.getLocation());
        Assertions.assertEquals(north.getY(), -20);
    }

    @Test
    void down() {
        Point south = MoveBuilder.down(player.getLocation());
        Assertions.assertEquals(south.getY(), 20);
    }

    @Test
    void left() {
        Point west = MoveBuilder.left(player.getLocation());
        Assertions.assertEquals(west.getX(), -20);
    }

    @Test
    void right() {
        Point east = MoveBuilder.right(player.getLocation());
        Assertions.assertEquals(east.getX(), 20);
    }
}