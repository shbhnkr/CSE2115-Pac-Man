package ghost;

import game.SpriteSheet;
import game.Types;

import java.awt.Point;

public class Blinky extends Ghost {
    /**
     * ghost 1.
     */
    public static final long serialVersionUID = 4328743;

    private transient String lastMove = "";

    /**
     * ghost constructor 1.
     *
     * @param x           x position of ghost.
     * @param y           y position of ghost.
     * @param spriteSheet the spritesheet to use.
     */
    Blinky(int x, int y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet);
    }

    int oneValidMove(int res, boolean upWall, boolean leftWall, boolean downWall, boolean rightWall) {
        if (leftWall & rightWall & downWall) {
            return 0;
        }
        if (downWall & rightWall & upWall) {
            return 1;
        }
        if (upWall & rightWall & leftWall) {
            return 2;
        }
        if (downWall & upWall & leftWall) {
            return 3;
        }
        return res;
    }

    @Override
    @SuppressWarnings("PMD")
    public void moveGhost(int height, int width) {
        if (unitLocations.isEmpty()) {
            System.out.println("no destination for Blinky!");
            return;
        }

        boolean upWall = isUpWall(height);

        boolean leftWall = isLeftWall(width);

        boolean downWall = isDownWall(height);

        boolean rightWall = isRightWall(width);

        Point destination = this.unitLocations.get(Types.playerType());
        double distance = Integer.MAX_VALUE;
        double temp;
        int res = -1;

        if (!upWall) {
            res = oneValidMove(res, upWall, leftWall, downWall, rightWall);

            if (!lastMove.equals("down")) {
                distance = Math.sqrt(Math.pow((this.getLocation().x
                        - destination.getLocation().x) / 20, 2) + Math.pow(((
                        this.getLocation().y - 20) - destination.getLocation().y) / 20, 2));
                res = 0;
            }
        }
        if (!leftWall) {
            res = oneValidMove(res, upWall, leftWall, downWall, rightWall);

            if (!lastMove.equals("right")) {
                temp = Math.sqrt(Math.pow(((
                        this.getLocation().x - 20) - destination.getLocation().x) / 20, 2)
                        + Math.pow((this.getLocation().y - destination.getLocation().y) / 20, 2));
                if (temp < distance) {
                    distance = temp;
                    res = 1;
                }
            }
        }
        if (!downWall) {
            res = oneValidMove(res, upWall, leftWall, downWall, rightWall);

            if (!lastMove.equals("up")) {
                temp = Math.sqrt(Math.pow((
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
            res = oneValidMove(res, upWall, leftWall, downWall, rightWall);

            if (!lastMove.equals("left")) {
                temp = Math.sqrt(Math.pow(((
                        this.getLocation().x + 20) - destination.getLocation().x) / 20, 2)
                        + Math.pow((this.getLocation().y - destination.getLocation().y) / 20, 2));
                if (temp < distance) {
                    res = 3;
                }
            }
        }
        moveBlinky(height, width, res);
    }

    private void moveBlinky(int height, int width, int res) {
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
