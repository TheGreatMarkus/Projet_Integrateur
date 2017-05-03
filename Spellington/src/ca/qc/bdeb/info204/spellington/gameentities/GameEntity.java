package ca.qc.bdeb.info204.spellington.gameentities;

import org.newdawn.slick.geom.Rectangle;

/**
 * An entity that will be in the game.
 *
 * @author Cristian Aldea
 */
public abstract class GameEntity extends Rectangle {

    public static enum ElementalType {
        FIRE,
        ICE,
        LIGHTNING,
        NEUTRAL
    }

    public GameEntity(float x, float y, float width, float height) {
        super(x, y, width, height);
    }
}
