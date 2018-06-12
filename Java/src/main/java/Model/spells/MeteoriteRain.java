package Model.spells;

import Model.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Spell creating throwing meteores randomly around a spot on the board
 */
public class MeteoriteRain extends Spell {
    private Random random;
    private int distanceMax;

    /**
     * Constructor of MeteoriteRain, adding a action that create a meteore rain randomly around a spot on the map
     */
    public MeteoriteRain() {
        super();
        random = new Random();
        //used to create a random offset to chose the spot that will be hit by the meteor rain
        distanceMax = 3;
        int damage = 100;
        aimCell = game.getSelected();

        //Add MeteorRainSpell command factory to action list
        actions.add(new Action() {
            @Override
            public ICmd createCommand() {

                return new ICmd() {
                    //Selected cell at the moment the command is created
                    Cell selectedAim = null;
                    //list of the touched cells, used if the command is undo to heal all those cells
                    ArrayList<Cell> touchedCells = new ArrayList<>();

                    //We instanciate the aim cell with an offset randomly created
                    {
                        int x;
                        int y;
                        do {
                            x = - distanceMax + (random.nextInt(2 * distanceMax + 1 ));
                        } while ((game.getSelected().x + x) >= game.getMap().width());
                        do {
                            y = - distanceMax + (random.nextInt(2 * distanceMax + 1));
                        } while ((game.getSelected().y + y) >= game.getMap().height());
                        System.out.println(x + " " + y);
                        selectedAim = game.getMap().getCell(game.getSelected().x + x, game.getSelected().y + y);
                        System.out.println(selectedAim);
                    }


                    @Override
                    //there's no need for a unit to be in order to launch the spell
                    public boolean condition() throws InterruptedException {
                        return true;
                    }

                    //Throw a meteor rain on the map, killing all the units touched by it
                    @Override
                    public void execute() throws InterruptedException {
                        //The spell can only be called once per turn
                        if(hasBeenExecuted){
                            noMoreMana();
                        } else {

                            hasBeenExecuted = true;

                            hitCell(selectedAim,0,0,damage, Color.RED);
                            hitCell(selectedAim,1,0,damage, Color.RED);
                            hitCell(selectedAim,-1,0,damage, Color.RED);
                            hitCell(selectedAim,0 ,1,damage, Color.RED);
                            hitCell(selectedAim,0,-1,damage, Color.RED);
                        }
                    }

                    //Heal all the cells touched by the meteor
                    @Override
                    public void undo() throws InterruptedException {
                        if(hasBeenExecuted){
                            for(Cell c: touchedCells){
                                c.getTouched(Color.GREEN);
                                if(c.getCellContents() != null){
                                    c.getCellContents().takeDamage(-100);
                                }
                            }
                        }else{
                            System.out.println("Nothing to undo");
                        }
                    }

                    //Hit a single cell, if it is on the map, and add it to the list of touched cell, return true if a unit
                    //has been hit, false otherwise
                    private boolean hitCell(Cell aim ,int offsetX, int offsetY, int damage, Color c) throws InterruptedException {
                        setAimCell(aim);
                        boolean hit = false;
                        attackCell(offsetX,offsetY,damage, c);
                        Cell cell = game.getMap().getCell(aim.x + offsetX, aim.y + offsetY);
                        if(cell != null){
                            hit = true;
                            touchedCells.add(cell);
                            cell.getTouched(c);
                        }
                        return hit;
                    }

                    //Name of the command
                    @Override
                    public String toString() {
                        return "Let the meteors fall!" + aimCell ;
                    }
                };
            }
            //Name of the action
            @Override
            public String toString() {
                return "Meteor Rain";
            }
        });

    }
    //name of the spell
    @Override
    public String toString() {
        return "Let the meteors fall!";
    }
}
