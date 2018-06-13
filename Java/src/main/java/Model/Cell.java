package Model;

import Controler.Controller;
import View.CellView;

import java.awt.*;

/**
 * Class representing a Cell of the board in the model
 */
public class Cell {
    public final int x;
    public final int y;
    //attribute used to know is the cell will be repaint
    private boolean changed = false;
    //content of the cell
    private Unit content;

    /**
     * Cell constructor
     * @param , int, x position
     * @param y, int, y position
     */
    public Cell(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * getter of the cell content
     * @return Unit, unit contained in the cell
     */
    public Unit getCellContents() {
        return content;
    }

    /**
     * setter of the content
     * @param u, Unit to put in the cell
     * @return true if the unit has been added
     */
    public boolean setContent(Unit u){
        // si la case est vide ou que on essaie de la vider
        if(content == null || u == null){
            content = u;
            changed = true;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Color the cell for a short laps
     * @param c, color of the cell
     * @throws InterruptedException
     */
    public void getTouched(Color c) throws InterruptedException {
        CellView cv  = getCorrespondingCellView();
        Color savedColor = cv.getBgColor();
        cv.setBgColor(c);
        if(getCellContents() != null){
            cv.drawUnit();
        }
        changed = true;
        Controller.getInstance().getFrame().update();
        Thread.sleep(300);
        cv.setBgColor(savedColor);
        if(getCellContents() != null){
            cv.drawUnit();
        }
        changed = true;
        Controller.getInstance().getFrame().update();
    }

    /**
     * set indicates that the cell has not been used
     */
    public void setUnchanged(){
        changed = false;
    }

    @Override
    public String toString() {
        return x + ";" + y;
    }

    /**
     * @return the CellView corresponding to the cell
     */
    public CellView getCorrespondingCellView() {
        return Controller.getInstance().getFrame().getGrid().at(x, y);
    }

    /**
     * Getter of changed
     * @return true if the content of the cell or its color has changed, false otherwise
     */
    public boolean isChanged() {
        return changed;
    }
}
