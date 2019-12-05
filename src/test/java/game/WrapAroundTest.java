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
        JFrame frame = new JFrame();
        frame.setTitle(Game.TITLE);
        frame.add(game);
        frame.setResizable(false);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(false);
        game.start();
        randy = game.level.randy;
    }

    @Test
    void playerWrapAroundRight() {
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
    void playerWrapAroundLeft() {
        game.moveLeftNow();
        Assertions.assertEquals(0, game.player.getLocation().getX());
        game.moveLeftNow();
        Assertions.assertEquals(game.getWidth()-20, game.player.getLocation().getX());
    }
    @Test
    void playerWrapAroundUp() {
        game.moveUpNow();
        Assertions.assertEquals(0, game.player.getLocation().getY());
        game.moveUpNow();
        Assertions.assertEquals(game.getHeight()-20, game.player.getLocation().getY());
    }
    @Test
    void playerWrapAroundDown() {
        game.moveDownNow();
        Assertions.assertEquals(game.getHeight()-20, game.player.getLocation().getY());
        game.moveDownNow();
        Assertions.assertEquals(0, game.player.getLocation().getY());
    }
//    @Test
//    void randyWrapAroundRight() {
//        randy.moveRightGhost();
//        Assertions.assertEquals(game.getWidth()-20, randy.getLocation().getX());
//        randy.moveRightGhost();
//        Assertions.assertEquals(randy.getLocation().getX(), 0);
//    }
//    @Test
//    void randyWrapAroundLeft() {
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
//    void randyWrapAroundUp() {
//        randy.moveUpGhost();
//        Assertions.assertEquals(0, randy.getLocation().getY());
//        randy.moveUpGhost();
//        Assertions.assertEquals(game.getHeight()-20, randy.getLocation().getY());
//    }
//    @Test
//    void randyWrapAroundDown() {
//        randy.moveDownGhost();
//        Assertions.assertEquals(game.getHeight()-20, randy.getLocation().getY());
//        randy.moveDownGhost();
//        Assertions.assertEquals(0, randy.getLocation().getY());
//    }
}
