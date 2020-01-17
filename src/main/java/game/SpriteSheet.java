package game;

import ghost.Ghost;

import javax.imageio.ImageIO;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static game.Player.xPixelPlayer;
import static game.Player.yPixelPlayer;

/**
 * Image for sprites on board.
 */
public class SpriteSheet {

    private transient BufferedImage sheet;

    /**
     * Icons for players and ghosts.
     * @param path Path
     */
    public SpriteSheet(String path) {
        try {
            sheet = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            System.out.println("Failed to load Image!");
        }
    }

    public BufferedImage getSprite(int xx, int yy) {
        return sheet.getSubimage(xx, yy, 16, 16);
    }

    static void animation(int xx, int yy, Player player, Graphics graphics)
    {
        int n1 = 0;
        while (n1 < 200) {
            xPixelPlayer = xx;
            yPixelPlayer = yy;
            player.render(graphics);
            n1++;
        }
    }
//    static void ghostAnimation(int xx, int yy, Ghost ghost, Graphics graphics)
//    {
//        int n1 = 0;
//        while (n1 < 200) {
//            xPixelGhost = xx;
//            yPixelGhost = yy;
//            ghost.render(graphics);
//            n1++;
//        }
//    }
}

