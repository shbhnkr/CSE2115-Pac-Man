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
    private static final String TITLE = "Pac-Man";
    private int width = 0;
    private int height = 0;

    private List<Unit> units;

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }



    private static boolean isRunning;

    static {
        isRunning = false;
    }

    private transient Level level;
    private transient Player player;
    private transient Thread thread;

    private GameSettings settings;

    /**
     * Game class.
     */
    public Game(GameSettings settings) {
        this.settings = settings;

        URL path = ClassLoader.getSystemResource("board2.txt");
        File file = new File(path.getFile());

        Dimension dimension = this.calculateDimensions(file);
        this.setComponentDimensions(dimension);
        this.level = new Level(path, width, height, this.settings.getSquareSize());
        addKeyListener(this);
    }

    /**
     * Sets the canvas component sizes
     * @param dimension
     */
    private void setComponentDimensions(Dimension dimension) {
        setPreferredSize(dimension);
        setMinimumSize(dimension);
        setMaximumSize(dimension);
    }

    /**
     * Checks the given input file for max width and height.
     * @param file
     * @return
     */
    private Dimension calculateDimensions(File file) {
        Scanner sc;
        try {
            sc = new Scanner(file);
            int n = 0;
            while (sc.hasNextLine()) {
                this.width = this.settings.getSquareSize() * (sc.nextLine().length());
                n++;
            }
            this.height = this.settings.getSquareSize() * n;
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
     * Initialize the game board.
     *
     * @param args args.
     */
    public static void main(String[] args) {
        GameSettings settings = new GameSettings(20);
        Game game = new Game(settings);

        game.initFrame();
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
}
