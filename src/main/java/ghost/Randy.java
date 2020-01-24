package ghost;

import game.SpriteSheet;

/**
 * ghost 5.
 */
public class Randy extends Ghost {
    public static final long serialVersionUID = 4328743;
    static int random = -1;

    /**
     * ghost constructor 5.
     *
     * @param x           x position of ghost.
     * @param y           y position of ghost.
     * @param spriteSheet the spritesheet to use.
     */
    Randy(int x, int y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet);
    }

    @Override
    public void moveGhost(int height, int width) {
        switch (random) {
            case 0:
                this.moveUpGhost(height);
                break;
            case 1:
                this.moveLeftGhost(width);
                break;
            case 2:
                this.moveDownGhost(height);
                break;
            case 3:
                this.moveRightGhost(width);
                break;
            default:
                break;
        }
    }

    @Override
    public String getType() {
        return "r";
    }
}
