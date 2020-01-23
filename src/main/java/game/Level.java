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
        this.width = width1 / squareSize;
        this.height = height1 / squareSize;
        this.squareSize = squareSize;
        this.path = path;
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

