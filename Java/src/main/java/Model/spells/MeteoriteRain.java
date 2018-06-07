package Model.spells;

import Model.*;

import java.util.ArrayList;
import java.util.Random;

public class MeteoriteRain extends Spell {
    private Random random;
    private int distanceMax;

    protected MeteoriteRain() {
        super();
        random = new Random();
        distanceMax = 5;

        actions.add(new Action() {
            @Override
            public ICmd createCommand() {

                return new ICmd() {
                    ArrayList<Cell> touchedCells = new ArrayList<Cell>();
                    public void execute() {
                        if(lastTurnUsed == Game.getInstance().turn()){
                            System.out.println("No more mana...");
                            lastTurnUsed = Game.getInstance().turn();
                            hasBeenExecuted = false;
                        } else {
                            int x;
                            int y;
                            do{
                                x = random.nextInt(distanceMax);
                            }while((Game.getInstance().getSelected().x + x) > Game.getInstance().getMap().width());
                            do{
                                y = random.nextInt(distanceMax);
                            }while((Game.getInstance().getSelected().y + y) > Game.getInstance().getMap().height());
                            Cell c = attackCell(0,0,100);
                            if(c != null){touchedCells.add(c);}
                            c = attackCell(1,0,100);
                            if(c != null){touchedCells.add(c);}
                            c = attackCell(-1,0,100);
                            if(c != null){touchedCells.add(c);}
                            c = attackCell(0,1,100);
                            if(c != null){touchedCells.add(c);}
                            c = attackCell(0,-1,100);
                            if(c != null){touchedCells.add(c);}

                            //Enlève les pv de certaines unités
                            hasBeenExecuted = true;
                        }
                    }

                    public void undo() {
                        if(hasBeenExecuted){
                            for(Cell c: touchedCells){
                                if(c.getCellContents() != null){
                                    c.getCellContents().takeHeal(100);
                                }
                            }
                        }else{
                            System.out.println("Nothing to undo");
                        }
                    }
                };
            }

            @Override
            public String toString() {
                return "Meteor Rain";
            }
        });

    }

    public String desctiption() {
        return "The Meteor Rain attack a random cells in a range";
    }

    public boolean Invoke(Cell c) {
        return false;
    }
}
