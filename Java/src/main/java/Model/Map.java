package Model;

/**
 * @author Vincent Guidoux
 */
public class Map {

    private Cell[][] cells;
    public Map() {

    }

    public Cell getCell(int i, int j){
        return cells[i][j];
    }
}
