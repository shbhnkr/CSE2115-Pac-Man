package ghost;

import java.awt.Point;

import static game.RenderLevel.pixels;

class CommonAIOperations {

    static double distance = Integer.MAX_VALUE;

    static boolean isUpWall(Ghost ghost, int height) {
        if (ghost.getLocation().y == 0) {
            return pixels[ghost.getLocation().x / 20][(height - 20) / 20] == '#';
        } else {
            return pixels[ghost.getLocation().x / 20][(ghost.getLocation().y - 20) / 20] == '#';
        }
    }

    static boolean isLeftWall(Ghost ghost, int width) {
        if (ghost.getLocation().x == 0) {
            return pixels[(width - 20) / 20][ghost.getLocation().y / 20] == '#';
        } else {
            return pixels[(ghost.getLocation().x - 20) / 20][ghost.getLocation().y / 20] == '#';
        }
    }

    static boolean isDownWall(Ghost ghost, int height) {
        if (ghost.getLocation().y == height - 20) {
            return pixels[ghost.getLocation().x / 20][0] == '#';
        } else {
            return pixels[ghost.getLocation().x / 20][(ghost.getLocation().y + 20) / 20] == '#';
        }
    }

    static boolean isRightWall(Ghost ghost, int width) {
        if (ghost.getLocation().x == width - 20) {
            return pixels[0][ghost.getLocation().y / 20] == '#';
        } else {
            return pixels[(ghost.getLocation().x + 20) / 20][ghost.getLocation().y / 20] == '#';
        }
    }

    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    static int getRes(Ghost ghost, String lastMove, Point destination, int width, int height) {
        boolean upWall = isUpWall(ghost, height);
        boolean leftWall = isLeftWall(ghost, width);
        boolean downWall = isDownWall(ghost, height);
        boolean rightWall = isRightWall(ghost, width);

        int res = -1;

        if (!upWall) {
            res = upValidMove(lastMove, leftWall, downWall, rightWall, res);
        }
        if (!leftWall) {
            res = leftValidMove(ghost, lastMove, destination, upWall, downWall, rightWall, res);
        }
        if (!downWall) {
            res = downValidMove(ghost, lastMove, destination, upWall, leftWall, rightWall, res);
        }
        if (!rightWall) {
            res = rightValidMove(ghost, lastMove, destination, upWall, leftWall, downWall, res);
        }
        return res;
    }

    private static int rightValidMove(Ghost ghost, String lastMove, Point destination, boolean upWall, boolean leftWall, boolean downWall, int res) {
        if (!lastMove.equals("left") || downWall & upWall & leftWall) {
            double temp = Math.sqrt(Math.pow(((ghost.getLocation().x + 20)
                    - destination.x) / 20, 2)
                    + Math.pow((ghost.getLocation().y - destination.y) / 20, 2));
            res = compare(temp, res, 3);
        }
        return res;
    }

    private static int downValidMove(Ghost ghost, String lastMove, Point destination, boolean upWall, boolean leftWall, boolean rightWall, int res) {
        if (!lastMove.equals("up") || upWall & rightWall & leftWall) {
            double temp = Math.sqrt(Math.pow((
                    ghost.getLocation().x - destination.x) / 20, 2)
                    + Math.pow(((ghost.getLocation().y + 20) - destination.y) / 20, 2));
            res = compare(temp, res, 2);
        }
        return res;
    }

    private static int leftValidMove(Ghost ghost, String lastMove, Point destination, boolean upWall, boolean downWall, boolean rightWall, int res) {
        if (!lastMove.equals("right") || downWall & rightWall & upWall) {
            double temp = Math.sqrt(Math.pow(((
                    ghost.getLocation().x - 20) - destination.x) / 20, 2)
                    + Math.pow((ghost.getLocation().y - destination.y) / 20, 2));
            res = compare(temp, res, 1);
        }
        return res;
    }

    private static int upValidMove(String lastMove, boolean leftWall, boolean downWall, boolean rightWall, int res) {
        if (!lastMove.equals("down") || downWall & rightWall & leftWall) {
            res = 0;
        }
        return res;
    }

    private static int compare(double temp, int res, int i) {
        if (temp < distance) {
            distance = temp;
             return i;
        } else {
            return res;
        }
    }
}
