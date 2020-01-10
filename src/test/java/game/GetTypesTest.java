package game;

import ghost.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetTypesTest {
    @Test
    void getWallTypeTest() {
        Wall wall = new Wall(5, 5);
        Assertions.assertEquals(wall.getType(), "#");
    }

    @Test
    void getPlayerTypeTest() {
        Player player = new Player(5, 5);
        Assertions.assertEquals(player.getType(), "p");
    }

    @Test
    void getBlinkyTypeTest() {
        Blinky blinky = new Blinky(5, 5, Game.blinkySprite);
        Assertions.assertEquals(blinky.getType(), "b");
    }

    @Test
    void getPinkyTypeTest() {
        Pinky pinky = new Pinky(5, 5, Game.pinkySprite);
        Assertions.assertEquals(pinky.getType(), "g");
    }

    @Test
    void getInkyTypeTest() {
        Inky inky = new Inky(5, 5, Game.inkySprite);
        Assertions.assertEquals(inky.getType(), "i");
    }

    @Test
    void getClydeTypeTest() {
        Clyde clyde = new Clyde(5, 5, Game.clydeSprite);
        Assertions.assertEquals(clyde.getType(), "c");
    }

    @Test
    void getRandyTypeTest() {
        Randy randy = new Randy(5, 5, Game.randySprite);
        Assertions.assertEquals(randy.getType(), "r");
    }

    @Test
    void getPelletTypeTest() {
        Pellet pellet = new Pellet(5, 5);
        Assertions.assertEquals(pellet.getType(), ".");
    }
}
