package game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.net.URL;

public class Game extends Canvas implements Runnable {

    public static final long serialVersionUID = 4328743;
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    public static final String TITLE = "Pac-Man";
    private static boolean isRunning = false;
    public transient Player player;
    public transient Level level;
    private transient Thread thread;


    public Game() {
        Dimension dimension = new Dimension(Game.WIDTH, Game.HEIGHT);
        setPreferredSize(dimension);
        setMinimumSize(dimension);
        setMaximumSize(dimension);
        player = new Player(Game.WIDTH / 2, Game.HEIGHT / 2);
        URL path = ClassLoader.getSystemResource("board.txt");
        level = new Level(path);
    }

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

    private synchronized void start() {
        if (isRunning) {
            return;
        }
        isRunning = true;
        thread = new Thread(this);
        thread.start();


    }

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


    private void make() {
        BufferStrategy bufferStrategy = getBufferStrategy();
        if (bufferStrategy == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics graphics = bufferStrategy.getDrawGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        player.render(graphics);
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
