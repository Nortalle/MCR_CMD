package Model.units;

import Model.Action;
import Model.Cell;
import Model.ICmd;
import Model.Unit;

import java.awt.*;

public class ThePrestigiousArcher extends Unit {

    private int distanceMax;

    public ThePrestigiousArcher(Cell start, String name, String sprite) {
        super(100, 5, start, name, sprite);
        damage  = 25;
        distanceMax = 4;

        actions.add(new Action() {

            @Override
            public ICmd createCommand() {
                return new ICmd() {


                    @Override
                    public boolean condition() throws InterruptedException {
                        return hp > 0;
                    }

                    public void execute() throws InterruptedException {
                        shoot(0, 1, damage);
                    }

                    public void undo() throws InterruptedException {
                        cancelShoot(0, 1, damage);
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
        actions.add(new Action() {
            @Override
            public ICmd createCommand() {
                return new ICmd() {


                    @Override
                    public boolean condition() throws InterruptedException {
                        return hp > 0;
                    }

                    public void execute() throws InterruptedException {
                        shoot(0, -1, damage);
                    }

                    public void undo() throws InterruptedException {
                        cancelShoot(0, -1, damage);
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
        actions.add(new Action() {
            @Override
            public ICmd createCommand() {
                return new ICmd() {


                    @Override
                    public boolean condition() throws InterruptedException {
                        return hp > 0;
                    }

                    public void execute() throws InterruptedException {
                        shoot(-1, 0, damage);
                    }

                    public void undo() throws InterruptedException {
                        cancelShoot(-1, 0, damage);
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
        actions.add(new Action() {
            @Override
            public ICmd createCommand() {
                return new ICmd() {


                    @Override
                    public boolean condition() throws InterruptedException {
                        return hp > 0;
                    }

                    public void execute() throws InterruptedException {
                        shoot(1, 0, damage);
                    }

                    public void undo() throws InterruptedException {
                        cancelShoot(1, 0, damage);
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

    public String desctiption() {
        return "The prestigious Archer";
    }

    public boolean Invoke(Cell c) {
        return false;
    }

    private void shoot(int offsetX, int offsetY, int damage) throws InterruptedException {
        int distance = 1;
        while (!attackCell(offsetX * distance, offsetY * distance, damage, Color.RED) && distance <= distanceMax) {
            ++distance;
        }
    }

    private void cancelShoot(int offsetX, int offsetY, int damage) throws InterruptedException {
        int distance = 1;
        while (!attackCell(offsetX * distance, offsetY * distance, -1 * damage, Color.GREEN) && distance <= distanceMax) {
            ++distance;
        }
    }

    @Override
    public String toString() {
        return name + " The Prestigious Archer";

    }


}
