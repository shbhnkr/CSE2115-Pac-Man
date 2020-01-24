package ghost;

import game.SpriteSheet;

/**
 * ghost 4.
 */
public class Clyde extends Ghost {
    public static final long serialVersionUID = 4328743;

    /**
     * ghost constructor 4.
     * @param x x position of ghost.
     * @param y y position of ghost.
     * @param spriteSheet the spritesheet to use.
     */
    Clyde(int x, int y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet);
    }

    @Override
    public void moveGhost(int height, int width) {

    }

    @Override
    public String getType() {
        return "c";
    }
}
