package game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

public class PelletCollisionTest {
    transient Game game;
    @BeforeEach
    void setUp() {
        GameSettings settings = new GameSettings(20);
        game = new Game(settings,"testBoard1.txt");
        game.initFrame();
    }
//
    @Test
    void PelletTest()
    {

        game.pelletLeft = 0;
        Assertions.assertEquals(game.pelletLeft,0);
        game.moveUpNow();
        game.moveDownNow();
        Assertions.assertEquals(game.pelletLeft,1);
        game.moveRightNow();
        game.moveLeftNow();
        Assertions.assertEquals(game.pelletLeft,2);
        game.moveLeftNow();
        game.moveRightNow();
        Assertions.assertEquals(game.pelletLeft,3);
        game.moveDownNow();
        Assertions.assertEquals(game.pelletLeft,4);
    }

}
