package Model.units;

import Model.*;

import java.awt.*;
import java.util.Random;

public class ThePsyCat extends Unit {

    public ThePsyCat(Cell start, String name, String sprite) {
        super(200, 3, start, name, sprite);
        damage = 100;

        actions.add(new Action() {
            @Override
            public ICmd createCommand() {
                return new ICmd() {
                    String hasAttacked = "";
                    Random random = new Random();
                    boolean lastActionWasAnAttack = false;

                    @Override
                    public boolean condition() throws InterruptedException {
                        return hp > 0;
                    }

                    public void execute() throws InterruptedException {
                        if(random.nextInt(4) < 3){
                            hasAttacked = " Ah bah non";
                            System.out.println("Maour... This is not very efficient");
                            lastActionWasAnAttack = false;
                        } else {
                            hasAttacked = " Ah bah oui";
                            attackCell(1, 0, damage, Color.RED);
                            attackCell(-1, 0, damage, Color.RED);
                            attackCell(0, 1, damage, Color.RED);
                            attackCell(0, -1, damage, Color.RED);
                            lastActionWasAnAttack = true;
                        }
                    }

                    public void undo() throws InterruptedException {
                        if(lastActionWasAnAttack){
                            attackCell(1,0, -1 * damage, Color.GREEN);
                            attackCell(-1, 0, -1 * damage, Color.GREEN);
                            attackCell(0, 1, -1 * damage, Color.GREEN);
                            attackCell(0, -1, -1 * damage, Color.GREEN);
                        }else{
                            System.out.println("Ronron...Really? What do you want to undo?");
                        }

                    }

                    @Override
                    public String toString() {
                        return "Attack!" + hasAttacked;
                    }
                };
            }

            @Override
            public String toString() {
                return "Attack!";
            }
        });
    }

    public String desctiption() {
        return "The Psy Cat is sometimes efficient... and sometimes annoying...";
    }

    public boolean Invoke(Cell c) {
        return false;
    }

    @Override
    public String toString() {
        return name + " The Cat";
    }

}
