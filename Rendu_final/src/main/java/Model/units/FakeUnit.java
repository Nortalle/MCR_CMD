package Model.units;

import Model.Cell;
import Model.Unit;

import java.util.Random;

/**
 * Class representing a fake unit used as an obstacle
 */
public class FakeUnit extends Unit {
    //Path used to chose the image of the fake unit
    private final String path;

    /**
     * Constructor of the fake unit
     * @param start, Cell where the fake unit will spawn
     */
    public FakeUnit(Cell start) {
        super(1, 0, start, "", "");
        Random rand = new Random();

        //The sprite of the obstacle is randomly chosen between two images
        if ((rand.nextInt(2) + 1) % 2 == 0) {
            path = "tree.png";
        } else {
            path = "rockart.png";
        }
    }

    //getter of the path to the sprite
    @Override
    public String getPath() {
        return path;
    }

    //Name of the unit
    @Override
    public String toString() {
        return "FakeUnit";
    }

}
