package game;

public class GameSettings {
    transient int squareSize;
    transient String username;
    public GameSettings(int squareSize,String username) {
        this.username = username;
        this.squareSize = squareSize;
    }

    public int getSquareSize() {
        return squareSize;
    }
    public String getUsername() {
        return username;
    }
}
