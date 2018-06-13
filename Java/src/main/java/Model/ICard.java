package Model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Interface representing a ICard
 */
public interface ICard {

    /**
     * Get the list of possible actions
     * @return ArrayList<Action> the list of possible actions
     */
    ArrayList<Action> getActions();

    /**
     * Represent the ICard as a String
     * @return the String describing the ICard
     */
    String toString();

    /**
     * Check if the card is Alive
     * @return
     */
    boolean isAlive();

    /**
     * determine wether an ICard is selected or not
     * @param isSelected boolean
     */
    void setSelected(boolean isSelected);

    /**
     * Attack a cell
     * @param offsetX, x offset of the attack
     * @param offsetY, y offset of the attack
     * @param damage, damage done to the unit on the cell
     * @param c color of the cell during the attack
     * @return true if a unit has been touched by the attack ,false otherwise
     * @throws InterruptedException
     */
    boolean attackCell(int offsetX, int offsetY, int damage, Color c) throws InterruptedException;
}
