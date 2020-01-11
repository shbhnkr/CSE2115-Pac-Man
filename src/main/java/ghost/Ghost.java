package ghost;

import game.Observer;
import game.SpriteSheet;
import game.Unit;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import java.awt.*;

public abstract class Ghost extends Unit implements Observer {
    public static final long serialVersionUID = 4328743;

    // Holding the spritesheet to show the different type of ghosts
    private SpriteSheet sheet;

    // Holds a list of all the locations for objects that are observed.
    public transient LinkedHashMap<String, Point> unitLocations;

    /**
     * Constructor for the different ghosts.
     * @param x position on the map.
     * @param y position on the map.
     * @param spriteSheet the image/spritesheet to display.
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


    @Override
    public void observe(String type, Point location) {
        unitLocations.put(type, location);
    }
}
