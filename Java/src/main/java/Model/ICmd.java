package Model;

/**
 * Interface representing an ICmd
 */
public interface ICmd {

    /**
     * Fix a condition that must be true in order for the command to be executed or undone
     * @return true if the condition is ok, false otherwise
     * @throws InterruptedException
     */
    boolean condition() throws InterruptedException;

    /**
     * Execute the action
     * @throws InterruptedException
     */
    void execute() throws InterruptedException;

    /**
     * Undo the action
     * @throws InterruptedException
     */
    void undo() throws InterruptedException;
}
