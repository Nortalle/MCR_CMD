package Model;

import Controler.Controller;

/**
 * @author Vincent Guidoux
 */
public class Game {

    private Controller controller;

    private static Game instance;

    private Map map;

    private Player playerOne;
    private Player playerTwo;

    private static int width = 20;
    private static int height = 15;
    public static int nbrActions = 5;

    private int turn;

    private Cell selected;

    private Game() {
        controller = new Controller();
        map = new Map(width, height);
        playerOne = new Player("one");
        playerTwo = new Player("two");
        turn = 0;

    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    public int turn(){
        return turn;
    }

    public void nextTurn(){
        turn++;
    }

    //TODO
    public Cell selected(){
        // retourne la case sélectionnées
        return selected;
    }
    public void setSelected(Cell c){
        selected = c;

    }
}
