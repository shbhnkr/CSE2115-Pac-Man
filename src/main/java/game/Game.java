package game;

import database.DBconnection;
import ghost.Ghost;
import ghost.Randy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static game.RenderLevel.pixels;
import static game.SpriteSheet.animation;

public class Game extends Canvas implements Runnable, KeyListener {

    public static final long serialVersionUID = 4328743;

    public static final String TITLE = "Pac-Man";
    public static final SpriteSheet pinkySprite = new SpriteSheet("/sprite/ghost_pink.png");
    public static final SpriteSheet inkySprite = new SpriteSheet("/sprite/ghost_cyan.png");
    public static final SpriteSheet blinkySprite = new SpriteSheet("/sprite/ghost_red.png");
    public static final SpriteSheet clydeSprite = new SpriteSheet("/sprite/ghost_orange.png");
    public static final SpriteSheet randySprite = new SpriteSheet("/sprite/ghost_green.png");
    static final SpriteSheet playerSprite = new SpriteSheet("/sprite/pacman.png");
    public static int pelletCount = 0;
    public static boolean isRunning;
    public static int point = 0;
    public static String playerDirection = "";
    private static int width = 0;
    private static int height = 0;
    private static int coolDown = 400;
    private static double timeSinceLastMove = System.currentTimeMillis();

    static {
        isRunning = false;
    }

    public transient int pelletEaten = 0;
    public transient Player player;
    private transient int key = Integer.MAX_VALUE;
    private transient boolean newKey = false;
    private transient Level level;
    private transient List<Ghost> ghosts;
    private transient Thread thread;
    private transient Gamesettings settings;
    private transient Connection conn;
    private transient ResultSet rs;

