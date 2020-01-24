package game;

import ghost.GhostFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.mockito.Mockito.*;

import java.awt.*;
import java.io.FileNotFoundException;


public class GameTest {

    final transient String winBoard = "winBoard.txt";
    Game game;

    @BeforeEach
    void setUp() {
        Game.pelletCount = 0;
        Game.isRunning = false;
    }

    @Test
    public void start() {
        Game game = new Game(new Gamesettings(20, null), this.winBoard);

        Assertions.assertTrue(game.start());
        Assertions.assertFalse(game.start());
    }


    @Test
    public void stop() {
        Game game = new Game(new Gamesettings(20, null), this.winBoard);

        Assertions.assertFalse(game.stop());

        Assertions.assertTrue(game.start());

        Assertions.assertTrue(game.stop());
    }


    @Test
    public void getHeight() {
        // square size  * the height of the winBoard file
        // 20*1 = 20
        Game game = new Game(new Gamesettings(20, null), this.winBoard);

        Assertions.assertEquals(20, game.getHeight());
    }


    @Test
    public void getWidth() {
        // square size  * the width of the winBoard file which is 2
        // 20*2  = 40
        Game game = new Game(new Gamesettings(20, null), this.winBoard);

        Assertions.assertEquals(40, game.getWidth());
    }


    @Test
    public void mapFileNotFound() {
        Assertions.assertThrows(Exception.class,() -> {
            new Game(new Gamesettings(20, null), "doesNotExist.txt");
        });
    }

    @Test
    @SuppressWarnings("PMD")
    // The warning is suppressed because here we need to check the register Observers when the player is empty.
    public void registerObserversNoPlayer() {
        Game game = new Game(new Gamesettings(20, null), this.winBoard);
        game.player = null;

        Assertions.assertFalse(game.registerObservers());
    }


    @Test
    public void registerObservers() {
        Game game = new Game(new Gamesettings(20, null), this.winBoard);

        Assertions.assertTrue(game.registerObservers());
    }


    @Test
    public void lose() {
        Game game = new Game(new Gamesettings(20, null), this.winBoard);
        game.start();
        Assertions.assertTrue(game.setScore("testUser", 20));
        Assertions.assertFalse(game.setScore("testUser", 20));
    }

    @Test
    public void win() {
        Game game = new Game(new Gamesettings(20, null), this.winBoard);
        game.start();
        // Cannot show GUI in the test.
        game.showWinPopUp = false;

        Assertions.assertFalse(game.win());

        Game.pelletCount = 2;
        game.pelletEaten = 2;
        Assertions.assertTrue(game.win());
    }
}
