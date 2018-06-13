package Model;

/**
 * Class representing a Map
 */
public class Map {

    private final int height;
    private final int width;
    private Cell[][] cells;

    /**
     * Constructor of the Map
     * @param i int, weight of the map
     * @param j int, height of the map
     */
    public Map(int i, int j) {
        cells = new Cell[i][j];

        for(int x = 0; x < i; ++x){
            for(int y = 0; y < j; ++y){
                cells[x][y] = new Cell(x, y);
            }
        }
        height = j;
        width = i;
    }

    /**
     * @return int, the height of the map
     */
    public int height(){
        return height;
    }

    /**
     * @return int, the width of the map
     */
    public int width(){
        return width;
    }

    /**
     * Return the cell of the map at the give coordonates
     * @param i, int x position of the cell
     * @param j, int y position of the cell
     * @return
     */
    public Cell getCell(int i, int j){
        try {
            return cells[i][j];
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

}
