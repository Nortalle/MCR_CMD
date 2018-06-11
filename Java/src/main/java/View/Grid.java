package View;

import Model.Cell;
import Model.Unit;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Grid extends JPanel {

    private ArrayList<ArrayList<CellView>> cells = new ArrayList<>();

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

    public CellView at(int i, int j) {

        return cells.get(i).get(j);
    }

    public void update() {
        for (ArrayList<CellView> rows : cells) {
            for (CellView cellView : rows) {
                Cell cell = cellView.getCorrespondingCell();

                if (cell.getCellContents() != null)
                    if (cell.getCellContents().isSelected()) {
                        cellView.setBgColor(Color.white);
                    }

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
