package Model;

import java.util.ArrayList;

/**
 * @author Vincent Guidoux
 */
public class Player {

    private ArrayList<ICard> cards = new ArrayList<ICard>();
    private ArrayList<ICmd> actionsList = new ArrayList<ICmd>();
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public ArrayList<ICmd> getActionsList() {
        return actionsList;
    }

    public ArrayList<ICard> getCards() {
        return cards;
    }

    @Override
    public String toString(){
        return name;
    }
}
