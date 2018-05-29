package Model.units;

import Model.*;

import java.util.ArrayList;

public class SteveTheWarrior extends Unit {


    SteveTheWarrior() {
        super(100, 5);

        actions.add(new Action() {
            @Override
            public ICmd createCommand() {
                return new ICmd() {

                    public void execute() {
                        if(Game.getInstance().getMap().getCell(currentCell.x,currentCell.y + 1).getCellContents() != null){
                            Game.getInstance().getMap().getCell(currentCell.x,currentCell.y + 1).getCellContents().takeDamage(50);
                        }
                    }

                    public void undo() {
                        if(Game.getInstance().getMap().getCell(currentCell.x,currentCell.y + 1).getCellContents() != null){
                            Game.getInstance().getMap().getCell(currentCell.x,currentCell.y + 1).getCellContents().takeHeal(50);
                        }
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

                    public void execute() {
                        if(Game.getInstance().getMap().getCell(currentCell.x,currentCell.y - 1).getCellContents() != null){
                            Game.getInstance().getMap().getCell(currentCell.x,currentCell.y - 1).getCellContents().takeDamage(50);
                        }
                    }

                    public void undo() {
                        if(Game.getInstance().getMap().getCell(currentCell.x,currentCell.y - 1).getCellContents() != null){
                            Game.getInstance().getMap().getCell(currentCell.x,currentCell.y - 1).getCellContents().takeHeal(50);
                        }
                    }
                };
            }

            @Override
            public String toString() {
                return "Swing sword down";
            }
        });
        actions.add(new Action() {
            @Override
            public ICmd createCommand() {
                return new ICmd() {

                    public void execute() {
                        if(Game.getInstance().getMap().getCell(currentCell.x -1,currentCell.y).getCellContents() != null){
                            Game.getInstance().getMap().getCell(currentCell.x -1,currentCell.y).getCellContents().takeDamage(50);
                        }
                    }

                    public void undo() {
                        if(Game.getInstance().getMap().getCell(currentCell.x - 1 ,currentCell.y).getCellContents() != null){
                            Game.getInstance().getMap().getCell(currentCell.x -1 ,currentCell.y).getCellContents().takeHeal(50);
                        }
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

                    public void execute() {
                        if(Game.getInstance().getMap().getCell(currentCell.x +1,currentCell.y).getCellContents() != null){
                            Game.getInstance().getMap().getCell(currentCell.x +1,currentCell.y).getCellContents().takeDamage(50);
                        }
                    }

                    public void undo() {
                        if(Game.getInstance().getMap().getCell(currentCell.x + 1 ,currentCell.y).getCellContents() != null){
                            Game.getInstance().getMap().getCell(currentCell.x +1 ,currentCell.y).getCellContents().takeHeal(50);
                        }
                    }
                };
            }

            @Override
            public String toString() {
                return "Swing sword right";
            }
        });
    }

    public String desctiption() {
        return "Steve is a badass Warrior";
    }

    public boolean Invoke(Cell c) {

        return move(c);
    }
}
