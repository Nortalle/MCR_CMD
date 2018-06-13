package Model.units;

import Model.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class representing the Warrior unit
 */
public class SteveTheWarrior extends Unit {

    /**
     * Constructor of the warrior, adding all the actions of the warrior
     * @param start cell used as spawner of the warrior
     * @param name name of the warrior
     * @param sprite path of the sprite of the warrior unit
     */
    public SteveTheWarrior(Cell start, String name, String sprite) {
        super(100, 5, start, name, sprite);
        //damage of an attack made by this unit
        this.damage = 50;

        //Add right attack of the warrior command factory to action list
        actions.add(new Action() {
            @Override
            public ICmd createCommand() {
                return new ICmd() {

                    //The warrior must be alive for its action to be done
                    @Override
                    public boolean condition() throws InterruptedException {
                        return getActionCondition();
                    }

                    //The warrior attack right
                    @Override
                    public void execute() throws InterruptedException {
                        attackCell(0, 1, damage, Color.RED);
                    }

                    //The warrior heal right
                    public void undo() throws InterruptedException {
                        attackCell(0, 1, -1 * damage, Color.GREEN);
                    }

                    //Name of the command
                    @Override
                    public String toString() {
                        return "Swing sword right";
                    }
                };
            }
            //Name of the action
            @Override
            public String toString() {
                return "Swing sword right";
            }
        });

        //Add left attack of the warrior command factory to action list
        actions.add(new Action() {
            @Override
            public ICmd createCommand() {
                return new ICmd() {


                    @Override
                    public boolean condition() throws InterruptedException {
                        return getActionCondition();
                    }

                    @Override
                    public void execute() throws InterruptedException {
                        attackCell(0, -1, damage, Color.RED);
                    }

                    @Override
                    public void undo() throws InterruptedException {
                        attackCell(0, -1, -1 * damage, Color.GREEN);
                    }

                    @Override
                    public String toString() {
                        return "Swing sword left";
                    }
                };
            }

            @Override
            public String toString() {
                return "Swing sword left";
            }
        });

        //Add up attack of the warrior command factory to action list
        actions.add(new Action() {
            @Override
            public ICmd createCommand() {
                return new ICmd() {

                    @Override
                    public boolean condition() throws InterruptedException {
                        return getActionCondition();
                    }

                    @Override
                    public void execute() throws InterruptedException {
                        attackCell(-1, 0, damage, Color.RED);
                    }

                    @Override
                    public void undo() throws InterruptedException {
                        attackCell(-1, 0, -1 * damage, Color.GREEN);
                    }

                    @Override
                    public String toString() {
                        return "Swing sword up";
                    }
                };
            }

            @Override
            public String toString() {
                return "Swing sword up";
            }
        });

        //Add down attack of the warrior command factory to action list
        actions.add(new Action() {
            @Override
            public ICmd createCommand() {
                return new ICmd() {

                    @Override
                    public boolean condition() throws InterruptedException {
                        return getActionCondition();
                    }

                    @Override
                    public void execute() throws InterruptedException {
                        attackCell(1, 0, damage,Color.RED);
                    }

                    @Override
                    public void undo() throws InterruptedException {
                        attackCell(1, 0, -1 * damage, Color.GREEN);
                    }

                    @Override
                    public String toString() {
                        return "Swing sword down";
                    }
                };
            }

            @Override
            public String toString() {
                return "Swing sword down";
            }
        });
    }

    //Name of the warrior
    @Override
    public String toString(){
        return name + " The Warrior";
    }
}
