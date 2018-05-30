package Model;

/**
 * @author Vincent Guidoux
 */
public class Map {

    private final int height;
    private final int width;

    private Cell[][] cells;

    public Map(int i, int j) {
        cells = new Cell[i][j];
        // init all cells ?
        //Pas sûre que ce soit dans le bon sens
        height = j;
        width = i;
    }

    public int height(){
        return height;
    }

    public int width(){
        return width;
    }

    public Cell getCell(int i, int j){
        return cells[i][j];
    }
}
