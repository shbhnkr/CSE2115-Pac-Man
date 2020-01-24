package ghost;

import game.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import static game.RenderLevel.pixels;

/**
 * the superclass for all ghosts.
 */
public abstract class Ghost extends Unit implements Observer, Observable {
    public static final long serialVersionUID = 4328743;

    // Holding the spritesheet to show the different type of ghosts
    private SpriteSheet sheet;

    // Holds a list of all the locations for objects that are observed.
    public LinkedHashMap<String, Point> unitLocations;


    private List<Observer> observerCollection;

    /**
     * Constructor for the different ghosts.
     *
     * @param x           x position on the map.
     * @param y           y position on the map.
     * @param spriteSheet the image/spritesheet to display.
     */
    public Ghost(int x, int y, SpriteSheet spriteSheet) {
        setBounds(x, y, 20, 20);
        this.unitLocations = new LinkedHashMap<>();
        this.observerCollection = new ArrayList<Observer>();
        this.sheet = spriteSheet;
    }

    /**
     * Each type of ghost defines its own movement method.
     *
     * @param height height of the board, needed for the wraparound to work.
     * @param width  width of the board, needed for the wraparound to work.
     */
    public abstract void moveGhost(int height, int width);

    public abstract String getType();

    /**
     * Renders each ghost with a specific sprite.
     *
     * @param g the graphics to render
     */
    public void render(Graphics g) {
        g.drawImage(this.sheet.getSprite(0, 0), x, y, 18, 18, null);
    }

    /**
     * Get the current sheet.
     *
     * @return returns a sprite sheet
     */
    public SpriteSheet getSheet() {
        return sheet;
    }

    /**
     * Change the sprite sheet.
     *
     * @param sheet the sprite sheet to set to
     */
    public void setSheet(SpriteSheet sheet) {
        this.sheet = sheet;
    }

    /**
     * observes type and location of the unit.
     *
     * @param type     the type of the unit
     * @param location the location of the unit
     */
    @Override
    public void observe(String type, Point location) {
        unitLocations.put(type, location);
    }

    private static int xPixelGhost = 0;
    private static int yPixelGhost = 0;


    public void move(Point movePosition) {
        this.setLocation((int) movePosition.getX(), (int) movePosition.getY());
    }

    void moveUpGhost(int height) {
        xPixelGhost = 0;
        yPixelGhost = 0;
        if (isUpWall(height)) {
            return;
        }
        if (this.getLocation().y == 0) {
            this.move(new Point(this.getLocation().x, height - 20));
        } else {
            move(MoveBuilder.up(this.getLocation()));
        }
    }

    void moveLeftGhost(int width) {
        xPixelGhost = 0;
        yPixelGhost = 48;

        if (isLeftWall(width)) {
            return;
        }
        if (this.getLocation().x == 0) {
            this.move(new Point(width - 20, this.getLocation().y));
        } else {
            move(MoveBuilder.left(this.getLocation()));
        }
    }

    void moveDownGhost(int height) {
        xPixelGhost = 0;
        yPixelGhost = 32;

        if (isDownWall(height)) {
            return;
        }
        if (this.getLocation().y == height - 20) {
            this.move(new Point(this.getLocation().x, 0));
        } else {
            move(MoveBuilder.down(this.getLocation()));
        }
    }

    void moveRightGhost(int width) {
        xPixelGhost = 0;
        yPixelGhost = 16;

        if (isRightWall(width)) {
            return;
        }

        if (this.getLocation().x == width - 20) {
            this.move(new Point(0, this.getLocation().y));
        } else {
            move(MoveBuilder.right(this.getLocation()));
        }
    }

    boolean isUpWall(int height) {
        if (getLocation().y == 0) {
            return pixels[getLocation().x / 20][(height - 20) / 20] == '#';
        } else {
            return pixels[getLocation().x / 20][(getLocation().y - 20) / 20] == '#';
        }
    }

    boolean isLeftWall(int width) {
        if (getLocation().x == 0) {
            return pixels[(width - 20) / 20][getLocation().y / 20] == '#';
        } else {
            return pixels[(getLocation().x - 20) / 20][getLocation().y / 20] == '#';
        }
    }

    boolean isDownWall(int height) {
        if (getLocation().y == (height - 20)) {
            return pixels[getLocation().x / 20][0] == '#';
        } else {
            return pixels[getLocation().x / 20][(getLocation().y + 20) / 20] == '#';
        }
    }

    boolean isRightWall(int width) {
        if (getLocation().x == (width - 20)) {
            return pixels[0][getLocation().y / 20] == '#';
        } else {
            return pixels[(getLocation().x + 20) / 20][getLocation().y / 20] == '#';
        }
    }

    public LinkedHashMap<String, Point> getUnitLocations() {
        return unitLocations;
    }

    public void setUnitLocations(LinkedHashMap<String, Point> unitLocations) {
        this.unitLocations = unitLocations;
    }

    @Override
    public void registerObserver(Observer observer) {
        this.observerCollection.add(observer);
    }

    @Override
    public void deregisterObserver(Observer observer) {
        this.observerCollection.remove(observer);
    }

    @Override
    // Known issue of PMD, described in the following link:
    // stackoverflow.com/questions/21592497/java-for-each-loop-being-flagged-as-ur-anomaly-by-pmd
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    public void notifyObservers() {
        this.observerCollection.forEach(observer -> {
            observer.observe(this.getType(), this.getLocation());
        });
    }

    /**
     * Get the observer collection.
     *
     * @return collection of observers.
     */
    public List<Observer> getObserverCollection() {
        return observerCollection;
    }

    /**
     * Set the observer collection.
     *
     * @param observerCollection the observerCollection to set to
     */
    public void setObserverCollection(List<Observer> observerCollection) {
        this.observerCollection = observerCollection;
    }

    public void setRandom(int bound, int offset) {
        Random rand = new Random();
        Randy.random = rand.nextInt(bound) + offset;
    }
}
