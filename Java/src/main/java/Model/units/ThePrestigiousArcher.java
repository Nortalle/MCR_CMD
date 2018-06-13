package Model.units;

import Model.Action;
import Model.Cell;
import Model.ICmd;
import Model.Unit;

import java.awt.*;

/**
 * Class representing the archer unit
 */
public class ThePrestigiousArcher extends Unit {
    //max shooting distance
    private int distanceMax;

    /**
     * Constructor of the archer
     * @param start, cell representing the start postion of the archer
     * @param name, name of the archer
     * @param sprite, path to the archer image
     */
    public ThePrestigiousArcher(Cell start, String name, String sprite) {
        super(100, 5, start, name, sprite);
        damage  = 25;
        distanceMax = 4;

        //Add right shooting command factory to action list
        actions.add(new Action() {

            @Override
            public ICmd createCommand() {
                return new ICmd() {


                    @Override
                    public boolean condition() throws InterruptedException {
                        return hp > 0;
                    }

                    @Override
                    public void execute() throws InterruptedException {
                        shoot(0, 1, damage, Color.RED);
                    }

                    @Override
                    public void undo() throws InterruptedException {
                        shoot(0, 1, -damage, Color.GREEN);
                    }

                    @Override
                    public String toString() {
                        return "Shoot an arrow right!";
                    }
                };
            }

            @Override
            public String toString() {
                return "Shoot an arrow right!";
            }
        });

        //Add left shooting command factory to action list
        actions.add(new Action() {
            @Override
            public ICmd createCommand() {
                return new ICmd() {


                    @Override
                    public boolean condition() throws InterruptedException {
                        return hp > 0;
                    }

                    @Override
                    public void execute() throws InterruptedException {
                        shoot(0, -1, damage, Color.RED);
                    }

                    @Override
                    public void undo() throws InterruptedException {
                        shoot(0, -1, -damage, Color.GREEN);
                    }

                    @Override
                    public String toString() {
                        return "Shoot an arrow left";
                    }
                };
            }

            @Override
            public String toString() {
                return "Shoot an arrow left";
            }
        });

        //Add up shooting command factory to action list
        actions.add(new Action() {
            @Override
            public ICmd createCommand() {
                return new ICmd() {


                    @Override
                    public boolean condition() throws InterruptedException {
                        return hp > 0;
                    }

                    @Override
                    public void execute() throws InterruptedException {
                        shoot(-1, 0, damage, Color.RED);
                    }

                    @Override
                    public void undo() throws InterruptedException {
                        shoot(-1, 0, -damage, Color.GREEN);
                    }

                    @Override
                    public String toString() {
                        return "Shoot an arrow up";
                    }
                };
            }

            @Override
            public String toString() {
                return "Shoot an arrow up";
            }
        });

        //Add down shooting command factory to action list
        actions.add(new Action() {
            @Override
            public ICmd createCommand() {
                return new ICmd() {


                    @Override
                    public boolean condition() throws InterruptedException {
                        return hp > 0;
                    }

                    @Override
                    public void execute() throws InterruptedException {
                        shoot(1, 0, damage, Color.RED);
                    }

                    @Override
                    public void undo() throws InterruptedException {
                        shoot(1, 0, -damage, Color.GREEN);
                    }

                    @Override
                    public String toString() {
                        return "Shoot an arrow down";
                    }
                };
            }

            @Override
            public String toString() {
                return "Shoot an arrow down";
            }
        });


    }

    //attack all the cells in a direction until it reach a prey or the distance max
    private void shoot(int offsetX, int offsetY, int damage, Color c) throws InterruptedException {
        int distance = 1;
        while (!attackCell(offsetX * distance, offsetY * distance, damage, c) && distance <= distanceMax) {
            ++distance;
        }
    }

    //Name of the archer
    @Override
    public String toString() {
        return name + " The Prestigious Archer";
    }


}
