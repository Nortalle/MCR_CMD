package Model.units;

import Model.*;

import java.util.ArrayList;

public class SteveTheWarrior extends Unit {


    public SteveTheWarrior(Cell start) {
        super(100, 5, start);

        actions.add(new Action() {
            @Override
            public ICmd createCommand() {
                return new ICmd() {

                    public void execute() throws InterruptedException {
                        attackCell(0, 1, 50);
                    }

                    public void undo() throws InterruptedException {
                        healCell(0, 1, 50);
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

                    public void execute() throws InterruptedException {
                        attackCell(0, -1, 50);
                    }

                    public void undo() throws InterruptedException {
                        healCell(0, -1, 50);
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

                    public void execute() throws InterruptedException {
                        attackCell(-1, 0, 50);
                    }

                    public void undo() throws InterruptedException {
                        healCell(-1, 0, 50);
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

                    public void execute() throws InterruptedException {
                        attackCell(1, 0, 50);
                    }

                    public void undo() throws InterruptedException {
                        healCell(1, 0, 50);
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
    public String getPath() {
        return "warr.png";
    }

    @Override
    public String toString() {
        return "Steve The Warrior";
    }
}
