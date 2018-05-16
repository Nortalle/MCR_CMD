package Model;

/**
 * @author Vincent Guidoux
 */
public interface ICmd {

    void execute();

    void undo();
}
