package View;

import Controler.Controller;
import Model.Cell;
import Model.Game;
import Model.Unit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CellView extends JPanel implements MouseListener {

    static final Color BG_LIGHT = new Color(129, 186, 68);
    static final Color BG_DARK = new Color(72, 156, 76);
    static final Color BG_HOVER = new Color(255, 255, 0);
    static final Color BG_SELECTED = new Color(0, 207, 255);

    private Game game;

    private Color bgColor;

    ArrayList<Unit> units = new ArrayList<>();

    private final int x, y;

    public void drawImage(String path) {
        ClassLoader classLoader = getClass().getClassLoader();
        ImageIcon ii = new ImageIcon(classLoader.getResource(path));
        JLabel label = new JLabel(ii, SwingConstants.CENTER);
        add(label);
        setBounds(0,0,ii.getIconWidth(), ii.getIconHeight());
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }

    public Color getBgColor() {
        return bgColor;
    }

    public void removeImage(BufferedImage bi) {
        //TODO
        System.err.println("Still not implemented");

        Component[] components = getComponents();
        for (Component c : components) {
            if (((JLabel) c).getIcon() == bi) {
                remove(c);
                break;
            }
        }
    }

    public void drawUnit() {
        Unit u = getCorrespondingCell().getCellContents();

        if (u != null) {
            drawImage(u.getPath());
        }
    }

    public void removeAllImages() {
        removeAll();
    }

    public CellView(int x, int y) {

        this.game = Controller.getInstance().game();

        this.x = x;
        this.y = y;

        if (y % 2 == 0 && x % 2 == 0 || y % 2 == 1 && x % 2 == 1)
            bgColor = BG_DARK;
        else
            bgColor = BG_LIGHT;

        setBackground(bgColor);
        addMouseListener(this);
    }

    public void refresh() {
        update();
    }
/*
    @Override
    public void repaint(){

        if(Game.getInstance().isSelected(x, y)){
            setBackground(BG_SELECTED);
        } else {
            setBackground(bgColor);
        }
    }*/

    public List<Unit> getUnits() {

        return units;
    }



    public void mouseClicked(MouseEvent mouseEvent) {
        Cell prevSelected = game.getSelected();
        game.setSelected(game.getMap().getCell(x, y));
        if (prevSelected != null)
            Controller.getInstance().getFrame().getGrid().at(prevSelected.x, prevSelected.y).setColor();
        Controller.getInstance().getFrame().update();
    }

    public void mousePressed(MouseEvent mouseEvent) {
    }

    public void mouseReleased(MouseEvent mouseEvent) {
    }

    public void mouseEntered(MouseEvent mouseEvent) {
        setBackground(BG_HOVER);
    }

    public void mouseExited(MouseEvent mouseEvent) {

        if (game.isSelected(x, y)) {
            setBackground(BG_SELECTED);
        } else {
            setBackground(bgColor);
        }
    }

    public void setColor() {
        if (game.isSelected(x, y)) {
            setBackground(BG_SELECTED);
        } else {
            setBackground(bgColor);
        }
    }

    @Override
    public String toString() {
        return x + ";" + y;
    }

    public void update() {

        setColor();
        drawUnit();
        repaint();
    }

    public Cell getCorrespondingCell() {
        return game.getMap().getCell(x, y);
    }
}
