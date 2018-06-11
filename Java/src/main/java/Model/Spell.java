package Model;

import Controler.Controller;

import java.awt.*;
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


    protected boolean isSelected;

    protected Spell(){
        lastTurnUsed = -1;
        hasBeenExecuted = false;
        aimCell = null;// TODO FIX
        actions = new ArrayList<>();
        this.isSelected = false;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    @Override
    public String toString() {
        return "Spell{}";
    }

    public void setAimCell(Cell c){
        aimCell = c;
    }


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

    /*
    public Cell attackCell(Cell cell, int offsetX, int offsetY, int damage){
        if(game.getMap().getCell(cell.x + offsetX,cell.y + offsetY) != null) {
            if (game.getMap().getCell(cell.x + offsetX, cell.y + offsetY).getCellContents() != null) {
                game.getMap().getCell(cell.x + offsetX, cell.y + offsetY).getCellContents().takeDamage(damage);
                return new Cell(cell.x + offsetX, cell.y + offsetY);
            }
        }
        return null;
    }
    */

    /*
    protected Cell healCell(int offsetX, int offsetY, int heal){
        if(game.getMap().getCell(aimCell.x + offsetX,aimCell.y + offsetY).getCellContents() != null){
            game.getMap().getCell(aimCell.x + offsetX,aimCell.y + offsetY).getCellContents().takeDamage(-heal);
            return new Cell(aimCell.x + offsetX, aimCell.y + offsetY);
        }
        return null;
    }
    */

    @Override
    public boolean isAlive(){
        return false;
    }

    protected void noMoreMana(){
        System.out.println("No more mana for the spell");
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setHasBeenExecuted(boolean hasBeenExecuted) {
        this.hasBeenExecuted = hasBeenExecuted;
    }
}
