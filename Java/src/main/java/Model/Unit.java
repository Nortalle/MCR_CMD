package Model;

import Controler.Controller;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Function;

/**
 * Abstract class representing a unit
 */
public abstract class Unit implements ICard {
    protected String name;
    private String sprite;
    protected int hp;
    protected int hpMax;
    protected int speed;
    protected int damage;
    protected Cell currentCell;
    protected ArrayList<Action> actions;
    protected Game game = Controller.getInstance().game();
    protected boolean isSelected;

    /**
     * Constructor of the unit
     * @param hpMax
     * @param speed
     * @param startPos
     * @param name
     * @param sprite
     */
    protected Unit(int hpMax, int speed, Cell startPos, String name, String sprite){
        Unit.this.speed = speed;
        this.hpMax = hpMax;
        this.hp = hpMax;
        this.currentCell = startPos;
        this.name = name;
        this.sprite = sprite;
        this.isSelected = false;
        this.damage = 0;
        startPos.setContent(this);

        actions = new ArrayList<>();

        // Ajout de l'action de déplacement en direction de la case sélectionnée
        actions.add(new Action() {
            @Override
            public ICmd createCommand() {

                // la commande est crée au moment ou on sélectionne l'action
                return new ICmd() {

                    // récupère la case sélectionnée au moment ou on crée la commande
                    Cell destination = game.getSelected();

                    Cell depart = currentCell;

                    private boolean moveX(int i) {
                        boolean b = false;
                        if(i > 0) b = moveNorth();
                        else if(i < 0) b = moveSouth();
                        return b;
                    }

                    private boolean moveY(int i) {
                        boolean b = false;
                        if(i > 0) b = moveEast();
                        else if(i < 0) b = moveWest();
                        return b;
                    }

                    private boolean moveNorth() {
                        return move(game.getMap().getCell(currentCell.x + 1, currentCell.y));
                    }

                    private boolean moveSouth() {
                        return move(game.getMap().getCell(currentCell.x - 1, currentCell.y));
                    }

                    private boolean moveEast() {
                        return move(game.getMap().getCell(currentCell.x, currentCell.y + 1));
                    }

                    private boolean moveWest() {
                        return move(game.getMap().getCell(currentCell.x, currentCell.y - 1));
                    }

                    @Override
                    public boolean condition() throws InterruptedException {
                        return hp > 0;
                    }

                    @Override
                    public void execute() {

                        // tant qu'on peut avancer et que on est pas arrivé à destination
                        for(int i = 0; i < Unit.this.speed && destination != currentCell; i++){
                            int deltaX = destination.x - currentCell.x;
                            int deltaY = destination.y - currentCell.y;

                            if(Math.abs(deltaX) > Math.abs(deltaY)) {
                                if(!moveX(deltaX)) {
                                    moveY(deltaY);
                                }
                            } else {
                                if(!moveY(deltaY)) {
                                    moveX(deltaX);
                                }
                            }
                        }
                    }

                    @Override
                    public void undo() {
                        move(depart);   // on se téléporte au départ
                    }

                    @Override
                    public String toString() {
                        return "Move towards cell " + destination;
                    }
                };
            }
            @Override
            public String toString() {
                return "Move towards cell " + game.getSelected();
            }
        });

    }

    /**
     * @return the path to the sprite of the unit
     */
    public String getPath(){
        return sprite;
    }

    //move a unit towards a cell
    protected boolean move(Cell c){
        if(c.setContent(Unit.this)){
            currentCell.setContent(null);
            currentCell = c;
            return true;
        } else {
            return false;
        }
    }

    /**
     * change the hp of a unit according to the damage amount
     * @param damage
     */
    public void takeDamage(int damage){
        hp -= damage;
        if(hp <= 0){
            currentCell.setContent(null);
            System.out.println("The unit died");
        }else if(hp > hpMax){
            hp = hpMax;
        }
    }

    /**
     * Attack a cell
     * @param offsetX, x offset of the attack
     * @param offsetY, y offset of the attack
     * @param damage, damage done to the unit on the cell
     * @param c color of the cell during the attack
     * @return true if a unit has been touched by the attack, false otherwise
     * @throws InterruptedException
     */
    public boolean attackCell(int offsetX, int offsetY, int damage, Color c) throws InterruptedException {
        if(currentCell.x + offsetX < game.getMap().width() && currentCell.y + offsetY < game.getMap().height() && currentCell.x + offsetX >= 0 && currentCell.y + offsetY >= 0) {
            game.getMap().getCell(currentCell.x + offsetX, currentCell.y + offsetY).getTouched(c);
            if (game.getMap().getCell(currentCell.x + offsetX, currentCell.y + offsetY).getCellContents() != null) {
                game.getMap().getCell(currentCell.x + offsetX, currentCell.y + offsetY).getCellContents().takeDamage(damage);
                return true;
            }
            return false;
        }
        return false;
    }

    //A player cannot do an action if its hp are below 0
    protected boolean getActionCondition(){
        return hp > 0;
    }

    /**
     * @return ArrayList<Action> the list of the possible actions
     */
    public ArrayList<Action> getActions() {
        return actions;
    }

    /**
     * @return int, the hp of the unit
     */
    public int getHp() {
        return hp;
    }

    /**
     * @return int, the hp max of the unit
     */
    public int getHpMax() {
        return hpMax;
    }

    @Override
    public void setSelected(boolean isSelected){
        this.isSelected = isSelected;
    }

    @Override
    public boolean isAlive(){
        return hp > 0;
    }
}
