package game;

import java.awt.Point;

public class MoveBuilder {

    public static Point up(Point current) {
        return new Point((int) current.getX(), (int) current.getY() - 20);
    }

    public static Point down(Point current) {
        return new Point((int) current.getX(), (int) current.getY() + 20);
    }

    public static Point left(Point current) {
        return new Point((int) current.getX() - 20, (int) current.getY());
    }

    public static Point right(Point current) {
        return new Point((int) current.getX() + 20, (int) current.getY());
    }
}
