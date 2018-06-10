package Model;

import Controler.Controller;

import java.util.ArrayList;

/**
 * @author Vincent Guidoux
 */
public abstract class Spell implements ICard {

    protected boolean hasBeenExecuted;
    protected int lastTurnUsed;
    protected Cell aimCell;
    protected ArrayList<Action> actions;
    protected Game game = Controller.getInstance().game();

    protected Spell(){
        lastTurnUsed = -1;
        hasBeenExecuted = false;
        aimCell = null;// TODO FIX
        actions = new ArrayList<>();
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    @Override
    public String toString() {
        return "Spell{}";
    }

    protected Cell attackCell(int offsetX, int offsetY, int damage){
        if(game.getMap().getCell(aimCell.x + offsetX,aimCell.y + offsetY) != null) {
            if (game.getMap().getCell(aimCell.x + offsetX, aimCell.y + offsetY).getCellContents() != null) {
                game.getMap().getCell(aimCell.x + offsetX, aimCell.y + offsetY).getCellContents().takeDamage(damage);
                return new Cell(aimCell.x + offsetX, aimCell.y + offsetY);
            }
        }
        return null;
    }

    protected Cell healCell(int offsetX, int offsetY, int heal){
        if(game.getMap().getCell(aimCell.x + offsetX,aimCell.y + offsetY).getCellContents() != null){
            game.getMap().getCell(aimCell.x + offsetX,aimCell.y + offsetY).getCellContents().takeHeal(heal);
            return new Cell(aimCell.x + offsetX, aimCell.y + offsetY);
        }
        return null;
    }

    @Override
    public boolean isAlive(){
        return false;
    }

    protected void noMoreMana(){
        System.out.println("No more mana for the spell");
    }

    public void setHasBeenExecuted(boolean hasBeenExecuted) {
        this.hasBeenExecuted = hasBeenExecuted;
    }
}
