package View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Grid extends JPanel {

    ArrayList<ArrayList<CellView>> cells = new ArrayList<ArrayList<CellView>>();

    public Grid(int rows, int columns) {

        setLayout(new GridLayout(0, columns));

        for(int i = 0; i < rows; ++i) {

            ArrayList<CellView> row = new ArrayList<CellView>();

            for (int j = 0; j < columns; ++j) {

                CellView cell = new CellView(i, j);
                add(cell);
                row.add(cell);
            }

            cells.add(row);
        }
    }

    public CellView at(int i, int j) {

        return cells.get(i).get(j);
    }
}
