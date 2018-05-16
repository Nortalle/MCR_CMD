package Model;

import java.util.ArrayList;

/**
 * @author Vincent Guidoux
 */
public class Unit extends CellContent implements ICard {

    public ArrayList<Action> getActions() {
        return null;
    }

    @Override
    public String toString() {
        return "Unit{}";
    }

    public void takeDamage(int damage) {
        // TODO
    }

    public void takeHeal(int heal) {
        // TODO
    }
}
