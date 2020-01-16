package game;

import ghost.Ghost;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static game.Level.*;

public class Player extends Unit implements Observable {
    public static final long serialVersionUID = 4328743;

    public static int xPixelPlayer, yPixelPlayer = 0;
    public List<Observer> observerCollection;
    public transient boolean drunk;
    public transient boolean power;

    public Player(int x, int y) {
        setBounds(x, y, 20, 20);
        this.observerCollection = new ArrayList<Observer>();
    }

    @Override
    public void registerObserver(Observer observer) {
        if (observer == null) {
            return;
        }
        this.observerCollection.add(observer);
    }

    @Override
    public void deregisterObserver(Observer observer) {
        if (observer == null || observerCollection == null) {
            return;
        }
        this.observerCollection.remove(observer);
    }

    @Override
    @SuppressWarnings("PMD")
    public void notifyObservers() {
        if (observerCollection.isEmpty()) {
            return;
        }
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
    }

    public void movePlayer(Point movePosition) {
        this.setLocation((int) movePosition.getX(), (int) movePosition.getY());
    }

    void moveUp(Game game, int height) {
        xPixelPlayer = 16;
        yPixelPlayer = 0;
        if (getLocation().y != 0 && pixels[getLocation().x / 20][(getLocation().y - 20) / 20] == '#') {
            if (poweredUp(game)) return;
        }
        if (getLocation().y == 0) {
            Point point = new Point(getLocation().x, height - 20);
            movePlayer(point);
            objectCheckerUp(game);
        } else {
            movePlayer(MoveBuilder.UP(getLocation()));
            objectCheckerUp(game);
        }
        notifyObservers();
    }

    private void objectCheckerUp(Game game) {
        switch (pixels[getLocation().x / 20][getLocation().y / 20]) {
            case '#':
                if (power == true) {
                    Wall wal = null;
                    pixels[getLocation().x / 20][getLocation().y / 20] = ' ';
                    walls[getLocation().x / 20][getLocation().y / 20] = wal;
                    game.point += 10;
                    break;
                } else {
                    movePlayer(new Point(getLocation().x, 0));
                    break;
                }
            case '.':
                Pellet pel = null;
                pixels[getLocation().x / 20][getLocation().y / 20] = ' ';
                pellets[getLocation().x / 20][getLocation().y / 20] = pel;
                Game.pelletEaten++;
                game.point += 10;
                game.win();
                break;
            case ',':
                FruitPellet fruitPel = null;
                game.point += fruitPellet[getLocation().x / 20][getLocation().y / 20].points();
                pixels[getLocation().x / 20][getLocation().y / 20] = ' ';
                fruitPellet[getLocation().x / 20][getLocation().y / 20] = fruitPel;
                break;
            case 'h':
                Beer beer = null;
                pixels[getLocation().x / 20][getLocation().y / 20] = ' ';
                beers[getLocation().x / 20][getLocation().y / 20] = beer;
                drunk = true;
                break;
            case '*':
                PowerPellet powerPellet = null;
                pixels[getLocation().x / 20][getLocation().y / 20] = ' ';
                dragonBall[getLocation().x / 20][getLocation().y / 20] = powerPellet;
                power = true;
                break;
            default:
                break;
        }
    }

    void moveLeft(Game game, int width) {
        xPixelPlayer = 16;
        yPixelPlayer = 48;
        if (getLocation().x != 0 && pixels[(getLocation().x - 20) / 20][getLocation().y / 20] == '#') {
            if (poweredUp(game)) return;
        }
        if (getLocation().x == 0) {
            Point point = new Point(width - 20, getLocation().y);
            movePlayer(point);
            objectCheckerLeft(game);
        } else {
            movePlayer(MoveBuilder.LEFT(getLocation()));
            objectCheckerLeft(game);
        }
        notifyObservers();
    }


    private void objectCheckerLeft(Game game) {
        switch (pixels[getLocation().x / 20][getLocation().y / 20]) {
            case '#':
                if (power == true) {
                    Wall wal = null;
                    pixels[getLocation().x / 20][getLocation().y / 20] = ' ';
                    walls[getLocation().x / 20][getLocation().y / 20] = wal;
                    game.point += 10;
                    break;
                } else {
                    movePlayer(new Point(0, getLocation().y));
                    break;
                }
            case '.':
                Pellet pel = null;
                pixels[getLocation().x / 20][getLocation().y / 20] = ' ';
                pellets[getLocation().x / 20][getLocation().y / 20] = pel;
                Game.pelletEaten++;
                game.point += 10;
                game.win();
                break;
            case ',':
                FruitPellet fruitPel = null;
                game.point += fruitPellet[getLocation().x / 20][getLocation().y / 20].points();
                pixels[getLocation().x / 20][getLocation().y / 20] = ' ';
                fruitPellet[getLocation().x / 20][getLocation().y / 20] = fruitPel;
                break;
            case 'h':
                Beer beer = null;
                pixels[getLocation().x / 20][getLocation().y / 20] = ' ';
                beers[getLocation().x / 20][getLocation().y / 20] = beer;
                drunk = true;
                break;
            case '*':
                PowerPellet powerPellet = null;
                pixels[getLocation().x / 20][getLocation().y / 20] = ' ';
                dragonBall[getLocation().x / 20][getLocation().y / 20] = powerPellet;
                power = true;
                break;
            default:
                break;
        }
    }

