package View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Class representing the grid of the map
 */
public class Grid extends JPanel {

    private ArrayList<ArrayList<CellView>> cells = new ArrayList<>();

    /**
     * Constructor of the Grid
     * @param rows
     * @param columns
     */
    public Grid(int rows, int columns) {

        setLayout(new GridLayout(0, columns));

        for (int i = 0; i < rows; ++i) {

            ArrayList<CellView> row = new ArrayList<>();

            for (int j = 0; j < columns; ++j) {

                CellView cell = new CellView(i, j);
                add(cell);
                row.add(cell);
            }

            cells.add(row);
        }
    }

    /**
     * Get a CellView instance located at the given coordonates
     * @param i
     * @param j
     * @return
     */
    public CellView at(int i, int j) {
        return cells.get(i).get(j);
    }

    /**
     * Update the grid
     */
    public void update() {
        for (ArrayList<CellView> rows : cells) {
            for (CellView cellView : rows) {
                if (cellView.getCorrespondingCell().isChanged()) {
                    cellView.removeAllImages();

                    cellView.update();

                    cellView.getCorrespondingCell().setUnchanged();
                }
            }
        }

        repaint();
    }
}
