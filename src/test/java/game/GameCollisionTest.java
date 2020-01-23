package game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;


class GameCollisionTest {

    @Test
    public void win() {
        Game game = new Game(new Gamesettings(20, null), "winBoard.txt");
        Player player = game.player;
        player.moveRight(game, game.getWidth());

        Assertions.assertEquals(game.pelletEaten, game.pelletCount);
    }

    @Test
    public void pelletCollision() {
        Game game = new Game(new Gamesettings(20, null), "board2.txt");
        Player player = game.player;
        Assertions.assertEquals(0, game.pelletEaten);
        player.moveLeft(game, game.getWidth());
        player.moveRight(game, game.getWidth());
        Assertions.assertEquals(1, game.pelletEaten);
        player.moveUp(game, game.getHeight());
        player.moveDown(game, game.getHeight());
        Assertions.assertEquals(2, game.pelletEaten);
        player.moveRight(game, game.getWidth());
        player.moveLeft(game, game.getWidth());
        Assertions.assertEquals(3, game.pelletEaten);
        player.moveDown(game, game.getHeight());
        player.moveUp(game, game.getHeight());
        Assertions.assertEquals(4, game.pelletEaten);
        game.stop();
    }

    @Test
    public void wallCollision() {

        Game game = new Game(new Gamesettings(20, null), "wallBoard.txt");
        Player player = game.player;
        Point loc = player.getLocation();
        player.moveLeft(game, game.getWidth());
        Assertions.assertEquals(player.getLocation(), loc);
        player.moveUp(game, game.getHeight());
        Assertions.assertEquals(player.getLocation(), loc);
        player.moveRight(game, game.getWidth());
        Assertions.assertEquals(player.getLocation(), loc);
        player.moveDown(game, game.getHeight());
        Assertions.assertEquals(player.getLocation(), loc);
        game.stop();
    }

    @Test
    public void wrapAroundTest() {
        Game game = new Game(new Gamesettings(20, null), "wrapLp.txt");
        Player player = game.player;
        Assertions.assertEquals(0, game.pelletEaten);
        player.moveLeft(game, game.getWidth());
        player.moveLeft(game, game.getWidth());
        Assertions.assertEquals(1, game.pelletEaten);
        game.stop();
        
        Game game1 = new Game(new Gamesettings(20, null), "wrapUp.txt");
        Player player1 = game1.player;
        Assertions.assertEquals(0, game1.pelletEaten);
        player1.moveUp(game1, game1.getHeight());
        player1.moveUp(game1, game1.getHeight());
        Assertions.assertEquals(1, game1.pelletEaten);
        game1.stop();
        
        Game game2 = new Game(new Gamesettings(20, null), "wrapDp.txt");
        Player player2 = game2.player;
        Assertions.assertEquals(0, game2.pelletEaten);
        player2.moveDown(game2, game2.getHeight());
        player2.moveDown(game2, game2.getHeight());
        Assertions.assertEquals(1, game2.pelletEaten);
        game2.stop();
        
        Game game3 = new Game(new Gamesettings(20, null), "wrapRp.txt");
        Player player3 = game3.player;
        Assertions.assertEquals(0, game3.pelletEaten);
        player3.moveRight(game3, game3.getWidth());
        player3.moveRight(game3, game3.getWidth());
        Assertions.assertEquals(1, game3.pelletEaten);
        game3.stop();
        
        Game game4 = new Game(new Gamesettings(20, null), "wrapLw.txt");
        Player player4 = game4.player;
        player4.moveLeft(game4, game4.getWidth());
        Point loc = player4.getLocation();
        player4.moveLeft(game4, game4.getWidth());
        Assertions.assertEquals(player4.getLocation(), loc);
        game4.stop();
        
        Game game5 = new Game(new Gamesettings(20, null), "wrapRw.txt");
        Player player5 = game5.player;
        player5.moveRight(game5, game5.getWidth());
        Point loc1 = player5.getLocation();
        player5.moveRight(game5, game5.getWidth());
        Assertions.assertEquals(player5.getLocation(), loc1);
        game5.stop();
        
        Game game6 = new Game(new Gamesettings(20, null), "wrapDw.txt");
        Player player6 = game6.player;
        player6.moveDown(game6, game6.getHeight());
        Point loc2 = player6.getLocation();
        player6.moveDown(game6, game6.getHeight());
        Assertions.assertEquals(player6.getLocation(), loc2);
        game6.stop();
        
        Game game7 = new Game(new Gamesettings(20, null), "wrapUw.txt");
        Player player7 = game7.player;
        Assertions.assertEquals(player7.getLocation(), new Point(20, 20));
        player7.moveUp(game7, game7.getHeight());
        Point loc3 = player7.getLocation();
        player7.moveUp(game7, game7.getHeight());
        Assertions.assertEquals(player7.getLocation(), loc3);
        game7.stop();
        
        Game game8 = new Game(new Gamesettings(20, null), "board3.txt");
        game8.stop();
    }
}