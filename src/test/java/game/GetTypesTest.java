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
    void getBlinkyTypeTest() throws Exception {
        Ghost blinky = GhostFactory.create(GhostFactory.BLINKY, 20, 20);
        Assertions.assertEquals(blinky.getType(), "b");
    }

    @Test
    void getPinkyTypeTest() throws Exception {
        Ghost pinky = GhostFactory.create(GhostFactory.PINKY, 20, 20);
        Assertions.assertEquals(pinky.getType(), "g");
    }

    @Test
    void getInkyTypeTest() throws Exception {
        Ghost inky = GhostFactory.create(GhostFactory.INKY, 20, 20);
        Assertions.assertEquals(inky.getType(), "i");
    }

    @Test
    void getClydeTypeTest() throws Exception {
        Ghost clyde = GhostFactory.create(GhostFactory.CLYDE, 20, 20);
        Assertions.assertEquals(clyde.getType(), "c");
    }

    @Test
    void getRandyTypeTest() throws Exception {
        Ghost randy = GhostFactory.create(GhostFactory.RANDY, 20, 20);
        Assertions.assertEquals(randy.getType(), "r");
    }

    @Test
    void getPelletTypeTest() {
        Pellet pellet = new Pellet(5, 5);
        Assertions.assertEquals(pellet.getType(), ".");
    }

    @Test
    void getFruitPelletTypeTest() {
        FruitPellet fruitPellet = new FruitPellet(5, 5);
        Assertions.assertEquals(fruitPellet.getType(), ",");
    }
}
