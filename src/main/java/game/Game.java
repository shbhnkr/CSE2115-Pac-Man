package game;

import database.DBconnection;
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
import java.util.Random;
import java.util.Scanner;

import static game.Level.*;
import static game.Player.xPixelPlayer;
import static game.Player.yPixelPlayer;
import static game.SpriteSheet.animation;
import static ghost.Randy.coolDown;

public class Game extends Canvas implements Runnable, KeyListener {

    public static final long serialVersionUID = 4328743;
    public static final String TITLE = "Pac-Man";
    public static final SpriteSheet playerSprite = new SpriteSheet("/sprite/pacman.png");
    public static final SpriteSheet pinkySprite = new SpriteSheet("/sprite/ghost_pink.png");
    public static final SpriteSheet inkySprite = new SpriteSheet("/sprite/ghost_cyan.png");
    public static final SpriteSheet blinkySprite = new SpriteSheet("/sprite/ghost_red.png");
    public static final SpriteSheet clydeSprite = new SpriteSheet("/sprite/ghost_orange.png");
    public static final SpriteSheet randySprite = new SpriteSheet("/sprite/ghost_green.png");
    public static int pelletCount = 0;
    public static int pelletEaten = 0;
    private static int width = 0;
    private static int height = 0;
    private transient Connection conn = null;
    private transient ResultSet rs = null;
    private static boolean isRunning;
    private static double timeSinceLastMove = System.currentTimeMillis();
    private transient int point = 0;


    static {
        isRunning = false;
    }

    public transient Level level;
    public transient Player player;
    private transient Randy randy;
    private transient Thread thread;
    private transient GameSettings settings;

