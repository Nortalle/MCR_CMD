package Model.units;

import Model.*;

import java.awt.*;
/**
 * Class representing a healer
 */
public class TheOldCrumbling extends Unit {

    /**
     * Constructor of the healer
     * @param start, cell representing the start position of the healer
     * @param name, name of the healer
     * @param sprite, path of the sprite of the healer
     */
    public TheOldCrumbling(Cell start, String name, String sprite) {
        super(100, 3, start, name, sprite);
        damage = -50;

        //Add right healing of the warrior command factory to action list
        actions.add(new Action() {

            @Override
            public ICmd createCommand() {
                return new ICmd() {

                    //the healer must be alive for his commands to be done
                    @Override
                    public boolean condition() throws InterruptedException {
                        return getActionCondition();
                    }

                    @Override
                    public void execute() throws InterruptedException {
                        attackCell(0, 1, damage,Color.GREEN );
                    }

                    @Override
                    public void undo() throws InterruptedException {
                        attackCell(0, 1, -1 * damage, Color.RED);
                    }

                    //Name of the command
                    @Override
                    public String toString() {
                        return "Heal right";
                    }
                };
            }
            //Name of the action
            @Override
            public String toString() {
                return "Heal right";
            }
        });

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
                        attackCell(0, -1, damage, Color.GREEN);
                    }

                    @Override
                    public void undo() throws InterruptedException {
                        attackCell(0, -1, -1 * damage, Color.RED);
                    }

                    @Override
                    public String toString() {
                        return "Heal left";
                    }
                };
            }

            @Override
            public String toString() {
                return "Heal left";
            }
        });
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
                        if(game.getMap().getCell(currentCell.x -1,currentCell.y).getCellContents() != null){
                            attackCell(-1, 0, damage, Color.GREEN);
                        }
                    }

                    @Override
                    public void undo() throws InterruptedException {
                        if(game.getMap().getCell(currentCell.x - 1 ,currentCell.y).getCellContents() != null){
                            attackCell(-1,0,-1 * damage, Color.RED);
                        }
                    }

                    @Override
                    public String toString() {
                        return "Heal up";
                    }
                };
            }

            @Override
            public String toString() {
                return "Heal up";
            }
        });
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
                        attackCell(1, 0, damage, Color.GREEN);
                    }

                    @Override
                    public void undo() throws InterruptedException {
                        attackCell(1,0,-1 * damage, Color.RED);
                    }

                    @Override
                    public String toString() {
                        return "Heal down";
                    }
                };
            }

            @Override
            public String toString() {
                return "Heal down";
            }
        });
    }

    //name of the healer unit
    @Override
    public String toString() {
        return name + " The Healer";
    }


}
