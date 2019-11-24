package game;

public class GameSettings {
    transient int squareSize;

    public GameSettings(int squareSize) {
        this.squareSize = squareSize;
    }

    public int getSquareSize() {
        return squareSize;
    }
}
