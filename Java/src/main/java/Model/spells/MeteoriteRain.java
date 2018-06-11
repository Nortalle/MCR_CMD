package Model.spells;

import Model.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class MeteoriteRain extends Spell {
    private Random random;
    private int distanceMax;

    public MeteoriteRain() {
        super();
        random = new Random();
        distanceMax = 5;
        int damage = 100;
        aimCell = game.getSelected();

        actions.add(new Action() {
            @Override
            public ICmd createCommand() {

                return new ICmd() {
                    Cell selectedAim = null;
                    ArrayList<Cell> touchedCells = new ArrayList<>();



                    @Override
                    public boolean condition() throws InterruptedException {
                        return true;
                    }

                    public void execute() throws InterruptedException {
                        //if(hasBeenExecuted){
                          //  noMoreMana();
                        //} else {

                            hasBeenExecuted = true;

                            int x;
                            int y;
                            do{
                                x = random.nextInt(distanceMax);
                            }while((game.getSelected().x + x) >= game.getMap().width());
                            do{
                                y = random.nextInt(distanceMax);
                            }while((game.getSelected().y + y) >= game.getMap().height());
                            System.out.println(x + " " + y);
                            selectedAim = game.getMap().getCell(game.getSelected().x + x, game.getSelected().y + y);
                            System.out.println(selectedAim);

                            hitCell(selectedAim,0,0,damage, Color.RED);
                            hitCell(selectedAim,1,0,damage, Color.RED);
                            hitCell(selectedAim,-1,0,damage, Color.RED);
                            hitCell(selectedAim,0 ,1,damage, Color.RED);
                            hitCell(selectedAim,0,-1,damage, Color.RED);
                        }
                   // }

                    public void undo() throws InterruptedException {
                        if(hasBeenExecuted){
                            for(Cell c: touchedCells){
                                if(c.getCellContents() != null){
                                    c.getCellContents().takeDamage(-100);
                                    c.getTouched(Color.GREEN);
                                }
                            }
                        }else{
                            System.out.println("Nothing to undo");
                        }
                    }

                    private boolean hitCell(Cell aim ,int offsetX, int offsetY, int damage, Color c) throws InterruptedException {
                        System.out.println(aim);
                        setAimCell(aim);
                        boolean hit = false;
                        if(attackCell(offsetX,offsetY,damage, c)){
                            Cell cell = game.getMap().getCell(aim.x + offsetX, aim.y + offsetY);
                            if(cell != null){
                                hit = true;
                                touchedCells.add(cell);
                                cell.getTouched(c);
                            }
                        }
                        return hit;
                    }
                };
            }

            @Override
            public String toString() {
                return "Meteor Rain";
            }
        });

    }

    @Override
    public String toString() {
        return "Let the meteors fall!";
    }

    public String desctiption() {
        return "The Meteor Rain attack a random cells in a range";
    }

    public boolean Invoke(Cell c) {
        return false;
    }
}
