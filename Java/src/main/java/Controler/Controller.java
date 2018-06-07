package Controler;

import Model.*;
import Model.units.*;
import View.Frame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Controller {

    private Game game;
    private Frame frame;
    private Player player1;
    private Player player2;
    private Map map;

    //TODO : est-ce que cette variable fait doublon avec celle de Game?
    private static final int maxAction = 5;

    public Controller() {
        this.game = Game.getInstance();
        this.map = game.getMap();
        this.player1 = game.getPlayerOne();
        this.player2 = game.getPlayerTwo();

        //The players get theirs cards
        game.addCardToPlayer(player1, new SteveTheWarrior(map.getCell(0, 3)));
        game.addCardToPlayer(player1, new TheOldCrumbling(map.getCell(0, 6)));
        game.addCardToPlayer(player1, new ThePrestigiousArcher(map.getCell(0, 9)));
        game.addCardToPlayer(player1, new ThePsyCat(map.getCell(0, 12)));

        game.addCardToPlayer(player2, new SteveTheWarrior(map.getCell(map.width()-1, 3)));
        game.addCardToPlayer(player2, new TheOldCrumbling(map.getCell(map.width()-1, 6)));
        game.addCardToPlayer(player2, new ThePrestigiousArcher(map.getCell(map.width()-1, 9)));
        game.addCardToPlayer(player2, new ThePsyCat(map.getCell(map.width()-1, 12)));

        this.frame = new Frame(game.getMap().width(), game.getMap().height());
    }

    private void startGame(){
        generateMap(game.getMap().width(), game.getMap().height());
        for(int i = 0; i < game.getMap().width(); ++i){
            for(int j = 0; j < game.getMap().height(); ++j){
                game.getMap().getCell(i,j).getCorrespondingCellView().drawUnit();
            }
        }
        frame.update();
    }

    public void runGame(){
        startGame();
        while(!PlayerHasLost(game.getPlayerOne()) && !PlayerHasLost(game.getPlayerTwo())){

            game.nextTurn();
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
            JOptionPane.showMessageDialog(null, "Player " + game.getPlayerOne() + " must have " + Game.nbrActions + " actions");
            return;
        }

        if(game.getPlayerTwo().getActionsList().size() < game.nbrActions) {
            JOptionPane.showMessageDialog(null, "Player " + game.getPlayerTwo() + " must have " + Game.nbrActions + " actions");
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < Game.nbrActions ; ++i){
                    try {
                        game.getPlayerOne().getActionsList().get(i).execute();
                        sleep(100);
                        frame.update();
                        game.getPlayerTwo().getActionsList().get(i).execute();
                        sleep(100);
                        frame.update();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                game.getPlayerOne().getActionsList().clear();
                game.getPlayerTwo().getActionsList().clear();
                frame.update();
            }
        }).start();
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

    private void generateMap(int columns, int rows){

        final int MAX_ELEMENT = 20;

        Random rand = new Random();

        int nbrElements = rand.nextInt(MAX_ELEMENT) + 1;

        for(int i = 0; i < nbrElements; i++){
            new FakeUnit(Game.getInstance().getMap().getCell(rand.nextInt(columns-2)+1, rand.nextInt(rows)));
        }
    }

}
