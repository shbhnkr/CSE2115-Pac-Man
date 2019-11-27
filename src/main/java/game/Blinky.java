package game;

import java.awt.Color;

import java.awt.Graphics;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Blinky extends Unit {
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

    public void moveBlinky(Player player) {
        //TODO
        Unit target = findPlayer(player);
        if (target == null) {
            return;
        }
        Point destination = target.getLocation();

        List<Point> path = shortestPath(getLocation(), destination, this);
        if (path != null && !path.isEmpty()) {
            this.setLocation((int) path.get(0).getX(), (int) path.get(0).getX());
            path.remove(0);
        }
    }
    @Override
    String getType() {
        return "b";
    }

    public Unit findPlayer(Player player) {
        //TODO
        return null;
    }

    public List<Point> shortestPath(Point location, Point target, Unit traveller) {
        //TODO
        return null;
    }
}
