package Model;

import java.util.ArrayList;

/**
 * @author Vincent Guidoux
 */
public class Cell {
    public final int x;
    public final int y;

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
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        try {
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x + ";" + y;
    }
}
