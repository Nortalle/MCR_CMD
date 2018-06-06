package Controler;

import Model.*;
import View.Frame;

import javax.swing.*;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Controller {

    private Game game;

    private Frame frame;

    private boolean isFinished;

    private static final int maxAction = 5;

    public Controller() {
        this.game = Game.getInstance();
    }

    public void startGame(){
        this.frame = new Frame(game.getMap().width(), game.getMap().height());
        Unit u = (Unit) game.getPlayerOne().getCards().get(0);

        for(int i = 0; i < game.getMap().width(); ++i){
            for(int j = 0; j < game.getMap().height(); ++j){
                    game.getMap().getCell(i,j).getCorrespondingCellView().drawUnit();
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
        if(game.getPlayerOne().getActionsList().size() < game.nbrActions) {
            JOptionPane.showMessageDialog(null, "IMPOSSIBLELESLLFSD il faut 5 actions par joueur");
            return;
        }

        if(game.getPlayerTwo().getActionsList().size() < game.nbrActions) {
            JOptionPane.showMessageDialog(null, "IMPOSSIBLELESLLFSD il faut 5 actions par joueur");
            return;
        }

        for(int i = 0; i < Game.nbrActions ; ++i){
            try {
                game.getPlayerOne().getActionsList().get(i).execute();
                sleep(500);
                frame.update();
                game.getPlayerTwo().getActionsList().get(i).execute();
                sleep(500);
                frame.update();
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
