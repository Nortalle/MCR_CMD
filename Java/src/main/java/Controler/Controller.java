package Controler;

import Model.Game;
import Model.ICard;
import Model.ICmd;
import Model.Player;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Controller {

    private Game game;

    public Controller() {
    }

    public void init(){
        game = Game.getInstance();
    }

    public boolean PlayerHasLost(Player player){

        for(ICard card : player.getCards()){
            if(card.isAlive()){
                return false;
            }
        }
        System.out.println("Le joueur " + player + " a perdu");
        return true;
    }

    public boolean isGameFinished(){

        return PlayerHasLost(game.getPlayerOne()) || PlayerHasLost(game.getPlayerTwo());
    }

    public void executeAllCommands(){
        for(int i = 0; i < Game.nbrActions ; ++i){
            try {
                game.getPlayerOne().getActionsList().get(i).execute();
                sleep(500);
                game.getPlayerTwo().getActionsList().get(i).execute();
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean addCommandToPlayer(Player player, ICmd cmd){
        /*
        On doit faire des tests
         */
        player.getActionsList().add(cmd);

        return true;
    }
}
