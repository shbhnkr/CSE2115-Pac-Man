package ghost;

import game.SpriteSheet;
import game.Types;

import java.awt.Point;

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
     * @param x x position of ghost.
     * @param y y position of ghost.
     * @param spriteSheet the spritesheet to use.
     */
    Pinky(int x, int y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet);
    }

    @Override
    @SuppressWarnings("PMD")
    public void moveGhost(int height, int width) {
        if (unitLocations.isEmpty()) {
            System.out.println("no destination for Pinky!");
            return;
        }

        boolean upWall = isUpWall(height);

        boolean leftWall = isLeftWall(width);

        boolean downWall = isDownWall(height);

        boolean rightWall = isRightWall(width);

        Point destination = this.unitLocations.get(Types.playerType());
        Point newDestination = getNewDestination(destination);

        double distance = Integer.MAX_VALUE;
        double temp;
        int res = -1;

        if (!upWall) {
            if (leftWall & rightWall & downWall) {
                res = 0;
            }
            else if (!lastMove.equals("down")) {
                distance = Math.sqrt(Math.pow((this.getLocation().x
                        - newDestination.getLocation().x) / 20, 2) + Math.pow(((
                        this.getLocation().y - 20) - newDestination.getLocation().y) / 20, 2));
                res = 0;
            }
        }
        if (!leftWall) {
            if (downWall & rightWall & upWall) {
                res = 1;
            }
            else if (!lastMove.equals("right")) {
                temp = Math.sqrt(Math.pow(((
                        this.getLocation().x - 20) - newDestination.getLocation().x) / 20, 2)
                        + Math.pow((this.getLocation().y - newDestination.getLocation().y) / 20, 2));
                if (temp < distance) {
                    distance = temp;
                    res = 1;
                }
            }
        }
        if (!downWall) {
            if (upWall & rightWall & leftWall) {
                res = 2;
            }
            else if (!lastMove.equals("up")) {
                temp = Math.sqrt(Math.pow((
                        this.getLocation().x - newDestination.getLocation().x) / 20, 2)
                        + Math.pow(((
                        this.getLocation().y + 20) - newDestination.getLocation().y) / 20, 2));
                if (temp < distance) {
                    distance = temp;
                    res = 2;
                }
            }
        }
        if (!rightWall) {
            if (downWall & upWall & leftWall) {
                res = 3;
            }
            else if (!lastMove.equals("left")) {
                temp = Math.sqrt(Math.pow(((
                        this.getLocation().x + 20) - newDestination.getLocation().x) / 20, 2)
                        + Math.pow((this.getLocation().y - newDestination.getLocation().y) / 20, 2));
                if (temp < distance) {
                    res = 3;
                }
            }
        }
        movePinky(height, width, res);
    }

    private void movePinky(int height, int width, int res) {
        switch (res) {
            case 0:
                lastMove = "up";
                moveUpGhost(height);
                break;
            case 1:
                lastMove = "left";
                moveLeftGhost(width);
                break;
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
            case "down":
                newDestination = new Point(destination.x, destination.y + 80);
                break;
            case "right":
                newDestination = new Point(destination.x + 80, destination.y);
                break;
            default:
                newDestination = new Point(destination.x, destination.y);
                break;
        }
        return newDestination;
    }

    @Override
    public String getType() {
        return "g";
    }
}
