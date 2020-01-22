package game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;


public class PowerPelletTest {
    transient String ftest = "fruitTest.txt";
    transient String btest = "beerTest.txt";
    transient String gtest = "poweredPelletTest.txt";

    @Test
    public void gallbossCollisionLeft() {

        Game game = new Game(new Gamesettings(20, null), gtest);
        Player player = game.player;
        player.moveLeft(game, game.getWidth());
        Assertions.assertTrue(player.power);
        Point loc = player.getLocation();
        player.moveLeft(game, game.getWidth());
        Assertions.assertNotEquals(player.getLocation(), loc);
    }

    @Test
    public void gallbossCollisionRight() {

        Game game = new Game(new Gamesettings(20, null), gtest);
        Player player = game.player;
        player.moveRight(game, game.getWidth());
        Assertions.assertTrue(player.power);
        Point loc = player.getLocation();
        player.moveRight(game, game.getWidth());
        Assertions.assertNotEquals(player.getLocation(), loc);
    }

    @Test
    public void gallbossCollisionUp() {

        Game game = new Game(new Gamesettings(20, null), gtest);
        Player player = game.player;
        player.moveUp(game, game.getHeight());
        Assertions.assertTrue(player.power);
        Point loc = player.getLocation();
        player.moveUp(game, game.getHeight());
        Assertions.assertNotEquals(player.getLocation(), loc);
    }

    @Test
    public void gallbossCollisionDown() {

        Game game = new Game(new Gamesettings(20, null), gtest);
        Player player = game.player;
        player.moveDown(game, game.getHeight());
        Assertions.assertTrue(player.power);
        Point loc = player.getLocation();
        player.moveDown(game, game.getHeight());
        Assertions.assertNotEquals(player.getLocation(), loc);
    }

    @Test
    public void beerCollisionLeft() {

        Game game = new Game(new Gamesettings(20, null), btest);
        Player player = game.player;
        player.moveLeft(game, game.getWidth());
        Assertions.assertTrue(player.drunk);

    }

    @Test
    public void beerCollisionRight() {

        Game game = new Game(new Gamesettings(20, null), btest);
        Player player = game.player;
        player.moveRight(game, game.getWidth());
        Assertions.assertTrue(player.drunk);

    }

    @Test
    public void beerCollisionUp() {

        Game game = new Game(new Gamesettings(20, null), btest);
        Player player = game.player;
        player.moveUp(game, game.getHeight());
        Assertions.assertTrue(player.drunk);

    }

    @Test
    public void beerCollisionDown() {

        Game game = new Game(new Gamesettings(20, null), btest);
        Player player = game.player;
        player.moveDown(game, game.getHeight());
        Assertions.assertTrue(player.drunk);

    }


    @Test
    public void fruitCollisionLeft() {

        Game game = new Game(new Gamesettings(20, null), ftest);
        Player player = game.player;
        int n = game.point;
        player.moveLeft(game, game.getWidth());
        Assertions.assertNotEquals(n, game.point);

    }

    @Test
    public void fruitCollisionRight() {

        Game game = new Game(new Gamesettings(20, null), ftest);
        Player player = game.player;
        int n = game.point;
        player.moveRight(game, game.getWidth());
        Assertions.assertNotEquals(n, game.point);
    }

    @Test
    public void fruitCollisionUp() {

        Game game = new Game(new Gamesettings(20, null), ftest);
        Player player = game.player;
        int n = game.point;
        player.moveUp(game, game.getHeight());
        Assertions.assertNotEquals(n, game.point);

    }

    @Test
    public void fruitCollisionDown() {


        Game game = new Game(new Gamesettings(20, null), ftest);
        Player player = game.player;
        int n = game.point;
        player.moveDown(game, game.getHeight());
        Assertions.assertNotEquals(n, game.point);

    }


}
