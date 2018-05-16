package Model;

/**
 * @author Vincent Guidoux
 */
public class Action {

    public ICmd createCommand() {
        return new ICmd() {
            public void execute() {
            }

            public void undo() {
            }
        };
    }

    public String toString() {
        return "cunni";
    }
}
