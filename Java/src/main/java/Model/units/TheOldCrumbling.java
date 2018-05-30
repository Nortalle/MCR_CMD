package Model.units;

import Model.*;

public class TheOldCrumbling extends Unit {

    TheOldCrumbling() {
        super(100, 3);

        actions.add(new Action() {
            @Override
            public ICmd createCommand() {
                return new ICmd() {

                    public void execute() {
                        healCell(0, 1, 50);
                    }

                    public void undo() {
                        attackCell(0, 1, 50);
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

                    public void execute() {
                        healCell(0, -1, 50);
                    }

                    public void undo() {
                        attackCell(0, -1, 50);
                    }
                };
            }

            @Override
            public String toString() {
                return "Heal down";
            }
        });
        actions.add(new Action() {
            @Override
            public ICmd createCommand() {
                return new ICmd() {

                    public void execute() {
                        if(Game.getInstance().getMap().getCell(currentCell.x -1,currentCell.y).getCellContents() != null){
                            healCell(-1, 0, 50);
                        }
                    }

                    public void undo() {
                        if(Game.getInstance().getMap().getCell(currentCell.x - 1 ,currentCell.y).getCellContents() != null){
                            attackCell(-1,0,50);
                        }
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

                    public void execute() {
                        healCell(1, 0, 50);
                    }

                    public void undo() {
                        attackCell(1,0,50);
                    }
                };
            }

            @Override
            public String toString() {
                return "Heal right";
            }
        });
    }

    public String desctiption() {
        return "The old wise crumbling is a wise and crumbling healer";
    }

    public boolean Invoke(Cell c) {
        return false;
    }
}
