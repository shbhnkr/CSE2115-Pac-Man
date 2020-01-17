package game;

import java.awt.*;

public class Beer extends Unit {

    public static final long serialVersionUID = 4328743;
    public static final SpriteSheet heineken = new SpriteSheet("/sprite/beer.png");


    public Beer(int x, int y) {
        setBounds(x, y, 20, 20);
    }


    public void render(Graphics g) {
        g.drawImage(heineken.getSprite(0, 0), x, y, 18, 18, null);
    }

    @Override
    public String getType() {
        return "h";
    }

}
