package ghost;

import java.awt.Point;

import static game.RenderLevel.pixels;

class CommonAIOperations {

    private static double distance = Integer.MAX_VALUE;
    private static int res = -1;

    private static boolean isUpWall(Ghost ghost, int height) {
        if (ghost.getLocation().y == 0) {
            return pixels[ghost.getLocation().x / 20][(height - 20) / 20] == '#';
        } else {
            return pixels[ghost.getLocation().x / 20][(ghost.getLocation().y - 20) / 20] == '#';
        }
    }

    private static boolean isLeftWall(Ghost ghost, int width) {
        if (ghost.getLocation().x == 0) {
            return pixels[(width - 20) / 20][ghost.getLocation().y / 20] == '#';
        } else {
            return pixels[(ghost.getLocation().x - 20) / 20][ghost.getLocation().y / 20] == '#';
        }
    }

    private static boolean isDownWall(Ghost ghost, int height) {
        if (ghost.getLocation().y == (height - 20)) {
            return pixels[ghost.getLocation().x / 20][0] == '#';
        } else {
            return pixels[ghost.getLocation().x / 20][(ghost.getLocation().y + 20) / 20] == '#';
        }
    }

    private static boolean isRightWall(Ghost ghost, int width) {
        if (ghost.getLocation().x == (width - 20)) {
            return pixels[0][ghost.getLocation().y / 20] == '#';
        } else {
            return pixels[(ghost.getLocation().x + 20) / 20][ghost.getLocation().y / 20] == '#';
        }
    }

    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    static int getRes(Ghost ghost, String lastMove, Point destination, int height, int width) {
        System.out.print(pixels[ghost.getLocation().x / 20][(ghost.getLocation().y - 20) / 20] + ", ");
        System.out.print(pixels[ghost.getLocation().x / 20][(ghost.getLocation().y + 20) / 20] + ", ");
        System.out.print(pixels[(ghost.getLocation().x + 20) / 20][(ghost.getLocation().y) / 20] + ", ");
        System.out.println(pixels[(ghost.getLocation().x - 20) / 20][(ghost.getLocation().y) / 20]);

        boolean upWall = isUpWall(ghost, height);
        boolean leftWall = isLeftWall(ghost, width);
        boolean downWall = isDownWall(ghost, height);
        boolean rightWall = isRightWall(ghost, width);

        System.out.print(upWall);
        System.out.print(leftWall);
        System.out.print(downWall);
        System.out.print(rightWall);

        if (!upWall) {
            upValidMove(lastMove, leftWall, downWall, rightWall);
        }
        if (!leftWall) {
            leftValidMove(ghost, lastMove, destination, upWall, downWall, rightWall, width);
        }
        if (!downWall) {
            downValidMove(ghost, lastMove, destination, upWall, leftWall, rightWall, height);
        }
        if (!rightWall) {
            rightValidMove(ghost, lastMove, destination, upWall, leftWall, downWall, width);
        }
        return res;
    }

    private static void rightValidMove(Ghost ghost, String lastMove, Point destination, boolean upWall, boolean leftWall, boolean downWall, int width) {
        if (downWall & upWall & leftWall) {
            res = 3;
        }
        if (!lastMove.equals("left")) {
            double temp;
            if (ghost.getLocation().x == (width - 20)) {
                temp = Math.sqrt(Math.pow(((0) - destination.x) / 20, 2)
                        + Math.pow((ghost.getLocation().y - destination.y) / 20, 2));
            } else {
                temp  = Math.sqrt(Math.pow(((ghost.getLocation().x + 20)
                        - destination.x) / 20, 2)
                        + Math.pow((ghost.getLocation().y - destination.y) / 20, 2));
            }
            res = compare(temp, res, 3);
        }
    }

    private static void downValidMove(Ghost ghost, String lastMove, Point destination, boolean upWall, boolean leftWall, boolean rightWall, int height) {
        if (leftWall & rightWall & upWall) {
            res = 2;
        }
        if (!lastMove.equals("up")) {
            double temp;
            if (ghost.getLocation().y == (height - 20)) {
                temp = Math.sqrt(Math.pow(((ghost.getLocation().y) - destination.x) / 20, 2)
                        + Math.pow((0 - destination.y) / 20, 2));
            } else {
                temp  = Math.sqrt(Math.pow(((ghost.getLocation().x)
                        - destination.x) / 20, 2)
                        + Math.pow(((ghost.getLocation().y + 20) - destination.y) / 20, 2));
            }
            res = compare(temp, res, 2);
        }
    }

    private static void leftValidMove(Ghost ghost, String lastMove, Point destination, boolean upWall, boolean downWall, boolean rightWall, int width) {
        if (downWall & rightWall & upWall) {
            res = 1;
        }
        if (!lastMove.equals("right")) {
            double temp;
            if (ghost.getLocation().x == 0) {
                temp = Math.sqrt(Math.pow(((width - 20) - destination.x) / 20, 2)
                        + Math.pow((ghost.getLocation().y - destination.y) / 20, 2));
            } else {
                temp  = Math.sqrt(Math.pow(((ghost.getLocation().x + 20)
                        - destination.x) / 20, 2)
                        + Math.pow((ghost.getLocation().y - destination.y) / 20, 2));
            }
            res = compare(temp, res, 1);
        }
    }

    private static void upValidMove(String lastMove, boolean leftWall, boolean downWall, boolean rightWall) {
        if (!lastMove.equals("down") || downWall & rightWall & leftWall) {
            res = 0;
        }
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
