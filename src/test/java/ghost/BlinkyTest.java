package ghost;

import game.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlinkyTest {

    private transient Ghost blinky;
    private transient Player player;
    private transient int height = 100;
    private transient int width = 100;

    @BeforeEach
    void setUp() {
        Game game = new Game(new Gamesettings(20, null), "testBoardBlinky.txt");
        player = game.player;
        int x = 0;
        int y = 0;
        try {
            blinky = GhostFactory.create(GhostFactory.BLINKY, x, y);
        } catch (Exception e) {
            e.printStackTrace();
        }
        blinky.move(new Point(60, 60));
        player.registerObserver(blinky);
    }

    @Test
    void moveUpGhost() {
        Level.pixels[blinky.getLocation().x / 20][(blinky.getLocation().y + 20) / 20] = '#';
        Level.pixels[(blinky.getLocation().x - 20) / 20][blinky.getLocation().y / 20] = '#';
        Level.pixels[(blinky.getLocation().x + 20) / 20][blinky.getLocation().y / 20] = '#';
        player.movePlayer(new Point(blinky.getLocation().x, blinky.getLocation().y - 40));
        player.notifyObservers();
        int beforeMove = blinky.getLocation().y;
        blinky.moveGhost(height, width);
        int afterMove = blinky.getLocation().y;
        assertEquals(beforeMove - afterMove, 20);
        blinky.moveGhost(height, width);
        assertEquals(blinky.getLocation().y, afterMove - 20);
    }

    @Test
    void moveDownWrapAroundWall() {
        player.movePlayer(new Point(60, 60));
        blinky.move(new Point(60, 0));
        Level.pixels[blinky.getLocation().x / 20][(height - 20) / 20] = '#';
        player.notifyObservers();
        blinky.moveGhost(height, width);
        assertEquals(blinky.getLocation().y, 20);
    }

    @Test
    void moveLeftGhost() {
        player.movePlayer(new Point(blinky.getLocation().x - 40, blinky.getLocation().y));
        player.notifyObservers();
        int beforeMove = blinky.getLocation().x;
        blinky.moveGhost(height, width);
        int afterMove = blinky.getLocation().x;
        assertEquals(beforeMove - afterMove, 20);

        blinky.move(new Point(0, blinky.getLocation().y));
        Level.pixels[blinky.getLocation().x / 20][(blinky.getLocation().y + 20) / 20] = '#';
        Level.pixels[blinky.getLocation().x / 20][(blinky.getLocation().y - 20) / 20] = '#';
        Level.pixels[(blinky.getLocation().x + 20) / 20][blinky.getLocation().y / 20] = '#';
        blinky.moveGhost(height, width);
        assertEquals(blinky.getLocation().x, width - 20);
    }

    @Test
    void moveRightWrapAroundWall() {
        player.movePlayer(new Point(60, 60));
        blinky.move(new Point(0, 60));
        Level.pixels[(width - 20) / 20][blinky.getLocation().y / 20] = '#';
        player.notifyObservers();
        blinky.moveGhost(height, width);
        assertEquals(blinky.getLocation().x, 20);
    }

    @Test
    void moveDownGhost() {
        player.movePlayer(new Point(blinky.getLocation().x, blinky.getLocation().y + 40));
        player.notifyObservers();
        int beforeMove = blinky.getLocation().y;
        blinky.moveGhost(height, width);
        int afterMove = blinky.getLocation().y;
        assertEquals(afterMove - beforeMove, 20);
        blinky.moveGhost(height, width);
        assertEquals(blinky.getLocation().y, 0);
    }

    @Test
    void moveUpWrapAroundWall() {
        player.movePlayer(new Point(60, 60));
        blinky.move(new Point(60, height - 20));
        Level.pixels[blinky.getLocation().x / 20][0] = '#';
        player.notifyObservers();
        blinky.moveGhost(height, width);
        assertEquals(blinky.getLocation().y, height - 40);
    }

    @Test
    void moveRightGhost() {
        player.movePlayer(new Point(blinky.getLocation().x + 40, blinky.getLocation().y));
        player.notifyObservers();
        int beforeMove = blinky.getLocation().x;
        blinky.moveGhost(height, width);
        int afterMove = blinky.getLocation().x;
        assertEquals(afterMove - beforeMove, 20);
        blinky.moveGhost(height, width);
        assertEquals(blinky.getLocation().x, 0);
    }

    @Test
    void moveLeftWrapAroundWall() {
        player.movePlayer(new Point(60, 60));
        blinky.move(new Point(width - 20, 60));
        Level.pixels[0][blinky.getLocation().y / 20] = '#';
        player.notifyObservers();
        blinky.moveGhost(height, width);
        assertEquals(blinky.getLocation().x, width - 40);
    }

    @Test
    void noDestination() {
        Player nullplayer = null;
        player = nullplayer;
        Point beforeMove = blinky.getLocation();
        blinky.moveGhost(height, width);
        Point afterMove = blinky.getLocation();
        assertEquals(beforeMove, afterMove);
    }

    @Test
    void getType() {
        Assertions.assertEquals(blinky.getType(), Types.blinkyType());
    }
}