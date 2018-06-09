package Model;

/**
 * @author Vincent Guidoux
 */
public interface ICmd {

    void execute() throws InterruptedException;

    void undo() throws InterruptedException;
}
