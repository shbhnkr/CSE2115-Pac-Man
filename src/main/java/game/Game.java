package game;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;
import javax.swing.*;

import static game.Level.pellets;
import static game.Level.pixels;
import static game.Player.*;

//import java.util.List;


public class Game extends Canvas implements Runnable, KeyListener {

    public static final long serialVersionUID = 4328743;
    public static final String TITLE = "Pac-Man";
    public static final SpriteSheet playerSprite = new SpriteSheet("/sprite/pacman.png");
    public static final SpriteSheet pinkySprite = new SpriteSheet("/sprite/ghost_pink.png");
    public static final SpriteSheet inkySprite = new SpriteSheet("/sprite/ghost_cyan.png");
    public static final SpriteSheet blinkySprite = new SpriteSheet("/sprite/ghost_red.png");
    public static final SpriteSheet clydeSprite = new SpriteSheet("/sprite/ghost_orange.png");
    private static int width = 0;

    //private static List<Unit> units;
    private static int height = 0;
    private static boolean isRunning;

    static {
        isRunning = false;
    }

    private transient Level level;
    private transient Player player;
    private transient Thread thread;
    private transient GameSettings settings;

    /**
     * Game class.
     *
     * @param settings the settings to use.
     */
    public Game(GameSettings settings) {
        this.settings = settings;
        URL path = ClassLoader.getSystemResource("board.txt");
        File file = new File(path.getFile());

        Dimension dimension = this.calculateDimensions(file);
        this.setComponentDimensions(dimension);
        this.level = new Level(path, width, height, this.settings.getSquareSize());
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
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        level.render(graphics);
        graphics.dispose();
        bufferStrategy.show();
    }

    @Override
    public void run() {
        while (isRunning) {
            make();
        }
        stop();
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
        System.out.println(e.getKeyCode());

        player = level.player;
        if (key == KeyEvent.VK_W) {
//            player.movePlayer(0, -1);
        xPixel = 16;
        yPixel = 0;
//            if (validMove(MoveBuilder.UP(player.getLocation()))) {
            player.movePlayer(MoveBuilder.UP(player.getLocation()));
//            }
            switch (pixels[player.getLocation().x / 20][player.getLocation().y / 20]) {
                case '#':
                    player.movePlayer(MoveBuilder.DOWN(player.getLocation()));
                    break;
                case '.':
                    Pellet pel = null;
                    pellets[player.getLocation().x / 20][player.getLocation().y / 20] = pel;
                    break;
                default:
                    break;
            }

            System.out.println("north");
        }

        if (key == KeyEvent.VK_A) {
//            player.movePlayer(-1, 0);
            xPixel = 16;
            yPixel = 48;
//            if (validMove(MoveBuilder.LEFT(player.getLocation()))) {
            player.movePlayer(MoveBuilder.LEFT(player.getLocation()));
//            }
            switch (pixels[player.getLocation().x / 20][player.getLocation().y / 20]) {
                case '#':
                    player.movePlayer(MoveBuilder.RIGHT(player.getLocation()));
                    break;
                case '.':
                    Pellet pel = null;
                    pellets[player.getLocation().x / 20][player.getLocation().y / 20] = pel;
                    break;
                default:
                    break;
            }
            System.out.println("west");
        }

        if (key == KeyEvent.VK_S) {
//            player.movePlayer(0, 1);
        xPixel = 16;
        yPixel = 32;
//            if (validMove(MoveBuilder.DOWN(player.getLocation()))) {
            player.movePlayer(MoveBuilder.DOWN(player.getLocation()));
//            }
            switch (pixels[player.getLocation().x / 20][player.getLocation().y / 20]) {
                case '#':
                    player.movePlayer(MoveBuilder.UP(player.getLocation()));
                    break;
                case '.':
                    Pellet pel = null;
                    pellets[player.getLocation().x / 20][player.getLocation().y / 20] = pel;
                    break;
                default:
                    break;
            }

            System.out.println("south");
        }

        if (key == KeyEvent.VK_D) {
            xPixel = 16;
            yPixel = 16;
//            player.movePlayer(1, 0);
//            if (validMove(MoveBuilder.RIGHT(player.getLocation()))) {
            player.movePlayer(MoveBuilder.RIGHT(player.getLocation()));
//            }
            switch (pixels[player.getLocation().x / 20][player.getLocation().y / 20]) {
                case '#':
                    player.movePlayer(MoveBuilder.LEFT(player.getLocation()));
                    break;
                case '.':
                    Pellet pel = null;
                    pellets[player.getLocation().x / 20][player.getLocation().y / 20] = pel;
                    break;
                default:
                    break;
            }

            System.out.println("east");

        }
    }


//    private boolean validMove(Point position) {
//        if (this.units !=null) {
//            for (Unit u : units) {
//                if (u != null && u.getType().equals("#") &&  u.getLocation().equals(position)) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
}
