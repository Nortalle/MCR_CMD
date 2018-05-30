package Model;

import Model.units.SteveTheWarrior;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class SteveTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.Test
    void getInstance() {
    }

    @Test
    public void createSteve(){
        SteveTheWarrior stw = new SteveTheWarrior();
        assertNotNull(stw);
    }

    /*
    @Test
    public void moveSteve(){

    }


    */
}
