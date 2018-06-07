package Model;

import Controler.Controller;

/**
 * Class Game is a  Singleton that represents the model of the game and contains all the informations about it
 */
public class Game {

    private static Game instance;

    //Create the controller of the game that will run all the actions
    private Controller controller;

    //Limits of the game
    private static int width = 20;
    private static int height = 15;
    public static int nbrActions = 5;

    // Steady elements of the game
    private Map map;
    private Player playerOne;
    private Player playerTwo;

    //Changing elements of the game
    private int turn;
    private Cell selected;

    /**
     * Private constructor
     */
    private Game() {
    }

    /**
     * Get the instance of the game and create it if it doesn't already exists
     * @return the instance of Game
     */
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    /**
     * Initialise all the parameters of the game
     */
    public void init(){

        controller = new Controller();

        map = new Map(width, height);
        playerOne = new Player("one");
        playerTwo = new Player("two");
        turn = 0;
        selected = map.getCell(width/2, height/2);
    }

    /**
     * Getter of the map
     * @return the map
     */
    public Map getMap() {
        return map;
    }

    //TODO : Est-ce que cette fonction est utilisée vu qu'on initialise la carte au début et c'est tout?
    /**
     * Change the map
     * @param map, new map
     */
    public void setMap(Map map) {
        this.map = map;
    }

    /**
     * Getter of playerOne
     * @return playerOne
     */
    public Player getPlayerOne() {
        return playerOne;
    }

    //TODO : Est-ce que cette fonction est utilisée vu qu'on initialise le playerOne au début et c'est tout?
    /**
     * Change the playerOne
     * @param playerOne
     */
    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    /**
     * Getter of playerTwo
     * @return playerTwo
     */
    public Player getPlayerTwo() {
        return playerTwo;
    }

    //TODO : Est-ce que cette fonction est utilisée vu qu'on initialise le playerTwo au début et c'est tout?
    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    /**
     * Getter of the turn
     * @return the current turn
     */
    public int turn(){
        return turn;
    }

    /**
     * increments the turn
     */
    public void nextTurn(){
        turn++;
    }

    /**
     * Gives the player a new Icard representing a Unit or a Spell
     * @param player player that will recieve the cards
     * @param card ICard added to the player
     */
    public void addCardToPlayer(Player player, ICard card){
        player.getCards().add(card);
    }

    /**
     * @return the currently selected cell
     */
    public Cell getSelected() {
        return selected;
    }

    /**
     * Met à jour la selection de cellule
     * @param c
     */
    public void setSelected(Cell c){
        //TODO : devrait mettre à jour toute la frame
       // controller.getFrame();
        selected = c;
    }

    /**
     * Check if the cell is currently selected
     * @param x, x value of the cell
     * @param y, y value of the cell
     * @return true if the cell is selected, false otherwise
     */
    public boolean isSelected(int x, int y){
        return selected != null && selected.x == x && selected.y == y;
    }

    /**
     * @return the Controller of the game
     */
    public Controller getController() {
        return controller;
    }
}
