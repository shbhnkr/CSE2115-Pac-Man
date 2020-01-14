package ghost;

import game.SpriteSheet;
import game.Types;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Blinky extends Ghost {
/**
 * ghost 1.
 */
    public static final long serialVersionUID = 4328743;

    /**
     * ghost constructor 1.
     */
    public Blinky(int x, int y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet);
    }

    @Override
    public void moveGhost(int height, int width) {
        Point destination = this.unitLocations.get(Types.playerType());
        if (unitLocations.isEmpty() || destination == null) {
            throw new IllegalArgumentException();
        }
        List<Point> path = shortestPath(getLocation(), destination);
        if (path != null && !path.isEmpty()) {
            for (int i = 0; i < path.size(); ) {
                path.remove(0);
            }
        }
    }

    @Override
    public String getType() {
        return "b";
    }

    private List<Point> shortestPath(Point location, Point target) {
        if (location.equals(target)) {
            return new ArrayList<>();
        }
        List<Point> queue = new ArrayList<>();
        return null;
    }
}
