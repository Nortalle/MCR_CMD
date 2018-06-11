package Model.units;

import Model.*;

import java.awt.*;
import java.util.ArrayList;

public class SteveTheWarrior extends Unit {


    public SteveTheWarrior(Cell start, String name, String sprite) {
        super(100, 5, start, name, sprite);

        this.damage = 50;

        actions.add(new Action() {
            @Override
            public ICmd createCommand() {
                return new ICmd() {

                    @Override
                    public boolean condition() throws InterruptedException {
                        return hp > 0;
                    }

                    public void execute() throws InterruptedException {
                        attackCell(0, 1, damage, Color.RED);
                    }

                    public void undo() throws InterruptedException {
                        attackCell(0, 1, -1 * damage, Color.GREEN);
                    }

                    @Override
                    public String toString() {
                        return "Swing sword right";
                    }
                };
            }

            @Override
            public String toString() {
                return "Swing sword right";
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
                        attackCell(0, -1, damage, Color.RED);
                    }

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
        actions.add(new Action() {
            @Override
            public ICmd createCommand() {
                return new ICmd() {

                    @Override
                    public boolean condition() throws InterruptedException {
                        return hp > 0;
                    }

                    public void execute() throws InterruptedException {
                        attackCell(-1, 0, damage, Color.RED);
                    }

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
        actions.add(new Action() {
            @Override
            public ICmd createCommand() {
                return new ICmd() {

                    @Override
                    public boolean condition() throws InterruptedException {
                        return hp > 0;
                    }

                    public void execute() throws InterruptedException {
                        attackCell(1, 0, damage,Color.RED);
                    }

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

    public String desctiption() {
        return "Steve is a badass Warrior";
    }

    public boolean Invoke(Cell c) {

        return move(c);
    }

    @Override
    public String toString(){
        return name + " The Warrior";
    }
}
