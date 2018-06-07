package Controler;

import Model.*;
import Model.units.*;
import View.Frame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * The Controller is a Singleton used to control the actions in the game
 */
public class Controller {

    public static Controller instance;

    private Game game;
    private Frame frame;
    private Player player1;
    private Player player2;
    private Map map;
    private ICmd previousCmd;

    /**
     * Private constructor
     */
    private Controller() {
        this.game = new Game();
        this.map = game.getMap();
        this.player1 = game.getPlayerOne();
        this.player2 = game.getPlayerTwo();
    }

    /**
     * Create a new instance of Controller if it doesn't already exists
     * @return the instance of the controller
     */
    public static Controller getInstance(){
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    //Initialise the game
    private void startGame(){

        //TODO Cette partie devrait être ajoutée au constructeur
        //The players get theirs cards
        game.addCardToPlayer(player1, new SteveTheWarrior(map.getCell(0, 3)));
        game.addCardToPlayer(player1, new TheOldCrumbling(map.getCell(0, 6)));
        game.addCardToPlayer(player1, new ThePrestigiousArcher(map.getCell(0, 9)));
        game.addCardToPlayer(player1, new ThePsyCat(map.getCell(0, 12)));

        game.addCardToPlayer(player2, new SteveTheWarrior(map.getCell(map.width()-1, 3)));
        game.addCardToPlayer(player2, new TheOldCrumbling(map.getCell(map.width()-1, 6)));
        game.addCardToPlayer(player2, new ThePrestigiousArcher(map.getCell(map.width()-1, 9)));
        game.addCardToPlayer(player2, new ThePsyCat(map.getCell(map.width()-1, 12)));

        //TODO Cette initialisation devra se retrouver dans le constructeur une fois la classe Frame refactorée
        this.frame = new Frame(game.getMap().width(), game.getMap().height());

        generateMap(game.getMap().width(), game.getMap().height());
        for(int i = 0; i < game.getMap().width(); ++i){
            for(int j = 0; j < game.getMap().height(); ++j){
                game.getMap().getCell(i,j).getCorrespondingCellView().drawUnit();
            }
        }
        frame.update();
    }

    /**
     * Run the game until one of the players lose all his units
     */
    public void runGame(){
        startGame();
        while(!PlayerHasLost(game.getPlayerOne()) && !PlayerHasLost(game.getPlayerTwo())){

            game.nextTurn();
        }
    }

    /**
     * Getter of Game
     * @return the game
     */
    public Game game(){
        return game;
    }

    /**
     * Check if a player has lost the game
     * @param player, player to check
     * @return true if the player has lost all his units, false otherwise
     */
    public boolean PlayerHasLost(Player player){

        for(ICard card : player.getCards()){
            if(card.isAlive()){
                return false;
            }
        }
        System.out.println("Le joueur " + player + " a perdu");
        return true;
    }

    /**
     * Execute the lists of commandes filled by the two players
     */
    public void executeAllCommands(){

        /*
        if(game.getPlayerOne().getActionsList().size() < game.nbrActions) {
            JOptionPane.showMessageDialog(null, "Player " + game.getPlayerOne() + " must have " + Game.nbrActions + " actions");
            return;
        }

        if(game.getPlayerTwo().getActionsList().size() < game.nbrActions) {
            JOptionPane.showMessageDialog(null, "Player " + game.getPlayerTwo() + " must have " + Game.nbrActions + " actions");
            return;
        }
        */
        if (!checkNumberOfActions(player1) || !checkNumberOfActions(player2)) {
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < Game.nbrActions ; ++i){
                    try {
                        if(game.turn() % 2 == 0){
                            executeMove(player1,i);
                            executeMove(player2,i);
                        }else{
                            executeMove(player2,i);
                            executeMove(player1,i);
                        }
                        /*
                        game.getPlayerOne().getActionsList().get(i).execute();
                        previousCmd = game.getPlayerOne().getActionsList().get(i);
                        sleep(500);
                        frame.update();
                        game.getPlayerTwo().getActionsList().get(i).execute();
                        previousCmd = game.getPlayerOne().getActionsList().get(i);
                        sleep(500);
                        frame.update();
                        */
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


    /**
     * Add a command to the player's list of commands
     * @param player
     * @param cmd command to add
     * @return true if the command has been added, false otherwise
     */
    public boolean addCommandToPlayer(Player player, ICmd cmd){
        //Commands are added while there the number of commands is <= nbrActions
        if(player.getActionsList().size() < Game.nbrActions) {
            player.getActionsList().add(cmd);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Getter of the frame
     * @return the frame
     */
    public Frame getFrame() {
        return frame;
    }

    //generate a map with a random number of obstacles and place them randomly on the map
    private void generateMap(int columns, int rows){

        final int MAX_ELEMENT = 20;
        final int MIN_ELEMENT = 5;

        Random rand = new Random();

        int nbrElements = rand.nextInt(MAX_ELEMENT-MIN_ELEMENT) + MIN_ELEMENT;

        for(int i = 0; i < nbrElements; i++){
            new FakeUnit(game.getMap().getCell(rand.nextInt(columns-2)+1, rand.nextInt(rows)));
        }
    }

    public void undoPerviousCmd(){
        try{
            previousCmd.undo();
        }catch(NullPointerException e){
            e.printStackTrace();
        }
    }

    public ICmd getPreviousCmd(){
        return previousCmd;
    }

    private boolean checkNumberOfActions(Player p){
        if(p.getActionsList().size() < Game.nbrActions) {
            JOptionPane.showMessageDialog(null, "Both players must have " + Game.nbrActions + " actions");
            return false;
        }
        return true;
    }

    private void executeMove(Player p, int actionPos) throws InterruptedException {
        p.getActionsList().get(actionPos).execute();
        previousCmd = p.getActionsList().get(actionPos);
        sleep(500);
        frame.update();
    }

}