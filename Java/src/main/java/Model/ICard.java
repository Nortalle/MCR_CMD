package Model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author Vincent Guidoux
 */
public interface ICard {

    ArrayList<Action> getActions();
    String toString();
    String desctiption();

    boolean Invoke(Cell c);

    boolean isAlive();
}
