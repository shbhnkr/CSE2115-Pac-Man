package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;
import javax.swing.JFrame;


public class Game extends Canvas implements Runnable {

    public static final long serialVersionUID = 4328743;
    public static final String TITLE = "Pac-Man";
    public int width = 0;

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int height = 0;
    private static boolean isRunning;

    static {
        isRunning = false;
    }

    public transient Level level;
    private transient Thread thread;

    /**
     * Game class.
     */
    public Game() {
        URL path = ClassLoader.getSystemResource("board2.txt");
        File file = new File(path.getFile());
        Scanner sc;
        try {
            sc = new Scanner(file);
            int n = 0;
            while (sc.hasNextLine()) {
                width = 20 * (sc.nextLine().length());
                n++;
            }
            height = 20 * n;
            Dimension dimension = new Dimension(width, height);
            setPreferredSize(dimension);
            setMinimumSize(dimension);
            setMaximumSize(dimension);
            level = new Level(path, width, height);
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
    }

    /**
     * Initialize the game board.
     *
     * @param args args.
     */
    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = new JFrame();
        frame.setTitle(Game.TITLE);
        frame.add(game);
        frame.setResizable(false);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        game.start();
    }

    /**
     * Start.
     */
    private synchronized void start() {
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
}
