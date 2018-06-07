import Model.Game;
import Model.Map;
import Model.Player;
import Model.units.SteveTheWarrior;
import Model.units.TheOldCrumbling;
import Model.units.ThePrestigiousArcher;
import Model.units.ThePsyCat;
import View.Frame;

/**
 * Class used to launch the game
 */
public class CMD {

    public static void main(String[] args) {
        Game game = Game.getInstance();
        game.init();
    }
}