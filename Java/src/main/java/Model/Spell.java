package Model;

import java.util.ArrayList;

/**
 * @author Vincent Guidoux
 */
public abstract class Spell implements ICard {

    protected int lastTurnUsed;
    protected int maxUse;
    protected Cell aimCell;
    protected ArrayList<Action> actions;

    protected Spell(int maxUse){
        this.maxUse = maxUse;
        lastTurnUsed = -1;
        aimCell = null;
        actions = new ArrayList<Action>();
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    @Override
    public String toString() {
        return "Spell{}";
    }

}
