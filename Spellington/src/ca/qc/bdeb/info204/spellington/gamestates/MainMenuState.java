package ca.qc.bdeb.info204.spellington.gamestates;

import ca.qc.bdeb.info204.spellington.GameCore;
import static ca.qc.bdeb.info204.spellington.GameCore.fontPaladin;
import ca.qc.bdeb.info204.spellington.textEntities.MenuItem;
import ca.qc.bdeb.info204.spellington.textEntities.MenuItem.MenuItemType;
import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.font.effects.OutlineEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * A BasicGameState corresponding with the main menu of the game.
 *
 * @author Cristian Aldea
 * @see GameCore
 */
public class MainMenuState extends BasicGameState {

    //Default menu font. Can be changed.
    public static Image IMG_MENU_CURSOR;

    //Default menu font.
    protected static UnicodeFont fontMenu;
    private Image backGround;

    //Text for the main menu.
    private static final String MM_TITLE = "Le réveil de Spellington";
    private static final String MM_PLAY = "Jouer";
    private static final String MM_OPTIONS = "Options";
    private static final String MM_EXIT = "Quitter";

    private MenuItem mnuItemTitle;
    private MenuItem mnuItemPlay;
    private MenuItem mnuItemOptions;
    private MenuItem mnuItemExit;

    private static float textGap;

    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        IMG_MENU_CURSOR = new Image("res/image/cursor/small_cursor.png");
        //Initialisation du font pour le menu.
        textGap = 10.0f * GameCore.SCALE;

        backGround = new Image("res/image/menu/mm_background.jpg");

        fontPaladin = fontPaladin.deriveFont(Font.BOLD, 110.0f * GameCore.SCALE);
        fontMenu = new UnicodeFont(fontPaladin);
        fontMenu.addAsciiGlyphs();
        fontMenu.getEffects().add(new ColorEffect(java.awt.Color.black));
        fontMenu.getEffects().add(new OutlineEffect(1, java.awt.Color.white));
        fontMenu.loadGlyphs();

        mnuItemTitle = new MenuItem(gc, MenuItemType.TITLE, MM_TITLE, true, false, 0, textGap, fontMenu.getWidth(MM_TITLE), fontMenu.getHeight(MM_TITLE));
        mnuItemPlay = new MenuItem(gc, MenuItemType.BUTTON, MM_PLAY, true, true, 0, mnuItemTitle.getY() + mnuItemTitle.getHeight() + textGap, fontMenu.getWidth(MM_PLAY), fontMenu.getHeight(MM_PLAY));
        mnuItemOptions = new MenuItem(gc, MenuItemType.BUTTON, MM_OPTIONS, true, false, 0, mnuItemPlay.getY() + mnuItemPlay.getHeight() + textGap, fontMenu.getWidth(MM_OPTIONS), fontMenu.getHeight(MM_OPTIONS));
        mnuItemExit = new MenuItem(gc, MenuItemType.BUTTON, MM_EXIT, true, false, 0, mnuItemOptions.getY() + mnuItemOptions.getHeight() + textGap, fontMenu.getWidth(MM_EXIT), fontMenu.getHeight(MM_EXIT));

    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        backGround.draw(0, 0, GameCore.SCREEN_SIZE.width, GameCore.SCREEN_SIZE.height);
        g.setColor(Color.white);
        g.setFont(fontMenu);

        mnuItemTitle.render(g, gc);
        mnuItemPlay.render(g, gc);
        mnuItemOptions.render(g, gc);
        mnuItemExit.render(g, gc);

        float renderMouseX = gc.getInput().getMouseX();
        float renderMouseY = gc.getInput().getMouseY();
        IMG_MENU_CURSOR.draw(renderMouseX, renderMouseY, 25f * GameCore.SCALE, 25f * GameCore.SCALE);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        int mouseX = gc.getInput().getMouseX();
        int mouseY = gc.getInput().getMouseY();
        mnuItemPlay.detHoveredOver(mouseX, mouseY);
        mnuItemOptions.detHoveredOver(mouseX, mouseY);
        mnuItemExit.detHoveredOver(mouseX, mouseY);

        boolean triedToClick = gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON);

        if (mnuItemPlay.getHoveredOver() && triedToClick) {
            game.enterState(GameCore.PLAY_STATE_ID);
        }
        if (mnuItemOptions.getHoveredOver() && triedToClick) {
            game.enterState(GameCore.OPTIONS_MENU_STATE_ID);
        }
        if (mnuItemExit.getHoveredOver() && triedToClick) {
            gc.exit();
        }

        GameCore.clearInputRecord(gc);

    }

    @Override
    public int getID() {
        return GameCore.MAIN_MENU_STATE_ID;
    }

}
