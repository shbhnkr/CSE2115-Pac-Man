package game;

import java.awt.Color;

import java.awt.Graphics;

import java.awt.Rectangle;

public class Blinky extends Rectangle {
    public static final long serialVersionUID = 4328743;

    public Blinky(int x, int y) {
        setBounds(x, y, 20, 20);
    }

    public void render(Graphics g) {
        SpriteSheet sheet = Game.blinkySprite;
        g.drawImage(sheet.getSprite(0,0),x,y,18,18, null);
        //g.setColor(Color.RED);
        //g.fillRect(x, y, width, height);
    }

}