    /**
     * Game class.
     *
     * @param settings the settings to use.
     */
    public Game(GameSettings settings, String filePath) {
        this.settings = settings;
        URL path = ClassLoader.getSystemResource(filePath);
        File file = new File(path.getFile());

        Dimension dimension = this.calculateDimensions(file);
        this.setComponentDimensions(dimension);
        this.level = new Level(path, width, height, this.settings.getSquareSize());
        randy = level.randy;
        player = level.player;
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
     * Initializes a Jframe class, specifies settings and binds it to the current Game / Component.
     */
    private void initFrame() {
        JFrame frame = new JFrame();
        frame.setTitle(Game.TITLE);
        frame.add(this);
        frame.setResizable(false);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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
        if (randy != null) {
            Random rand = new Random();
            int random = rand.nextInt(4);
            moveRandy(random);
        }
    }

    @Override
    public void run() {
        while (isRunning) {
            make();
        }
        stop();
    }

    public void moveRandy(int random) {
        double currentTime = System.currentTimeMillis();
        if ((currentTime - timeSinceLastMove) >= coolDown) {
            switch (random) {
                case 0:
                    if (randy.getLocation().y == 0) {
                        Point point = new Point(randy.getLocation().x, getHeight() - 20);
                        randy.moveGhost(point);
                    }
                    randy.moveUpGhost();
                    break;
                case 1:
                    if (randy.getLocation().y == getHeight() - 20) {
                        Point point = new Point(randy.getLocation().x, 0);
                        randy.moveGhost(point);
                    }
                    randy.moveDownGhost();
                    break;
                case 2:
                    if (randy.getLocation().x == 0) {
                        Point point = new Point(getWidth() - 20, randy.getLocation().y);
                        randy.moveGhost(point);
                    }
                    randy.moveLeftGhost();
                    break;
                default:
                    if (randy.getLocation().x == getWidth() - 20) {
                        Point point = new Point(0, randy.getLocation().y);
                        randy.moveGhost(point);
                    }
                    randy.moveRightGhost();
                    break;
            }
            timeSinceLastMove = currentTime;
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
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            animation(32, 0, player, getGraphics());
            moveUp();
        }

        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            animation(32, 48, player, getGraphics());
            moveLeft();
        }

        if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            animation(32, 32, player, getGraphics());
            moveDown();

        }

        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            animation(32, 16, player, getGraphics());
            moveRight();

        }
    }

    public void moveUp() {
        xPixelPlayer = 16;
        yPixelPlayer = 0;
        if (player.getLocation().y != 0 && pixels[player.getLocation().x / 20][(player.getLocation().y - 20) / 20] == '#') {
            return;
        }
        if (player.getLocation().y == 0) {
            Point point = new Point(player.getLocation().x, getHeight() - 20);
            player.movePlayer(point);

            objectCheckerUp();
        } else {
            player.movePlayer(MoveBuilder.UP(player.getLocation()));
            objectCheckerUp();

        }

    }

    private void objectCheckerUp() {
        switch (pixels[player.getLocation().x / 20][player.getLocation().y / 20]) {
            case '#':
                player.movePlayer(new Point(player.getLocation().x, 0));
                break;
            case '.':
                Pellet pel = null;
                pixels[player.getLocation().x / 20][player.getLocation().y / 20] = ' ';
                pellets[player.getLocation().x / 20][player.getLocation().y / 20] = pel;
                pelletEaten++;
                point += 10;
                win();
                break;
            case ',':
                FruitPellet fpel = null;
                point += fruitPellet[player.getLocation().x / 20][player.getLocation().y / 20].points();
                pixels[player.getLocation().x / 20][player.getLocation().y / 20] = ' ';
                fruitPellet[player.getLocation().x / 20][player.getLocation().y / 20] = fpel;
                pelletEaten++;
                win();
                break;


            default:
                break;

        }
    }

    public void moveDown() {
        xPixelPlayer = 16;
        yPixelPlayer = 32;
        if (player.getLocation().y != getHeight() - 20 && pixels[player.getLocation().x / 20][(player.getLocation().y + 20) / 20] == '#') {
            return;
        }
        if (player.getLocation().y == getHeight() - 20) {
            Point point = new Point(player.getLocation().x, 0);
            player.movePlayer(point);
            objectCheckerDown();
        } else {
            player.movePlayer(MoveBuilder.DOWN(player.getLocation()));
            objectCheckerDown();
        }

    }

    private void objectCheckerDown() {
        switch (pixels[player.getLocation().x / 20][player.getLocation().y / 20]) {
            case '#':
                player.movePlayer(new Point(player.getLocation().x, getHeight() - 20));
                break;
            case '.':
                Pellet pel = null;
                pixels[player.getLocation().x / 20][player.getLocation().y / 20] = ' ';
                pellets[player.getLocation().x / 20][player.getLocation().y / 20] = pel;
                pelletEaten++;
                point += 10;
                win();
                break;
            case ',':
                FruitPellet fpel = null;
                point += fruitPellet[player.getLocation().x / 20][player.getLocation().y / 20].points();
                pixels[player.getLocation().x / 20][player.getLocation().y / 20] = ' ';
                fruitPellet[player.getLocation().x / 20][player.getLocation().y / 20] = fpel;
                pelletEaten++;
                win();
                break;
            default:
                break;
        }
    }

    public void moveLeft() {
        xPixelPlayer = 16;
        yPixelPlayer = 48;
        if (player.getLocation().x != 0 && pixels[(player.getLocation().x - 20) / 20][player.getLocation().y / 20] == '#') {
            return;
        }
        if (player.getLocation().x == 0) {
            Point point = new Point(getWidth() - 20, player.getLocation().y);
            player.movePlayer(point);
            objectCheckerLeft();
        } else {
            player.movePlayer(MoveBuilder.LEFT(player.getLocation()));
            objectCheckerLeft();

        }
    }

    private void objectCheckerLeft() {
        switch (pixels[player.getLocation().x / 20][player.getLocation().y / 20]) {
            case '#':
                player.movePlayer(new Point(0, player.getLocation().y));
                break;
            case '.':
                pixels[player.getLocation().x / 20][player.getLocation().y / 20] = ' ';
                Pellet pel = null;
                pellets[player.getLocation().x / 20][player.getLocation().y / 20] = pel;
                pelletEaten++;
                point += 10;
                win();
                break;
            case ',':
                FruitPellet fpel = null;
                point += fruitPellet[player.getLocation().x / 20][player.getLocation().y / 20].points();
                pixels[player.getLocation().x / 20][player.getLocation().y / 20] = ' ';
                fruitPellet[player.getLocation().x / 20][player.getLocation().y / 20] = fpel;
                pelletEaten++;
                win();
                break;
            default:
                break;
        }
    }

    public void moveRight() {
        xPixelPlayer = 16;
        yPixelPlayer = 16;
        if (player.getLocation().x != getWidth() - 20 && pixels[(player.getLocation().x + 20) / 20][player.getLocation().y / 20] == '#') {
            return;
        }
        if (player.getLocation().x == getWidth() - 20) {
            Point point = new Point(0, player.getLocation().y);
            player.movePlayer(point);
            objectCheckerRight();
        } else {
            player.movePlayer(MoveBuilder.RIGHT(player.getLocation()));
            objectCheckerRight();
        }

    }

    private void objectCheckerRight() {
        switch (pixels[player.getLocation().x / 20][player.getLocation().y / 20]) {
            case '#':
                player.movePlayer(new Point(getWidth() - 20, player.getLocation().y));
                break;
            case '.':
                pixels[player.getLocation().x / 20][player.getLocation().y / 20] = ' ';
                Pellet pel = null;
                pellets[player.getLocation().x / 20][player.getLocation().y / 20] = pel;
                pelletEaten++;
                point += 10;
                win();
                break;
            case ',':
                FruitPellet fpel = null;
                point += fruitPellet[player.getLocation().x / 20][player.getLocation().y / 20].points();
                pixels[player.getLocation().x / 20][player.getLocation().y / 20] = ' ';
                fruitPellet[player.getLocation().x / 20][player.getLocation().y / 20] = fpel;
                pelletEaten++;
                win();
                break;
            default:
                break;
        }
    }


    public void win() {
        if (pelletEaten == pelletCount) {
            if (isRunning) {
                JOptionPane.showMessageDialog(getParent(), "You Won" + "\n" + " Your Score is : " + point, "Congrats", JOptionPane.DEFAULT_OPTION);
                String uname = settings.username;
                ;
                int score = point;


                String query = "INSERT INTO `ScoreBoard`(`username`, `score`) VALUES (?, ?)";

                try {
                    conn = DBconnection.getConnection();
                    PreparedStatement ps = conn.prepareStatement(query);
                    ps.setString(1, uname);
                    ps.setInt(2, score);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Welcome " + uname);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid password/username");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                stop();
            }
        }
    }
}
