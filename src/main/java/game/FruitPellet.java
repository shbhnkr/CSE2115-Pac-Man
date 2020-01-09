package game;

import java.awt.*;
import java.util.Random;

public class FruitPellet extends Unit {

    public static final long serialVersionUID = 4328743;
    public static final SpriteSheet apple = new SpriteSheet("/sprite/apple.png");
    public static final SpriteSheet cherry = new SpriteSheet("/sprite/cherry.png");
    public static final SpriteSheet strawberry = new SpriteSheet("/sprite/strawberry.png");
    public static final SpriteSheet melon = new SpriteSheet("/sprite/melon.png");
    public static final SpriteSheet orange = new SpriteSheet("/sprite/orange.png");
    public transient SpriteSheet fin;
    public transient SpriteSheet[] fruitArray = new SpriteSheet[5];


    public FruitPellet(int x, int y) {
        setBounds(x, y, 20, 20);

        fruitArray[0] = apple;
        fruitArray[1] = cherry;
        fruitArray[2] = strawberry;
        fruitArray[3] = melon;
        fruitArray[4] = orange;
        Random rand = new Random();
        int i = rand.nextInt(5);
        fin = fruitArray[i];

    }

    public void render(Graphics g) {
        g.drawImage(fin.getSprite(0, 0), x, y, 18, 18, null);
    }

    @Override
    protected String getType() {
        return ",";
    }

}
