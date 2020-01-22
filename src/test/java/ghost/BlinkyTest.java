package ghost;

import game.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Point;

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
        player.movePlayer(new Point(blinky.getLocation().x, blinky.getLocation().y - 40));
        player.notifyObservers();
        int beforeMove = blinky.getLocation().y;
        blinky.moveGhost(height, width);
        int afterMove = blinky.getLocation().y;
        assertEquals(beforeMove - afterMove, 20);
    }

    @Test
    void moveLeftGhost() {
        player.movePlayer(new Point(blinky.getLocation().x - 40, blinky.getLocation().y));
        player.notifyObservers();
        int beforeMove = blinky.getLocation().x;
        blinky.moveGhost(height, width);
        int afterMove = blinky.getLocation().x;
        assertEquals(beforeMove - afterMove, 20);
    }

    @Test
    void moveDownGhost() {
        player.movePlayer(new Point(blinky.getLocation().x, blinky.getLocation().y + 40));
        player.notifyObservers();
        int beforeMove = blinky.getLocation().y;
        blinky.moveGhost(height, width);
        int afterMove = blinky.getLocation().y;
        assertEquals(afterMove - beforeMove, 20);
    }

    @Test
    void moveRightGhost() {
        player.movePlayer(new Point(blinky.getLocation().x + 40, blinky.getLocation().y));
        player.notifyObservers();
        int beforeMove = blinky.getLocation().x;
        blinky.moveGhost(height, width);
        int afterMove = blinky.getLocation().x;
        assertEquals(afterMove - beforeMove, 20);
    }

    @Test
    void noPlayer() {
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