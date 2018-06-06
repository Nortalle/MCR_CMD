package Model.units;

import Model.Cell;
import Model.Unit;

import java.util.Random;

public class FakeUnit extends Unit {

    public FakeUnit(Cell start) {
        super(1, 0, start);
    }

    public String desctiption() {
        return "A fake unit, obstructing the way!";
    }

    public boolean Invoke(Cell c) {
        return false;
    }

    @Override
    public String getPath(){

        Random rand = new Random();

        if((rand.nextInt(2) + 1) % 2 == 0){
            return "tree.png";
        } else {
            return "rockart.png";
        }
    }

    public String toString() {
        return "FakeTree";
    }

}
