package ghost;

import game.SpriteSheet;

/**
 * ghost 3.
 */
public class Inky extends Ghost {
    public static final long serialVersionUID = 4328743;

    /**
     * ghost constructor 3.
     * @param x x position of ghost.
     * @param y y position of ghost.
     * @param spriteSheet the spritesheet to use.
     */
    Inky(int x, int y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet);
    }

    @Override
    public void moveGhost(int height, int width) {

    }

    @Override
    public String getType() {
        return "i";
    }
}
