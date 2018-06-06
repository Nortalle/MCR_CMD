package Controler;

import Model.*;
import View.Frame;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Controller {

    private Game game;

    private Frame frame;

    private boolean isFinished;

    private static final int maxAction = 5;

    public Controller(Game game) {
        this.game = game;
    }

    public void startGame(){
        this.frame = new Frame(game.getMap().width(), game.getMap().height());

        for(int i = 0; i < game.getMap().width(); ++i){
            for(int j = 0; j < game.getMap().height(); ++j){
                if(game.getMap().getCell(i,j).getCellContents() != null){
                    System.out.println("test");
                    Game.getInstance().getController().getFrame().getGrid().at(i,j).drawUnit();
                }
            }
        }

        runGame();
    }

    public void runGame(){
        while(!isFinished){
            
        }
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

        game.getPlayerOne().getActionsList().clear();
        game.getPlayerTwo().getActionsList().clear();
    }

    public boolean addCommandToPlayer(Player player, ICmd cmd){
        /*
        On doit faire des tests
         */
        if(player.getActionsList().size() < maxAction) {
            player.getActionsList().add(cmd);
            return true;
        } else {
            return false;
        }
    }

    public Frame getFrame() {
        return frame;
    }

}
