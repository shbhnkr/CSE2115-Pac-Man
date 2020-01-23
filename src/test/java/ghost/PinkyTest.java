package ghost;

import game.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Point;

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
    }

    @Test
    void moveGhostNoPlayerDirection() {
        player.movePlayer(new Point(pinky.getLocation().x, pinky.getLocation().y - 40));
        player.notifyObservers();
        Game.playerDirection = "";
        int beforeMove = pinky.getLocation().y;
        pinky.moveGhost(height, width);
        int afterMove = pinky.getLocation().y;
        assertEquals(beforeMove - afterMove, 20);
    }

    @Test
    void moveUpGhost() {
        Level.pixels[pinky.getLocation().x/20][(pinky.getLocation().y + 20)/20] = '#';
        Level.pixels[(pinky.getLocation().x - 20)/20][pinky.getLocation().y/20] = '#';
        Level.pixels[(pinky.getLocation().x + 20)/20][pinky.getLocation().y/20] = '#';

        player.movePlayer(new Point(pinky.getLocation().x, pinky.getLocation().y - 40));
        player.notifyObservers();
        Game.playerDirection = "up";
        int beforeMove = pinky.getLocation().y;
        pinky.moveGhost(height, width);
        int afterMove = pinky.getLocation().y;
        assertEquals(beforeMove - afterMove, 20);
    }

    @Test
    void moveLeftGhost() {
        player.movePlayer(new Point(pinky.getLocation().x - 40, pinky.getLocation().y));
        player.notifyObservers();
        Game.playerDirection = "left";
        int beforeMove = pinky.getLocation().x;
        pinky.moveGhost(height, width);
        int afterMove = pinky.getLocation().x;
        assertEquals(beforeMove - afterMove, 20);
    }

    @Test
    void moveDownGhost() {
        Level.pixels[pinky.getLocation().x/20][(pinky.getLocation().y - 20)/20] = '#';

        player.movePlayer(new Point(pinky.getLocation().x, pinky.getLocation().y + 40));
        player.notifyObservers();
        Game.playerDirection = "down";
        int beforeMove = pinky.getLocation().y;
        pinky.moveGhost(height, width);
        int afterMove = pinky.getLocation().y;
        assertEquals(afterMove - beforeMove, 20);
    }

    @Test
    void moveRightGhost() {
        player.movePlayer(new Point(pinky.getLocation().x + 40, pinky.getLocation().y));
        player.notifyObservers();
        Game.playerDirection = "right";
        int beforeMove = pinky.getLocation().x;
        pinky.moveGhost(height, width);
        int afterMove = pinky.getLocation().x;
        assertEquals(afterMove - beforeMove, 20);
    }

    @Test
    void moveFromEdges() {

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