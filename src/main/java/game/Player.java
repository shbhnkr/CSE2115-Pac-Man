package game;

import java.awt.Graphics;
import java.awt.Point;

public class Player extends Unit {
    public static final long serialVersionUID = 4328743;


    public Player(int x, int y) {
        setBounds(x, y, 20, 20);
    }

    /**
     * renders the player on board.
     * @param g Graphics
     */
    public void render(Graphics g) {
        SpriteSheet sheet = Game.playerSprite;
        g.drawImage(sheet.getSprite(0,0),x,y,18,18, null);
        //g.setColor(Color.YELLOW);
        //g.fillRect(x, y, width, height);
    }

    public void movePlayer(Point movePosition) {
        this.setLocation((int) movePosition.getX(), (int) movePosition.getY());
    }

    public void movePlayer(int dx, int dy) {
        this.setLocation(this.x + dx, this.y + dy);

    }

    @Override
    String getType() {
        return "p";
    }
}
