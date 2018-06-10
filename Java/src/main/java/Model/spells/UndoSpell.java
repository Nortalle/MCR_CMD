package Model.spells;

import Controler.Controller;
import Model.Action;
import Model.Cell;
import Model.ICmd;
import Model.Spell;

public class UndoSpell extends Spell {


    public UndoSpell() {
        super();

        actions.add(new Action(){

            public ICmd createCommand() {
                return new ICmd() {
                    ICmd previousCMD = Controller.getInstance().getPreviousCmd();

                    @Override
                    public boolean condition() throws InterruptedException {
                        return true;
                    }

                    public void execute() throws InterruptedException {
                        if(hasBeenExecuted) {
                            noMoreMana();
                        }else {
                            //we save the command
                            hasBeenExecuted = true;
                            previousCMD = Controller.getInstance().getPreviousCmd();
                            if (previousCMD != null && previousCMD.condition()) {
                                previousCMD.undo();
                            }
                        }
                    }

                    public void undo() throws InterruptedException {
                        previousCMD.execute();
                    }
                };
            }

            public String toString() {
                return "Undo Action";
            }

        });
    }

    @Override
    public String toString() {
        return "Undo Action";
    }

    public String desctiption() {
        return "Undo the last action done by the opponent";
    }

    public boolean Invoke(Cell c) {
        return false;
    }
}
