package game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class GameSettingsTest {

    @Test
    public void getUsername() {
        int squareSize = 20;
        String testUsername = "testUsername";
        Gamesettings gamesettings = new Gamesettings(squareSize, testUsername);

        Assertions.assertEquals(testUsername, gamesettings.getUsername());
    }


    @Test
    public void getSquareSize() {
        int squareSize = 20;
        String testUsername = "testUsername";
        Gamesettings gamesettings = new Gamesettings(squareSize, testUsername);

        Assertions.assertEquals(squareSize, gamesettings.getSquareSize());
    }
}
