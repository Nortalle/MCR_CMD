package Model;

/**
 * @author Vincent Guidoux
 */
public class Game {

    private static Game instance;

    private Map map;

    private Player playerOne;
    private Player playerTwo;

    private Cell selected;

    private Game() {

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

    //TODO
    public Cell selected(){
        // retourne la case sélectionnées
        return selected;
    }
    public void setSelected(Cell c){
        selected = c;

    }
}
