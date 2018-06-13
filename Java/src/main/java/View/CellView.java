package View;

import Controler.Controller;
import Model.Cell;
import Model.Game;
import Model.Unit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

    /**
     * Draw the unit in the cell
     * @param path, String , path to the unit sprite
     */
    public void drawImage(String path) {
        ClassLoader classLoader = getClass().getClassLoader();
        ImageIcon ii = new ImageIcon(classLoader.getResource(path));
        JLabel label = new JLabel(ii, SwingConstants.CENTER);
        add(label);
        setBounds(0,0,ii.getIconWidth(), ii.getIconHeight());
    }

    /**
     * set the background color of the cell
     * @param bgColor, color chosen for the background
     */
    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }

    /**
     * get the background color of the cell
     * @return background color
     */
    public Color getBgColor() {
        return bgColor;
    }

    /**
     * Get the corresponding cell and draw the unit
     */
    public void drawUnit() {
        Unit u = getCorrespondingCell().getCellContents();

        if (u != null) {
            drawImage(u.getPath());
        }
    }

    /**
     * Remove the image of the cell
     */
    public void removeAllImages() {
        removeAll();
    }

    /**
     * Constructor of the CellView
     * @param x,int, x position
     * @param y, int , y position
     */
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

    /**
     * @return List<Unit> all the units in a cell
     */
    public List<Unit> getUnits() {
        return units;
    }

    /**
     * Determine the event called when the mouse is clicked
     * @param mouseEvent
     */
    public void mouseClicked(MouseEvent mouseEvent) {
        Cell prevSelected = game.getSelected();
        game.setSelected(game.getMap().getCell(x, y));
        if (prevSelected != null)
            Controller.getInstance().getFrame().getGrid().at(prevSelected.x, prevSelected.y).setColorIfSelected();
        Controller.getInstance().getFrame().update();
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        setBackground(BG_HOVER);
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

        if (game.isSelected(x, y)) {
            setBackground(BG_SELECTED);
        } else {
            setBackground(bgColor);
        }
    }

    /**
     * Set the color of the cell when it is selected
     */
    public void setColorIfSelected() {
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

    /**
     * Repaint the cell
     */
    public void update() {

        setColorIfSelected();
        drawUnit();
        repaint();
    }

    /**
     * Get the corresponding cell in the model
     * @return Cell from the model with the same coordonate
     */
    public Cell getCorrespondingCell() {
        return game.getMap().getCell(x, y);
    }
}