    void moveDown(Game game, int height) {
        xPixelPlayer = 16;
        yPixelPlayer = 32;
        if (getLocation().y != height - 20 && pixels[getLocation().x / 20][(getLocation().y + 20) / 20] == '#') {
            if (poweredUp(game)) return;
        }
        if (getLocation().y == height - 20) {
            Point point = new Point(getLocation().x, 0);
            movePlayer(point);
            objectCheckerDown(game, height);
        } else {
            movePlayer(MoveBuilder.DOWN(getLocation()));
            objectCheckerDown(game, height);
        }
        notifyObservers();
    }

    private void objectCheckerDown(Game game, int height) {
        switch (pixels[getLocation().x / 20][getLocation().y / 20]) {
            case '#':
                if (power == true) {
                    Wall wal = null;
                    pixels[getLocation().x / 20][getLocation().y / 20] = ' ';
                    walls[getLocation().x / 20][getLocation().y / 20] = wal;
                    game.point += 10;
                    break;
                } else {
                    movePlayer(new Point(getLocation().x, height - 20));
                    break;
                }
            case '.':
                Pellet pel = null;
                pixels[getLocation().x / 20][getLocation().y / 20] = ' ';
                pellets[getLocation().x / 20][getLocation().y / 20] = pel;
                Game.pelletEaten++;
                game.point += 10;
                game.win();
                break;
            case ',':
                FruitPellet fruitPel = null;
                game.point += fruitPellet[getLocation().x / 20][getLocation().y / 20].points();
                pixels[getLocation().x / 20][getLocation().y / 20] = ' ';
                fruitPellet[getLocation().x / 20][getLocation().y / 20] = fruitPel;
                break;
            case 'h':
                Beer beer = null;
                pixels[getLocation().x / 20][getLocation().y / 20] = ' ';
                beers[getLocation().x / 20][getLocation().y / 20] = beer;
                drunk = true;
                break;
            case '*':
                PowerPellet powerPellet = null;
                pixels[getLocation().x / 20][getLocation().y / 20] = ' ';
                dragonBall[getLocation().x / 20][getLocation().y / 20] = powerPellet;
                power = true;
                break;
            default:
                break;
        }
    }

    void moveRight(Game game, int width) {
        xPixelPlayer = 16;
        yPixelPlayer = 16;
        if (getLocation().x != width - 20 && pixels[(getLocation().x + 20) / 20][getLocation().y / 20] == '#') {
            if (poweredUp(game)) return;
        }
        if (getLocation().x == width - 20) {
            Point point = new Point(0, getLocation().y);
            movePlayer(point);
            objectCheckerRight(game, width);
        } else {
            movePlayer(MoveBuilder.RIGHT(getLocation()));
            objectCheckerRight(game, width);
        }
        notifyObservers();
    }

    private boolean poweredUp(Game game) {
        if (power == true) {
            Wall wal = null;
            pixels[getLocation().x / 20][getLocation().y / 20] = ' ';
            walls[getLocation().x / 20][getLocation().y / 20] = wal;
            game.point += 10;
        } else {
            return true;
        }
        return false;
    }

    private void objectCheckerRight(Game game, int width) {
        switch (pixels[getLocation().x / 20][getLocation().y / 20]) {
            case '#':
                if (power == true) {
                    Wall wal = null;
                    pixels[getLocation().x / 20][getLocation().y / 20] = ' ';
                    walls[getLocation().x / 20][getLocation().y / 20] = wal;
                    game.point += 10;
                    break;
                } else {
                    movePlayer(new Point(width - 20, getLocation().y));
                    break;
                }
            case '.':
                Pellet pel = null;
                pixels[getLocation().x / 20][getLocation().y / 20] = ' ';
                pellets[getLocation().x / 20][getLocation().y / 20] = pel;
                Game.pelletEaten++;
                game.point += 10;
                game.win();
                break;
            case ',':
                FruitPellet fruitPel = null;
                game.point += fruitPellet[getLocation().x / 20][getLocation().y / 20].points();
                pixels[getLocation().x / 20][getLocation().y / 20] = ' ';
                fruitPellet[getLocation().x / 20][getLocation().y / 20] = fruitPel;
                break;
            case 'h':
                Beer beer = null;
                pixels[getLocation().x / 20][getLocation().y / 20] = ' ';
                beers[getLocation().x / 20][getLocation().y / 20] = beer;
                drunk = true;
                break;
            case '*':
                PowerPellet powerPellet = null;
                pixels[getLocation().x / 20][getLocation().y / 20] = ' ';
                dragonBall[getLocation().x / 20][getLocation().y / 20] = powerPellet;
                power = true;
                break;
            default:
                break;
        }
    }

    boolean hasCollided(Ghost ghost) {
        if (ghost == null) {
            return false;
        }
        return (this.getLocation().x == ghost.getLocation().x && this.getLocation().y == ghost.getLocation().y);
    }

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
