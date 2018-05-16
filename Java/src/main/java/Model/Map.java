package Model;

/**
 * @author Vincent Guidoux
 */
public class Map {

    private Cell[][] cells;

    public Map(int i, int j) {
        cells = new Cell[i][j];
        // init all cells ?
    }

    public Cell getCell(int i, int j){
        return cells[i][j];
    }
}
