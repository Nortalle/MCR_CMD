package Model;

import Controler.Controller;
import View.CellView;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Vincent Guidoux
 */
public class Cell {
    public final int x;
    public final int y;
    private boolean changed = false;

    public Cell(int x, int y){
        this.x = x;
        this.y = y;
    }

    private Unit content;

    public Unit getCellContents() {
        return content;
    }

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

    public void setUnchanged(){
        changed = false;
    }

    @Override
    public String toString() {
        return x + ";" + y;
    }

    public CellView getCorrespondingCellView() {
        return Controller.getInstance().getFrame().getGrid().at(x, y);
    }

    public boolean isChanged() {
        return changed;
    }
}
