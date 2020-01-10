package game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;


class GameCollisionTest {

    @Test
    public void win() {
        Game game = new Game(new GameSettings(20), "winBoard.txt");
        game.moveRight();
        System.out.println(game);
        Assertions.assertEquals(game.pelletEaten, game.pelletCount);


    }

    @Test
    public void pelletCollision() {
        Game game = new Game(new GameSettings(20), "board2.txt");
        Assertions.assertEquals(0, game.pelletEaten);
        game.moveLeft();
        game.moveRight();
        Assertions.assertEquals(1, game.pelletEaten);
        game.moveUp();
        game.moveDown();
        Assertions.assertEquals(2, game.pelletEaten);
        game.moveRight();
        game.moveLeft();
        Assertions.assertEquals(3, game.pelletEaten);
        game.moveDown();
        game.moveUp();
        Assertions.assertEquals(4, game.pelletEaten);
        game.stop();


    }

    @Test
    public void wallCollision() {
        Game game = new Game(new GameSettings(20), "wallBoard.txt");
        Point loc = game.player.getLocation();
        game.moveLeft();
        Assertions.assertEquals(game.player.getLocation(), loc);
        game.moveUp();
        Assertions.assertEquals(game.player.getLocation(), loc);
        game.moveRight();
        Assertions.assertEquals(game.player.getLocation(), loc);
        game.moveDown();
        Assertions.assertEquals(game.player.getLocation(), loc);
        game.stop();
    }

    @Test
    public void wrapAroundTest() {
        Game game = new Game(new GameSettings(20), "wrapLp.txt");
        Assertions.assertEquals(0, game.pelletEaten);
        game.moveLeft();
        game.moveLeft();
        Assertions.assertEquals(1, game.pelletEaten);
        game.stop();
        Game game1 = new Game(new GameSettings(20), "wrapUp.txt");
        Assertions.assertEquals(0, game1.pelletEaten);
        game1.moveUp();
        game1.moveUp();
        Assertions.assertEquals(1, game1.pelletEaten);
        game1.stop();
        Game game2 = new Game(new GameSettings(20), "wrapDp.txt");
        Assertions.assertEquals(0, game2.pelletEaten);
        game2.moveDown();
        game2.moveDown();
        Assertions.assertEquals(1, game2.pelletEaten);
        game2.stop();
        Game game3 = new Game(new GameSettings(20), "wrapRp.txt");
        Assertions.assertEquals(0, game3.pelletEaten);
        game3.moveRight();
        game3.moveRight();
        Assertions.assertEquals(1, game3.pelletEaten);
        game3.stop();
        Game game4 = new Game(new GameSettings(20), "wrapLw.txt");
        game4.moveLeft();
        Point loc = game4.player.getLocation();
        game4.moveLeft();
        Assertions.assertEquals(game4.player.getLocation(), loc);
        game4.stop();
        Game game5 = new Game(new GameSettings(20), "wrapRw.txt");
        game5.moveRight();
        Point loc1 = game5.player.getLocation();
        game5.moveRight();
        Assertions.assertEquals(game5.player.getLocation(), loc1);
        game5.stop();
        Game game6 = new Game(new GameSettings(20), "wrapDw.txt");
        game6.moveDown();
        Point loc2 = game6.player.getLocation();
        game6.moveDown();
        Assertions.assertEquals(game6.player.getLocation(), loc2);
        game6.stop();
        Game game7 = new Game(new GameSettings(20), "wrapUw.txt");
        Assertions.assertEquals(game7.player.getLocation(), new Point(20, 20));
        game7.moveUp();
        Point loc3 = game7.player.getLocation();
        game7.moveUp();
        Assertions.assertEquals(game7.player.getLocation(), loc3);
        game7.stop();
        Game game8 = new Game(new GameSettings(20), "board3.txt");
        game8.stop();
    }


}