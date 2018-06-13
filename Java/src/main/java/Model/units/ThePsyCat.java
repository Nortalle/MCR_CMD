package Model.units;

import Model.*;

import java.awt.*;
import java.util.Random;

/**
 * Class representing the Giant Cat
 */
public class ThePsyCat extends Unit {

    /**
     * Constructor of the PsyCat
     * @param start cell representing the start position of the unit
     * @param name name of the cat
     * @param sprite path to the sprite of the cat
     */
    public ThePsyCat(Cell start, String name, String sprite) {
        super(200, 3, start, name, sprite);
        damage = 100;

        //Add right cat attack command factory to action list
        actions.add(new Action() {
            @Override
            public ICmd createCommand() {
                return new ICmd() {
                    String hasAttacked = "";
                    Random random = new Random();
                    boolean lastActionWasAnAttack = false;

                    @Override
                    public boolean condition() throws InterruptedException {
                        return hp > 0;
                    }

                    @Override
                    public void execute() throws InterruptedException {
                        if(random.nextInt(4) < 3){
                            hasAttacked = "I'll never obey you, human!";
                            lastActionWasAnAttack = false;
                        } else {
                            hasAttacked = name + "Attacked! What a surprise!" ;
                            attackCell(1, 0, damage, Color.RED);
                            attackCell(-1, 0, damage, Color.RED);
                            attackCell(0, 1, damage, Color.RED);
                            attackCell(0, -1, damage, Color.RED);
                            lastActionWasAnAttack = true;
                        }
                    }

                    @Override
                    public void undo() throws InterruptedException {
                        if(lastActionWasAnAttack){
                            hasAttacked = " Attack cancelled!";
                            attackCell(1,0, -1 * damage, Color.GREEN);
                            attackCell(-1, 0, -1 * damage, Color.GREEN);
                            attackCell(0, 1, -1 * damage, Color.GREEN);
                            attackCell(0, -1, -1 * damage, Color.GREEN);
                        }else{
                            hasAttacked = "I didn't obey... what do you want to undo?";
                        }

                    }

                    @Override
                    public String toString() {
                        return "Attack!" + hasAttacked;
                    }
                };
            }

            @Override
            public String toString() {
                return "Attack!";
            }
        });
    }

    //Name of the cat
    @Override
    public String toString() {
        return name + " The Cat";
    }

}
