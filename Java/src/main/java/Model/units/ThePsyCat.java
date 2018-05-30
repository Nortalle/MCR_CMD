package Model.units;

import Model.*;

import java.util.Random;

public class ThePsyCat extends Unit {

    public ThePsyCat() {
        super(200, 3);

        actions.add(new Action() {
            @Override
            public ICmd createCommand() {
                return new ICmd() {
                    Random random = new Random();
                    boolean lastActionWasAnAttack = false;

                    public void execute() {
                        if(random.nextInt(4) < 3){
                            System.out.println("Maour... This is not very efficient");
                            lastActionWasAnAttack = false;
                        } else {
                            attackCell(1, 0, 25);
                            attackCell(-1, 0, 25);
                            attackCell(0, 1, 25);
                            attackCell(0, -1, 25);
                            lastActionWasAnAttack = true;
                        }
                    }

                    public void undo() {
                        if(lastActionWasAnAttack){
                            healCell(1,0,25);
                            healCell(-1, 0, 25);
                            healCell(0, 1, 25);
                            healCell(0, -1, 25);
                        }else{
                            System.out.println("Ronron...Really? What do you want to undo?");
                        }

                    }
                };
            }

            @Override
            public String toString() {
                return "Mreow!";
            }
        });
    }

    public String desctiption() {
        return "The Psy Cat is sometimes efficient... and sometimes annoying...";
    }

    public boolean Invoke(Cell c) {
        return false;
    }


}
