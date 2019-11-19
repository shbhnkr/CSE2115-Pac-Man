package game;

import java.awt.*;

public class MoveBuilder {

    static Point UP(Point current) {
        return new Point( (int ) current.getX(), (int) current.getY() - 20);
    }

    static Point DOWN(Point current) {
        return new Point( (int ) current.getX(), (int) current.getY() + 20);
    }

    static Point LEFT(Point current) {
        return new Point( (int ) current.getX() - 20, (int) current.getY());
    }

    static Point RIGHT(Point current) {
        return new Point( (int ) current.getX() + 20, (int) current.getY());
    }
}
