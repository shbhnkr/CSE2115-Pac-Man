package ghost;

import game.SpriteSheet;

public class Pinky extends Ghost {
    public static final long serialVersionUID = 4328743;

    public Pinky(int x, int y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet);
    }

    @Override
    public void moveGhost(int height, int width) {

    }

    @Override
    public String getType() {
        return "g";
    }
}
