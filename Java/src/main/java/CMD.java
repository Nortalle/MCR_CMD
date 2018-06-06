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
        game.init();

        Player player1 = game.getPlayerOne();
        Player player2 = game.getPlayerTwo();

        Map map = Game.getInstance().getMap();

        game.addCardToPlayer(player1, new SteveTheWarrior(map.getCell(0, 3)));
        game.addCardToPlayer(player1, new TheOldCrumbling(map.getCell(0, 6)));
        game.addCardToPlayer(player1, new ThePrestigiousArcher(map.getCell(0, 9)));
        game.addCardToPlayer(player1, new ThePsyCat(map.getCell(0, 12)));

        game.addCardToPlayer(player2, new SteveTheWarrior(map.getCell(map.width()-1, 3)));
        game.addCardToPlayer(player2, new TheOldCrumbling(map.getCell(map.width()-1, 6)));
        game.addCardToPlayer(player2, new ThePrestigiousArcher(map.getCell(map.width()-1, 9)));
        game.addCardToPlayer(player2, new ThePsyCat(map.getCell(map.width()-1, 12)));

        game.setMap(new Map(20, 15));

        game.getController().startGame();

    }
}