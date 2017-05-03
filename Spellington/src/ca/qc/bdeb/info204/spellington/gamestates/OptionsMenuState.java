package ca.qc.bdeb.info204.spellington.gamestates;

import ca.qc.bdeb.info204.spellington.GameCore;
import ca.qc.bdeb.info204.spellington.textEntities.MenuItem;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Input;
import static ca.qc.bdeb.info204.spellington.gamestates.MainMenuState.fontMenu;

/**
 *
 * @author Fallen Angel
 */
public class OptionsMenuState extends BasicGameState {

    private static final String OM_TITLE = "Options";
    private static final String OM_BACK = "Revenir";

    private MenuItem mnuItemTitle;
    private MenuItem mnuItemBack;

    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {

        mnuItemTitle = new MenuItem(gc, MenuItem.MenuItemType.TEXT, OM_TITLE, true, false, 0, MainMenuState.TEXT_GAP, fontMenu.getWidth(OM_TITLE), fontMenu.getHeight(OM_TITLE));
        mnuItemBack = new MenuItem(gc, MenuItem.MenuItemType.BUTTON, OM_BACK, false, false, MainMenuState.TEXT_GAP, MainMenuState.TEXT_GAP, fontMenu.getWidth(OM_BACK), fontMenu.getHeight(OM_BACK));
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        g.setFont(fontMenu);
        mnuItemTitle.render(g, gc);
        mnuItemBack.render(g, gc);

        MainMenuState.renderMouseCursor(gc);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        int mouseX = gc.getInput().getMouseX();
        int mouseY = gc.getInput().getMouseY();
        mnuItemBack.detHoveredOver(mouseX, mouseY);

        boolean triedToClick = gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON);

        if (mnuItemBack.getHoveredOver() && triedToClick) {
            ((MainMenuState) game.getState(GameCore.MAIN_MENU_STATE_ID)).prepareMainMenu();
            game.enterState(GameCore.MAIN_MENU_STATE_ID);
        }
        GameCore.clearInputRecord(gc);
    }

    @Override
    public int getID() {
        return GameCore.OPTIONS_MENU_STATE_ID;
    }
}
