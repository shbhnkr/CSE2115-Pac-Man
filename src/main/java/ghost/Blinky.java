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
     *
     * @param x           x position of
     * @param y           y position of
     * @param spriteSheet the spritesheet to use.
     */
    Blinky(int x, int y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet);
    }

    transient double distance = Integer.MAX_VALUE;
    transient int res = -1;

    @Override
    public void moveGhost(int height, int width) {
        if (unitLocations.isEmpty()) {
            System.out.println("no destination for Blinky!");
            return;
        }

        Point destination = this.unitLocations.get(Types.playerType());
        res = getRes(destination, height, width);
        System.out.println(res + " is debugg");
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

    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    int getRes(Point destination, int height, int width) {
        boolean upWall = isUpWall(height);
        boolean leftWall = isLeftWall(width);
        boolean downWall = isDownWall(height);
        boolean rightWall = isRightWall(width);

        if (!upWall) {
            upValidMove(leftWall, downWall, rightWall);
        }
        if (!leftWall) {
            leftValidMove(destination, upWall, downWall, rightWall, width);
        }
        if (!downWall) {
            downValidMove(destination, upWall, leftWall, rightWall, height);
        }
        if (!rightWall) {
            rightValidMove(destination, upWall, leftWall, downWall, width);
        }
        return res;
    }

    void rightValidMove(Point destination, boolean upWall, boolean leftWall, boolean downWall, int width) {
        if (downWall & upWall & leftWall) {
            res = 3;
        }
        if (!lastMove.equals("left")) {
            double temp;
            if (getLocation().x == (width - 20)) {
                temp = Math.sqrt(Math.pow(((0) - destination.x) / 20, 2)
                        + Math.pow((getLocation().y - destination.y) / 20, 2));
            } else {
                temp = Math.sqrt(Math.pow(((getLocation().x + 20)
                        - destination.x) / 20, 2)
                        + Math.pow((getLocation().y - destination.y) / 20, 2));
            }
            res = compare(temp, res, 3);
        }
    }

    void downValidMove(Point destination, boolean upWall, boolean leftWall, boolean rightWall, int height) {
        if (leftWall & rightWall & upWall) {
            res = 2;
        }
        if (!lastMove.equals("up")) {
            double temp;
            if (getLocation().y == (height - 20)) {
                temp = Math.sqrt(Math.pow(((getLocation().y) - destination.x) / 20, 2)
                        + Math.pow((0 - destination.y) / 20, 2));
            } else {
                temp = Math.sqrt(Math.pow(((getLocation().x)
                        - destination.x) / 20, 2)
                        + Math.pow(((getLocation().y + 20) - destination.y) / 20, 2));
            }
            res = compare(temp, res, 2);
        }
    }

    void leftValidMove(Point destination, boolean upWall, boolean downWall, boolean rightWall, int width) {
        if (downWall & rightWall & upWall) {
            res = 1;
        }
        if (!lastMove.equals("right")) {
            double temp;
            if (getLocation().x == 0) {
                temp = Math.sqrt(Math.pow(((width - 20) - destination.x) / 20, 2)
                        + Math.pow((getLocation().y - destination.y) / 20, 2));
            } else {
                temp = Math.sqrt(Math.pow(((getLocation().x + 20)
                        - destination.x) / 20, 2)
                        + Math.pow((getLocation().y - destination.y) / 20, 2));
            }
            res = compare(temp, res, 1);
        }
    }

    void upValidMove(boolean leftWall, boolean downWall, boolean rightWall) {
        if (!lastMove.equals("down") || downWall & rightWall & leftWall) {
            res = 0;
        }
    }

    int compare(double temp, int res, int i) {
        if (temp < distance) {
            distance = temp;
            return i;
        } else {
            return res;
        }
    }
    
    @Override
    public String getType() {
        return "b";
    }
}

