package Model.spells;

import Controler.Controller;
import Model.*;
import Model.units.FakeUnit;

public class FakeUnitSpell extends Spell {

    public FakeUnitSpell() {
        super();

        actions.add(new Action(){

            public ICmd createCommand() {
                return new ICmd() {
                    Cell c = game.getSelected();
                    {
                        System.out.println(c.x + ":" + c.y);
                    }

                    public void execute() {
                        if(hasBeenExecuted){
                            noMoreMana();
                        }else{
                            hasBeenExecuted = true;
                            System.out.println(c.x + ":" + c.y);
                            Game game = Controller.getInstance().game();
                            FakeUnit fu = new FakeUnit(c);
                            if(c.getCellContents() != null){
                                c.setContent(fu);
                            } else {
                                System.out.println("The fake unit couldn't be add");
                            }
                        }
                    }

                    public void undo() {
                        c.setContent(null);
                    }
                };
            }

            public String toString() {
                return "Fake unit creator";
            }
        });
    }

    @Override
    public String toString() {
        return "Create an obstacle";
    }

    public String desctiption() {
        return "Create and place a fake unit on the map";
    }

    public boolean Invoke(Cell c) {
        return false;
    }
}
