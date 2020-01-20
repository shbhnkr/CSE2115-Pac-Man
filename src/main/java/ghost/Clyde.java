package ghost;

import game.SpriteSheet;
/**
 * ghost 2.
 */
public class Clyde extends Ghost {
    public static final long serialVersionUID = 4328743;

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
