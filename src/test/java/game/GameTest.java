package game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.mockito.Mockito.*;

import java.io.FileNotFoundException;


public class GameTest {

    final transient String winBoard = "winBoard.txt";

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

//    @Test
//    public void mapFileNotFound() {
//        Assertions.assertThrows(Exception.class,() -> {
//            new Game(new Gamesettings(20, null), "doesNotExist.txt");
//        });
//    }


}
