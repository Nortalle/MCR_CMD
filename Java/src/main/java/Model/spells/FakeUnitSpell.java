package Model.spells;

import Model.*;
import Model.units.FakeUnit;

/**
 * Spell creating a fake unit on the board
 */
public class FakeUnitSpell extends Spell {

    /**
     * Constructor of Fake Unit, adding the command that create a fake unit on the board
     */
    public FakeUnitSpell() {
        super();

        //Add fakeUnit spell command factory to action list
        actions.add(new Action(){

            public ICmd createCommand() {
                return new ICmd() {
                    boolean hasBeenSpawned = false;
                    Cell c = game.getSelected();
                    {
                        System.out.println(c.x + ":" + c.y);
                    }

                    //The spell can only be executed once per turn
                    @Override
                    public boolean condition() throws InterruptedException {
                        return true;
                    }

                    //A fake unit is created on the chosen cell if the content is still null
                    @Override
                    public void execute() throws InterruptedException {
                        if(hasBeenExecuted){
                            noMoreMana();
                        }else{
                            //We note that the spell has been executed
                            hasBeenExecuted = true;
                            FakeUnit fu = new FakeUnit(c);
                            if(c.getCellContents() != null){
                                c.setContent(fu);
                                hasBeenSpawned = true;
                            }
                        }
                    }

                    //The fake unit is destroyed
                    @Override
                    public void undo() {
                        if(hasBeenSpawned){
                            c.setContent(null);
                        }
                    }
                };
            }
            //Name of the action
            @Override
            public String toString() {
                return "Fake unit creator";
            }
        });
    }

    //return the name of the spell
    @Override
    public String toString() {
        return "Create an obstacle";
    }

}
