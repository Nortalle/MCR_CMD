package Model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author Vincent Guidoux
 */
public interface ICard {

    ArrayList<Action> getActions();

    String toString();

    boolean isAlive();

    public void setSelected(boolean isSelected);

    public boolean isSelected();

    boolean attackCell(int offsetX, int offsetY, int damage, Color c) throws InterruptedException;
}
