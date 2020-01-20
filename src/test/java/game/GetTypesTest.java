package game;

import ghost.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GetTypesTest {
    @Test
    void getWallTypeTest() {
        Wall wall = new Wall(5, 5);
        Assertions.assertEquals(wall.getType(), Types.wallType());
    }

    @Test
    void getPlayerTypeTest() {
        Player player = new Player(5, 5);
        Assertions.assertEquals(player.getType(), Types.playerType());
    }

    @Test
    void getBlinkyTypeTest() throws Exception {
        Ghost blinky = GhostFactory.create(GhostFactory.BLINKY, 20, 20);
        Assertions.assertEquals(blinky.getType(), Types.blinkyType());
    }

    @Test
    void getPinkyTypeTest() throws Exception {
        Ghost pinky = GhostFactory.create(GhostFactory.PINKY, 20, 20);
        Assertions.assertEquals(pinky.getType(), Types.pinkyType());
    }

    @Test
    void getInkyTypeTest() throws Exception {
        Ghost inky = GhostFactory.create(GhostFactory.INKY, 20, 20);
        Assertions.assertEquals(inky.getType(), Types.inkyType());
    }

    @Test
    void getClydeTypeTest() throws Exception {
        Ghost clyde = GhostFactory.create(GhostFactory.CLYDE, 20, 20);
        Assertions.assertEquals(clyde.getType(), Types.clydeType());
    }

    @Test
    void getRandyTypeTest() throws Exception {
        Ghost randy = GhostFactory.create(GhostFactory.RANDY, 20, 20);
        Assertions.assertEquals(randy.getType(), Types.randyType());
    }

    @Test
    void getPelletTypeTest() {
        Pellet pellet = new Pellet(5, 5);
        Assertions.assertEquals(pellet.getType(), Types.pelletType());
    }

    @Test
    void getFruitPelletTypeTest() {
        FruitPellet fruitPellet = new FruitPellet(5, 5);
        Assertions.assertEquals(fruitPellet.getType(), Types.fruitPelletType());
    }
    @Test
    void getBeerTypeTest() {
        Beer beer = new Beer(5, 5);
        Assertions.assertEquals(beer.getType(), Types.beerType());
    }
    @Test
    void getPowerPelletTypeTest() {
        PowerPellet powerPellet = new PowerPellet(5, 5);
        Assertions.assertEquals(powerPellet.getType(), Types.powerPelletType());
    }

}
