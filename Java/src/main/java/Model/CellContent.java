package Model;

/**
 * @author Vincent Guidoux
 */
public abstract class CellContent {

    private int health;

    public abstract void takeDamage(int damage);

    public abstract void takeHeal(int heal);

    public void move(Cell cell){

    }
}
