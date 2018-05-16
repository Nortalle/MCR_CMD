package Model;

/**
 * @author Vincent Guidoux
 */
public class Spell implements ICard {

    public Action[] getAction() {
        return new Action[0];
    }

    @Override
    public String toString() {
        return "Spell{}";
    }
}
