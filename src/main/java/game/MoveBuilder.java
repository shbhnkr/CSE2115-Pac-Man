package game;

import java.awt.Point;

public class MoveBuilder {

    public static Point UP(Point current) {
        return new Point((int) current.getX(), (int) current.getY() - 20);
    }

    public static Point DOWN(Point current) {
        return new Point((int) current.getX(), (int) current.getY() + 20);
    }

    public static Point LEFT(Point current) {
        return new Point((int) current.getX() - 20, (int) current.getY());
    }

    public static Point RIGHT(Point current) {
        return new Point((int) current.getX() + 20, (int) current.getY());
    }
}
