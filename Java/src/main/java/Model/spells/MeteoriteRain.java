package Model.spells;

import Model.*;

import java.util.ArrayList;
import java.util.Random;

public class MeteoriteRain extends Spell {
    private Random random;
    private int distanceMax;

    protected MeteoriteRain() {
        //Maxuse à -1 signifie que le sort est infini
        super(-1);
        random = new Random();
        distanceMax = 5;

        actions.add(new Action() {
            @Override
            public ICmd createCommand() {

                return new ICmd() {
                    boolean hasBeenExecuted = false;
                    ArrayList<Cell> touchedCells = new ArrayList<Cell>();
                    public void execute() {
                        if(lastTurnUsed == Game.getInstance().turn()){
                            System.out.println("No more mana...");
                            hasBeenExecuted = false;
                        } else {
                            int x;
                            int y;
                            do{
                                x = random.nextInt(distanceMax);
                            }while((Game.getInstance().selected().x + x) > Game.getInstance().getMap().width());
                            do{
                                y = random.nextInt(distanceMax);
                            }while((Game.getInstance().selected().y + y) > Game.getInstance().getMap().height());
                            

                            //Enlève les pv de certaines unités
                            hasBeenExecuted = true;
                        }
                    }

                    public void undo() {
                        if(hasBeenExecuted){
                            for(Cell c: touchedCells){
                                //Rend les pv à toutes les unitées touchées par la météorite
                            }
                        }else{
                            System.out.println("Nothing to undo");
                        }
                    }
                };
            }

            @Override
            public String toString() {
                return "Meteor Raaaaaain!";
            }
        });

    }

    public String desctiption() {
        return null;
    }

    public boolean Invoke(Cell c) {
        return false;
    }
}
