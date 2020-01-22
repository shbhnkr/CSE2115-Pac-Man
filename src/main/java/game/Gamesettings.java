package game;

public class Gamesettings {
    transient int squareSize;
    transient String username;

    /**
     * constructor method for game settings.
     *
     * @param squareSize the size of each square in pixels.
     * @param username   the username of the current player.
     */
    public Gamesettings(int squareSize, String username) {
        this.username = username;
        this.squareSize = squareSize;
        System.out.println(username);
    }

    public int getSquareSize() {
        return squareSize;
    }

    public String getUsername() {
        return username;
    }
}
