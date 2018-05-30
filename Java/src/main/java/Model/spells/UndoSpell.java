package Model.spells;

import Model.Action;
import Model.Cell;
import Model.ICmd;
import Model.Spell;

public class UndoSpell extends Spell {
    private int maxUse;
    private int nbUse;


    protected UndoSpell() {
        super();
        maxUse = 3;
        nbUse = 0;

        actions.add(new Action(){

            public ICmd createCommand() {
                return new ICmd() {
                    public void execute() {
                        //On effectue le undo de la dernière commande faite par l'adversaire
                        if(++nbUse == maxUse){
                            System.out.println("That spell has not more effect");
                        }
                    }

                    public void undo() {
                        //On redo l'action
                    }
                };
            }

            public String toString() {
                return "Undo Action : still " + (maxUse - nbUse) + " spells";
            }

        });
    }

    public String desctiption() {
        return "Undo the last action done by the opponent";
    }

    public boolean Invoke(Cell c) {
        return false;
    }
}
