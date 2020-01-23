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

    private transient String lastMove;

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
        Point destination = this.unitLocations.get(Types.playerType());
        if (unitLocations.isEmpty() || destination == null) {
            System.out.println("no destination for Pinky!");
            return;
        }
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
        double distance = (double) Integer.MAX_VALUE;

        boolean upWall;
        if (this.getLocation().y == 0) {
            upWall = pixels[getLocation().x / 20][(height - 20) / 20] == '#';
        } else {
            upWall = pixels[getLocation().x / 20][(getLocation().y - 20) / 20] == '#';
        }

        boolean leftWall;
        if (this.getLocation().x == 0) {
            leftWall = pixels[(width - 20) / 20][getLocation().y / 20] == '#';
        } else {
            leftWall = pixels[(getLocation().x - 20) / 20][getLocation().y / 20] == '#';
        }

        boolean downWall;
        if (this.getLocation().y == height - 20) {
            downWall = pixels[getLocation().x / 20][0] == '#';
        } else {
            downWall = pixels[getLocation().x / 20][(getLocation().y + 20) / 20] == '#';
        }
        boolean rightWall;
        if (this.getLocation().x == width - 20) {
            rightWall = pixels[0][getLocation().y / 20] == '#';
        } else {
            rightWall = pixels[(getLocation().x + 20) / 20][getLocation().y / 20] == '#';
        }

        int res = -1;
        if (!upWall) {
            if (lastMove == null || !lastMove.equals("down") || downWall && rightWall && leftWall) {
                double temp = Math.sqrt(Math.pow((
                        this.getLocation().x - newDestination.getLocation().x) / 20, 2)
                        + Math.pow(((this.getLocation().y - 20)
                        - newDestination.getLocation().y) / 20, 2));
                if (temp < distance) {
                    distance = temp;
                    res = 0;
                }
            }
        }
        if (!leftWall) {
            if (lastMove == null || !lastMove.equals("right") || downWall && rightWall && upWall) {
                double temp = Math.sqrt(Math.pow(((
                        this.getLocation().x - 20) - newDestination.getLocation().x) / 20, 2)
                        + Math.pow((
                                this.getLocation().y - newDestination.getLocation().y) / 20, 2));
                if (temp < distance) {
                    distance = temp;
                    res = 1;
                }
            }
        }
        if (!downWall) {
            if (lastMove == null || !lastMove.equals("up") || upWall && rightWall && leftWall) {
                double temp = Math.sqrt(Math.pow((
                        this.getLocation().x - newDestination.getLocation().x) / 20, 2)
                        + Math.pow(((this.getLocation().y + 20)
                        - newDestination.getLocation().y) / 20, 2));
                if (temp < distance) {
                    distance = temp;
                    res = 2;
                }
            }
        }
        if (!rightWall) {
            if (lastMove == null || !lastMove.equals("left") || downWall && upWall && leftWall) {
                double temp = Math.sqrt(Math.pow(((
                        this.getLocation().x + 20) - newDestination.getLocation().x) / 20, 2)
                        + Math.pow((
                                this.getLocation().y - newDestination.getLocation().y) / 20, 2));
                if (temp < distance) {
                    res = 3;
                }
            }
        }
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
        }
    }

    @Override
    public String getType() {
        return "g";
    }
}
