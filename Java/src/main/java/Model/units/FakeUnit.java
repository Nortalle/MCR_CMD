package Model.units;

import Model.Cell;
import Model.Unit;

import java.util.Random;

public class FakeUnit extends Unit {

    private final String path;

    public FakeUnit(Cell start) {
        super(1, 0, start);
        Random rand = new Random();

        if ((rand.nextInt(2) + 1) % 2 == 0) {
            path = "tree.png";
        } else {
            path = "rockart.png";
        }
    }

    public String desctiption() {
        return "A fake unit, obstructing the way!";
    }

    public boolean Invoke(Cell c) {
        return false;
    }

    @Override
    public String getPath() {
        return path;
    }

    public String toString() {
        return "FakeTree";
    }

}
