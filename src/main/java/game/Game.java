package game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;


public class Game extends Canvas implements Runnable, KeyListener {

    public static final long serialVersionUID = 4328743;
    public static final String TITLE = "Pac-Man";
    public int width = 0;


    public List<Unit> units;

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
    public transient Player player;
    private transient Thread thread;

    protected GameSettings settings;

    /**
     * Game class.
     */
    public Game(GameSettings settings) {
        this.settings = settings;

        URL path = ClassLoader.getSystemResource("board2.txt");
        File file = new File(path.getFile());
        Scanner sc;
        try {
            sc = new Scanner(file);
            int n = 0;
            while (sc.hasNextLine()) {
                width = this.settings.getSquareSize() * (sc.nextLine().length());
                n++;
            }
            height = this.settings.getSquareSize() * n;
            Dimension dimension = new Dimension(width, height);
            setPreferredSize(dimension);
            setMinimumSize(dimension);
            setMaximumSize(dimension);
            level = new Level(path, width, height, this.settings.getSquareSize());
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
        GameSettings settings = new GameSettings(20);
        Game game = new Game(settings);

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
        addKeyListener(this);
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
        int key = e.getKeyCode();
        System.out.println(e.getKeyCode());

        player = level.player;
        if (key == KeyEvent.VK_W)  {
//            player.movePlayer(0, -1);

            if (validMove(MoveBuilder.UP(player.getLocation()))) {
                player.movePlayer(MoveBuilder.UP(player.getLocation()));
            }
            System.out.println("north");
        }

        if(key == KeyEvent.VK_A){
//            player.movePlayer(-1, 0);

            if (validMove(MoveBuilder.LEFT(player.getLocation()))) {
                player.movePlayer(MoveBuilder.LEFT(player.getLocation()));
            }
            System.out.println("west");
        }

        if(key == KeyEvent.VK_S){
//            player.movePlayer(0, 1);
            player.movePlayer(MoveBuilder.LEFT(player.getLocation()));

            if (validMove(MoveBuilder.DOWN(player.getLocation()))) {
                player.movePlayer(MoveBuilder.DOWN(player.getLocation()));
            }
            System.out.println("east");
        }

        if(key == KeyEvent.VK_D ) {
//            player.movePlayer(1, 0);
            if (validMove(MoveBuilder.RIGHT(player.getLocation()))) {
                player.movePlayer(MoveBuilder.RIGHT(player.getLocation()));
            }

            System.out.println("south");
        }
    }

    private boolean validMove(Point position) {
        if (this.units !=null) {
            for (Unit u : units) {
                if (u != null && u.getType().equals("#") &&  u.getLocation().equals(position)) {
                    return false;
                }
            }
        }


        return true;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
