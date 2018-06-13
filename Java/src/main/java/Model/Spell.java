package Model;

import Controler.Controller;

import java.awt.*;
import java.util.ArrayList;

/**
 * Abstract class representing a spell
 */
public abstract class Spell implements ICard {

    protected boolean hasBeenExecuted;
    protected int lastTurnUsed;
    protected Cell aimCell;
    protected ArrayList<Action> actions;
    protected Game game = Controller.getInstance().game();

    //indicates wether the spell is selected or not
    protected boolean isSelected;

    //Constructor of the spell
    protected Spell(){
        lastTurnUsed = -1;
        hasBeenExecuted = false;
        aimCell = null;
        actions = new ArrayList<>();
        this.isSelected = false;
    }

    /**
     * @return ArrayList<Action> a list of actions the spell can do
     */
    public ArrayList<Action> getActions() {
        return actions;
    }

    @Override
    public String toString() {
        return "Spell{}";
    }

    /**
     * set the aimed cell
     * @param c the cell the player wants to aim
     */
    public void setAimCell(Cell c){
        aimCell = c;
    }

    /**
     * Attack a cell
     * @param offsetX, x offset of the attack
     * @param offsetY, y offset of the attack
     * @param damage, damage done to the unit on the cell
     * @param c color of the cell during the attack
     * @return true if a unit has been touched by the spell, false otherwise
     * @throws InterruptedException
     */
    public boolean attackCell(int offsetX, int offsetY, int damage, Color c) throws InterruptedException {
        System.out.println(game.getMap());
        System.out.println(aimCell);
        if(game.getMap().getCell(aimCell.x + offsetX,aimCell.y + offsetY) != null) {
            game.getMap().getCell(aimCell.x + offsetX,aimCell.y + offsetY).getTouched(c);
            if (game.getMap().getCell(aimCell.x + offsetX, aimCell.y + offsetY).getCellContents() != null) {
                game.getMap().getCell(aimCell.x + offsetX, aimCell.y + offsetY).getCellContents().takeDamage(damage);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isAlive(){
        return false;
    }

    /**
     * Indicates that there is no more mana for the spell
     */
    protected void noMoreMana(){
        System.out.println("No more mana for the spell");
    }

    @Override
    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    /**
     * Determine wether a spell has already been executed for the turn 
     * @param hasBeenExecuted
     */
    public void setHasBeenExecuted(boolean hasBeenExecuted) {
        this.hasBeenExecuted = hasBeenExecuted;
    }
}
