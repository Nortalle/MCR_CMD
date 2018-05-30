package Model;

import java.util.ArrayList;

/**
 * @author Vincent Guidoux
 */
public abstract class Spell implements ICard {

    protected boolean hasBeenExecuted;
    protected int lastTurnUsed;
    protected Cell aimCell;
    protected ArrayList<Action> actions;

    protected Spell(){
        lastTurnUsed = -1;
        hasBeenExecuted = false;
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

    protected Cell attackCell(int offsetX, int offsetY, int damage){
        if(Game.getInstance().getMap().getCell(aimCell.x + offsetX,aimCell.y + offsetY).getCellContents() != null){
            Game.getInstance().getMap().getCell(aimCell.x + offsetX,aimCell.y + offsetY).getCellContents().takeDamage(damage);
            return new Cell(aimCell.x + offsetX, aimCell.y + offsetY);
        }
        return null;
    }

    protected Cell healCell(int offsetX, int offsetY, int heal){
        if(Game.getInstance().getMap().getCell(aimCell.x + offsetX,aimCell.y + offsetY).getCellContents() != null){
            Game.getInstance().getMap().getCell(aimCell.x + offsetX,aimCell.y + offsetY).getCellContents().takeHeal(heal);
            return new Cell(aimCell.x + offsetX, aimCell.y + offsetY);
        }
        return null;
    }



}
