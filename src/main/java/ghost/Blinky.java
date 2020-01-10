package ghost;

import game.Game;
import game.Player;
import game.SpriteSheet;
import game.Unit;

import java.awt.Graphics;

import java.awt.Point;
import java.util.List;

public class Blinky extends Ghost {
    public static final long serialVersionUID = 4328743;

    public Blinky(int x, int y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet);
    }

    @Override
    public void move() {

    }

    public void moveBlinky(Player player) {
        Unit target = findPlayer(player);
        if (target != null) {
            Point destination = target.getLocation();

            List<Point> path = shortestPath(getLocation(), destination, this);
            if (path != null && !path.isEmpty()) {
                for (int i = 0; i < path.size(); ) {
                    this.setLocation((int) path.get(0).getX(), (int) path.get(0).getX());
                    path.remove(0);
                }
            }
        }
    }
    @Override
    public String getType() {
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
