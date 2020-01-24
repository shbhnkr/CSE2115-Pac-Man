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
     * @param x x position of ghost.
     * @param y y position of ghost.
     * @param spriteSheet the spritesheet to use.
     */
    Blinky(int x, int y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet);
    }

    @Override
    public void moveGhost(int height, int width) {
        if (unitLocations.isEmpty()) {
            System.out.println("no destination for Blinky!");
            return;
        }

        Point destination = this.unitLocations.get(Types.playerType());
        int res = CommonAIOperations.getRes(this, lastMove, destination, height, width);
        System.out.println(res + "debugggg");
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

