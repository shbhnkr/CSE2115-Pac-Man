package game;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

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
}
