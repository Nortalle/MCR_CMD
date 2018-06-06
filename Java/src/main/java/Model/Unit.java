package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Vincent Guidoux
 *
 */
public abstract class Unit implements ICard {

    protected int hp;
    protected int hpMax;

    protected int speed;

    protected Cell currentCell;

    protected ArrayList<Action> actions;

    protected Unit(int hpMax, int speed, Cell startPos){
        Unit.this.speed = speed;
        this.hpMax = hpMax;
        this.currentCell = startPos;
        actions = new ArrayList<Action>();

        // Ajout de l'action de déplacement en direction de la case sélectionnée
        actions.add(new Action() {
            @Override
            public ICmd createCommand() {

                // la commande est crée au moment ou on sélectionne l'action
                return new ICmd() {

                    // récupère la case sélectionnée au moment ou on crée la commande
                    Cell destination = Game.getInstance().selected();

                    Cell depart = currentCell;

                    public void execute() {

                        // tant qu'on peut avancer et que on est pas arrivé à destination
                        for(int i = 0; i < Unit.this.speed || destination != currentCell; i++){
                            int deltaX = destination.x - currentCell.x;
                            int deltaY = destination.y - currentCell.y;

                            // axe X est le plus loin on va aller en direction de X en premier
                            if(Math.abs(deltaX) > Math.abs(deltaY)){

                                if(deltaX > 0){
                                    if(!move(Game.getInstance().getMap().getCell(currentCell.x + 1, currentCell.y))){
                                        break;      // interrupton du déplacement qui a échoué
                                    }
                                } else {
                                    if(!move(Game.getInstance().getMap().getCell(currentCell.x - 1, currentCell.y))){
                                        break;      // interrupton du déplacement qui a échoué
                                    }
                                }

                            }
                            // sinon on va bouger sur Y
                            else {

                                if(deltaY > 0){
                                    if(!move(Game.getInstance().getMap().getCell(currentCell.x, currentCell.y + 1))){
                                        break;      // interrupton du déplacement qui a échoué
                                    }
                                } else {
                                    if(!move(Game.getInstance().getMap().getCell(currentCell.x, currentCell.y - 1))){
                                        break;      // interrupton du déplacement qui a échoué
                                    }
                                }
                            }
                        }
                    }

                    public void undo() {
                        move(depart);   // on se téléporte au départ
                    }
                };
            }
            @Override
            public String toString() {
                return "Move towards cell " + Game.getInstance().selected();
            }
        });

    }

    public void displayUnit(){
        //currentCell.
    }

    public BufferedImage getSprite() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(getPath()).getFile());
        BufferedImage bf = ImageIO.read(file);
        return bf;
    }

    abstract public String getPath();


    protected boolean move(Cell c){
        if(c.setContent(Unit.this)){
            currentCell.setContent(null);
            currentCell = c;
            return true;
        } else {
            return false;
        }
    }

    public void takeDamage(int damage){
        hp -= damage;
        if(hp <= 0){
            currentCell.setContent(null);
            System.out.println("The unit died");
            //TODO on veut qu'il se passe quoi quand on meurt ? Et si on annule ?
            //TODO Retirer l'unité de la liste des unités du joueur
        }
    }
    public void takeHeal(int heal){
        hp += heal;
        if(hp > hpMax){
            hp = hpMax;
        }
    }

    protected boolean attackCell(int offsetX, int offsetY, int damage){
        if(currentCell.x + offsetX <= Game.getInstance().getMap().width() && currentCell.y + offsetY <= Game.getInstance().getMap().height() &&
                (Game.getInstance().getMap().getCell(currentCell.x + offsetX,currentCell.y + offsetY).getCellContents() != null)){
            Game.getInstance().getMap().getCell(currentCell.x + offsetX,currentCell.y + offsetY).getCellContents().takeDamage(damage);
            return true;
        }
        return false;
    }

    protected boolean healCell(int offsetX, int offsetY, int heal){
        if(currentCell.x + offsetX <= Game.getInstance().getMap().width() && currentCell.y + offsetY <= Game.getInstance().getMap().height() &&
                (Game.getInstance().getMap().getCell(currentCell.x + offsetX,currentCell.y + offsetY).getCellContents() != null)){
            Game.getInstance().getMap().getCell(currentCell.x + offsetX,currentCell.y + offsetY).getCellContents().takeHeal(heal);
            return true;
        }
        return false;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    protected int deltaXToCursor(){
        return Game.getInstance().selected().x - currentCell.x;
    }

    protected int deltaYToCursor(){
        return Game.getInstance().selected().y - currentCell.y;
    }

    @Override
    public boolean isAlive(){
        return hp > 0;
    }
}
