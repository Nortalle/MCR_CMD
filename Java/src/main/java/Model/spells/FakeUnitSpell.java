package Model.spells;

import Controler.Controller;
import Model.*;
import Model.units.FakeUnit;

public class FakeUnitSpell extends Spell {

    protected FakeUnitSpell() {
        super();

        actions.add(new Action(){

            public ICmd createCommand() {
                return new ICmd() {
                    Cell c = null;

                    public void execute() {
                        Game game = Controller.getInstance().game();
                        FakeUnit fu = new FakeUnit(game.getSelected());
                        if(game.getSelected().getCellContents() != null){
                            game.getSelected().setContent(fu);
                            c = game.getSelected();
                        } else {
                            System.out.println("The fake unit couldn't be add");
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

    public String desctiption() {
        return "Create and place a fake unit on the map";
    }

    public boolean Invoke(Cell c) {
        return false;
    }
}
