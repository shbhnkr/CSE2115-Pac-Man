package game;


import java.awt.*;
import java.net.URL;

import static game.RenderLevel.*;

public class Level {

    public static int width;
    public static int height;
    static Wall[][] walls;
    static int squareSize;
    static URL path;

    /**
     * Generates a level from given file.
     *
     * @param path    Path
     * @param width  Width
     * @param height Height
     */
    Level(URL path, int width, int height, int squareSize) {
        Level.width = width / squareSize;
        Level.height = height / squareSize;
        Level.squareSize = squareSize;
        Level.path = path;
        mapMaker();
    }

    public static Wall[][] getWalls() {
        return Level.walls;
    }

    public static void setWalls(Wall[][] walls) {
        Level.walls = walls;
    }

    public static Beer[][] getBeers() {
        return beers;
    }

    public static void setBeers(Beer[][] beers) {
        RenderLevel.beers = beers;
    }

    public static PowerPellet[][] getPowerPellets() {
        return dragonBall;
    }

    public static void setPowerPellets(PowerPellet[][] dragonBall) {
        RenderLevel.dragonBall = dragonBall;
    }


    /**
     * Renders a screen.
     *
     * @param g Graphics
     */
    void render(Graphics g) {
        RenderLevel.render(g);
    }
}

