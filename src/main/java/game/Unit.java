package game;

import java.awt.Rectangle;

abstract class Unit extends Rectangle {
    public static final long serialVersionUID = 42;
    abstract String getType();
}
