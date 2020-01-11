package ghost;

import game.Game;
import game.MoveBuilder;
import game.SpriteSheet;
import game.Unit;

import java.awt.*;
import java.util.Random;

import static game.Level.pixels;

/**
 * ghost 5.
 */
public class Randy extends Ghost {
    public static final long serialVersionUID = 4328743;

    public Randy(int x, int y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet);
    }

    @Override
    public void moveGhost(int height, int width) {
        Random rand = new Random();
        int random = rand.nextInt(4);

        switch (random) {
            case 0:
                if (this.getLocation().y == 0) {
                    Point point = new Point(this.getLocation().x, height - 20);
                    this.move(point);
                }
                this.moveUpGhost();
                break;
            case 1:
                if (this.getLocation().y == height - 20) {
                    Point point = new Point(this.getLocation().x, 0);
                    this.move(point);
                }
                this.moveDownGhost();
                break;
            case 2:
                if (this.getLocation().x == 0) {
                    Point point = new Point( width - 20, this.getLocation().y);
                    this.move(point);
                }
                this.moveLeftGhost();
                break;
            default:
                if (this.getLocation().x == width - 20) {
                    Point point = new Point(0, this.getLocation().y);
                    this.move(point);
                }
                this.moveRightGhost();
                break;
        }
    }

    public static int xPixelGhost = 0;
    public static int yPixelGhost = 0;
    public static int coolDown = 300;


    public void move(Point movePosition) {
        this.setLocation((int) movePosition.getX(), (int) movePosition.getY());
    }

//    public boolean hasCollided(Player player) {
//        if (player == null) {
//            return false;
//        }
//        return (this.getLocation().x == player.getLocation().x && this.getLocation().y == player.getLocation().y);
//    }

    public void moveUpGhost() {
        move(MoveBuilder.UP(getLocation()));
        xPixelGhost = 0;
        yPixelGhost = 0;
        switch (pixels[getLocation().x / 20][getLocation().y / 20]) {
            case '#':
                move(MoveBuilder.DOWN(getLocation()));
                break;
//            case 'p':
//                System.out.println("PLAYER SHOULD DIE NOW");
//                break;
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
//            case 'p':
//                System.out.println("PLAYER SHOULD DIE NOW");
//                break;
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
//            case 'p':
//                System.out.println("PLAYER SHOULD DIE NOW");
//                break;
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
//            case 'p':
//                System.out.println("PLAYER SHOULD DIE NOW");
//                break;
            default:
                break;
        }
    }
    @Override
    public String getType() {
        return "r";
    }
}
