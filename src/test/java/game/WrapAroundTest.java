package game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

public class WrapAroundTest {
    transient Game game;
    transient Randy randy;

    @BeforeEach
    void setUp() {
        GameSettings settings = new GameSettings(20);
        game = new Game(settings,"testBoard.txt");
        game.initFrame();
        randy = game.level.randy;
    }

    @Test
    void playerWrapAroundRightTest() {
        randy.moveUpGhost();
        game.moveRightNow();
        game.moveRightNow();
        game.moveRightNow();
        game.moveRightNow();
        game.moveRightNow();
        game.moveRightNow();
        Assertions.assertEquals(game.getWidth()-20, game.player.getLocation().getX());
        game.moveRightNow();
        Assertions.assertEquals(game.player.getLocation().getX(), 0);
    }
    @Test
    void playerWrapAroundLeftTest() {
        game.moveLeftNow();
        Assertions.assertEquals(0, game.player.getLocation().getX());
        game.moveLeftNow();
        Assertions.assertEquals(game.getWidth()-20, game.player.getLocation().getX());
    }
    @Test
    void playerWrapAroundUpTest() {
        game.moveUpNow();
        Assertions.assertEquals(0, game.player.getLocation().getY());
        game.moveUpNow();
        Assertions.assertEquals(game.getHeight()-20, game.player.getLocation().getY());
    }
    @Test
    void playerWrapAroundDownTest() {
        game.moveDownNow();
        Assertions.assertEquals(game.getHeight()-20, game.player.getLocation().getY());
        game.moveDownNow();
        Assertions.assertEquals(0, game.player.getLocation().getY());
    }
//    @Test
//    void randyWrapAroundRightTest() {
//        randy.moveRightGhost();
//        Assertions.assertEquals(game.getWidth()-20, randy.getLocation().getX());
//        randy.moveRightGhost();
//        Assertions.assertEquals(randy.getLocation().getX(), 0);
//    }
//    @Test
//    void randyWrapAroundLeftTest() {
//        game.moveUpNow();
//        randy.moveLeftGhost();
//        randy.moveLeftGhost();
//        randy.moveLeftGhost();
//        randy.moveLeftGhost();
//        randy.moveLeftGhost();
//        randy.moveLeftGhost();
//        Assertions.assertEquals(0, randy.getLocation().getX());
//        randy.moveLeftGhost();
//        Assertions.assertEquals(game.getWidth()-20, randy.getLocation().getX());
//    }
//    @Test
//    void randyWrapAroundUpTest() {
//        randy.moveUpGhost();
//        Assertions.assertEquals(0, randy.getLocation().getY());
//        randy.moveUpGhost();
//        Assertions.assertEquals(game.getHeight()-20, randy.getLocation().getY());
//    }
//    @Test
//    void randyWrapAroundDownTest() {
//        randy.moveDownGhost();
//        Assertions.assertEquals(game.getHeight()-20, randy.getLocation().getY());
//        randy.moveDownGhost();
//        Assertions.assertEquals(0, randy.getLocation().getY());
//    }
}
