package Model;

/**
 * Abstract class representing factory of a specific command
 */
public abstract class Action {

    /**
     * Creator of a command
     * @return an instance of ICMD
     */
    public abstract ICmd createCommand();

    /**
     * Name of the action
     * @return name of the action
     */
    public abstract String toString();
}
