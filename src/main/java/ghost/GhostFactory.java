package ghost;

import game.Game;

public class GhostFactory {
    public static final String RANDY = "randy";
    public static final String CLYDE = "clyde";
    public static final String BlINKY = "blinky";
    public static final String INKY = "inky";
    public static final String PINKY = "pinky";

    public static Ghost create(String ghostType, int x, int y) throws Exception {

        if (ghostType.equals(GhostFactory.RANDY)) {
            return new Randy(x, y, Game.randySprite);
        }

        if (ghostType.equals(GhostFactory.BlINKY)) {
            return new Blinky(x, y, Game.blinkySprite);
        }

        if (ghostType.equals(GhostFactory.CLYDE)) {
            return new Clyde(x, y, Game.clydeSprite);
        }

        if (ghostType.equals(GhostFactory.INKY)) {
            return new Inky(x, y, Game.inkySprite);
        }

        if (ghostType.equals(GhostFactory.PINKY)) {
            return new Pinky(x, y, Game.pinkySprite);
        }


        throw new Exception("Cannot find matching ghost for ghost type");
    }
}
