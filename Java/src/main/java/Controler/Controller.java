package Controler;

import Model.*;
import Model.spells.FakeUnitSpell;
import Model.spells.MeteoriteRain;
import Model.spells.UndoSpell;
import Model.units.*;
import View.Frame;

import javax.swing.*;
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
        previousCmd = null;
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
    public void startGame(){

        //TODO Cette partie devrait être ajoutée au constructeur
        //The players get theirs cards
        game.addCardToPlayer(player1, new SteveTheWarrior(map.getCell(3, 0), "Steve", "warr.png"));
        game.addCardToPlayer(player1, new TheOldCrumbling(map.getCell(6, 0), "Morth", "vieux.png"));
        game.addCardToPlayer(player1, new ThePrestigiousArcher(map.getCell(9, 0), "Grath", "archer.png"));
        game.addCardToPlayer(player1, new ThePsyCat(map.getCell(12, 0), "Moutarde", "catsprite.png"));
        game.addCardToPlayer(player1, new MeteoriteRain());
        game.addCardToPlayer(player1, new UndoSpell());
        game.addCardToPlayer(player1, new FakeUnitSpell());


        game.addCardToPlayer(player2, new SteveTheWarrior(map.getCell(3, map.width()-1), "Lynda", "warr2.png"));
        game.addCardToPlayer(player2, new TheOldCrumbling(map.getCell(6, map.width()-1), "Baba", "vieux2.png"));
        game.addCardToPlayer(player2, new ThePrestigiousArcher(map.getCell(9, map.width()-1), "Sen", "archer2.png"));
        game.addCardToPlayer(player2, new ThePsyCat(map.getCell(12, map.width()-1), "Podfleur", "catsprite2.png"));
        game.addCardToPlayer(player2, new MeteoriteRain());
        game.addCardToPlayer(player2, new UndoSpell());
        game.addCardToPlayer(player2, new FakeUnitSpell());

        //TODO Cette initialisation devra se retrouver dans le constructeur une fois la classe Frame refactorée
        this.frame = new Frame(game.getMap().width(), game.getMap().height());

        generateMap(game.getMap().width(), game.getMap().height());
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
        JOptionPane.showMessageDialog(null, "Jeu fini");
        System.exit(0);
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
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                game.nextTurn();
                player1.getActionsList().clear();
                for(ICard c : player1.getCards()){
                    if(c instanceof Spell){
                        ((Spell) c).setHasBeenExecuted(false);
                    }
                }
                player2.getActionsList().clear();
                for(ICard c : player2.getCards()){
                    if(c instanceof Spell){
                        ((Spell) c).setHasBeenExecuted(false);
                    }
                }
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
            new FakeUnit(game.getMap().getCell(rand.nextInt(columns), rand.nextInt(rows-2)+1));
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
        System.out.println("Pred " + previousCmd);
        ICmd cmd = p.getActionsList().get(actionPos);
        if(cmd.condition()){
            cmd.execute();
            p.publish(cmd.toString());
        }
        previousCmd = cmd;
        System.out.println("new Pred " + previousCmd);
        System.out.println("new Curr " + cmd);
        sleep(1000);
        frame.update();

        if(PlayerHasLost(player1) || PlayerHasLost(player2)){
            JOptionPane.showMessageDialog(null, "Jeu fini");
            System.exit(0);
        }
    }

}