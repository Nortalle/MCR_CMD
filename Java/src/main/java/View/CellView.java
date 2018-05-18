package View;

import Model.Unit;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CellView extends JPanel {

    ArrayList<Unit> units   = new ArrayList<Unit>();

    public CellView(int x, int y) {

        if(y % 2 == 0 && x % 2 == 0 || y % 2 == 1 && x % 2 == 1)
            setBackground(new Color(210,210,210));
        else
            setBackground(new Color(230,230,230));
    }

    public void refresh() {

    }

    public List<Unit> getUnits() {

        return units;
    }
}
