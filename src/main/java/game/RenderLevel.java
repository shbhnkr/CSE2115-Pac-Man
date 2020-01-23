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

    static Player player;
    transient Ghost inky;
    static Ghost blinky;
    static Ghost pinky;
    transient Ghost clyde;
    static Ghost randy;
    static Pellet[][] pellets;
    static FruitPellet[][] fruitPellet;
    static Beer[][] beers;
    static PowerPellet[][] dragonBall;
    public static char[][] pixels;
    static List<Ghost> ghosts = new ArrayList<>();
    public static void render(Graphics g)
    {
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
                if (dragonBall[x][y] != null) {
                    dragonBall[x][y].render(g);
                }
                if (pellets[x][y] != null) {
                    pellets[x][y].render(g);
                }
                if (player != null) {
                    player.render(g);
                }
                if (blinky != null) {
                    blinky.render(g);
                }
                if (pinky != null) {
                    pinky.render(g);
                }
                if (randy != null) {
                    randy.render(g);
                }

            }
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
    public static void mapMaker()
    {
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
            setWalls(new Wall[width][height]);
            setPellets(new Pellet[width][height]);
            setFruitPellets(new FruitPellet[width][height]);
            setBeers(new Beer[width][height]);
            setPowerPellets(new PowerPellet[width][height]);


            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {

                    switch (pixels[x][y]) {
                        case ',':
                            getFruitPellets()[x][y]
                                    = new FruitPellet(x * squareSize, y * squareSize);
                            break;
                        case '*':
                            getPowerPellets()[x][y]
                                    = new PowerPellet(x * squareSize, y * squareSize);
                            break;
                        case '#':
                            getWalls()[x][y] = new Wall(x * squareSize, y * squareSize);
                            break;
                        case 'h':
                            getBeers()[x][y] = new Beer(x * squareSize, y * squareSize);
                            break;
                        case '.':
                            pelletCount++;
                            getPellets()[x][y] = new Pellet(x * squareSize, y * squareSize);
                            break;
                        case 'p':
                            player = new Player(x * squareSize, y * squareSize);
                            break;
                        case 'b':
                            blinky = GhostFactory.create(GhostFactory.BLINKY, x * 20, y * 20);
                            ghosts.add(blinky);
                            break;
                        case 'g':
                            pinky = GhostFactory.create(GhostFactory.PINKY, x * 20, y * 20);
                            ghosts.add(pinky);
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
        }
    }
}
