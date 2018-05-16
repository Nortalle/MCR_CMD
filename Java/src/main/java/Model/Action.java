package Model;

/**
 * @author Vincent Guidoux
 */
public abstract class Action {

    public abstract ICmd createCommand();

    public abstract String toString();
}
