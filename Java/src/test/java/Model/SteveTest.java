package Model;

import Model.units.SteveTheWarrior;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;

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
        SteveTheWarrior stw = new SteveTheWarrior(Game.getInstance().getMap().getCell(0,0));
        assertNotNull(stw);
    }

    @Test
    public void getSprite(){
        SteveTheWarrior stw = new SteveTheWarrior(Game.getInstance().getMap().getCell(0,0));
        try {
            BufferedImage b = stw.getSprite();
            assertNotNull(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    @Test
    public void moveSteve(){

    }


    */
}
