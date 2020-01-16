package game;


import ghost.Ghost;
import ghost.GhostFactory;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static game.Game.pelletCount;

public class Level {

    public transient int width;
    public transient int height;
    transient Wall[][] walls;
    transient Player player;
    transient Ghost inky;
    transient Ghost blinky;
    transient Ghost pinky;
    transient Ghost clyde;
    transient Ghost randy;
    static Pellet[][] pellets;
    static FruitPellet[][] fruitPellet;
    static Beer[][] beers;
    public static char[][] pixels;
    transient List<Ghost> ghosts = new ArrayList<>();

    /**
     * Generates a level from given file.
     *
     * @param path    Path
     * @param width1  Width
     * @param height1 Height
     */
    Level(URL path, int width1, int height1, int squareSize) {
        this.width = width1 / squareSize;
        this.height = height1 / squareSize;
        setPixels(new char[width][height]);
        File file = new File(path.getFile());
        Scanner sc;
        try {
            sc = new Scanner(file);
            int n = 0;
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                for (int i = 0; i < s.length(); i++) {

                    getPixels()[i][n] = s.charAt(i);
                }
                n++;
            }
            walls = new Wall[width][height];
            setPellets(new Pellet[width][height]);
            setFruitPellets(new FruitPellet[width][height]);
            setBeers(new Beer[width][height]);

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    switch (pixels[x][y]) {
                        case ',':
                            getFruitPellets()[x][y] = new FruitPellet(x * squareSize, y * squareSize);
                            break;
                        case '#':
                            walls[x][y] = new Wall(x * squareSize, y * squareSize);
                            break;
                        case 'h':
                            getBeers()[x][y] = new Beer(x * squareSize, y * squareSize);
                            break;
                        case '.':
                            pelletCount++;
                            getPellets()[x][y] = new Pellet(x * squareSize, y * squareSize);
                            break;
                        case 'p':
                            this.player = new Player(x * squareSize, y * squareSize);
                            break;
                        case 'i':
                            inky = GhostFactory.create(GhostFactory.INKY, x * 20, y * 20);
                            ghosts.add(inky);
                            break;
                        case 'b':
                            blinky = GhostFactory.create(GhostFactory.BLINKY, x * 20, y * 20);
                            ghosts.add(blinky);
                            break;
                        case 'g':
                            pinky = GhostFactory.create(GhostFactory.PINKY, x * 20, y * 20);
                            ghosts.add(pinky);
                            break;
                        case 'c':
                            clyde = GhostFactory.create(GhostFactory.CLYDE, x * 20, y * 20);
                            ghosts.add(clyde);
                            break;
                        case 'r':
                            randy = GhostFactory.create(GhostFactory.RANDY, x * 20, y * 20);
                            ghosts.add(randy);
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }// TODO make a ghostFactory expection.

    }

    private static Beer[][] getBeers() {
        return beers;
    }

    private static void setBeers(Beer[][] beers) {
        Level.beers = beers;
    }

    private static Pellet[][] getPellets() {
        return pellets;
    }

    private static void setPellets(Pellet[][] pellets) {
        Level.pellets = pellets;
    }

    private static FruitPellet[][] getFruitPellets() {
        return fruitPellet;
    }

    private static void setFruitPellets(FruitPellet[][] fruitPellet) {
        Level.fruitPellet = fruitPellet;
    }

    private static char[][] getPixels() {
        return pixels;
    }

    private static void setPixels(char[][] pixels) {
        Level.pixels = pixels;
    }

    /**
     * Renders a screen.
     *
     * @param g Graphics
     */
    void render(Graphics g) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (fruitPellet[x][y] != null) {
                    fruitPellet[x][y].render(g);
                }
                if (walls[x][y] != null) {
                    walls[x][y].render(g);
                }
                if (beers[x][y] != null) {
                    beers[x][y].render(g);
                }
                if (pellets[x][y] != null) {
                    pellets[x][y].render(g);
                }
                if (player != null) {
                    player.render(g);
                }
                if (inky != null) {
                    inky.render(g);
                }
                if (blinky != null) {
                    blinky.render(g);
                }
                if (pinky != null) {
                    pinky.render(g);
                }
                if (clyde != null) {
                    clyde.render(g);
                }
                if (randy != null) {
                    randy.render(g);
                }

            }
        }
    }
}

