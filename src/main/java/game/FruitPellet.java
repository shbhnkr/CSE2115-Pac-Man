package game;

import java.awt.*;
import java.util.Random;

public class FruitPellet extends Unit {

    public static final long serialVersionUID = 4328743;
    public static final SpriteSheet apple = new SpriteSheet("/sprite/apple.png");
    public static final SpriteSheet cherry = new SpriteSheet("/sprite/cherry.png");
    public static final SpriteSheet strawberry = new SpriteSheet("/sprite/strawberry.png");
    public transient int num;
    public transient SpriteSheet fin;
    public transient SpriteSheet[] fruitArray = new SpriteSheet[3];


    public FruitPellet(int x, int y) {
        setBounds(x, y, 20, 20);

        fruitArray[0] = apple;
        fruitArray[1] = cherry;
        fruitArray[2] = strawberry;

        Random rand = new Random();
        int i = rand.nextInt(3);
        num = i;
        fin = fruitArray[i];

    }

    public int points() {
        if (num == 0) {
            return 500;
        }
        int n = 1;
        if (num == n) {
            return 100;
        }
        int m = 2;
        if (num == m) {
            return 300;
        }
        return 0;
    }

    public void render(Graphics g) {
        g.drawImage(fin.getSprite(0, 0), x, y, 18, 18, null);
    }

    @Override
    protected String getType() {
        return ",";
    }

}
