package Model.units;

import Model.Action;
import Model.Cell;
import Model.ICmd;
import Model.Unit;

public class ThePrestigiousArcher extends Unit {

    private int distanceMax;

    public ThePrestigiousArcher() {
        super(100, 5);
        distanceMax = 4;

        actions.add(new Action() {

            @Override
            public ICmd createCommand() {
                return new ICmd() {

                    public void execute() {
                        shoot(0, 1, 25);
                    }

                    public void undo() {
                        cancelShoot(0, 1, 25);
                    }
                };
            }

            @Override
            public String toString() {
                return "Shoot an arrow up!";
            }
        });
        actions.add(new Action() {
            @Override
            public ICmd createCommand() {
                return new ICmd() {

                    public void execute() {
                        shoot(0, -1, 50);
                    }

                    public void undo() {
                        cancelShoot(0, -1, 50);
                    }
                };
            }

            @Override
            public String toString() {
                return "Shoot an arrow down";
            }
        });
        actions.add(new Action() {
            @Override
            public ICmd createCommand() {
                return new ICmd() {

                    public void execute() {
                        shoot(-1, 0, 50);
                    }

                    public void undo() {
                        cancelShoot(-1, 0, 50);
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

                    public void execute() {
                        shoot(1, 0, 50);
                    }

                    public void undo() {
                        cancelShoot(1, 0, 50);
                    }
                };
            }

            @Override
            public String toString() {
                return "Shoot an arrow right";
            }
        });


    }

    public String desctiption() {
        return "The prestigious Archer";
    }

    public boolean Invoke(Cell c) {
        return false;
    }

    private void shoot( int offsetX, int offsetY, int damage){
        int distance = 1;
        while(!attackCell(offsetX * distance, offsetY * distance, 25) && distance <= distanceMax){
            ++distance;
        }
    }

    private void cancelShoot( int offsetX, int offsetY, int damage){
        int distance = 1;
        while(!healCell(offsetX * distance, offsetY * distance, 25) && distance <= distanceMax){
            ++distance;
        }
    }

}
