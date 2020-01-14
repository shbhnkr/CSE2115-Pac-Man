package ghost;

import game.MoveBuilder;
import game.Observer;
import game.SpriteSheet;
import game.Unit;

//import java.util.ArrayList;
import java.util.LinkedHashMap;
//import java.util.List;

import java.awt.*;

import static game.Level.pixels;

public abstract class Ghost extends Unit implements Observer {
    public static final long serialVersionUID = 4328743;

    // Holding the SpriteSheet to show the different type of ghosts
    private SpriteSheet sheet;

    // Holds a list of all the locations for objects that are observed.
    public transient LinkedHashMap<String, Point> unitLocations;

    /**
     * Constructor for the different ghosts.
     * @param x position on the map.
     * @param y position on the map.
     * @param spriteSheet the image/SpriteSheet to display.
     */
    public Ghost(int x, int y, SpriteSheet spriteSheet) {
        setBounds(x, y, 20, 20);

        this.unitLocations = new LinkedHashMap<>();

        this.sheet = spriteSheet;
    }

    /**
     * Each type of ghost defines its own movement method.
     * @param height height of the board, needed for the wraparound to work.
     * @param width width of the board, needed for the wraparound to work.

     */
    public abstract void moveGhost(int height, int width);

    /**
     * Renders each ghost with a specific sprite.
     * @param g
     */
    public void render(Graphics g) {
        g.drawImage(this.sheet.getSprite(0,0),x,y,18,18, null);
        //g.setColor(Color.ORANGE);
        //g.fillRect(x, y, width, height);
    }

    /**
     * Get the current sheet.
     * @return returns a sprite sheet
     */
    public SpriteSheet getSheet() {
        return sheet;
    }

    /**
     * Change the sprite sheet.
     * @param sheet
     */
    public void setSheet(SpriteSheet sheet) {
        this.sheet = sheet;
    }


    /**
     *
     * @param type
     * @param location
     */
    @Override
    public void observe(String type, Point location) {
        unitLocations.put(type, location);
    }

    public static int xPixelGhost = 0;
    public static int yPixelGhost = 0;


    public void move(Point movePosition) {
        this.setLocation((int) movePosition.getX(), (int) movePosition.getY());
    }

    public void moveUpGhost() {
        move(MoveBuilder.UP(getLocation()));
        xPixelGhost = 0;
        yPixelGhost = 0;
        switch (pixels[getLocation().x / 20][getLocation().y / 20]) {
            case '#':
                move(MoveBuilder.DOWN(getLocation()));
                break;
            default:
                break;
        }
    }

    public void moveDownGhost() {
        move(MoveBuilder.DOWN(getLocation()));
        xPixelGhost = 0;
        yPixelGhost = 32;
        switch (pixels[getLocation().x / 20][getLocation().y / 20]) {
            case '#':
                move(MoveBuilder.UP(getLocation()));
                break;
            default:
                break;
        }
    }

    public void moveLeftGhost() {
        move(MoveBuilder.LEFT(getLocation()));
        xPixelGhost = 0;
        yPixelGhost = 48;
        switch (pixels[getLocation().x / 20][getLocation().y / 20]) {
            case '#':
                move(MoveBuilder.RIGHT(getLocation()));
                break;
            default:
                break;
        }
    }

    public void moveRightGhost() {
        move(MoveBuilder.RIGHT(getLocation()));
        xPixelGhost = 0;
        yPixelGhost = 16;
        switch (pixels[getLocation().x / 20][getLocation().y / 20]) {
            case '#':
                move(MoveBuilder.LEFT(getLocation()));
                break;
            default:
                break;
        }
    }
}
