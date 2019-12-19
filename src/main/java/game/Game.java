package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;

import static game.Level.pellets;
import static game.Level.pixels;
import static game.Player.xPixelPlayer;
import static game.Player.yPixelPlayer;
import static game.Randy.coolDown;

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
    public static int pelletLeft = 0;
    private static int width = 0;
    private static int height = 0;
    private static boolean isRunning;
    private static boolean gameLost;
    private static double timeSinceLastMove = System.currentTimeMillis();


    static {
        isRunning = false;
        gameLost = false;
    }

    private transient Level level;
    private transient Player player;
    private transient Randy randy;
    private transient Thread thread;
    private transient GameSettings settings;


    /**
     * Game class.
     *
     * @param settings the settings to use.
     */
    public Game(GameSettings settings, String fileName) {
        this.settings = settings;
        URL path = ClassLoader.getSystemResource(fileName);
        File file = new File(path.getFile());

        Dimension dimension = this.calculateDimensions(file);
        this.setComponentDimensions(dimension);
        this.level = new Level(path, width, height, this.settings.getSquareSize());
        randy = level.randy;
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
    public void initFrame() {
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
    private synchronized void stop() {
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
        if (isRunning && (currentTime - timeSinceLastMove) >= coolDown) {
            if (!gameLost && randy != null && player != null && player.hasCollided(randy)) {
                gameLost = true;
                lose();
            }
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
        if(isRunning) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
                moveUpNow();
                System.out.println(player.getLocation());
            }

            if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
                moveLeftNow();
                System.out.println(player.getLocation());

            }

            if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
                moveDownNow();
                System.out.println(player.getLocation());
            }

            if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
                moveRightNow();
                System.out.println(player.getLocation());

            }
            if (!gameLost && randy != null && player != null && player.hasCollided(randy)) {
                gameLost = true;
                lose();
            }
        }
    }

    public void moveUp() {
        switch (pixels[player.getLocation().x / 20][player.getLocation().y / 20]) {
            case '#':
                player.movePlayer(MoveBuilder.DOWN(player.getLocation()));
                break;
            case '.':
                Pellet pel = null;
                pixels[player.getLocation().x / 20][player.getLocation().y / 20] = ' ';
                pellets[player.getLocation().x / 20][player.getLocation().y / 20] = pel;
                pelletLeft++;
                win();
                break;
            default:
                break;

        }
    }

    private void moveDown() {
        switch (pixels[player.getLocation().x / 20][player.getLocation().y / 20]) {
            case '#':
                player.movePlayer(MoveBuilder.UP(player.getLocation()));
                break;
            case '.':
                Pellet pel = null;
                pixels[player.getLocation().x / 20][player.getLocation().y / 20] = ' ';
                pellets[player.getLocation().x / 20][player.getLocation().y / 20] = pel;
                pelletLeft++;
                win();
                break;
            default:
                break;
        }
    }

    private void moveLeft() {
        switch (pixels[player.getLocation().x / 20][player.getLocation().y / 20]) {
            case '#':
                player.movePlayer(MoveBuilder.RIGHT(player.getLocation()));
                break;
            case '.':
                pixels[player.getLocation().x / 20][player.getLocation().y / 20] = ' ';
                Pellet pel = null;
                pellets[player.getLocation().x / 20][player.getLocation().y / 20] = pel;
                pelletLeft++;
                win();
                break;
            default:
                break;
        }
    }

    private void moveRight() {
        switch (pixels[player.getLocation().x / 20][player.getLocation().y / 20]) {
            case '#':
                player.movePlayer(MoveBuilder.LEFT(player.getLocation()));
                break;
            case '.':
                pixels[player.getLocation().x / 20][player.getLocation().y / 20] = ' ';
                Pellet pel = null;
                pellets[player.getLocation().x / 20][player.getLocation().y / 20] = pel;
                pelletLeft++;
                win();
                break;
            default:
                break;
        }
    }
    

    public void win() {
        if (pelletLeft == pelletCount) {
            JOptionPane.showMessageDialog(getParent(), "You Won", "Congrats", JOptionPane.DEFAULT_OPTION);
            stop();
        }
    }
    public void lose() {
        JOptionPane.showMessageDialog(getParent(), "You Lost", "Oops", JOptionPane.DEFAULT_OPTION);
        stop();
    }

    public void moveUpNow() {
        player = level.player;
        int n1 = 0;
        while (n1 < 200) {
            xPixelPlayer = 32;
            yPixelPlayer = 0;
            player.render(getGraphics());
            n1++;
        }
        xPixelPlayer = 16;
        yPixelPlayer = 0;
        if (player.getLocation().y != 0 && pixels[player.getLocation().x / 20][(player.getLocation().y - 20) / 20] == '#') {
            return;
        }
        if (player.getLocation().y == 0) {
            Point point = new Point(player.getLocation().x, getHeight() - 20);
            player.movePlayer(point);

            moveUp();
        } else {
            player.movePlayer(MoveBuilder.UP(player.getLocation()));
            moveUp();

        }
    }

    public void moveLeftNow() {
        player = level.player;
        int n1 = 0;
        while (n1 < 200) {
            xPixelPlayer = 32;
            yPixelPlayer = 48;
            player.render(getGraphics());
            n1++;
        }
        xPixelPlayer = 16;
        yPixelPlayer = 48;
        if (player.getLocation().x != 0 && pixels[(player.getLocation().x - 20) / 20][player.getLocation().y / 20] == '#') {
            return;
        }
        if (player.getLocation().x == 0) {
            Point point = new Point(getWidth() - 20, player.getLocation().y);
            player.movePlayer(point);
            moveLeft();
        } else {
            player.movePlayer(MoveBuilder.LEFT(player.getLocation()));
            moveLeft();

        }
    }

    public void moveDownNow() {
        player = level.player;
        int n1 = 0;
        while (n1 < 200) {
            xPixelPlayer = 32;
            yPixelPlayer = 32;
            player.render(getGraphics());
            n1++;
        }
        xPixelPlayer = 16;
        yPixelPlayer = 32;
        if (player.getLocation().y != getHeight() - 20 && pixels[player.getLocation().x / 20][(player.getLocation().y + 20) / 20] == '#') {
            return;
        }
        if (player.getLocation().y == getHeight() - 20) {
            Point point = new Point(player.getLocation().x, 0);
            player.movePlayer(point);
            moveDown();
        } else {
            player.movePlayer(MoveBuilder.DOWN(player.getLocation()));
            moveDown();
        }
    }

    public void moveRightNow() {
        player = level.player;
        int n1 = 0;
        while (n1 < 200) {
            xPixelPlayer = 32;
            yPixelPlayer = 16;
            player.render(getGraphics());
            n1++;
        }
        xPixelPlayer = 16;
        yPixelPlayer = 16;
        if (player.getLocation().x != getWidth() - 20 && pixels[(player.getLocation().x + 20) / 20][player.getLocation().y / 20] == '#') {
            return;
        }
        if (player.getLocation().x == getWidth() - 20) {
            Point point = new Point(0, player.getLocation().y);
            player.movePlayer(point);
            moveRight();
        } else {
            player.movePlayer(MoveBuilder.RIGHT(player.getLocation()));
            moveRight();
        }
    }
}
