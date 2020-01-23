package ghost;

import game.SpriteSheet;
import game.Types;

import java.awt.Point;

import static game.RenderLevel.pixels;


public class Blinky extends Ghost {
    /**
     * ghost 1.
     */
    public static final long serialVersionUID = 4328743;

    private transient String lastMove = "";

    /**
     * ghost constructor 1.
     * @param x x position of ghost.
     * @param y y position of ghost.
     * @param spriteSheet the spritesheet to use.
     */
    Blinky(int x, int y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet);
    }

    @Override
    @SuppressWarnings("PMD")
    public void moveGhost(int height, int width) {
        if (unitLocations.isEmpty()) {
            System.out.println("no destination for Pinky!");
            return;
        }

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

        Point destination = this.unitLocations.get(Types.playerType());
        double distance = Integer.MAX_VALUE;
        int res = 0;

        if (!upWall) {
            if (!lastMove.equals("down") || downWall & rightWall & leftWall) {
                distance = Math.sqrt(Math.pow((this.getLocation().x
                        - destination.getLocation().x) / 20, 2) + Math.pow(((
                                this.getLocation().y - 20) - destination.getLocation().y) / 20, 2));
            }
        }
        if (!leftWall) {
            if (!lastMove.equals("right") || downWall & rightWall & upWall) {
                double temp = Math.sqrt(Math.pow(((
                        this.getLocation().x - 20) - destination.getLocation().x) / 20, 2)
                        + Math.pow((this.getLocation().y - destination.getLocation().y) / 20, 2));
                if (temp < distance) {
                    distance = temp;
                    res = 1;
                }
            }
        }
        if (!downWall) {
            if (!lastMove.equals("up") || upWall & rightWall & leftWall) {
                double temp = Math.sqrt(Math.pow((
                        this.getLocation().x - destination.getLocation().x) / 20, 2)
                        + Math.pow(((
                                this.getLocation().y + 20) - destination.getLocation().y) / 20, 2));
                if (temp < distance) {
                    distance = temp;
                    res = 2;
                }
            }
        }
        if (!rightWall) {
            if (!lastMove.equals("left") || downWall & upWall & leftWall) {
                double temp = Math.sqrt(Math.pow(((
                        this.getLocation().x + 20) - destination.getLocation().x) / 20, 2)
                        + Math.pow((this.getLocation().y - destination.getLocation().y) / 20, 2));
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
                break;
        }
    }

    @Override
    public String getType() {
        return "b";
    }
}

