package game;

public class Gamesettings {
    transient int squareSize;
    transient String username;
    transient boolean showWinPopUp = true;

    /**
     * constructor method for game settings.
     *
     * @param squareSize the size of each square in pixels.
     * @param username   the username of the current player.
     */
    public Gamesettings(int squareSize, String username) {
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
