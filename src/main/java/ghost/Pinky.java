package ghost;

import game.SpriteSheet;
import game.Types;

import java.awt.*;

import static game.Game.playerDirection;
import static game.RenderLevel.pixels;

/**
 * ghost 2.
 */
public class Pinky extends Ghost {
    public static final long serialVersionUID = 4328743;

    private transient String lastMove = "";

    /**
     * ghost constructor 2.
     *
     * @param x           x position of ghost.
     * @param y           y position of ghost.
     * @param spriteSheet the spritesheet to use.
     */
    Pinky(int x, int y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet);
    }

    @Override
    public void moveGhost(int height, int width) {
        if (unitLocations.isEmpty()) {
            System.out.println("no destination for Pinky!");
            return;
        }

        Point destination = this.unitLocations.get(Types.playerType());
        Point newDestination = getNewDestination(destination);

        int res = CommonAIOperations.getRes(this, lastMove, newDestination, height, width);

        switch (res) {
            case 0:
                lastMove = "up";
                moveUpGhost(height);
                break;
            case 1:
                lastMove = "left";
                moveLeftGhost(width);
                break;
            default:
                twoOrThree(res);
                break;
        }
    }

    private void twoOrThree(int res) {
        switch (res) {
            case 2:
                lastMove = "down";
                moveDownGhost(height);
                break;
            case 3:
                lastMove = "right";
                moveRightGhost(width);
                break;
            default:
                break;
        }
    }

    private Point getNewDestination(Point destination) {
        Point newDestination;

        switch (playerDirection) {
            case "up":
                newDestination = new Point(destination.x, destination.y - 80);
                break;
            case "left":
                newDestination = new Point(destination.x - 80, destination.y);
                break;
            default:
                newDestination = downOrRight(destination);
                break;
        }
        return newDestination;
    }

    private Point downOrRight(Point destination) {
        if ("down".equals(playerDirection)) {
            return new Point(destination.x, destination.y + 80);
        } else if ("right".equals(playerDirection)) {
            return new Point(destination.x + 80, destination.y);
        }
        return new Point(destination.x, destination.y);
    }

    @Override
    public String getType() {
        return "g";
    }
}
