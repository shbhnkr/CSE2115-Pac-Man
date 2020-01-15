package ghost;

import game.SpriteSheet;
import game.Types;

import java.awt.Point;

import static game.Level.pixels;

public class Blinky extends Ghost {
    /**
     * ghost 1.
     */
    public static final long serialVersionUID = 4328743;

    private transient String lastMove;
    /**
     * ghost constructor 1.
     */
    Blinky(int x, int y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet);
    }

    @Override
    @SuppressWarnings("PMD")
    public void moveGhost(int height, int width) {
        Point destination = this.unitLocations.get(Types.playerType());
        if (unitLocations.isEmpty() || destination == null) {
            System.out.println("no destination!");
            return;
        }
        double distance = (double) Integer.MAX_VALUE;
        boolean upWall = getLocation().y != 0 && pixels[getLocation().x / 20][(getLocation().y - 20) / 20] == '#';
        boolean leftWall = getLocation().x != 0 && pixels[(getLocation().x - 20) / 20][getLocation().y / 20] == '#';
        boolean downWall = getLocation().y != height - 20 && pixels[getLocation().x / 20][(getLocation().y + 20) / 20] == '#';
        boolean rightWall = getLocation().x != width - 20 && pixels[(getLocation().x + 20) / 20][getLocation().y / 20] == '#';

        int res = -1;
        if (!upWall) {
            if(lastMove == null || !lastMove.equals("down") || downWall && rightWall && leftWall) {
                double temp = Math.sqrt(Math.pow((this.getLocation().x - destination.getLocation().x) / 20, 2)
                        + Math.pow(((this.getLocation().y - 20) - destination.getLocation().y) / 20, 2));
                if (temp < distance) {
                    distance = temp;
                    res = 0;
                }
            }
        }
        if (!leftWall) {
            if(lastMove == null || !lastMove.equals("right") || downWall && rightWall && upWall) {
                double temp = Math.sqrt(Math.pow(((this.getLocation().x - 20) - destination.getLocation().x) / 20, 2)
                        + Math.pow((this.getLocation().y - destination.getLocation().y) / 20, 2));
                if (temp < distance) {
                    distance = temp;
                    res = 1;
                }
            }
        }
        if (!downWall) {
            if(lastMove == null || !lastMove.equals("up") || upWall && rightWall && leftWall) {
                double temp = Math.sqrt(Math.pow((this.getLocation().x - destination.getLocation().x) / 20, 2)
                        + Math.pow(((this.getLocation().y + 20) - destination.getLocation().y) / 20, 2));
                if (temp < distance) {
                    distance = temp;
                    res = 2;
                }
            }
        }
        if (!rightWall) {
            if(lastMove == null || !lastMove.equals("left") || downWall && upWall && leftWall) {
                double temp = Math.sqrt(Math.pow(((this.getLocation().x + 20) - destination.getLocation().x) / 20, 2)
                        + Math.pow((this.getLocation().y - destination.getLocation().y) / 20, 2));
                if (temp < distance) {
                    res = 3;
                }
            }
        }
        switch(res) {
            case 0:
                lastMove = "up";
                moveUpGhost();
                break;
            case 1:
                lastMove = "left";
                moveLeftGhost();
                break;
            case 2:
                lastMove = "down";
                moveDownGhost();
                break;
            case 3:
                lastMove = "right";
                moveRightGhost();
                break;
            default:
                break;
        }
    }

    @Override
    public String getType() {
        return "b";
    }
}

