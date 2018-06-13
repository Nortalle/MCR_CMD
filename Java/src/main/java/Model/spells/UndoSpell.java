package Model.spells;

import Controler.Controller;
import Model.Action;
import Model.Cell;
import Model.ICmd;
import Model.Spell;
/**
 * Spell calling the undo function on the last action done
 */
public class UndoSpell extends Spell {
    /**
     * Constructor of UndoSpell adding the command to undo a spell
     */
    public UndoSpell() {
        super();

        //Add undo spell command factory to action list
        actions.add(new Action(){

            public ICmd createCommand() {
                return new ICmd() {
                    ICmd previousCMD = Controller.getInstance().getPreviousCmd();


                    //there's no need for a unit to be alive in order to launch the spell
                    @Override
                    public boolean condition() throws InterruptedException {
                        return true;
                    }

                    //Undo the last command. The undo spell doesn't work on a previous list of commands
                    @Override
                    public void execute() throws InterruptedException {
                        //The spell can only be called once per turn and per player
                        if(hasBeenExecuted) {
                            noMoreMana();
                        }else {
                            //we save the command
                            hasBeenExecuted = true;
                            //We get the previous command in the list
                            previousCMD = Controller.getInstance().getPreviousCmd();
                            if (previousCMD != null && previousCMD.condition()) {
                                previousCMD.undo();
                            }
                        }
                    }

                    //redo the action previously undone
                    @Override
                    public void undo() throws InterruptedException {
                        if (previousCMD != null && previousCMD.condition()) {
                            previousCMD.execute();
                        }
                    }

                    //Name of the command
                    @Override
                    public String toString() {
                        return "Undo ";
                    }
                };
            }
            //Name of the action
            @Override
            public String toString() {
                return "Undo last action" ;
            }

        });
    }
    //Name of the spell
    @Override
    public String toString() {
        return "Undo Spell";
    }
}
