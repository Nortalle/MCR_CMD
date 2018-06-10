package Model;

/**
 * @author Vincent Guidoux
 */
public interface ICmd {

    boolean condition() throws InterruptedException;

    void execute() throws InterruptedException;

    void undo() throws InterruptedException;
}
