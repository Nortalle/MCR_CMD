package Model.spells;

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
                        FakeUnit fu = new FakeUnit();
                        if(Game.getInstance().selected().getCellContents() != null){
                            Game.getInstance().selected().setContent(fu);
                            c = Game.getInstance().selected();
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
