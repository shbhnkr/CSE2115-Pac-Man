package ghost;

import game.Game;

/**
 * the factory that creates ghosts.
 */
public class GhostFactory {
    /**
     * Identifiers used for creating a specific type of Ghost.
     */
    public static final String RANDY = "randy";
    public static final String CLYDE = "clyde";
    public static final String BLINKY = "blinky";
    public static final String INKY = "inky";
    public static final String PINKY = "pinky";

    /**
     * Creates a specific type of ghost based on the type identifier being passed.
     * @param ghostType Holding the type of Ghost.
     * @param x X coordinate where the ghost should be placed
     * @param y Y coordinate where the ghost should be placed.
     * @return Specific implementation of a ghost.
     * @throws Exception Whenever the type doesn't meet any of the ghosts.
     */
    public static Ghost create(String ghostType, int x, int y) throws Exception {

        if (ghostType.equals(GhostFactory.RANDY)) {
            return new Randy(x, y, Game.randySprite);
        }

        if (ghostType.equals(GhostFactory.BLINKY)) {
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
