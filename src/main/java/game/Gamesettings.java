package game;

public class Gamesettings {
    transient int squareSize;

    public Gamesettings(int squareSize) {
        this.squareSize = squareSize;
    }

    public int getSquareSize() {
        return squareSize;
    }
}
