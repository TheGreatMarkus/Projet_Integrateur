package ca.qc.bdeb.info204.spellington.gameentities.enemies;

import ca.qc.bdeb.info204.spellington.gameentities.GameAnimation;
import ca.qc.bdeb.info204.spellington.gameentities.Projectile;
import ca.qc.bdeb.info204.spellington.gameentities.Spellington;
import ca.qc.bdeb.info204.spellington.gameentities.Tile;
import java.util.ArrayList;
import org.newdawn.slick.Graphics;

/**
 * A enemy that will be fought at the end of the game.
 *
 * @author Cristian Aldea
 * @see RangedEnemy
 */
public class BossEnemy extends Enemy {

    public BossEnemy(float x, float y, EnemyType enemyType) {
        super(x, y, enemyType);
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void move(float time, Spellington spellington, ArrayList<Projectile> activeProjectiles, ArrayList<GameAnimation> activeAnimations, Tile[][] mapinfo) {
    }

    @Override
    public void attack(float time, Spellington spellington, ArrayList<Projectile> activeProjectiles, Tile[][] map) {
    }

    @Override
    public void loadAnimations() {
    }

}
