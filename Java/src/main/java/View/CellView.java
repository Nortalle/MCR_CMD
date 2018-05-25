package View;

import Model.Unit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class CellView extends JPanel implements MouseListener {

    static final Color BG_LIGHT = new Color(230,230,230);
    static final Color BG_DARK  = new Color(210,210,210);
    static final Color BG_HOVER = new Color(255, 255, 0);

    private Color bgColor;

    ArrayList<Unit> units   = new ArrayList<Unit>();

    public CellView(int x, int y) {

        if(y % 2 == 0 && x % 2 == 0 || y % 2 == 1 && x % 2 == 1)
            bgColor = BG_DARK;
        else
            bgColor = BG_LIGHT;

        setBackground(bgColor);
        addMouseListener(this);
    }

    public void refresh() {

    }

    public List<Unit> getUnits() {

        return units;
    }

    public void mouseClicked(MouseEvent mouseEvent) {
        System.out.println("clicked");
    }

    public void mousePressed(MouseEvent mouseEvent) {
        System.out.println("pressed");
    }

    public void mouseReleased(MouseEvent mouseEvent) {
        System.out.println("released");
    }

    public void mouseEntered(MouseEvent mouseEvent) {
        setBackground(BG_HOVER);
    }

    public void mouseExited(MouseEvent mouseEvent) {
        setBackground(bgColor);
    }
}
