package game;

import ghost.Ghost;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player extends Unit implements Observable {
    public static final long serialVersionUID = 4328743;

    public static int xPixelPlayer, yPixelPlayer = 0;
    public List<Observer> observerCollection;

    public Player(int x, int y) {
        setBounds(x, y, 20, 20);
        this.observerCollection = new ArrayList<Observer>();
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
    @SuppressWarnings("PMD")
    public void notifyObservers() {
        this.observerCollection.forEach(observer -> {
            observer.observe(this.getType(), this.getLocation());
        });
    }

    /**
     * renders the player on board.
     *
     * @param g Graphics
     */
    public void render(Graphics g) {
        SpriteSheet sheet = Game.playerSprite;
        g.drawImage(sheet.getSprite(xPixelPlayer, yPixelPlayer), x, y, 18, 18, null);
        //g.setColor(Color.YELLOW);
        //g.fillRect(x, y, width, height);
    }

    public void movePlayer(Point movePosition) {
        this.setLocation((int) movePosition.getX(), (int) movePosition.getY());
    }

    public boolean hasCollided(Ghost ghost) {
        if (ghost == null) {
            return false;
        }
        return (this.getLocation().x == ghost.getLocation().x && this.getLocation().y == ghost.getLocation().y);
    }

    //public void movePlayer(int dx, int dy) {
        //this.setLocation(this.x + dx, this.y + dy);
    //}

    @Override
    public String getType() {
        return "p";
    }

    public List<Observer> getObserverCollection() {
        return observerCollection;
    }

    public void setObserverCollection(List<Observer> observerCollection) {
        this.observerCollection = observerCollection;
    }
}
