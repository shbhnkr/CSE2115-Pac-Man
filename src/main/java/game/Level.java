package game;

import ghost.*;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

import static game.Game.pelletCount;

public class Level {


    public transient int width;
    public transient int height;
    public transient Wall[][] walls;
    public transient Player player;
    public transient ghost.Inky inky;
    public transient ghost.Blinky blinky;
    public transient ghost.Pinky pinky;
    public transient ghost.Clyde clyde;
    public transient ghost.Randy randy;
    public static Pellet[][] pellets;
    public static FruitPellet[][] fruitPellet;
    public static char[][] pixels;

    /**
     * Generates a level from given file.
     *
     * @param path    Path
     * @param width1  Width
     * @param height1 Height
     */
    public Level(URL path, int width1, int height1, int squareSize) {
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

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    switch (pixels[x][y]) {
                        case ',':
                            //pelletCount++;
                            getFruitPellets()[x][y] = new FruitPellet(x * squareSize, y * squareSize);
                            break;
                        case '#':
                            walls[x][y] = new Wall(x * squareSize, y * squareSize);
                            break;
                        case '.':
                            pelletCount++;
                            getPellets()[x][y] = new Pellet(x * squareSize, y * squareSize);
                            break;
                        case 'p':
                            this.player = new Player(x * squareSize, y * squareSize);
                            break;
                        case 'i':
                            inky = new ghost.Inky(x * 20, y * 20);
                            break;
                        case 'b':
                            blinky = new ghost.Blinky(x * 20, y * 20);
                            break;
                        case 'g':
                            pinky = new ghost.Pinky(x * 20, y * 20);
                            break;
                        case 'c':
                            clyde = new ghost.Clyde(x * 20, y * 20);
                            break;
                        case 'r':
                            randy = new ghost.Randy(x * 20, y * 20);
                            break;
                        case ' ':
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Pellet[][] getPellets() {
        return pellets;
    }

    public static void setPellets(Pellet[][] pellets) {
        Level.pellets = pellets;
    }

    public static FruitPellet[][] getFruitPellets() {
        return fruitPellet;
    }

    public static void setFruitPellets(FruitPellet[][] fruitPellet) {
        Level.fruitPellet = fruitPellet;
    }

    public static char[][] getPixels() {
        return pixels;
    }

    public static void setPixels(char[][] pixels) {
        Level.pixels = pixels;
    }

    /**
     * Renders a screen.
     *
     * @param g Graphics
     */
    public void render(Graphics g) {
        //System.out.println(width+" " +height+"dtyas");
        //int n = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                //System.out.println(n+" dtffgyas");
                // n++;
                if (fruitPellet[x][y] != null) {
                    fruitPellet[x][y].render(g);
                }
                if (walls[x][y] != null) {
                    walls[x][y].render(g);
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

