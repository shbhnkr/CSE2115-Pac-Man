package game;

public class Gamesettings {
    transient int squareSize;
    transient String username;

    public Gamesettings(int squareSize,String username) {
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