    /**
     * Game class.
     *
     * @param settings the settings to use.
     */
    public Game(Gamesettings settings, String filePath) {
        this.settings = settings;
        URL path = ClassLoader.getSystemResource(filePath);
        File file = new File(path.getFile());

        Dimension dimension = this.calculateDimensions(file);
        this.setComponentDimensions(dimension);
        this.level = new Level(path, width, height, this.settings.getSquareSize());
        ghosts = RenderLevel.ghosts;
        player = RenderLevel.player;
        conn = DBconnection.conn;
        addKeyListener(this);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    /**
     * Sets the canvas component sizes.
     *
     * @param dimension the dimension of which the components are going to be set.
     */
    private void setComponentDimensions(Dimension dimension) {
        setPreferredSize(dimension);
        setMinimumSize(dimension);
        setMaximumSize(dimension);
    }

    /**
     * Checks the given input file for max width and height.
     *
     * @param file the file to calculate dimensions of.
     * @return dimensions of file.
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    private Dimension calculateDimensions(File file) {
        Scanner sc;
        try {
            sc = new Scanner(file);
            int n = 0;
            while (sc.hasNextLine()) {
                width = this.settings.getSquareSize() * (sc.nextLine().length());
                n++;
            }
            height = this.settings.getSquareSize() * n;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return new Dimension(width, height);
    }

    /**
     * Start.
     */
    public synchronized void start() {
        if (isRunning) {
            return;
        }
        isRunning = true;
        thread = new Thread(this);
        thread.start();
        registerObservers();
        player.notifyObservers();
    }

    /**
     * Stop.
     */
    public synchronized void stop() {
        pelletCount = 0;
        pelletEaten = 0;
        if (!isRunning) {
            return;
        }
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Render the game board.
     */
    private void make() {
        BufferStrategy bufferStrategy = getBufferStrategy();
        if (bufferStrategy == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics graphics = bufferStrategy.getDrawGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        level.render(graphics);
        graphics.dispose();
        bufferStrategy.show();
        movePlayer();
        moveGhosts();
    }

    @Override
    public void run() {
        while (isRunning) {
            make();
        }
        stop();
    }

    @SuppressWarnings("PMD")
    private void registerObservers() {
        if (player != null) {
            for (Ghost ghost : ghosts) {
                if (ghost.getType().equals(Types.blinkyType())) {
                    player.registerObserver(ghost);
                } else if (ghost.getType().equals(Types.pinkyType())) {
                    player.registerObserver(ghost);
                } else if (ghost.getType().equals(Types.inkyType())) {
                    player.registerObserver(ghost);
                    if (RenderLevel.blinky != null) {
                        RenderLevel.blinky.registerObserver(ghost);
                    }
                } else if (ghost.getType().equals(Types.clydeType())) {
                    player.registerObserver(ghost);
                } else {
                    return;
                }
            }
        }
    }

    @SuppressWarnings("PMD")
    private void movePlayer() {
        double currentTime = System.currentTimeMillis();
        if (currentTime - timeSinceLastMove >= coolDown >> 1) {
            win();
            lose();
        }
        if (player.drunk) {
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            player.drunk = false;
                        }
                    },
                    15000
            );
        }
        boolean upWall = upWall();

        boolean leftWall = leftWall();

        boolean downWall = downWall();

        boolean rightWall = rightWall();

        if (key < Integer.MAX_VALUE && (currentTime - timeSinceLastMove) >= coolDown) {
            if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
                playerDrunkUp(upWall, downWall);
            }
            if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {

                playerDrunkLeft(leftWall, rightWall);
            }
            if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
                playerDrunkDown(upWall, downWall);
            }
            if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
                playerDrunkRight(leftWall, rightWall);
            }
            switchDirection();
        }
    }

    private void switchDirection() {
        switch (playerDirection) {
            case "up":
                upKey();
                break;
            case "left":
                leftKey();
                break;
            case "down":
                downKey();
                break;
            case "right":
                rightKey();
                break;
            default:
                break;
        }
    }

    private void playerDrunkRight(boolean leftWall, boolean rightWall) {
        if (player.drunk) {
            if (!leftWall || !newKey || player.power) {
                playerDirection = "left";
            }
        } else {
            if (!rightWall || !newKey || player.power) {
                playerDirection = "right";
            }
        }
    }

    private void playerDrunkDown(boolean upWall, boolean downWall) {
        if (player.drunk) {
            if (!upWall || !newKey || player.power) {
                playerDirection = "up";
            }
        } else {
            if (!downWall || !newKey || player.power) {
                playerDirection = "down";
            }
        }
    }

    private void playerDrunkLeft(boolean leftWall, boolean rightWall) {
        if (player.drunk) {
            if (!rightWall || !newKey || player.power) {
                playerDirection = "right";
            }
        } else {
            if (!leftWall || !newKey || player.power) {
                playerDirection = "left";
            }
        }
    }

    private void playerDrunkUp(boolean upWall, boolean downWall) {
        if (player.drunk) {
            if (!downWall || !newKey || player.power) {
                playerDirection = "down";
            }
        } else {
            if (!upWall || !newKey || player.power) {
                playerDirection = "up";
            }
        }
    }

    private boolean rightWall() {
        boolean rightWall;
        if (player.getLocation().x == width - 20) {
            rightWall = pixels[0][player.getLocation().y / 20] == '#';
        } else {
            rightWall = pixels[(player.getLocation().x + 20) / 20][player.getLocation().y / 20]
                    == '#';
        }
        return rightWall;
    }

    private boolean downWall() {
        boolean downWall;
        if (player.getLocation().y == height - 20) {
            downWall = pixels[player.getLocation().x / 20][0] == '#';
        } else {
            downWall = pixels[player.getLocation().x / 20][(player.getLocation().y + 20) / 20]
                    == '#';
        }
        return downWall;
    }

    private boolean leftWall() {
        boolean leftWall;
        if (player.getLocation().x == 0) {
            leftWall = pixels[(width - 20) / 20][player.getLocation().y / 20] == '#';
        } else {
            leftWall = pixels[(player.getLocation().x - 20) / 20][player.getLocation().y / 20]
                    == '#';
        }
        return leftWall;
    }

    private boolean upWall() {
        boolean upWall;
        if (player.getLocation().y == 0) {
            upWall = pixels[player.getLocation().x / 20][(height - 20) / 20] == '#';
        } else {
            upWall = pixels[player.getLocation().x / 20][(player.getLocation().y - 20) / 20]
                    == '#';
        }
        return upWall;
    }

    @SuppressWarnings("PMD")
    private void moveGhosts() {
        if (isRunning) {
            double currentTime = System.currentTimeMillis();
            if ((currentTime - timeSinceLastMove) >= coolDown) {
                for (Ghost ghost : ghosts) {
                    if (ghost instanceof Randy) {
                        ghost.setRandom(4, 0);
                    }
                    ghost.moveGhost(getHeight(), getWidth());
                }
                timeSinceLastMove = currentTime;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }


    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_P) {
            if (isRunning && (coolDown < 600)) {
                coolDown = 9999999;
            } else if (isRunning && (coolDown > 9999995)) {
                coolDown = 400;
            }
        }

        if (player.drunk) {
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            player.drunk = false;
                        }
                    },
                    15000
            );
        }
        if (player.power) {
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            player.power = false;
                        }
                    },
                    5000
            );
        }
        if (isRunning) {
            newKey = key != e.getKeyCode();
            key = e.getKeyCode();
        }
    }

    private void upKey() {
        animation(32, 0, player, getGraphics());
        player.moveUp(this, getHeight());

    }

    private void leftKey() {
        animation(32, 48, player, getGraphics());
        player.moveLeft(this, getWidth());

    }

    private void downKey() {
        animation(32, 32, player, getGraphics());
        player.moveDown(this, getHeight());

    }

    private void rightKey() {
        animation(32, 16, player, getGraphics());
        player.moveRight(this, getWidth());

    }

    void win() {
        if (pelletEaten == pelletCount) {
            coolDown = 999999;
            if (isRunning) {
                JOptionPane.showMessageDialog(getParent(), "You Won" + "\n" + " Your Score is : "
                        + point, "Congrats", JOptionPane.DEFAULT_OPTION);
                setScore(settings.username, point);
                stop();

            }
        }
    }

    @SuppressWarnings("PMD")
    void lose() {
        for (Ghost ghost : ghosts) {
            if (player.hasCollided(ghost)) {
                coolDown = 999999;
                if (isRunning) {
                    JOptionPane.showMessageDialog(getParent(), "You Lost" + "\n" + "Your Score is: "
                            + point, "Oops", JOptionPane.DEFAULT_OPTION);
                }
                setScore(settings.username, point);

                stop();
                break;
            }
        }
    }

    /**
     * setting the score.
     *
     * @param username the username of the current player.
     * @param score    the score of the player after the game ends.
     */
    public void setScore(String username, int score) {
        //check query
        String query = "INSERT INTO `ScoreBoard`(`username`, `score`) VALUES (?, ?)";
        try {
            //connecting to DataBase
            conn = DBconnection.getConnection();

            //preparing and executing query
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username + "");
            ps.setInt(2, score);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        stop();
    }
}