package game;

import java.awt.Rectangle;

public abstract class Unit extends Rectangle {
    public static final long serialVersionUID = 42;
    protected abstract String getType();
}
