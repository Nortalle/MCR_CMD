package Model;

/**
 * @author Vincent Guidoux
 */
public interface ICmd {

    public abstract void execute();

    public abstract void undo();
}
