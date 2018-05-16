package Model;

/**
 * @author Vincent Guidoux
 */
public abstract class CellContent {

    private int pv;

    public abstract void takeDamage(int damage);

    public abstract void takeheal(int heal);

    public void move(Cell cell){

    }
}
