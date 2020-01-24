package game;

import ghost.Ghost;
import ghost.GhostFactory;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static game.Game.pelletCount;
import static game.Level.*;

public class RenderLevel {

    public static char[][] pixels;
    static Player player;
    static Ghost blinky;
    static Ghost pinky;
    static Ghost randy;
    static Pellet[][] pellets;
    static FruitPellet[][] fruitPellet;
    static Beer[][] beers;
    static PowerPellet[][] dragonBall;
    static List<Ghost> ghosts = new ArrayList<>();
    transient Ghost inky;
    transient Ghost clyde;
    public static int n;

    /**
     * render.
     * @param g graphics.
     */
    public static void render(Graphics g) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                fruitPelletRender(g, fruitPellet[x][y]);
                wallRender(g, walls[x][y]);
                beerRender(g, beers[x][y]);
                dragonRender(g, dragonBall[x][y]);
                pelletRender(g, pellets[x][y]);
                playerRender(g);
                blinkyRender(g);
                pinkyRender(g);
                randyRender(g);
            }
        }
    }

    private static void randyRender(Graphics g) {
        if (randy != null) {
            randy.render(g);
        }
    }

    private static void pinkyRender(Graphics g) {
        if (pinky != null) {
            pinky.render(g);
        }
    }

    private static void blinkyRender(Graphics g) {
        if (blinky != null) {
            blinky.render(g);
        }
    }

    private static void playerRender(Graphics g) {
        if (player != null) {
            player.render(g);
        }
    }

    private static void pelletRender(Graphics g, Pellet pellet) {
        if (pellet != null) {
            pellet.render(g);
        }
    }

    private static void dragonRender(Graphics g, PowerPellet powerPellet) {
        if (powerPellet != null) {
            powerPellet.render(g);
        }
    }

    private static void beerRender(Graphics g, Beer beer) {
        if (beer != null) {
            beer.render(g);
        }
    }

    private static void wallRender(Graphics g, Wall wall) {
        if (wall != null) {
            wall.render(g);
        }
    }

    private static void fruitPelletRender(Graphics g, FruitPellet fruitPellet) {
        if (fruitPellet != null) {
            fruitPellet.render(g);
        }
    }


    public static Pellet[][] getPellets() {
        return pellets;
    }

    public static void setPellets(Pellet[][] pellets) {
        RenderLevel.pellets = pellets;
    }

    public static FruitPellet[][] getFruitPellets() {
        return fruitPellet;
    }

    public static void setFruitPellets(FruitPellet[][] fruitPellet) {
        RenderLevel.fruitPellet = fruitPellet;
    }

    public static char[][] getPixels() {
        return pixels;
    }

    public static void setPixels(char[][] pixels) {
        RenderLevel.pixels = pixels;
    }

    /**
     * makes map.
     */
    public static void mapMaker() {
        n = 0;
        setPixels(new char[width][height]);
        File file = new File(path.getFile());
        Scanner sc;
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                for (int i = 0; i < s.length(); i++) {
                    getPixels()[i][n] = s.charAt(i);
                }
                n++;
            }
            setWalls(new Wall[width][height]);
            setPellets(new Pellet[width][height]);
            setFruitPellets(new FruitPellet[width][height]);
            setBeers(new Beer[width][height]);
            setPowerPellets(new PowerPellet[width][height]);


            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {

                    fruitPelletSetter(x, y);
                    powerPelletSetter(x, y);
                    wallSetter(x, y);
                    beerSetter(x, y);
                    pelletSetter(x, y);
                    playerSetter(x, y);
                    blinkySetter(x, y);
                    pinkySetter(x, y);
                    randySetter(x, y);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static void randySetter(int x, int y) throws Exception {
        switch (pixels[x][y]) {
            case ('r'):
                randyMaker(x, y);
                break;
            default:
                return;
        }
    }

    private static void pinkySetter(int x, int y) throws Exception {
        switch (pixels[x][y]) {
            case ('g'):
                pinkyMaker(x, y);
                break;
            default:
                return;
        }
    }

    private static void blinkySetter(int x, int y) throws Exception {
        switch (pixels[x][y]) {
            case ('b'):
                blinkyMaker(x, y);
                break;
            default:
                return;
        }
    }

    private static void playerSetter(int x, int y) {
        switch (pixels[x][y]) {
            case ('p'):
                player = new Player(x * squareSize, y * squareSize);
                break;
            default:
                return;
        }
    }

    private static void pelletSetter(int x, int y) {
        switch (pixels[x][y]) {
            case ('.'):
                pelletMaker(x, y);
                break;
            default:
                return;
        }
    }

    private static void beerSetter(int x, int y) {
        switch (pixels[x][y]) {
            case ('h'):
                getBeers()[x][y] = new Beer(x * squareSize, y * squareSize);
                break;
            default:
                return;
        }
    }

    private static void wallSetter(int x, int y) {
        switch (pixels[x][y]) {
            case ('#'):
                getWalls()[x][y] = new Wall(x * squareSize, y * squareSize);
                break;
            default:
                return;
        }
    }

    private static void powerPelletSetter(int x, int y) {
        switch (pixels[x][y]) {
            case ('*'):
                getPowerPellets()[x][y]
                        = new PowerPellet(x * squareSize, y * squareSize);
                break;
            default:
                return;
        }
    }

    private static void fruitPelletSetter(int x, int y) {
        switch (pixels[x][y]) {
            case (','):
                getFruitPellets()[x][y]
                        = new FruitPellet(x * squareSize, y * squareSize);
                break;
            default:
                return;
        }
    }

    private static void randyMaker(int x, int y) throws Exception {
        randy = GhostFactory.create(GhostFactory.RANDY, x * 20, y * 20);
        ghosts.add(randy);
    }

    private static void pinkyMaker(int x, int y) throws Exception {
        pinky = GhostFactory.create(GhostFactory.PINKY, x * 20, y * 20);
        ghosts.add(pinky);
    }

    private static void blinkyMaker(int x, int y) throws Exception {
        blinky = GhostFactory.create(GhostFactory.BLINKY, x * 20, y * 20);
        ghosts.add(blinky);
    }

    private static void pelletMaker(int x, int y) {
        pelletCount++;
        getPellets()[x][y] = new Pellet(x * squareSize, y * squareSize);
    }
}
