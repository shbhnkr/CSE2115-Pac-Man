package game;

import java.awt.*;

import static game.Level.pixels;

/**
 * ghost 5.
 */
public class Randy extends Unit {
    public static final long serialVersionUID = 4328743;

    public static int xPixelGhost, yPixelGhost = 0;

    public static int coolDown = 300;

    /**
     * ghost constructor 5.
     */
    public Randy(int x, int y) {
        setBounds(x, y, 20, 20);
    }

    /**
     * ghost render 5.
     */
    public void render(Graphics g) {
        SpriteSheet sheet = Game.randySprite;
        g.drawImage(sheet.getSprite(0,0),x,y,18,18, null);
        //g.setColor(Color.GREEN);
        //g.fillRect(x, y, width, height);
    }

    public void moveGhost(Point movePosition) {
        this.setLocation((int) movePosition.getX(), (int) movePosition.getY());
    }

    public void moveUpGhost() {
        moveGhost(MoveBuilder.UP(getLocation()));
        switch (pixels[getLocation().x / 20][getLocation().y / 20]) {
            case '#':
                moveGhost(MoveBuilder.DOWN(getLocation()));
                break;
//            case 'p':
//                System.out.println("PLAYER SHOULD DIE NOW");
//                break;
            default:
                break;
        }
    }

    public void moveDownGhost() {
        moveGhost(MoveBuilder.DOWN(getLocation()));
        switch (pixels[getLocation().x / 20][getLocation().y / 20]) {
            case '#':
                moveGhost(MoveBuilder.UP(getLocation()));
                break;
//            case 'p':
//                System.out.println("PLAYER SHOULD DIE NOW");
//                break;
            default:
                break;
        }
    }

    public void moveLeftGhost() {
        moveGhost(MoveBuilder.LEFT(getLocation()));
        switch (pixels[getLocation().x / 20][getLocation().y / 20]) {
            case '#':
                moveGhost(MoveBuilder.RIGHT(getLocation()));
                break;
//            case 'p':
//                System.out.println("PLAYER SHOULD DIE NOW");
//                break;
            default:
                break;
        }
    }

    public void moveRightGhost() {
        moveGhost(MoveBuilder.RIGHT(getLocation()));
        switch (pixels[getLocation().x / 20][getLocation().y / 20]) {
            case '#':
                moveGhost(MoveBuilder.LEFT(getLocation()));
                break;
//            case 'p':
//                System.out.println("PLAYER SHOULD DIE NOW");
//                break;
            default:
                break;
        }
    }
    @Override
    String getType() {
        return "r";
    }
}
