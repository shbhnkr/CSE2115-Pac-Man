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
     * @param width1  Width
     * @param height1 Height
     */
    Level(URL path, int width1, int height1, int squareSize) {
        setWidth(width1 / squareSize);
        setHeight(height1 / squareSize);
        setSquareSize(squareSize);
        setPath(path);
        mapMaker();
    }

    public static void setWidth(int width) {
        Level.width = width;
    }

    public static void setHeight(int height) {
        Level.height = height;
    }

    public static void setSquareSize(int squareSize) {
        Level.squareSize = squareSize;
    }

    public static void setPath(URL path) {
        Level.path = path;
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

