package game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static game.Level.pixels;

public class WallCollisionTest {
    transient Game game;

    @BeforeEach
    void setUp() {
        System.setProperty("java.awt.headless", "true");
        GameSettings settings = new GameSettings(20);
        game = new Game(settings,"testBoard.txt");
        game.initFrame();
        game.start();
    }

    @Test
    void playerMoveUpWallTest() {
        game.moveRightNow();
        Point pacmanLocation = game.player.getLocation();
        game.moveUpNow();
        Assertions.assertEquals(pixels[game.player.getLocation().x / 20][(game.player.getLocation().y - 20) / 20], '#');
        Assertions.assertEquals(pacmanLocation, game.player.getLocation());
    }
    @Test
    void playerMoveDownWallTest() {
        game.moveRightNow();
        Point pacmanLocation = game.player.getLocation();
        game.moveDownNow();
        Assertions.assertEquals(pixels[game.player.getLocation().x / 20][(game.player.getLocation().y + 20) / 20], '#');
        Assertions.assertEquals(pacmanLocation, game.player.getLocation());
    }
    @Test
    void playerMoveLeftWallTest() {
        game.moveDownNow();
        Point pacmanLocation = game.player.getLocation();
        game.moveLeftNow();
        Assertions.assertEquals(pixels[(game.player.getLocation().x - 20) / 20][game.player.getLocation().y / 20], '#');
        Assertions.assertEquals(pacmanLocation, game.player.getLocation());
    }
    @Test
    void playerMoveRightWallTest() {
        game.moveUpNow();
        Point pacmanLocation = game.player.getLocation();
        game.moveRightNow();
        Assertions.assertEquals(pixels[(game.player.getLocation().x + 20)/ 20][game.player.getLocation().y / 20], '#');
        Assertions.assertEquals(pacmanLocation, game.player.getLocation());
    }
//    @Test
//    void randyMoveUpWallTest() {
//        game.level.randy.moveRightGhost();
//        Point randyLocation = game.level.randy.getLocation();
//        game.level.randy.moveUpGhost();
//        Assertions.assertEquals(pixels[game.level.randy.getLocation().x / 20][(game.level.randy.getLocation().y - 20) / 20], '#');
//        Assertions.assertEquals(randyLocation, game.level.randy.getLocation());
//    }
//    @Test
//    void randyMoveDownWallTest() {
//        game.randy.moveRightGhost();
//        Point randyLocation = game.randy.getLocation();
//        game.randy.moveDownGhost();
//        Assertions.assertEquals(pixels[game.randy.getLocation().x / 20][(game.randy.getLocation().y + 20) / 20], '#');
//        Assertions.assertEquals(randyLocation, game.randy.getLocation());
//    }
//    @Test
//    void randyMoveLeftWallTest() {
//        game.randy.moveDownGhost();
//        Point randyLocation = game.randy.getLocation();
//        game.randy.moveLeftGhost();
//        Assertions.assertEquals(pixels[(game.randy.getLocation().x - 20) / 20][game.randy.getLocation().y / 20], '#');
//        Assertions.assertEquals(randyLocation, game.randy.getLocation());
//    }
//    @Test
//    void randyMoveRightWallTest() {
//        game.randy.moveUpGhost();
//        Point randyLocation = game.randy.getLocation();
//        game.randy.moveRightGhost();
//        Assertions.assertEquals(pixels[(game.randy.getLocation().x + 20)/ 20][game.randy.getLocation().y / 20], '#');
//        Assertions.assertEquals(randyLocation, game.randy.getLocation());
//    }
}
