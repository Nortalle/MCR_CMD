import Model.Game;
import Model.Map;
import Model.Player;
import Model.units.SteveTheWarrior;
import Model.units.TheOldCrumbling;
import Model.units.ThePrestigiousArcher;
import Model.units.ThePsyCat;
import View.Frame;

public class CMD {

    public static void main(String[] args) {

        final int width = 20;

        Game game = Game.getInstance();
        game.getController().init();

        Player player1 = game.getPlayerOne();
        Player player2 = game.getPlayerTwo();

        game.addCardToPlayer(player1, new SteveTheWarrior());
        game.addCardToPlayer(player1, new TheOldCrumbling());
        game.addCardToPlayer(player1, new ThePrestigiousArcher());
        game.addCardToPlayer(player1, new ThePsyCat());

        game.addCardToPlayer(player2, new SteveTheWarrior());
        game.addCardToPlayer(player2, new TheOldCrumbling());
        game.addCardToPlayer(player2, new ThePrestigiousArcher());
        game.addCardToPlayer(player2, new ThePsyCat());

        game.setMap(new Map(20, 15));

        Frame frame = new Frame(20, 15);
    }
}