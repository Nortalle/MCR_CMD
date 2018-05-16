package Model;

import java.util.ArrayList;

/**
 * @author Vincent Guidoux
 */
public interface ICard {

    ArrayList<Action> getActions();

    String toString();
}
