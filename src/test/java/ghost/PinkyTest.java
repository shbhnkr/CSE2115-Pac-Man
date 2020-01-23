package ghost;

import game.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Point;

import static game.Game.playerDirection;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PinkyTest {

    private transient Ghost pinky;
    private transient Player player;
    private transient int height = 100;
    private transient int width = 100;

    @BeforeEach
    void setUp() {
        Game game = new Game(new Gamesettings(20, null), "testBoardPinky.txt");
        player = game.player;
        int x = 0;
        int y = 0;
        try {
            pinky = GhostFactory.create(GhostFactory.PINKY, x, y);
        } catch (Exception e) {
            e.printStackTrace();
        }
        pinky.move(new Point(60, 60));
        player.registerObserver(pinky);
        playerDirection = "";
    }

    @Test
    void moveUpGhost() {
        Level.pixels[pinky.getLocation().x / 20][(pinky.getLocation().y + 20) / 20] = '#';
        Level.pixels[(pinky.getLocation().x - 20) / 20][pinky.getLocation().y / 20] = '#';
        Level.pixels[(pinky.getLocation().x + 20) / 20][pinky.getLocation().y / 20] = '#';
        player.movePlayer(new Point(pinky.getLocation().x, pinky.getLocation().y - 40));
        playerDirection = "up";
        player.notifyObservers();
        int beforeMove = pinky.getLocation().y;
        pinky.moveGhost(height, width);
        int afterMove = pinky.getLocation().y;
        assertEquals(beforeMove - afterMove, 20);
        pinky.moveGhost(height, width);
        assertEquals(pinky.getLocation().y, afterMove - 20);
    }

    @Test
    void moveDownWrapAroundWall() {
        player.movePlayer(new Point(60, 60));
        pinky.move(new Point(60, 0));
        Level.pixels[pinky.getLocation().x / 20][(height - 20) / 20] = '#';
        player.notifyObservers();
        pinky.moveGhost(height, width);
        assertEquals(pinky.getLocation().y,20);
    }

    @Test
    void moveLeftGhost() {
        player.movePlayer(new Point(pinky.getLocation().x - 40, pinky.getLocation().y));
        playerDirection = "left";
        player.notifyObservers();
        int beforeMove = pinky.getLocation().x;
        pinky.moveGhost(height, width);
        int afterMove = pinky.getLocation().x;
        assertEquals(beforeMove - afterMove, 20);

        pinky.move(new Point(0, pinky.getLocation().y));
        Level.pixels[pinky.getLocation().x / 20][(pinky.getLocation().y + 20) / 20] = '#';
        Level.pixels[pinky.getLocation().x / 20][(pinky.getLocation().y - 20) / 20] = '#';
        Level.pixels[(pinky.getLocation().x + 20) / 20][pinky.getLocation().y / 20] = '#';
        pinky.moveGhost(height, width);
        assertEquals(pinky.getLocation().x, width - 20);}

    @Test
    void moveRightWrapAroundWall() {
        player.movePlayer(new Point(60, 60));
        pinky.move(new Point(0, 60));
        Level.pixels[(width - 20) / 20][pinky.getLocation().y / 20] = '#';
        player.notifyObservers();
        pinky.moveGhost(height, width);
        assertEquals(pinky.getLocation().x,20);
    }

    @Test
    void moveDownGhost() {
        player.movePlayer(new Point(pinky.getLocation().x, pinky.getLocation().y + 40));
        playerDirection = "down";
        player.notifyObservers();
        int beforeMove = pinky.getLocation().y;
        pinky.moveGhost(height, width);
        int afterMove = pinky.getLocation().y;
        assertEquals(afterMove - beforeMove, 20);
        pinky.moveGhost(height, width);
        assertEquals(pinky.getLocation().y, 0);
    }

    @Test
    void moveUpWrapAroundWall() {
        player.movePlayer(new Point(60, 60));
        pinky.move(new Point(60, height - 20));
        Level.pixels[pinky.getLocation().x / 20][0] = '#';
        player.notifyObservers();
        pinky.moveGhost(height, width);
        assertEquals(pinky.getLocation().y, height - 40);
    }

    @Test
    void moveRightGhost() {
        player.movePlayer(new Point(pinky.getLocation().x + 40, pinky.getLocation().y));
        playerDirection = "right";
        player.notifyObservers();
        int beforeMove = pinky.getLocation().x;
        pinky.moveGhost(height, width);
        int afterMove = pinky.getLocation().x;
        assertEquals(afterMove - beforeMove, 20);
        pinky.moveGhost(height, width);
        assertEquals(pinky.getLocation().x, 0);
    }

    @Test
    void moveLeftWrapAroundWall() {
        player.movePlayer(new Point(60, 60));
        pinky.move(new Point(width - 20, 60));
        Level.pixels[0][pinky.getLocation().y / 20] = '#';
        player.notifyObservers();
        pinky.moveGhost(height, width);
        assertEquals(pinky.getLocation().x,width - 40);
    }

    @Test
    void noDestination() {
        Player nullplayer = null;
        player = nullplayer;
        Point beforeMove = pinky.getLocation();
        pinky.moveGhost(height, width);
        Point afterMove = pinky.getLocation();
        assertEquals(beforeMove, afterMove);
    }

    @Test
    void getType() {
        Assertions.assertEquals(pinky.getType(), Types.pinkyType());
    }
}