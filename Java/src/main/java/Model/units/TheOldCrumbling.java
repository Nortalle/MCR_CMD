package Model.units;

import Model.*;

public class TheOldCrumbling extends Unit {

    public TheOldCrumbling(Cell start, String name, String sprite) {
        super(100, 3, start, name, sprite);

        actions.add(new Action() {
            @Override
            public ICmd createCommand() {
                return new ICmd() {

                    public void execute() throws InterruptedException {
                        healCell(0, 1, 50);
                    }

                    public void undo() throws InterruptedException {
                        attackCell(0, 1, 50);
                    }

                    @Override
                    public String toString() {
                        return "Heal right";
                    }
                };
            }

            @Override
            public String toString() {
                return "Heal right";
            }
        });

        actions.add(new Action() {
            @Override
            public ICmd createCommand() {
                return new ICmd() {

                    public void execute() throws InterruptedException {
                        healCell(0, -1, 50);
                    }

                    public void undo() throws InterruptedException {
                        attackCell(0, -1, 50);
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

                    public void execute() throws InterruptedException {
                        if(game.getMap().getCell(currentCell.x -1,currentCell.y).getCellContents() != null){
                            healCell(-1, 0, 50);
                        }
                    }

                    public void undo() throws InterruptedException {
                        if(game.getMap().getCell(currentCell.x - 1 ,currentCell.y).getCellContents() != null){
                            attackCell(-1,0,50);
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

                    public void execute() throws InterruptedException {
                        healCell(1, 0, 50);
                    }

                    public void undo() throws InterruptedException {
                        attackCell(1,0,50);
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

    public String desctiption() {
        return "The old wise crumbling is a wise and crumbling healer";
    }

    public boolean Invoke(Cell c) {
        return false;
    }

    @Override
    public String toString() {
        return name + " The Healer";
    }


}
