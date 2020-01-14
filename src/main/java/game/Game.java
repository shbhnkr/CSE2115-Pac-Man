package game;

import ghost.Ghost;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

import static game.SpriteSheet.animation;

public class Game extends Canvas implements Runnable, KeyListener {

    public static final long serialVersionUID = 4328743;
    public static final String TITLE = "Pac-Man";
    public static final SpriteSheet playerSprite = new  SpriteSheet("/sprite/pacman.png");
    public static final SpriteSheet pinkySprite = new SpriteSheet("/sprite/ghost_pink.png");
    public static final SpriteSheet inkySprite = new SpriteSheet("/sprite/ghost_cyan.png");
    public static final SpriteSheet blinkySprite = new SpriteSheet("/sprite/ghost_red.png");
    public static final SpriteSheet clydeSprite = new SpriteSheet("/sprite/ghost_orange.png");
    public static final SpriteSheet randySprite = new SpriteSheet("/sprite/ghost_green.png");
    public static int pelletCount = 0;
    public static int pelletEaten = 0;
    private static int width = 0;
    private static int height = 0;
    private static boolean isRunning;
    private static int coolDown = 300;
    private static double timeSinceLastMove = System.currentTimeMillis();
    public transient int point = 0;

    static {
        isRunning = false;
    }

    public transient Level level;
    public transient Player player;
    private transient Ghost randy;
    private transient Ghost blinky;
    private transient Thread thread;
    private transient Gamesettings settings;

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
        randy = level.randy;
        blinky = level.blinky;
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
        moveGhosts();
    }

    @Override
    public void run() {
        while (isRunning) {
            make();
        }
        stop();
    }


    public void registerObservers() {
        player.registerObserver(blinky);
    }

    public void moveGhosts() {
        if(isRunning) {
            double currentTime = System.currentTimeMillis();
            if ((currentTime - timeSinceLastMove) >= coolDown) {
                lose();
                if (randy != null) {
                    randy.moveGhost(getHeight(), getWidth());
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
        if (isRunning) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
                animation(32, 0, player, getGraphics());
                player.moveUp(this, getHeight());
                lose();
            }

            if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
                animation(32, 48, player, getGraphics());
                player.moveLeft(this, getWidth());
                lose();
            }

            if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
                animation(32, 32, player, getGraphics());
                player.moveDown(this, getHeight());
                lose();
            }

            if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
                animation(32, 16, player, getGraphics());
                player.moveRight(this, getWidth());
                lose();
            }
        }
    }

    public void win() {
        if (pelletEaten == pelletCount) {
            coolDown = 999999;
            if (isRunning) {
                JOptionPane.showMessageDialog(getParent(), "You Won" + "\n" + "Your Score is: " + point, "Congrats", JOptionPane.DEFAULT_OPTION);
            }
            stop();
        }
    }
    private void lose() {
        if (player.hasCollided(randy)) {
            coolDown = 999999;
            if (isRunning) {
                JOptionPane.showMessageDialog(getParent(), "You Lost" + "\n" + "Your Score is: " + point, "Oops", JOptionPane.DEFAULT_OPTION);
            }
            stop();
        }
    }
}
