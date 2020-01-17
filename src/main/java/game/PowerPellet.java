package game;

import java.awt.*;

public class PowerPellet extends Unit {
    public static final long serialVersionUID = 4328743;
    public static final SpriteSheet galBoss = new SpriteSheet("/sprite/galboss.png");


    public PowerPellet(int x, int y) {
        setBounds(x, y, 20, 20);
    }


    public void render(Graphics g) {
        g.drawImage(galBoss.getSprite(0, 0), x, y, 18, 18, null);
    }

    @Override
    public String getType() {
        return "*";
}
}
