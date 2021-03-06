package ca.qc.bdeb.info204.spellington.gameentities.enemies;

import ca.qc.bdeb.info204.spellington.gameentities.GameAnimation;
import ca.qc.bdeb.info204.spellington.gameentities.Projectile;
import ca.qc.bdeb.info204.spellington.gameentities.Spellington;
import ca.qc.bdeb.info204.spellington.gameentities.Tile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * An Enemy that will do nothing.
 *
 * @author Cristian Aldea
 * @see Enemy
 */
public class DummyEnemy extends Enemy {

    private Image imgDummy;

    public DummyEnemy(float x, float y, EnemyType enemyType) {
        super(x, y, enemyType);

    }

    @Override
    public void loadAnimations() {
        try {
            imgDummy = new Image("res/image/animation/enemies/dummy/standing.png");
        } catch (SlickException ex) {
            Logger.getLogger(DummyEnemy.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void render(Graphics g) {
        renderGeneralInfo(g);
        imgDummy.draw(x, y, width, height);

    }

    @Override
    public void move(float time, Spellington spellington, ArrayList<Projectile> activeProjectiles, ArrayList<GameAnimation> activeAnimations, Tile[][] map) {
        //No mouvement
    }

    @Override
    public void attack(float time, Spellington spellington, ArrayList<Projectile> activeProjectiles, Tile[][] map) {
        //No attacking
    }

}
