package Model.units;

import Model.Cell;
import Model.Unit;

public class FakeUnit extends Unit {

    public FakeUnit() {
        super(100, 0);
    }

    public String desctiption() {
        return "A fake unit, obstructing the way!";
    }

    public boolean Invoke(Cell c) {
        return false;
    }

    @Override
    public String getPath(){
        return "tree.png";
    }

    public String toString() {
        return "FakeTree";
    }

}
